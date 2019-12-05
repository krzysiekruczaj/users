package com.staxter.login;

enum ErrorCode {
    INCORRECT_CREDENTIALS("Username or password do not match");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
