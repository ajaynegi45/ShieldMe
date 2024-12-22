package com.shieldme.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email address is required. Please provide a valid email.")
        @Email(message = "Invalid email format. Please provide a properly formatted email address (e.g., user@example.com).")
        String email,

        @NotBlank(message = "Password is required. Please enter your password.")
        String password
) {
}
