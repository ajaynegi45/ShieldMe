package com.shieldme.authentication.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String userId;

    private String name;

    private String email;

    private String password;

    private String profileImage; // Base64

    private Role role; // "ADMIN", "USER", "MODERATOR"

    public User() {
        this.role = Role.USER; // Default to "USER"
    }

    public User(String userId, String name, String email, String password, String profileImage, Role role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.role = Role.USER; // Default to "USER"
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", role=" + role +
                '}';
    }
}

