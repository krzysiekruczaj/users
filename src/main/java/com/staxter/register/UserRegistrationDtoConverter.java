package com.staxter.register;

import org.springframework.stereotype.Component;

@Component
class UserRegistrationDtoConverter {

    UserRegistration convertFromDto(UserRegistrationDto user) {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setFirstName(user.getFirstName());
        userRegistration.setLastName(user.getLastName());
        userRegistration.setUserName(user.getUserName());
        userRegistration.setPlainTextPassword(user.getPassword());
        return userRegistration;
    }
}
