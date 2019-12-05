package com.staxter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StaxterApplication)
class IntegrationTest extends Specification {

    @Autowired
    private MockMvc mvc;

    def "should verify critical path"() {
        given:
        def userName = randomString()
        def password = randomString()
        def firstName = randomString()
        def lastName = randomString()
        def registerJson = buildRegisterJson(userName, password, firstName, lastName)
        def loginJson = buildLoginJson(userName, password)

        when: "Registering an user"
        mvc.perform(post("/userservice/register").contentType(MediaType.APPLICATION_JSON).content(registerJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.id").isNotEmpty())
                .andExpect(jsonPath("\$.userName").value(userName))
                .andExpect(jsonPath("\$.firstName").value(firstName))
                .andExpect(jsonPath("\$.lastName").value(lastName))

        then: "User should be able to login"
        mvc.perform(post("/userservice/login").contentType(MediaType.APPLICATION_JSON).content(loginJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.id").isNotEmpty())
                .andExpect(jsonPath("\$.userName").value(userName))
                .andExpect(jsonPath("\$.firstName").value(firstName))
                .andExpect(jsonPath("\$.lastName").value(lastName))

        and: "User should not be able to register with same username"
        mvc.perform(post("/userservice/register").contentType(MediaType.APPLICATION_JSON).content(registerJson))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.code").value("USER_ALREADY_EXISTS"))
                .andExpect(jsonPath("\$.description").value("A user with the given username already exists"))
    }

    void "should reject incorrect username"() throws Exception {
        given:
        def userName = randomString()
        def password = randomString()
        def loginJson = buildLoginJson(userName, password)

        expect:
        mvc.perform(post("/userservice/login").contentType(MediaType.APPLICATION_JSON).content(loginJson))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.code").value("INCORRECT_CREDENTIALS"))
                .andExpect(jsonPath("\$.description").value("Username or password do not match"));
    }

    void "should reject incorrect password"() throws Exception {
        given:
        def userName = randomString()
        def password = randomString()
        def firstName = randomString()
        def lastName = randomString()
        def registerJson = buildRegisterJson(userName, password, firstName, lastName)
        def loginJson = buildLoginJson(userName, "different-password")

        when: "Registering an user"
        mvc.perform(post("/userservice/register").contentType(MediaType.APPLICATION_JSON).content(registerJson))

        then: "User should not be able to login with incorrect password"
        mvc.perform(post("/userservice/login").contentType(MediaType.APPLICATION_JSON).content(loginJson))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.code").value("INCORRECT_CREDENTIALS"))
                .andExpect(jsonPath("\$.description").value("Username or password do not match"));
    }

    static buildLoginJson(String username, String password) {
        return """{"userName": "$username","password": "$password"}""";
    }

    static buildRegisterJson(String username, String password, String firstname, String lastname) {
        return """{"userName": "$username","password": "$password", "firstName": "$firstname", "lastName": "$lastname"}""";
    }

    static String randomString() {
        return UUID.randomUUID().toString();
    }
}
