package com.project.raluca.controller;


import com.project.raluca.dto.AppointmentRequestDTO;
import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.dto.UserPacientDTO;
import com.project.raluca.service.UserDoctorService;
import com.project.raluca.service.UserPacientService;
import java.io.IOException;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@RequestScope
@Named("userPacientController")
public class UserPacientController {

    @Autowired
    private  UserPacientService userPacientService;

    @Autowired
    private UserDoctorService userDoctorService;

    public UserDoctorDTO userDoctorDTO;

    private static final Logger log = LoggerFactory.getLogger(UserPacientService.class);




    public Iterable<UserPacientDTO> getAll() {
        return userPacientService.getAll();
    }




    public ResponseEntity<?> create(@RequestBody UserPacientDTO pacientDTO) {
        userPacientService.create(pacientDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public UserPacientDTO getPacient(@PathVariable final int id) {
        return userPacientService.get(id);
    }



    public ResponseEntity<?> update(@PathVariable final int id, @RequestBody UserPacientDTO pacientDTO) {
        userPacientService.update(pacientDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public ResponseEntity<?> delete(@PathVariable final int id) {
        userPacientService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public UserDoctorDTO getUserDoctorDTO() {
        return userDoctorDTO;
    }

    public void setUserDoctorDTO(UserDoctorDTO userDoctorDTO) {
        this.userDoctorDTO = userDoctorDTO;
    }

    public UserPacientDTO requestAppointment(AppointmentRequestDTO appointmentRequestDTO){
        log.info("requested!!!!");
        return userPacientService.requestAppointment(appointmentRequestDTO.getAppointmentDTO().getPacient().getId(),
                appointmentRequestDTO.getDoctorId(),appointmentRequestDTO.getAppointmentDTO().getDate());
    }

    public void setUserDoctor(int id){
        UserDoctorDTO doctorDTO = userDoctorService.get(id);
        this.userDoctorDTO = doctorDTO;
    }

}
