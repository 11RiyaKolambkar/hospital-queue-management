package com.hospital.queue_management.controller;

import com.hospital.queue_management.entity.Patient;
import com.hospital.queue_management.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public Patient addPatient(@Valid @RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable int id, @Valid @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return "Patient Deleted";
    }
}
