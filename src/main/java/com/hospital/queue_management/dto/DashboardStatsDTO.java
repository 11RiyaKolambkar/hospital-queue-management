package com.hospital.queue_management.dto;

public class DashboardStatsDTO {
    private long totalPatients;
    private long totalDoctors;
    private long totalAppointments;
    private long waitingAppointments;
    private long consultedAppointments;
    private long inProgressAppointments;

    public DashboardStatsDTO() {
    }

    public DashboardStatsDTO(long totalPatients, long totalDoctors, long totalAppointments,
                             long waitingAppointments, long consultedAppointments, long inProgressAppointments) {
        this.totalPatients = totalPatients;
        this.totalDoctors = totalDoctors;
        this.totalAppointments = totalAppointments;
        this.waitingAppointments = waitingAppointments;
        this.consultedAppointments = consultedAppointments;
        this.inProgressAppointments = inProgressAppointments;
    }

    public long getTotalPatients() { return totalPatients; }
    public void setTotalPatients(long totalPatients) { this.totalPatients = totalPatients; }

    public long getTotalDoctors() { return totalDoctors; }
    public void setTotalDoctors(long totalDoctors) { this.totalDoctors = totalDoctors; }

    public long getTotalAppointments() { return totalAppointments; }
    public void setTotalAppointments(long totalAppointments) { this.totalAppointments = totalAppointments; }

    public long getWaitingAppointments() { return waitingAppointments; }
    public void setWaitingAppointments(long waitingAppointments) { this.waitingAppointments = waitingAppointments; }

    public long getConsultedAppointments() { return consultedAppointments; }
    public void setConsultedAppointments(long consultedAppointments) { this.consultedAppointments = consultedAppointments; }

    public long getInProgressAppointments() { return inProgressAppointments; }
    public void setInProgressAppointments(long inProgressAppointments) { this.inProgressAppointments = inProgressAppointments; }
}
