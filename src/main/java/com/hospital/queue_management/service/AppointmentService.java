package com.hospital.queue_management.service;

import com.hospital.queue_management.dto.DashboardStatsDTO;
import com.hospital.queue_management.entity.Appointment;
import com.hospital.queue_management.exception.ResourceNotFoundException;
import com.hospital.queue_management.repository.AppointmentRepository;
import com.hospital.queue_management.repository.DoctorRepository;
import com.hospital.queue_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Appointment bookAppointment(Appointment appointment) {
        if (appointment.getDoctorName() == null || appointment.getDoctorName().trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor name is required for booking an appointment");
        }
        if (appointment.getPatientName() == null || appointment.getPatientName().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name is required for booking an appointment");
        }

        int tokenNumber = appointmentRepository
                .countByDoctorNameIgnoreCase(appointment.getDoctorName().trim()) + 1;
        appointment.setTokenNumber(tokenNumber);
        if (appointment.getStatus() == null || appointment.getStatus().trim().isEmpty()) {
            appointment.setStatus("Waiting");
        }
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        if (!appointmentRepository.existsById(appointment.getId())) {
            throw new ResourceNotFoundException("Appointment not found with id: " + appointment.getId());
        }
        return appointmentRepository.save(appointment);
    }

    public Appointment updateStatus(int id, String status) {
        Appointment appointment = getAppointmentById(id);
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorName) {
        return appointmentRepository.findByDoctorNameIgnoreCase(doctorName.trim());
    }

    public List<Appointment> searchByPatientName(String patientName) {
        return appointmentRepository.findByPatientNameContainingIgnoreCase(patientName.trim());
    }

    public void deleteAppointment(int id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    public DashboardStatsDTO getDashboardStats() {
        long totalPatients = patientRepository.count();
        long totalDoctors = doctorRepository.count();
        long totalAppointments = appointmentRepository.count();
        long waiting = appointmentRepository.countByStatusIgnoreCase("Waiting");
        long consulted = appointmentRepository.countByStatusIgnoreCase("Consulted");
        long inProgress = appointmentRepository.countByStatusIgnoreCase("In Progress");

        return new DashboardStatsDTO(totalPatients, totalDoctors, totalAppointments, waiting, consulted, inProgress);
    }
}
