package com.project.raluca.service;

import com.project.raluca.dao.UserDoctorDAO;
import com.project.raluca.dto.AppointmentDTO;
import com.project.raluca.dto.BookableTimeDTO;
import com.project.raluca.model.Appointment;
import com.project.raluca.model.Notification;
import com.project.raluca.model.Doctor;
import com.project.raluca.model.enums.AppointmentStatus;
import com.project.raluca.model.enums.NotificationType;
import com.project.raluca.repository.UserDoctorRepository;
import com.project.raluca.repository.UserPacientRepository;
import com.project.raluca.utils.GeneralUtils;
import com.project.raluca.utils.GenericMapper;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.project.raluca.dao.UserPacientDAO;
import com.project.raluca.dto.UserPacientDTO;
import com.project.raluca.model.Pacient;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserPacientService {

    private final UserPacientRepository userPacientRepository;
    private final UserPacientDAO userPacientDAO;
    private AppointmentService appointmentService;
    private UserDoctorRepository userDoctorRepository;
    private UserDoctorDAO userDoctorDAO;
    private GenericMapper dtoHelper = new GenericMapper(Pacient.class, UserPacientDTO.class);
    private static final Logger log = LoggerFactory.getLogger(UserDoctorService.class);

    @Autowired
    public UserPacientService(final UserPacientRepository userPacientRepository, final UserPacientDAO userPacientDAO,
                              AppointmentService appointmentService, UserDoctorRepository userDoctorRepository, UserDoctorDAO userDoctorDAO) {
        this.userPacientRepository = userPacientRepository;
        this.userPacientDAO = userPacientDAO;
        this.appointmentService = appointmentService;
        this.userDoctorRepository = userDoctorRepository;
        this.userDoctorDAO = userDoctorDAO;
    }

    @PostConstruct
    public void bootstrap() {
        Pacient userPacient = GeneralUtils.bootstrapDummyPacient("Mircea","Ionescu");
        Pacient savedPacient = userPacientRepository.findByFirstNameAndLastName(userPacient.getFirstName(), userPacient.getLastName());
        if(savedPacient == null){
            userPacientRepository.save(userPacient);
        }
        log.info("insert");

    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public UserPacientDTO get(final int id) {
        return (UserPacientDTO) dtoHelper.convertToDTO(userPacientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")), UserPacientDTO.class);
    }

    public Iterable<UserPacientDTO> getAll() {
        return dtoHelper.convertToDtoList(StreamSupport.stream(userPacientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList()), Pacient.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public UserPacientDTO create(final UserPacientDTO product) {
        Pacient pacient = userPacientRepository.save((Pacient) dtoHelper.convertToEntity(product, Pacient.class));
        return (UserPacientDTO) dtoHelper.convertToDTO(pacient, UserPacientDTO.class);

    }

    public void update(final UserPacientDTO pacientDTO) {
        userPacientDAO.update((Pacient) dtoHelper.convertToEntity(pacientDTO, Pacient.class));
    }


    public void delete(final int id) {
        userPacientRepository.deleteById(id);
    }

    public UserPacientDTO requestAppointment(int userPacientId, int doctorId, BookableTimeDTO time) {
        UserPacientDTO userPacientDTO = (UserPacientDTO) dtoHelper.convertToDTO(userPacientRepository.findById(userPacientId).get(), UserPacientDTO.class);
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setAppointmentStatus(AppointmentStatus.REQUESTED);
        appointment.setDate(time);
        appointment.setPacient(get(userPacientDTO.getId()));

        Notification notification = new Notification();
        notification.setType(NotificationType.APPOINTMENT_REQUEST);
        notification.setContent("You have an appointment request from patient " + userPacientDTO.getFirstName());
        notification.setCreateDate(new Date());
        notification.setSeen(Boolean.FALSE);

        Doctor userDoctor = userDoctorRepository.findById(doctorId).get();
       // userDoctor.getSchedule().getAppointments().add((Appointment) dtoHelper.convertToEntity(appointment, Pacient.class));
        userDoctor.getNotificationList().add(notification);
        userDoctorDAO.update(userDoctor);

        Pacient userPacient = userPacientRepository.findById(userPacientDTO.getId()).get();
        userPacient.getAppointmentList().add((Appointment) dtoHelper.convertToEntity(appointment, Pacient.class));
        userPacientDAO.update(userPacient);

        return (UserPacientDTO) dtoHelper.convertToDTO(userPacient, UserPacientDTO.class);
    }

}
