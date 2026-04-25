package com.hospital.queue_management.controller;

import com.hospital.queue_management.entity.Patient;
import com.hospital.queue_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public String addPatient(@RequestBody Patient patient) {
        patientService.savePatient(patient);
        return "Saved";
    }

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
}