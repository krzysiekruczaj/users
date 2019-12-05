package com.staxter.login;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

class UserLoginDto {

    @Valid
    @NotNull
    private String userName;

    @Valid
    @NotNull
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
