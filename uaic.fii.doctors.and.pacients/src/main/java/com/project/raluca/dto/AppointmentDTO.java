package com.project.raluca.dto;

import com.project.raluca.model.enums.AppointmentStatus;

import java.util.Objects;

public class AppointmentDTO {
    private int id;
    private String title;
    private UserDoctorDTO doctor;
    private UserPacientDTO pacient;
    private BookableTimeDTO date;
    private String comment;
    private AppointmentStatus appointmentStatus;
    private double starsNr;

    public AppointmentDTO(int id,  UserPacientDTO pacient, BookableTimeDTO date, String comment, AppointmentStatus appointmentStatus, double starsNr) {
        this.id = id;
        this.pacient = pacient;
        this.date = date;
        this.comment = comment;
        this.appointmentStatus = appointmentStatus;
        this.starsNr = starsNr;
    }
    public AppointmentDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserPacientDTO getPacient() {
        return pacient;
    }

    public void setPacient(UserPacientDTO pacient) {
        this.pacient = pacient;
    }

    public BookableTimeDTO getDate() {
        return date;
    }

    public void setDate(BookableTimeDTO date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public double getStarsNr() {
        return starsNr;
    }

    public void setStarsNr(double starsNr) {
        this.starsNr = starsNr;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(UserDoctorDTO doctor) {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentDTO that = (AppointmentDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
