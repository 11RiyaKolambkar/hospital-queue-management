package com.hospital.queue_management.repository;

import com.hospital.queue_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    int countByDoctorName(String doctorName);
}