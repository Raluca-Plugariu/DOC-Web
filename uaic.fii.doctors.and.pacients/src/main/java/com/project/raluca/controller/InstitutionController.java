package com.project.raluca.controller;

import com.project.raluca.dto.InstitutionDTO;
import com.project.raluca.service.InstitutionService;
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
        path = "institution"
)
public class InstitutionController {
    private final InstitutionService institutionService;

    @Autowired
    public InstitutionController(final InstitutionService institutionService){
        this.institutionService = institutionService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "getAll"
    )
    public List<InstitutionDTO> getAll() {
        return institutionService.getAll();
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "create"
    )
    public ResponseEntity<?> create(@RequestBody InstitutionDTO institutionDTO) {
        institutionService.create(institutionDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "get/{id}"
    )
    public InstitutionDTO getInstitution(@PathVariable final int id) {
        return institutionService.get(id);
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "delete/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable final int id) {
        institutionService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}
