package com.project.raluca.repository;

import com.project.raluca.model.Doctor;
import com.project.raluca.model.Pacient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPacientRepository extends CrudRepository<Pacient,Integer> {
    Pacient findByFirstNameAndLastName(String firstName,String lastName);

    Pacient findByUserUsername(String username);
}
