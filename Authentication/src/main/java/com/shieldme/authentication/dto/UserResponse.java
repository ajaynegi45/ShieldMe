package com.shieldme.authentication.dto;

import com.shieldme.authentication.entity.ProfileImage;
import com.shieldme.authentication.entity.Role;

public record UserResponse(
        String userId,
        String name,
        String email,
        Role role,
        ProfileImage profileImage
) {

}
