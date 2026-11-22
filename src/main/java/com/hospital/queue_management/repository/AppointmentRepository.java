package com.hospital.queue_management.repository;

import com.hospital.queue_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    int countByDoctorName(String doctorName);
    int countByDoctorNameIgnoreCase(String doctorName);
    List<Appointment> findByDoctorNameIgnoreCase(String doctorName);
    List<Appointment> findByStatusIgnoreCase(String status);
    List<Appointment> findByDoctorNameIgnoreCaseAndStatusIgnoreCase(String doctorName, String status);
    List<Appointment> findByPatientNameContainingIgnoreCase(String patientName);
    long countByStatusIgnoreCase(String status);
}
