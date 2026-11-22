package com.hospital.queue_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.queue_management.entity.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldBookAndRetrieveAppointment() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setPatientName("Rahul Verma");
        appointment.setDoctorName("Dr. Rajesh Sharma");
        appointment.setAppointmentDate("2026-11-25");
        appointment.setAppointmentTime("14:30");

        mockMvc.perform(post("/appointments/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.patientName").value("Rahul Verma"))
                .andExpect(jsonPath("$.tokenNumber").isNumber())
                .andExpect(jsonPath("$.status").value("Waiting"));

        mockMvc.perform(get("/appointments/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnDashboardStats() throws Exception {
        mockMvc.perform(get("/dashboard/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalDoctors").isNumber())
                .andExpect(jsonPath("$.totalPatients").isNumber())
                .andExpect(jsonPath("$.totalAppointments").isNumber());
    }
}
