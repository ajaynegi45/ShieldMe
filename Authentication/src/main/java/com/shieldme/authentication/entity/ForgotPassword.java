package com.shieldme.authentication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder




public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer fpid;

@Column(nullable = false)
    private Integer otp;

@Column(nullable = false)
    private Date expirationTime;


    @OneToOne
    private User user;



    // Getters and setters
    public Integer getFpid() {
        return fpid;
    }

    public void setFpid(Integer fpid) {
        this.fpid = fpid;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Custom Builder
    public static Builder builder() {
        return new Builder();
    }

    public Integer getfpid() {
        return getFpid();
    }

    public static class Builder {
        private Integer otp;
        private Date expirationTime;
        private User user;

        public Builder otp(Integer otp) {
            this.otp = otp;
            return this;
        }

        public Builder expirationTime(Date expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public ForgotPassword build() {
            ForgotPassword forgotPassword = new ForgotPassword();
            forgotPassword.setOtp(otp);
            forgotPassword.setExpirationTime(expirationTime);
            forgotPassword.setUser(user);
            return forgotPassword;
        }
    }





}




