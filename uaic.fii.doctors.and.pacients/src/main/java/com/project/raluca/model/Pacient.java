package com.project.raluca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.raluca.model.enums.Gender;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Pacient")
@JsonIgnoreProperties("pacientsList")
public class Pacient extends AbstractEntity implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

    private String firstName;

    private String lastName;

    private int age;

    private Gender gender;

    @OneToMany(mappedBy="pacient",cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Notification> notificationList;

    @OneToMany(mappedBy="pacient",cascade = CascadeType.ALL,orphanRemoval = true,fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Appointment> appointmentList;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE}, mappedBy = "pacientsList", fetch = FetchType.LAZY)
    private List<Doctor> doctorsList;

    @OneToMany(mappedBy="pacient", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},orphanRemoval = true,fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Review> reviewList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pacientDetails_id")
    private PacientDetails pacientDetails;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Doctor> getDoctorsList() {
        return doctorsList;
    }

    public void setDoctorsList(List<Doctor> doctorsList) {
        this.doctorsList = doctorsList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public PacientDetails getPacientDetails() {
        return pacientDetails;
    }

    public void setPacientDetails(PacientDetails pacientDetails) {
        this.pacientDetails = pacientDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pacient)) {
            return false;
        }
        Pacient pacient = (Pacient) o;
        return getId() == pacient.getId() &&
                getAge() == pacient.getAge() &&
                Objects.equals(getUser(), pacient.getUser()) &&
                Objects.equals(getFirstName(), pacient.getFirstName()) &&
                Objects.equals(getLastName(), pacient.getLastName()) &&
                getGender() == pacient.getGender() &&
                Objects.equals(getNotificationList(), pacient.getNotificationList()) &&
                Objects.equals(getAppointmentList(), pacient.getAppointmentList()) &&
                Objects.equals(getDoctorsList(), pacient.getDoctorsList()) &&
                Objects.equals(getReviewList(), pacient.getReviewList()) &&
                Objects.equals(getPacientDetails(), pacient.getPacientDetails());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUser(), getFirstName(), getLastName(), getAge(), getGender(), getNotificationList(), getAppointmentList(), getDoctorsList(), getReviewList(), getPacientDetails());
    }
}
