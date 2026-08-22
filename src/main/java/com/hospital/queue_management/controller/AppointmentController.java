package com.hospital.queue_management.controller;

import com.hospital.queue_management.entity.Appointment;
import com.hospital.queue_management.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    @PutMapping("/update/{id}")
    public String updateStatus(@PathVariable int id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        appointment.setStatus("Consulted");
        appointmentService.updateAppointment(appointment);
        return "Status Updated";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
        return "Appointment Deleted";
    }

}
