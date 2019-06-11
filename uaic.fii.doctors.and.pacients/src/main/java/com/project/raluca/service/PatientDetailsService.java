package com.project.raluca.service;

import com.project.raluca.dto.PacientDetailsDTO;
import com.project.raluca.repository.PatientDetailsRepository;
import com.project.raluca.utils.GenericMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientDetailsService {
    private final PatientDetailsRepository patientDetailsRepository;
    private GenericMapper dtoHelper = new GenericMapper(com.project.raluca.model.PacientDetails.class, PacientDetailsDTO.class);


    @Autowired
    public PatientDetailsService(final PatientDetailsRepository patientDetailsRepository){
        this.patientDetailsRepository = patientDetailsRepository;
    }
    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public PacientDetailsDTO get(final int id) {
        return (PacientDetailsDTO)dtoHelper.convertToDTO(patientDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")), PacientDetailsDTO.class);
    }

    public List<PacientDetailsDTO> getAll() {
       return dtoHelper.convertToDtoList(StreamSupport.stream(patientDetailsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()), PacientDetailsDTO.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void create(final PacientDetailsDTO details) {
        patientDetailsRepository.save((com.project.raluca.model.PacientDetails)dtoHelper.convertToEntity(details, com.project.raluca.model.PacientDetails.class));
    }

    public void delete(final int id) {
        patientDetailsRepository.deleteById(id);
    }
}
