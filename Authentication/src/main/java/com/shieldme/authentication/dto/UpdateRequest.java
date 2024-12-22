package com.shieldme.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record UpdateRequest(
        @NotBlank(message = "User ID is required. Please provide a valid user ID.")
        String userId,

        String name,

        String email,

        String password,

        MultipartFile profileImage
) {
}
