package com.project.raluca.dto;
import com.project.raluca.utils.GeneralUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import org.omg.CORBA.DATA_CONVERSION;


public class BookableTimeDTO {
    private int id;
    private Date startTime;
    private Date endTime;
    private UserDoctorDTO userDoctorDTO;

    public BookableTimeDTO(int id,Date startTime, Date endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;

    }
    public BookableTimeDTO(){}

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
