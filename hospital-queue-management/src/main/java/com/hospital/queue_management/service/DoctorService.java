package com.hospital.queue_management.service;

import com.hospital.queue_management.entity.Doctor;
import com.hospital.queue_management.exception.ResourceNotFoundException;
import com.hospital.queue_management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    public Doctor updateDoctor(int id, Doctor updated) {
        Doctor existing = getDoctorById(id);
        existing.setName(updated.getName());
        existing.setSpecialization(updated.getSpecialization());
        existing.setAvailable(updated.isAvailable());
        return doctorRepository.save(existing);
    }

    public void deleteDoctor(int id) {
        Doctor existing = getDoctorById(id);
        doctorRepository.delete(existing);
    }
}
