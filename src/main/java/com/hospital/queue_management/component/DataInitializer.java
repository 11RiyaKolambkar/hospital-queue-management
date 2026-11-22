package com.hospital.queue_management.component;

import com.hospital.queue_management.entity.Appointment;
import com.hospital.queue_management.entity.Doctor;
import com.hospital.queue_management.entity.Patient;
import com.hospital.queue_management.entity.QueueStatus;
import com.hospital.queue_management.repository.AppointmentRepository;
import com.hospital.queue_management.repository.DoctorRepository;
import com.hospital.queue_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) {
        if (doctorRepository.count() == 0) {
            Doctor d1 = new Doctor();
            d1.setName("Dr. Rajesh Sharma");
            d1.setSpecialization("Cardiology");
            d1.setAvailable(true);
            doctorRepository.save(d1);

            Doctor d2 = new Doctor();
            d2.setName("Dr. Priya Patel");
            d2.setSpecialization("Dermatology");
            d2.setAvailable(true);
            doctorRepository.save(d2);

            Doctor d3 = new Doctor();
            d3.setName("Dr. Amit Verma");
            d3.setSpecialization("Orthopedics");
            d3.setAvailable(true);
            doctorRepository.save(d3);

            Doctor d4 = new Doctor();
            d4.setName("Dr. Sunita Rao");
            d4.setSpecialization("Pediatrics");
            d4.setAvailable(true);
            doctorRepository.save(d4);

            Doctor d5 = new Doctor();
            d5.setName("Dr. Vikram Singh");
            d5.setSpecialization("General Medicine");
            d5.setAvailable(true);
            doctorRepository.save(d5);
        }

        if (patientRepository.count() == 0) {
            LocalDate today = LocalDate.now();

            Patient p1 = new Patient();
            p1.setName("Rohan Mehta");
            p1.setAge(28);
            p1.setTokenNumber(1);
            p1.setVisitDate(today);
            p1.setStatus(QueueStatus.IN_PROGRESS);
            patientRepository.save(p1);

            Patient p2 = new Patient();
            p2.setName("Ananya Iyer");
            p2.setAge(34);
            p2.setTokenNumber(2);
            p2.setVisitDate(today);
            p2.setStatus(QueueStatus.WAITING);
            patientRepository.save(p2);

            Patient p3 = new Patient();
            p3.setName("Vikram Malhotra");
            p3.setAge(45);
            p3.setTokenNumber(3);
            p3.setVisitDate(today);
            p3.setStatus(QueueStatus.WAITING);
            patientRepository.save(p3);
        }

        if (appointmentRepository.count() == 0) {
            LocalDate today = LocalDate.now();

            Appointment a1 = new Appointment();
            a1.setPatientName("Rohan Mehta");
            a1.setDoctorName("Dr. Rajesh Sharma");
            a1.setTokenNumber(1);
            a1.setStatus("In Progress");
            a1.setAppointmentDate(today.toString());
            a1.setAppointmentTime("10:00");
            appointmentRepository.save(a1);

            Appointment a2 = new Appointment();
            a2.setPatientName("Ananya Iyer");
            a2.setDoctorName("Dr. Rajesh Sharma");
            a2.setTokenNumber(2);
            a2.setStatus("Waiting");
            a2.setAppointmentDate(today.toString());
            a2.setAppointmentTime("10:30");
            appointmentRepository.save(a2);

            Appointment a3 = new Appointment();
            a3.setPatientName("Vikram Malhotra");
            a3.setDoctorName("Dr. Priya Patel");
            a3.setTokenNumber(1);
            a3.setStatus("Waiting");
            a3.setAppointmentDate(today.toString());
            a3.setAppointmentTime("11:00");
            appointmentRepository.save(a3);
        }
    }
}
