package com.hospital.queue_management.repository;

import com.hospital.queue_management.entity.Patient;
import com.hospital.queue_management.entity.QueueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findTopByVisitDateOrderByTokenNumberDesc(LocalDate date);
    Optional<Patient> findFirstByVisitDateAndStatusOrderByTokenNumberAsc(LocalDate date, QueueStatus status);
    List<Patient> findByNameContainingIgnoreCase(String name);
    long countByStatus(QueueStatus status);
}
