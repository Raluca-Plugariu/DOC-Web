package com.project.raluca.dto;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import org.omg.CORBA.DATA_CONVERSION;


public class BookableTimeDTO {
    private int id;
    private LocalDate startTime;
    private LocalDate endTime;
    private UserDoctorDTO userDoctorDTO;

    public BookableTimeDTO(int id,LocalDate startTime, LocalDate endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;

    }
    public BookableTimeDTO(){}

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDoctorDTO getUserDoctorDTO() {
        return userDoctorDTO;
    }

    public void setUserDoctorDTO(UserDoctorDTO userDoctorDTO) {
        this.userDoctorDTO = userDoctorDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookableTimeDTO)) {
            return false;
        }
        BookableTimeDTO that = (BookableTimeDTO) o;
        return getId() == that.getId() &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getStartTime(), getEndTime());
    }
}
