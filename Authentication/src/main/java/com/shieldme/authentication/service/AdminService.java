package com.shieldme.authentication.service;

import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.entity.Role;
import com.shieldme.authentication.entity.User;
import com.shieldme.authentication.exception.InvalidCredentialsException;
import com.shieldme.authentication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepository userRepository;
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        public List<UserResponse> getAllUsers(String userId) {
            // Convert the userId to ObjectId
            ObjectId adminId;
            try {
                adminId = new ObjectId(userId);
            } catch (IllegalArgumentException e) {
                throw new InvalidCredentialsException("Invalid user ID format.");
            }

            // Check if the user is an admin
            if (!userRepository.existsByUserIdAndRole( adminId, Role.ADMIN)) {
                throw new InvalidCredentialsException("You are not an admin and do not have access to this dashboard.");
            }

            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(user -> new UserResponse(
                            user.getUserId().toString(),
                            user.getName(),
                            user.getEmail(),
                            user.getRole(),
                            user.getProfileImage()
                    ))
                    .collect(Collectors.toList());
        }

}
