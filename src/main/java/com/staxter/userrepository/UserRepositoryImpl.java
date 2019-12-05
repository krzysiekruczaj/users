package com.staxter.userrepository;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> database = new ConcurrentHashMap<>();

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        String userName = user.getUserName();
        if (database.putIfAbsent(userName, user) != null) {
            throw new UserAlreadyExistsException(userName);
        }
        return user;
    }

    @Override
    public Optional<User> findUser(String userName) {
        return Optional.ofNullable(database.get(userName));
    }
}
