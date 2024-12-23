package com.shieldme.authentication.controller;

import com.shieldme.authentication.dto.UpdateRequest;
import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.service.ProfileUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class ProfileUpdateController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileUpdateController.class);
    private final ProfileUpdateService profileUpdateService;
    public ProfileUpdateController(ProfileUpdateService profileUpdateService) {
        this.profileUpdateService = profileUpdateService;
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile( @Validated
            @RequestParam("userId") String userId,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {

        try {
            // Validate the profile image if provided
            if (profileImage != null) {
                if (!Pattern.matches("image/.*", profileImage.getContentType())) {
                    logger.warn("Invalid file type provided for userId: {}", userId);
                    return ResponseEntity.badRequest().body("Invalid file type. Only images are allowed.");
                }
                if (profileImage.getSize() > 1024 * 1024) { // 1MB limit
                    logger.warn("Image size exceeded for userId: {}", userId);
                    return ResponseEntity.badRequest().body("Image size should not exceed 1MB!");
                }
            }

            // Create an UpdateRequest and process the update
            UpdateRequest request = new UpdateRequest(userId, name, email, password, profileImage);
            UserResponse updatedUser = profileUpdateService.updateUserProfile(request);

            logger.info("Profile updated successfully for userId: {}", userId);
            return ResponseEntity.ok(updatedUser);

        } catch (Exception e) {
            logger.error("Profile update failed for userId: {}: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the profile.");
        }
    }
}