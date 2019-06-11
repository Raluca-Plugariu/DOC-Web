package com.project.raluca.model;


import com.project.raluca.model.enums.AppointmentStatus;

import java.util.Objects;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Appointment")
public class Appointment extends AbstractEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="pacient_id")
    private Pacient pacient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookableTime_id")
    private BookableTime date;

    private String comment;

    private AppointmentStatus appointmentStatus;

    private double starsNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public BookableTime getDate() {
        return date;
    }

    public void setDate(BookableTime date) {
        this.date = date;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public double getStarsNumber() {
        return starsNumber;
    }

    public void setStarsNumber(double starsNumber) {
        this.starsNumber = starsNumber;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Appointment)) {
            return false;
        }
        Appointment that = (Appointment) o;
        return getId() == that.getId() &&
                Double.compare(that.getStarsNumber(), getStarsNumber()) == 0 &&
                Objects.equals(getPacient(), that.getPacient()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getComment(), that.getComment()) &&
                getAppointmentStatus() == that.getAppointmentStatus();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getPacient(), getDate(), getComment(), getAppointmentStatus(), getStarsNumber());
    }
}
