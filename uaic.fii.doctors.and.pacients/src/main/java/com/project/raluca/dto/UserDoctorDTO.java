package com.project.raluca.dto;

import com.project.raluca.model.enums.AppointmentStatus;
import com.project.raluca.model.enums.Gender;
import com.project.raluca.model.enums.InsuranceHouse;
import com.project.raluca.model.enums.Range;
import com.project.raluca.model.enums.Speciality;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDoctorDTO {

    private int id;
    private UserDTO user;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String parafa;
    private String phone;
    private Range range;
    private InsuranceHouse insuranceHouse;
    private String workAdress;
    private double starRate;
    private Speciality speciality;
    private InstitutionDTO institution;
    private List<ReviewDTO> reviewList = new ArrayList<>();
    private List<UserPacientDTO> pacientsList= new ArrayList<>();
    private List<NotificationDTO> notificationList  = new ArrayList<>();
    private List<AppointmentDTO> appointments = new ArrayList<>();
    private List<BookableTimeDTO> bookableTimes = new ArrayList<>();

    public UserDoctorDTO(int id, UserDTO user, String firstName, String lastName, int age, Gender gender, String parafa, Range range, InsuranceHouse insuranceHouse,
                         String workAdress, int starRate, InstitutionDTO institution, List<ReviewDTO> reviewList,
                         List<UserPacientDTO> pacientsList,
                         List<NotificationDTO> notificationList) {
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.parafa = parafa;
        this.range = range;
        this.insuranceHouse = insuranceHouse;
        this.workAdress = workAdress;
        this.starRate = starRate;
        this.institution = institution;
        this.reviewList = reviewList;
        this.pacientsList = pacientsList;
        this.notificationList = notificationList;
    }

    public UserDoctorDTO() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String getWorkAdress() {
        return workAdress;
    }

    public void setWorkAdress(String workAdress) {
        this.workAdress = workAdress;
    }

    public int getStarRate() {
        return(int) starRate;
    }

    public void setStarRate(double starRate) {
        this.starRate = starRate;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public InstitutionDTO getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionDTO institution) {
        this.institution = institution;
    }

    public List<ReviewDTO> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDTO> reviewList) {
        this.reviewList = reviewList;
    }

    public List<UserPacientDTO> getPacientsList() {
        return pacientsList;
    }

    public void setPacientsList(List<UserPacientDTO> pacientsList) {
        this.pacientsList = pacientsList;
    }


    public List<NotificationDTO> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<NotificationDTO> notificationList) {
        this.notificationList = notificationList;
    }

    public List<AppointmentDTO> getAppointments() {
            return appointments;
    }

    public List<AppointmentDTO> getRequesedAppoiments(){
        return this.appointments.stream()
                .filter(appointmentDTO -> appointmentDTO.getAppointmentStatus().equals(AppointmentStatus.REQUESTED))
                .collect(Collectors.toList());
    }

    public void setAppointments(List<AppointmentDTO> appointments) {
        this.appointments = appointments;
    }

    public List<BookableTimeDTO> getBookableTimes() {
        return bookableTimes;
    }

    public void setBookableTimes(List<BookableTimeDTO> bookableTimes) {
        this.bookableTimes = bookableTimes;
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
        if (!(o instanceof UserDoctorDTO)) {
            return false;
        }
        UserDoctorDTO that = (UserDoctorDTO) o;
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
