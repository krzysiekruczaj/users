package com.staxter.login;

class IncorrectCredentialsException extends RuntimeException {
    IncorrectCredentialsException() {
        super("Username or password do not match");
    }
}
