package com.project.raluca.dto;

import java.util.List;

public class InstitutionDTO {

    private int id;
    private String name;
    private AddressDTO locations;
    private List<UserDoctorDTO> doctorList;

    public InstitutionDTO(int id, String name, AddressDTO location, List<UserDoctorDTO> doctorList) {
        this.id = id;
        this.name = name;
        this.locations = location;
        this.doctorList = doctorList;
    }
    public InstitutionDTO(){}

    public int getInstitution_id() {
        return id;
    }

    public void setInstitution_id(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getLocations() {
        return locations;
    }

    public void setLocations(AddressDTO locations) {
        this.locations = locations;
    }

    public List<UserDoctorDTO> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<UserDoctorDTO> doctorList) {
        this.doctorList = doctorList;
    }
}
