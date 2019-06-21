package com.project.raluca.dto;

public class ReviewDTO {

    private int id;
    private UserDoctorDTO doctor;
    private UserPacientDTO pacient;
    private String content;
    private int starsNumber;

    public ReviewDTO(int id, UserDoctorDTO doctor, UserPacientDTO pacient, String content, int starsNumber) {
        this.id = id;
        this.doctor = doctor;
        this.pacient = pacient;
        this.content = content;
        this.starsNumber = starsNumber;
    }
    public ReviewDTO(){}

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

    public int getStarsNumber() {
        return starsNumber;
    }

    public void setStarsNumber(int starsNumber) {
        this.starsNumber = starsNumber;
    }
}
