package com.staxter.register;

import com.staxter.userrepository.User;
import com.staxter.userrepository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    RegistrationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserRegistration userRegistration) {
        return userRepository.createUser(mapToUser(userRegistration));
    }

    private User mapToUser(UserRegistration userDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setPlainTextPassword(null);
        user.setHashedPassword(passwordEncoder.encode(userDto.getPlainTextPassword()));
        return user;
    }
}
