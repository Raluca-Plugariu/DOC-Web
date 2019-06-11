package com.project.raluca.dto;

import com.project.raluca.model.enums.NotificationType;

import java.util.Date;

public class NotificationDTO {

    private int id;

    private UserDoctorDTO doctor;

    private UserPacientDTO pacient;

    private String content;

    private Date createDate;

    private NotificationType type;

    private Boolean isSeen;

    public NotificationDTO(int id, UserDoctorDTO doctor, UserPacientDTO pacient, String content, Date createDate, NotificationType type, Boolean isSeen) {
        this.id = id;
        this.doctor = doctor;
        this.pacient = pacient;
        this.content = content;
        this.createDate = createDate;
        this.type = type;
        this.isSeen = isSeen;
    }
    public NotificationDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(UserDoctorDTO doctor) {
        this.doctor = doctor;
    }

    public UserPacientDTO getPacient() {
        return pacient;
    }

    public void setPacient(UserPacientDTO pacient) {
        this.pacient = pacient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }
}
