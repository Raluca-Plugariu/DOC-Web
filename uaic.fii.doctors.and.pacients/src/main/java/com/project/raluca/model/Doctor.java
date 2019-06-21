package com.project.raluca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.raluca.model.enums.Gender;
import com.project.raluca.model.enums.InsuranceHouse;
import com.project.raluca.model.enums.Range;
import com.project.raluca.model.enums.Speciality;

import java.util.Objects;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Doctor")
public class Doctor extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

    private String firstName;

    private String lastName;

    private int age;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String parafa;

    private Range range;

    @Enumerated(EnumType.STRING)
    private InsuranceHouse insuranceHouse;

    private int starRate;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="institution_id")
    private Institution institution;

    @OneToMany(mappedBy="doctor",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Review> reviewList;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "Doctor_Pacient",
            joinColumns = { @JoinColumn(name = "doctor_id") },
            inverseJoinColumns = { @JoinColumn(name = "pacient_id") }
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Pacient> pacientsList;


    @OneToMany(mappedBy="doctor",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Notification> notificationList;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BookableTime> bookableTimes;

    @OneToMany(mappedBy="doctor", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Appointment> appointments;

    public List<BookableTime> getBookableTimes() {
        return bookableTimes;
    }

    public void setBookableTimes(List<BookableTime> bookableTimes) {
        this.bookableTimes = bookableTimes;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getParafa() {
        return parafa;
    }

    public void setParafa(String parafa) {
        this.parafa = parafa;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public InsuranceHouse getInsuranceHouse() {
        return insuranceHouse;
    }

    public void setInsuranceHouse(InsuranceHouse insuranceHouse) {
        this.insuranceHouse = insuranceHouse;
    }

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Pacient> getPacientsList() {
        return pacientsList;
    }

    public void setPacientsList(List<Pacient> pacientsList) {
        this.pacientsList = pacientsList;
    }


    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Doctor)) {
            return false;
        }
        Doctor doctor = (Doctor) o;
        return getId() == doctor.getId() &&
                getAge() == doctor.getAge() &&
                getStarRate() == doctor.getStarRate() &&
                Objects.equals(getUser(), doctor.getUser()) &&
                Objects.equals(getFirstName(), doctor.getFirstName()) &&
                Objects.equals(getLastName(), doctor.getLastName()) &&
                getGender() == doctor.getGender() &&
                Objects.equals(getParafa(), doctor.getParafa()) &&
                getRange() == doctor.getRange() &&
                getInsuranceHouse() == doctor.getInsuranceHouse() &&
                Objects.equals(getSpeciality(), doctor.getSpeciality()) &&
                Objects.equals(getReviewList(), doctor.getReviewList()) &&
                Objects.equals(getPacientsList(), doctor.getPacientsList()) &&
                Objects.equals(getNotificationList(), doctor.getNotificationList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUser(), getFirstName(), getLastName(), getAge(), getGender(), getParafa(), getRange(), getInsuranceHouse(), getStarRate(), getSpeciality(), getReviewList(), getPacientsList(), getNotificationList());
    }
}
