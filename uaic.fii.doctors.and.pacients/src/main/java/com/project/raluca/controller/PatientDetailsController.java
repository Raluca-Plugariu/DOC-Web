package com.project.raluca.controller;

import com.project.raluca.dto.PacientDetailsDTO;
import com.project.raluca.service.PatientDetailsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "pacientDetails"
)
public class PatientDetailsController {
    private final PatientDetailsService patientDetailsService;

    @Autowired
    public PatientDetailsController(final PatientDetailsService patientDetailsService){
        this.patientDetailsService = patientDetailsService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "getAll"
    )
    public List<PacientDetailsDTO> getAll() {
        return patientDetailsService.getAll();
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "create"
    )
    public ResponseEntity<?> create(@RequestBody PacientDetailsDTO detailsDTO) {
        patientDetailsService.create(detailsDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "get/{id}"
    )
    public PacientDetailsDTO get(@PathVariable final int id) {
        return patientDetailsService.get(id);
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "delete/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable final int id) {
        patientDetailsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
