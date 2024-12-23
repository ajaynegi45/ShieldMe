package com.shieldme.authentication.service;

import com.shieldme.authentication.dto.UpdateRequest;
import com.shieldme.authentication.dto.UserResponse;

public class ProfileUpdateService {
    public UserResponse updateUserProfile(UpdateRequest request) {
        return null;
    }


    //    public UserResponse updateUserProfile(UpdateRequest request) {
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//        if (request.getName() != null) user.setName(request.getName());
//        if (request.getEmail() != null) user.setEmail(request.getEmail());
//        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
//            String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(request.getPassword());
//            user.setPassword(encodedPassword);
//        }
//        if (request.getProfileImage() != null) {
////            String imageUrl = fileStorageService.storeFile(request.getProfileImage());
////            user.setProfileImage(imageUrl); // Save the image URL
//        }
//
//        user.setUpdatedAt(LocalDateTime.now());
//        userRepository.save(user);
//
//        return toUserResponse(user);
//    }
}
