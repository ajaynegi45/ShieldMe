package com.shieldme.authentication.service;

import com.shieldme.authentication.dto.UpdateRequest;
import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.entity.User;
import com.shieldme.authentication.config.PasswordEncoder;
import com.shieldme.authentication.exception.UserNotFoundException;
import com.shieldme.authentication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileUpdateService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ProfileUpdateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse updateUserProfile(UpdateRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (request.name() != null) user.setName(request.name());
        if (request.email() != null) user.setEmail(request.email());
        if (request.password() != null && !request.password().isEmpty()) {
            String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(request.password());
            user.setPassword(encodedPassword);
        }
        if (request.profileImage() != null) {
//            String imageUrl = fileStorageService.storeFile(request.getProfileImage());
//            user.setProfileImage(imageUrl); // Save the image URL
        }

        userRepository.save(user);

        return new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getProfileImage()
        );
    }
}
