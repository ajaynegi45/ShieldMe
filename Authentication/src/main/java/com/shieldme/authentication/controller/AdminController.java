package com.shieldme.authentication.controller;

import com.shieldme.authentication.dto.UserResponse;
import com.shieldme.authentication.service.AdminService;
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

    @GetMapping("/get/all/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam (required = true) String userId) {
        return ResponseEntity.ok(adminService.getAllUsers(userId));
    }
}
