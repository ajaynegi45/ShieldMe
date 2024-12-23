package com.shieldme.authentication.dto;

public record UserResponse(
        String userId,
        String name,
        String email,
        String profileImage
) {

}
