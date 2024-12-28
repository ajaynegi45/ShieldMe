package com.shieldme.authentication.controller;

import com.shieldme.authentication.dto.LoginRequest;
import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Validated @RequestBody LoginRequest request) {
        logger.info("Processing login request for email: {}", request.email());
        UserResponse response = loginService.loginUser(request);
        logger.info("Login successful for email: {}", request.email());
        return ResponseEntity.ok(response);
    }
}

