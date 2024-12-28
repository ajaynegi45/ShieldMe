package com.shieldme.authentication.service;

import com.shieldme.authentication.config.PasswordEncoder;
import com.shieldme.authentication.dto.RegistrationRequest;
import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.entity.User;
import com.shieldme.authentication.exception.UserAlreadyExistsException;
import com.shieldme.authentication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse registerUser(RegistrationRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            logger.warn("Email {} is already registered.", request.email());
            throw new UserAlreadyExistsException("The email address is already registered.");
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.getPasswordDetails().setPassword(passwordEncoder.bCryptPasswordEncoder().encode(request.password())) ;

        userRepository.save(user);
        logger.info("User with email {} registered successfully.", request.email());

        return new UserResponse(
                user.getUserId().toString(),
                user.getName(),
                user.getEmail(),
                user.getProfileImage()
        );
    }
}

