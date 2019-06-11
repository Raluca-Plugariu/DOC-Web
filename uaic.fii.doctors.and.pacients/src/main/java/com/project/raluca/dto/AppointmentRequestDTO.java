package com.project.raluca.dto;

public class AppointmentRequestDTO {
    private AppointmentDTO appointmentDTO;
    private int doctorId;

    public AppointmentRequestDTO(AppointmentDTO appointmentDTO, int doctorId) {
        this.appointmentDTO = appointmentDTO;
        this.doctorId = doctorId;
    }

    public AppointmentRequestDTO() {
    }

    public AppointmentDTO getAppointmentDTO() {
        return appointmentDTO;
    }

    public void setAppointmentDTO(AppointmentDTO appointmentDTO) {
        this.appointmentDTO = appointmentDTO;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
