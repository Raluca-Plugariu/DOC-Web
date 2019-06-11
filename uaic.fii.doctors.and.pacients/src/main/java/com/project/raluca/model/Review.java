package com.project.raluca.model;

import java.util.Objects;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Review")
public class Review extends AbstractEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;


    @ManyToOne
    @JoinColumn(name="pacient_id")
    private Pacient pacient;

    private String content;

    private double starsNumber;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getStarsNumber() {
        return starsNumber;
    }

    public void setStarsNumber(double starsNumber) {
        this.starsNumber = starsNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Review)) {
            return false;
        }
        Review review = (Review) o;
        return getId() == review.getId() &&
                Double.compare(review.getStarsNumber(), getStarsNumber()) == 0 &&
                Objects.equals(getDoctor(), review.getDoctor()) &&
                Objects.equals(getPacient(), review.getPacient()) &&
                Objects.equals(getContent(), review.getContent());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDoctor(), getPacient(), getContent(), getStarsNumber());
    }
}
