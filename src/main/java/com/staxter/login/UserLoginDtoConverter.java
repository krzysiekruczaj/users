package com.staxter.login;

import org.springframework.stereotype.Component;

@Component
class UserLoginDtoConverter {

    UserLogin convertFromDto(UserLoginDto user) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(user.getUserName());
        userLogin.setPassword(user.getPassword());
        return userLogin;
    }
}
