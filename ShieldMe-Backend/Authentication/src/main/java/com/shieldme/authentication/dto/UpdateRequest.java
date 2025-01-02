package com.shieldme.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

public record UpdateRequest(

        @NotBlank(message = "User ID is required. Please provide a valid user ID.")
        ObjectId userId,

        String name,

        String email,

        String password
        ) {
}
