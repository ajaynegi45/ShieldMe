package com.shieldme.authentication.controller;


import com.shieldme.authentication.config.PasswordEncoder;
import com.shieldme.authentication.dto.MailBody;
import com.shieldme.authentication.entity.ForgotPassword;
import com.shieldme.authentication.entity.User;
import com.shieldme.authentication.exception.UserNotFoundException;
import com.shieldme.authentication.repository.ForgotPasswordRepository;
import com.shieldme.authentication.repository.UserRepository;
import com.shieldme.authentication.service.EmailService;
import com.shieldme.authentication.utils.ChangePassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import static java.lang.ProcessBuilder.Redirect.to;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.emailService= emailService;
        this.forgotPasswordRepository= forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Please provide an valid email"));


        int otp = otpGenerator();
        MailBody mailBody= MailBody.builder()
                .to(email)
                .text("This is the OTP for your Forgot Password request : " + otp)
                .subject("OTP for Forgot Password request")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        ForgotPasswordRepository.save(fp);

        return ResponseEntity.ok("Email sent for verification");



    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Please provide an valid email"));



        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(()-> new RuntimeException("Invalid OTP for email: " + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(fp.getfpid());
            return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);

        }
        return ResponseEntity.ok("OTP verified");
    }



    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword,
                                                        @PathVariable String email){

    if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())){
        return new ResponseEntity<>("Please enter the password again!", HttpStatus.EXPECTATION_FAILED);

    }

    String encodedPassword = passwordEncoder.encode(changePassword.password());
     userRepository.updatePassword(email, encodedPassword);

     return ResponseEntity.ok("Password has been changed");
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt( 100_000, 999_999);
    }


}
