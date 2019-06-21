package com.project.raluca.dto;

import com.project.raluca.model.enums.Speciality;

public class SearchFilter {
    public String name;
    public boolean scoreRate;
    public boolean localization;
    public String speciality;


    public SearchFilter(String name, boolean scoreRate, boolean localization, String speciality) {
        this.name = name;
        this.scoreRate = scoreRate;
        this.localization = localization;
        this.speciality = speciality;
    }

    public SearchFilter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(boolean scoreRate) {
        this.scoreRate = scoreRate;
    }

    public boolean isLocalization() {
        return localization;
    }

    public void setLocalization(boolean localization) {
        this.localization = localization;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
