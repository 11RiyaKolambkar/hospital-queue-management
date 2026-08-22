package com.hospital.queue_management.controller;

import com.hospital.queue_management.entity.Doctor;
import com.hospital.queue_management.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable int id, @Valid @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return "Doctor Deleted";
    }
}
