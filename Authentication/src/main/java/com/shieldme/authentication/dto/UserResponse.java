package com.shieldme.authentication.dto;

import com.shieldme.authentication.entity.ProfileImage;
import org.bson.types.ObjectId;

public record UserResponse(
        String userId,
        String name,
        String email,
        ProfileImage profileImage
) {

}
