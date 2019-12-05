package com.staxter.userrepository;

import java.util.Optional;

public interface UserRepository {
    User createUser(User user) throws UserAlreadyExistsException;

    Optional<User> findUser(String userName);
}
