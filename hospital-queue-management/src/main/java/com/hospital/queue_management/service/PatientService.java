package com.hospital.queue_management.service;

import com.hospital.queue_management.entity.Patient;
import com.hospital.queue_management.exception.ResourceNotFoundException;
import com.hospital.queue_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public Patient updatePatient(int id, Patient updated) {
        Patient existing = getPatientById(id);
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        return patientRepository.save(existing);
    }

    public void deletePatient(int id) {
        Patient existing = getPatientById(id);
        patientRepository.delete(existing);
    }
}
