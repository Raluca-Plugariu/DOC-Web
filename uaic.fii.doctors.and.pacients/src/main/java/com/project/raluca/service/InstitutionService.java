package com.project.raluca.service;

import com.project.raluca.repository.InstitutionRepository;
import com.project.raluca.utils.GenericMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.project.raluca.dto.InstitutionDTO;
import com.project.raluca.model.Institution;
import com.project.raluca.utils.DTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private GenericMapper dtoHelper = new GenericMapper(Institution.class, InstitutionDTO.class);


    @Autowired
    public InstitutionService(final InstitutionRepository institutionRepository){
        this.institutionRepository = institutionRepository;
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public InstitutionDTO get(final int id) {
        return (InstitutionDTO) dtoHelper.convertToDTO(institutionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")),InstitutionDTO.class);
    }

    public List<InstitutionDTO> getAll() {
      return  dtoHelper.convertToDtoList(StreamSupport.stream(institutionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()),InstitutionDTO.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void create(final InstitutionDTO institution) {
       institutionRepository.save((Institution)dtoHelper.convertToEntity(institution,Institution.class));
    }

    public void delete(final int id) {
        institutionRepository.deleteById(id);
    }


}
