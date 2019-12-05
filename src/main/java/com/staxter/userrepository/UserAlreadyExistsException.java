package com.staxter.userrepository;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userName) {
        super("An user with the given username already exists: " + userName);
    }
}
