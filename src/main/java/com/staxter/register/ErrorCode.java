package com.staxter.register;

enum ErrorCode {
    USER_ALREADY_EXISTS("A user with the given username already exists");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
