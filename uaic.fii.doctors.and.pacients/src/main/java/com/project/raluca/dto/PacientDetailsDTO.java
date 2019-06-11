package com.project.raluca.dto;

public class PacientDetailsDTO {
    private int id;
    private String cnp;
    private String serie;
    private String number;
    private AddressDTO adresa;
    private String diagnostic;

    public PacientDetailsDTO(int id, String cnp, String serie, String number, AddressDTO adresa) {
        this.id = id;
        this.cnp = cnp;
        this.serie = serie;
        this.number = number;
        this.adresa = adresa;
    }
    public PacientDetailsDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AddressDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AddressDTO adresa) {
        this.adresa = adresa;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }
}
