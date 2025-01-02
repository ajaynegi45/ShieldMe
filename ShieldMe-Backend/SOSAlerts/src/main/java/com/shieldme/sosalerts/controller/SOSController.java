package com.shieldme.sosalerts.controller;

import com.shieldme.sosalerts.model.SOSAlertLog;

import java.time.LocalDateTime;
import com.shieldme.sosalerts.model.UserContact;
import com.shieldme.sosalerts.repository.SOSAlertLogRepository;
import com.shieldme.sosalerts.repository.UserContactRepository;
import com.shieldme.sosalerts.service.EmailService;
import com.shieldme.sosalerts.service.SOSAlertLogService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sos")
@CrossOrigin("*")
public class SOSController {

    private final UserContactRepository contactRepository;
    private final EmailService emailService;
    private final SOSAlertLogRepository alertLogRepository;
    private final SOSAlertLogService alertLogService;
    public SOSController(UserContactRepository contactRepository, EmailService emailService, SOSAlertLogRepository alertLogRepository, SOSAlertLogService alertLogService) {
        this.contactRepository = contactRepository;
        this.emailService = emailService;
        this.alertLogRepository = alertLogRepository;
        this.alertLogService = alertLogService;
    }

    @PostMapping("/alert")
    public ResponseEntity<String> sendSOSAlert(
            @RequestParam String userId,
            @RequestParam String username,
            @RequestParam double latitude,
            @RequestParam double longitude) {


        SOSAlertLog alertLog = new SOSAlertLog();
        alertLog.setUserId(userId);
        alertLog.setTimestamp(LocalDateTime.now());
        alertLog.setLatitude(latitude);
        alertLog.setLongitude(longitude);

        try {
            Optional<UserContact> userContact = contactRepository.findByUserId(userId);

            if (userContact.isPresent()) {
                UserContact contact = userContact.get();
                String googleMapsLink = String.format("https://www.google.com/maps?q=%s,%s", latitude, longitude);

                emailService.sendAlertEmails(contact.getContacts(), googleMapsLink, username);

                alertLog.setStatus("Sent");
                alertLogRepository.save(alertLog);

                return ResponseEntity.ok("SOS alerts sent successfully!");
            } else {
                alertLog.setStatus("Failed");
                alertLogRepository.save(alertLog);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        } catch (Exception e) {
            alertLog.setStatus("Failed");
            alertLogRepository.save(alertLog);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send SOS alerts: " + e.getMessage());
        }
    }


    @GetMapping("/alert-logs/{userId}")
    public ResponseEntity<?> getSOSAlertLogs(@PathVariable String userId) {
        try {
            List<SOSAlertLog> logs = alertLogService.getLogsByUserId(userId);
            if (logs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No logs found for the given user ID.");
            }
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch alert logs: " + e.getMessage());
        }
    }

}

