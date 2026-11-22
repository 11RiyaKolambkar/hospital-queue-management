package com.hospital.queue_management.service;

import com.hospital.queue_management.dto.LoginRequest;
import com.hospital.queue_management.dto.RegisterRequest;
import com.hospital.queue_management.entity.Doctor;
import com.hospital.queue_management.entity.User;
import com.hospital.queue_management.exception.ResourceNotFoundException;
import com.hospital.queue_management.repository.DoctorRepository;
import com.hospital.queue_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public User register(RegisterRequest req) {
        if (req.getUsername() == null || req.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (req.getPassword() == null || req.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (userRepository.existsByUsernameIgnoreCase(req.getUsername().trim())) {
            throw new IllegalArgumentException("Username is already taken. Please choose another.");
        }

        String role = (req.getRole() == null || req.getRole().trim().isEmpty()) ? "STAFF" : req.getRole().trim().toUpperCase();
        String fullName = (req.getFullName() == null || req.getFullName().trim().isEmpty()) ? req.getUsername() : req.getFullName().trim();

        User user = new User(req.getUsername().trim().toLowerCase(), req.getPassword(), fullName, role);
        User savedUser = userRepository.save(user);

        // If registered role is DOCTOR, automatically create corresponding doctor record in doctor directory
        if ("DOCTOR".equalsIgnoreCase(role)) {
            Doctor doc = new Doctor();
            doc.setName(fullName);
            doc.setSpecialization(req.getSpecialization() != null && !req.getSpecialization().trim().isEmpty() ? req.getSpecialization().trim() : "General Medicine");
            doc.setAvailable(true);
            doctorRepository.save(doc);
        }

        return savedUser;
    }

    public Map<String, Object> authenticate(LoginRequest req) {
        if (req.getUsername() == null || req.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }

        User user = userRepository.findByUsernameIgnoreCase(req.getUsername().trim())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));

        if (!user.getPassword().equals(req.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return Map.of(
                "status", "success",
                "username", user.getUsername(),
                "fullName", user.getFullName(),
                "role", user.getRole().toLowerCase()
        );
    }
}
