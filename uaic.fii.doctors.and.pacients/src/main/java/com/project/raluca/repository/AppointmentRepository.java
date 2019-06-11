package com.project.raluca.repository;

import com.project.raluca.model.Appointment;
import com.project.raluca.model.enums.AppointmentStatus;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    Appointment findByAppointmentStatus(AppointmentStatus status);
    Appointment findByTitleAndAndDoctor_Id(String title, int doctor_id);
}
