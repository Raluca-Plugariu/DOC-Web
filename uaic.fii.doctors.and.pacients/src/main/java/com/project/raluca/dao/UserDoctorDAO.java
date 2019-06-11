package com.project.raluca.dao;

import com.project.raluca.model.Doctor;
import org.springframework.stereotype.Repository;

@Repository
public class UserDoctorDAO extends AbstractDAO<Doctor> {
    protected  UserDoctorDAO(){
        super(Doctor.class);
    }


}
