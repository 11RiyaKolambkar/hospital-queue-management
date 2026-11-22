package com.hospital.queue_management.controller;

import com.hospital.queue_management.entity.Patient;
import com.hospital.queue_management.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
@Tag(name = "Patients", description = "Endpoints for patient intake, queue calling, and patient directory")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    @Operation(summary = "Register a patient for today's OPD queue", description = "Issues a daily token number and enqueues the patient.")
    public ResponseEntity<Map<String, Object>> addPatient(@RequestBody Patient patient) {
        Patient saved = patientService.savePatient(patient);
        return new ResponseEntity<>(Map.of("message", "Saved", "patient", saved), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "List all patients", description = "Fetches the full list of patients.")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID", description = "Fetches details of a single patient.")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search patients by name", description = "Finds patients matching a name query.")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam String name) {
        return ResponseEntity.ok(patientService.searchPatients(name));
    }

    @PostMapping("/queue/next")
    @Operation(summary = "Call next waiting patient", description = "Advances the lowest uncalled token for today to IN_PROGRESS.")
    public ResponseEntity<Patient> callNext() {
        return ResponseEntity.ok(patientService.callNext());
    }

    @PutMapping("/{id}/complete")
    @Operation(summary = "Mark patient consultation completed", description = "Sets patient queue status to COMPLETED.")
    public ResponseEntity<Patient> completePatient(@PathVariable int id) {
        return ResponseEntity.ok(patientService.completePatient(id));
    }
}
