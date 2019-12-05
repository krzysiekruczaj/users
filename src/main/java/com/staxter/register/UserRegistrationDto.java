package com.staxter.register;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

class UserRegistrationDto {
    @Valid
    @NotNull
    private String firstName;
    @Valid
    @NotNull
    private String lastName;
    @Valid
    @NotNull
    private String userName;
    @Valid
    @NotNull
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
