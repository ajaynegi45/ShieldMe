package com.shieldme.sosalerts.controller;

import com.shieldme.sosalerts.model.SOSAlertLog;
import com.shieldme.sosalerts.model.UserContact;
import com.shieldme.sosalerts.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {


    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/allcontact")
    public ResponseEntity<List<UserContact>> getAllContact() {
        return ResponseEntity.ok(adminService.getAllContacts());
    }


    @GetMapping("/alertlogs")
    public ResponseEntity<List<SOSAlertLog>> getAllSOSAlertLogs() {
        return ResponseEntity.ok(adminService.getAllLogs());
    }

}
