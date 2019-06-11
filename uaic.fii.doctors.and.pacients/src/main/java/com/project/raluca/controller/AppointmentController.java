package com.project.raluca.controller;

import com.project.raluca.dto.AppointmentDTO;
import com.project.raluca.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "appointment"
)
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(final AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }
    @RequestMapping(
            method = RequestMethod.GET,
            path = "getAll"
    )
    public List<AppointmentDTO> getAll() {
        return appointmentService.getAll();
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "create"
    )
    public ResponseEntity<?> create(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.create(appointmentDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "get/{id}"
    )
    public AppointmentDTO getAppointment(@PathVariable final int id) {
        return appointmentService.get(id);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            path = "update"
    )
    public ResponseEntity<?> update( @RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.update(appointmentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "delete/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable final int id) {
        appointmentService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
