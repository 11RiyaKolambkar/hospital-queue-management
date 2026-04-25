package com.hospital.queue_management.service;

import com.hospital.queue_management.entity.Appointment;
import com.hospital.queue_management.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment) {
        int tokenNumber = appointmentRepository
                .countByDoctorName(appointment.getDoctorName()) + 1;
        appointment.setTokenNumber(tokenNumber);
        appointment.setStatus("Waiting");
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }
}