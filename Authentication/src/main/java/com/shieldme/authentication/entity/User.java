package com.shieldme.authentication.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private ObjectId userId;

    private String name;

    @Indexed(unique = true)
    private String email;

    private PasswordDetails passwordDetails = new PasswordDetails();

    private ProfileImage profileImage = new ProfileImage();

    private Role role; // "ADMIN", "USER"

    public User() {
        this.role = Role.USER; // Default to "USER"
    }

    public User(ObjectId userId, String name, String email, PasswordDetails passwordDetails, ProfileImage profileImage, Role role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordDetails = passwordDetails;
        this.profileImage = profileImage;
        this.role = Role.USER;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
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

    public PasswordDetails getPasswordDetails() {
        return passwordDetails;
    }

    public void setPasswordDetails(PasswordDetails passwordDetails) {
        this.passwordDetails = passwordDetails;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

