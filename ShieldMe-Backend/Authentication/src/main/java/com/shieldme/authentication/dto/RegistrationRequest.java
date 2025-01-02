package com.shieldme.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(

        @NotBlank(message = "Name cannot be empty. Please provide your full name.")
        String name,

        @NotBlank(message = "Email address is required.")
        @Email(message = "Please provide a valid email address (e.g., user@example.com).")
        String email,

        @NotBlank(message = "Password cannot be empty. Please create a secure password.")
        @Size(min = 6, message = "Password must be at least 6 characters long.")
        String password
) {
}
