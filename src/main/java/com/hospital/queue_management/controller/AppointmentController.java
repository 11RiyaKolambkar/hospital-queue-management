package com.hospital.queue_management.controller;

import com.hospital.queue_management.entity.Appointment;
import com.hospital.queue_management.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
@Tag(name = "Appointments", description = "Endpoints for scheduling, querying, updating, and cancelling patient appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    @Operation(summary = "Book a new appointment", description = "Schedules an appointment and assigns an incremental token number for the doctor.")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
        Appointment saved = appointmentService.bookAppointment(appointment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all appointments", description = "Fetches the complete appointment list across all doctors.")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID", description = "Retrieves an appointment's details by its primary key.")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/doctor/{doctorName}")
    @Operation(summary = "Get appointments by doctor name", description = "Filters appointments assigned to a specific doctor.")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable String doctorName) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctor(doctorName));
    }

    @GetMapping("/search")
    @Operation(summary = "Search appointments by patient name", description = "Performs case-insensitive search on patient names.")
    public ResponseEntity<List<Appointment>> searchAppointments(@RequestParam String patientName) {
        return ResponseEntity.ok(appointmentService.searchByPatientName(patientName));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update appointment status to Consulted", description = "Marks an appointment as Consulted.")
    public ResponseEntity<Map<String, String>> updateStatus(@PathVariable int id) {
        appointmentService.updateStatus(id, "Consulted");
        return ResponseEntity.ok(Map.of("message", "Status Updated", "status", "Consulted"));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Set custom appointment status", description = "Allows updating status to Waiting, In Progress, Consulted, or Cancelled.")
    public ResponseEntity<Appointment> setStatus(@PathVariable int id, @RequestParam String status) {
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an appointment", description = "Removes an appointment from the queue.")
    public ResponseEntity<Map<String, String>> deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok(Map.of("message", "Appointment Deleted"));
    }
}
