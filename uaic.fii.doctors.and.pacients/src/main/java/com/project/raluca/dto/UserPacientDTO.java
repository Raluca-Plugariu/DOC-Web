package com.project.raluca.dto;

import com.project.raluca.model.enums.Gender;

import java.util.List;
import java.util.Objects;

public class UserPacientDTO {

    private int id;

    private UserDTO user;

    private String firstName;

    private String lastName;

    private int age;

    private Gender gender;

    private List<NotificationDTO> notificationList;

    private List<AppointmentDTO> appointmentList;

    private List<UserDoctorDTO> doctorsList;

    private List<ReviewDTO> reviewList;

    private PacientDetailsDTO pacientDetails;


    public UserPacientDTO(int id, UserDTO user, String firstName, String lastName, int age, Gender gender, List<NotificationDTO> notificationList, List<AppointmentDTO> appointmentList, List<UserDoctorDTO> doctorsList, List<ReviewDTO> reviewList, PacientDetailsDTO pacientDetails) {
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.notificationList = notificationList;
        this.appointmentList = appointmentList;
        this.doctorsList = doctorsList;
        this.reviewList = reviewList;
        this.pacientDetails = pacientDetails;
    }
    public UserPacientDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
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

    public List<NotificationDTO> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<NotificationDTO> notificationList) {
        this.notificationList = notificationList;
    }

    public List<AppointmentDTO> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<AppointmentDTO> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<UserDoctorDTO> getDoctorsList() {
        return doctorsList;
    }

    public void setDoctorsList(List<UserDoctorDTO> doctorsList) {
        this.doctorsList = doctorsList;
    }

    public List<ReviewDTO> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDTO> reviewList) {
        this.reviewList = reviewList;
    }

    public PacientDetailsDTO getPacientDetails() {
        return pacientDetails;
    }

    public void setPacientDetails(PacientDetailsDTO pacientDetails) {
        this.pacientDetails = pacientDetails;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPacientDTO)) {
            return false;
        }
        UserPacientDTO that = (UserPacientDTO) o;
        return getId() == that.getId() &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUser(), getFirstName(), getLastName());
    }
}
