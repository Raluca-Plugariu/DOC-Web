package com.project.raluca.dao;

import com.project.raluca.model.Pacient;
import org.springframework.stereotype.Repository;

@Repository
public class UserPacientDAO extends AbstractDAO<Pacient>{
    protected UserPacientDAO(){
        super(Pacient.class);
    }
}
