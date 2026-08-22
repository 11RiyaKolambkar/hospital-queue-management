package com.hospital.queue_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotBlank(message = "Doctor name is required")
    private String doctorName;

    private int tokenNumber;
    private String status;

    @NotBlank(message = "Appointment date is required")
    private String appointmentDate;

    @NotBlank(message = "Appointment time is required")
    private String appointmentTime;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public int getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(int tokenNumber) { this.tokenNumber = tokenNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }
}
