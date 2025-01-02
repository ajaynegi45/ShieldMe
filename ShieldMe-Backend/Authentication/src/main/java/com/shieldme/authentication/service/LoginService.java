package com.shieldme.authentication.service;

import com.shieldme.authentication.config.PasswordEncoder;
import com.shieldme.authentication.dto.LoginRequest;
import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.entity.User;
import com.shieldme.authentication.exception.InvalidCredentialsException;
import com.shieldme.authentication.exception.UserNotFoundException;
import com.shieldme.authentication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> {
                    logger.warn("User not found for email: {}", request.email());
                    return new UserNotFoundException("The email address is not registered.");
                });

        if (!passwordEncoder.bCryptPasswordEncoder().matches(request.password(), user.getPasswordDetails().getPassword())) {
            logger.warn("Login failed: Invalid password for email: {}", request.email());
            throw new InvalidCredentialsException("The password you entered is incorrect.");
        }

        return new UserResponse(
                user.getUserId().toString(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getProfileImage()
        );
    }
}

