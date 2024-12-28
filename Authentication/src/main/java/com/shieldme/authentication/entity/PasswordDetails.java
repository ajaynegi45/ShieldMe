package com.shieldme.authentication.entity;

import java.time.LocalDateTime;

public class PasswordDetails {

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private String password;

    public PasswordDetails() {
    }

    public PasswordDetails(String token, LocalDateTime createdAt, LocalDateTime lastUpdatedAt, String password) {
        this.token = token;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PasswordDetails{" +
                "token='" + token + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", password='" + password + '\'' +
                '}';
    }
}
