package com.project.raluca.service;

import com.project.raluca.dto.UserDTO;
import com.project.raluca.model.Authorities;
import com.project.raluca.model.Users;
import com.project.raluca.repository.AuthoritiesRepository;;
import com.project.raluca.repository.UserRepository;
import com.project.raluca.utils.GenericMapper;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final AuthoritiesRepository roleRepository;
    private GenericMapper dtoHelper = new GenericMapper(Users.class, UserDTO.class);
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(final UserRepository userRepository, final AuthoritiesRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void bootstrap() {
        Users user = new Users();
        user.setUsername("test2");
        user.setPassword("{noop}12345");
        user.setEnabled(true);

        Authorities role = new Authorities();
        role.setUsername(user.getUsername());
        role.setAuthority("ROLE_DOCTOR");
        Users savedUser = userRepository.findByUsername(user.getUsername());
       if(savedUser == null){
           userRepository.save(user);
           log.info(String.format("created user : %s",user.getUsername()));
       }
       // Authorities rolec = roleRepository.save(role);
        //log.info("created role");

    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public UserDTO get(final Long id) {
        return (UserDTO)dtoHelper.convertToDTO(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")),UserDTO.class);
    }

    public Iterable<UserDTO> getAll() {
        return  dtoHelper.convertToDtoList(StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()),UserDTO.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public UserDTO create(final UserDTO user) {
        return (UserDTO) dtoHelper.convertToDTO(userRepository.save((Users)dtoHelper.convertToEntity(user,Users.class)),UserDTO.class);
    }

    public void update(final UserDTO user) {
       userRepository.save((Users)dtoHelper.convertToEntity(user,Users.class));
    }


    public void delete(UserDTO userDTO) {
        userRepository.delete((Users) dtoHelper.convertToEntity(userDTO,Users.class));
    }

    public UserDTO findByUsername(String username){
        return (UserDTO) dtoHelper.convertToDTO( userRepository.findByUsername(username),UserDTO.class);
    }

}
