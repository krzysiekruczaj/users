package com.staxter.login;

import com.staxter.userrepository.User;
import com.staxter.userrepository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    LoginService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(UserLogin userLogin) {
        return userRepository.findUser(userLogin.getUserName())
                .filter(user -> isPasswordMatching(userLogin, user))
                .orElseThrow(IncorrectCredentialsException::new);
    }

    private boolean isPasswordMatching(UserLogin userLogin, User user) {
        return passwordEncoder.matches(userLogin.getPassword(), user.getHashedPassword());
    }
}
