package com.shieldme.authentication.controller;

import com.shieldme.authentication.dto.RegistrationRequest;
import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Validated @RequestBody RegistrationRequest request) {
        logger.info("Processing registration request for email: {}", request.email());
        UserResponse response = registrationService.registerUser(request);
        logger.info("User registered successfully for email: {}", request.email());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

