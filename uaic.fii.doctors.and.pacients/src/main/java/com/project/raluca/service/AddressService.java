package com.project.raluca.service;

import com.project.raluca.dto.AddressDTO;
import com.project.raluca.model.Address;
import com.project.raluca.repository.AddressRepository;
import com.project.raluca.utils.DTOHelper;
import com.project.raluca.utils.GenericMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private GenericMapper dtoHelper = new GenericMapper(Address.class, AddressDTO.class);

    @Autowired
    public AddressService(final AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }


    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public AddressDTO get(final int id) {
        AddressDTO appointmentDTO = (AddressDTO)dtoHelper.convertToDTO(addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")),AddressDTO.class);
        return appointmentDTO;
    }

    public List<AddressDTO> getAll() {
        List<Address>appointmentList = StreamSupport.stream(addressRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return dtoHelper.convertToDtoList(appointmentList,AddressDTO.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void create(final AddressDTO appointment) {
        addressRepository.save((Address)dtoHelper.convertToEntity(appointment, Address.class));
    }

    public void delete(final int id) {
        addressRepository.deleteById(id);
    }

    public void update(final int id, final AddressDTO appointmentDTO) {
        addressRepository.save((Address) dtoHelper.convertToEntity(appointmentDTO,Address.class));
    }

}
