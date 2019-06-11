package com.project.raluca.repository;

import com.project.raluca.model.Doctor;
import com.project.raluca.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByUserUsername(String username);

}
