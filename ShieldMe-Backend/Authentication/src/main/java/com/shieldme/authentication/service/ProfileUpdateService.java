package com.shieldme.authentication.service;

import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.entity.User;
import com.shieldme.authentication.config.PasswordEncoder;
import com.shieldme.authentication.exception.UserNotFoundException;
import com.shieldme.authentication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileUpdateService {

    private static final Logger logger = LoggerFactory.getLogger(ProfileUpdateService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ProfileUpdateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse updateUserProfile(ObjectId userId, String name, String email, String password, MultipartFile profileImage) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.warn("User not found for userId: {}", userId);
                    return new UserNotFoundException("User not found.");
                });

        if (name != null) user.setName(name);
        if (email != null) user.setEmail(email);
        if (password != null && !password.isEmpty()) user.getPasswordDetails().setPassword(passwordEncoder.bCryptPasswordEncoder().encode(password));

        if (profileImage != null) {
            if (!profileImage.getContentType().startsWith("image/"))
                throw new IllegalArgumentException("Invalid file type. Only images are allowed.");

            if (profileImage.getSize() > 1024 * 1024) // profileImage size should be less than 1MB
                throw new IllegalArgumentException("Image size should not exceed 1MB!");

            user.getProfileImage().setImageName(profileImage.getOriginalFilename());
            user.getProfileImage().setImageType(profileImage.getContentType());
            user.getProfileImage().setImageData(profileImage.getBytes());
        }
        userRepository.save(user);
        return new UserResponse(
                user.getUserId().toString(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getProfileImage()
        );
    }
}
