package com.project.raluca.repository;

import com.project.raluca.model.PacientDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDetailsRepository extends CrudRepository<PacientDetails, Integer> {
}
