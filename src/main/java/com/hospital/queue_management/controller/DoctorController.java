package com.hospital.queue_management.controller;

import com.hospital.queue_management.entity.Doctor;
import com.hospital.queue_management.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
@Tag(name = "Doctors", description = "Endpoints for managing hospital doctors, specializations, and availability")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    @Operation(summary = "Register a new doctor", description = "Adds a doctor with specialization and availability.")
    public ResponseEntity<Map<String, Object>> addDoctor(@RequestBody Doctor doctor) {
        if (!doctor.isAvailable()) {
            doctor.setAvailable(true);
        }
        Doctor saved = doctorService.saveDoctor(doctor);
        return new ResponseEntity<>(Map.of("message", "Doctor Saved", "doctor", saved), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all doctors", description = "Retrieves the directory of all registered doctors.")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get doctor by ID", description = "Fetches a specific doctor profile by ID.")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PutMapping("/{id}/toggle-availability")
    @Operation(summary = "Toggle doctor availability", description = "Switches a doctor's availability status between true and false.")
    public ResponseEntity<Doctor> toggleAvailability(@PathVariable int id) {
        return ResponseEntity.ok(doctorService.toggleAvailability(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete doctor", description = "Removes a doctor record.")
    public ResponseEntity<Map<String, String>> deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok(Map.of("message", "Doctor Deleted"));
    }
}
