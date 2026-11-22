package com.hospital.queue_management.controller;

import com.hospital.queue_management.dto.LoginRequest;
import com.hospital.queue_management.dto.RegisterRequest;
import com.hospital.queue_management.entity.User;
import com.hospital.queue_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Authentication", description = "Endpoints for user registration and role-based login")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user account", description = "Creates a user account with role: ADMIN, RECEPTIONIST, DOCTOR, or PATIENT.")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest req) {
        User user = userService.register(req);
        return new ResponseEntity<>(Map.of(
                "message", "User registered successfully",
                "username", user.getUsername(),
                "role", user.getRole()
        ), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "User login authentication", description = "Validates credentials and returns user profile with role.")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest req) {
        Map<String, Object> response = userService.authenticate(req);
        return ResponseEntity.ok(response);
    }
}
