package com.shieldme.authentication.repository;


import com.shieldme.authentication.entity.ForgotPassword;
import com.shieldme.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordRepository, Integer> {


    static void save(ForgotPassword fp) {
    }

    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user =?2")
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
}
