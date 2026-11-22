package com.hospital.queue_management.service;

import com.hospital.queue_management.entity.Patient;
import com.hospital.queue_management.entity.QueueStatus;
import com.hospital.queue_management.exception.ResourceNotFoundException;
import com.hospital.queue_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        if (patient.getName() == null || patient.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be blank");
        }
        LocalDate today = LocalDate.now();
        int nextToken = patientRepository
                .findTopByVisitDateOrderByTokenNumberDesc(today)
                .map(p -> p.getTokenNumber() + 1)
                .orElse(1);

        patient.setVisitDate(today);
        patient.setTokenNumber(nextToken);
        if (patient.getStatus() == null) {
            patient.setStatus(QueueStatus.WAITING);
        }
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public List<Patient> searchPatients(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name.trim());
    }

    public Patient callNext() {
        Patient next = patientRepository
                .findFirstByVisitDateAndStatusOrderByTokenNumberAsc(
                        LocalDate.now(), QueueStatus.WAITING)
                .orElseThrow(() -> new ResourceNotFoundException("No patients waiting in queue for today"));
        next.setStatus(QueueStatus.IN_PROGRESS);
        return patientRepository.save(next);
    }

    public Patient completePatient(int id) {
        Patient patient = getPatientById(id);
        patient.setStatus(QueueStatus.COMPLETED);
        return patientRepository.save(patient);
    }
}
