package com.staxter.login;

import com.staxter.user.UserDto;
import com.staxter.user.UserDtoConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
class LoginController {

    private final LoginService loginService;
    private final UserDtoConverter userDtoConverter;
    private final UserLoginDtoConverter userRegistrationDtoConverter;

    LoginController(LoginService loginService, UserDtoConverter userDtoConverter, UserLoginDtoConverter userRegistrationDtoConverter) {
        this.loginService = loginService;
        this.userDtoConverter = userDtoConverter;
        this.userRegistrationDtoConverter = userRegistrationDtoConverter;
    }

    @PostMapping("/userservice/login")
    UserDto login(@RequestBody @Valid UserLoginDto user) {
        return userDtoConverter.convertToDto(
                loginService.login(
                        userRegistrationDtoConverter.convertFromDto(user)
                )
        );
    }
}
