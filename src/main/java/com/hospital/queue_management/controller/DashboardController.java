package com.hospital.queue_management.controller;

import com.hospital.queue_management.dto.DashboardStatsDTO;
import com.hospital.queue_management.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
@Tag(name = "Dashboard", description = "Real-time metrics and statistics for hospital administration")
public class DashboardController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/stats")
    @Operation(summary = "Get live dashboard statistics", description = "Returns real-time counts for patients, doctors, total appointments, waiting, in-progress, and completed consultations.")
    public ResponseEntity<DashboardStatsDTO> getStats() {
        return ResponseEntity.ok(appointmentService.getDashboardStats());
    }
}
