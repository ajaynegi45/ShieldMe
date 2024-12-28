package com.shieldme.authentication.service;

import com.shieldme.authentication.dto.UpdateRequest;
import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.entity.PasswordDetails;
import com.shieldme.authentication.entity.ProfileImage;
import com.shieldme.authentication.entity.User;
import com.shieldme.authentication.config.PasswordEncoder;
import com.shieldme.authentication.exception.UserNotFoundException;
import com.shieldme.authentication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileUpdateService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileUpdateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse updateUserProfile(ObjectId userId, String name, String email, String password, MultipartFile profileImage) throws IOException, IOException {

        // Fetch the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Update fields
        if (name != null) user.setName(name);
        if (email != null) user.setEmail(email);
        if (password != null && !password.isEmpty()) {
            String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(password);
            user.getPasswordDetails().setPassword(encodedPassword);
        }

        // Process the profile image
        if (profileImage != null) {
            if (!profileImage.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("Invalid file type. Only images are allowed.");
            }

            if (profileImage.getSize() > 1024 * 1024) { // 1MB limit
                throw new IllegalArgumentException("Image size should not exceed 1MB!");
            }

            user.getProfileImage().setImageName(profileImage.getOriginalFilename());
            user.getProfileImage().setImageType(profileImage.getContentType());
            user.getProfileImage().setImageData(profileImage.getBytes());
        }

        userRepository.save(user);

        return new UserResponse(
                user.getUserId().toString(),
                user.getName(),
                user.getEmail(),
                user.getProfileImage()
        );
    }
}
