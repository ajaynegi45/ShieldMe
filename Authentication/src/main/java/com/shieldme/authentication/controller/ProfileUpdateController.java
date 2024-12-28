package com.shieldme.authentication.controller;

import com.shieldme.authentication.dto.UpdateRequest;
import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.service.ProfileUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class ProfileUpdateController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileUpdateController.class);
    private final ProfileUpdateService profileUpdateService;
    public ProfileUpdateController(ProfileUpdateService profileUpdateService) {
        this.profileUpdateService = profileUpdateService;
    }

    @PostMapping("/update-profile")
    public ResponseEntity<?> updateProfile(
            @RequestPart(value = "profileData" , required = false)UpdateRequest profileData,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {

        try {
            if(profileData.userId() == null) {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER ID is not null");
            }
            UserResponse updatedUser = profileUpdateService.updateUserProfile(profileData.userId(), profileData.name(), profileData.email(), profileData.password(), profileImage);
            logger.info("Profile updated successfully for userId: {}", profileData.userId());
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error("Profile update failed for userId: {}", profileData.userId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
