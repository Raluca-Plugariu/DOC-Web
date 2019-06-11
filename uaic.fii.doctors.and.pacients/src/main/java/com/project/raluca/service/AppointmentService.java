package com.project.raluca.service;

import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.repository.AppointmentRepository;
import com.project.raluca.dto.AppointmentDTO;
import com.project.raluca.model.Appointment;
import com.project.raluca.utils.DTOHelper;
import com.project.raluca.utils.GenericMapper;
import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private GenericMapper dtoHelper = new GenericMapper(Appointment.class, AppointmentDTO.class);
    private static final Logger log = LoggerFactory.getLogger(UserDoctorService.class);


    @Autowired
    UserDoctorService userDoctorService;

    @Autowired
    public AppointmentService(final AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

//    @PostConstruct
//    public void bootstrap() {
//        AppointmentDTO appointment = new AppointmentDTO();
//        appointment.setComment("second app");
//        appointment.setDate(new Date());
//        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
//        create(appointment);
//
//    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public AppointmentDTO get(final int id) {
        AppointmentDTO appointmentDTO = (AppointmentDTO)dtoHelper.convertToDTO(appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")),AppointmentDTO.class);
        return appointmentDTO;
    }

    public List<AppointmentDTO> getAll() {
         List<Appointment>appointmentList = StreamSupport.stream(appointmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return dtoHelper.convertToDtoList(appointmentList,AppointmentDTO.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public AppointmentDTO create(final AppointmentDTO appointment) {

      return (AppointmentDTO) dtoHelper.convertToDTO(appointmentRepository.
              save((Appointment) dtoHelper.convertToEntity(appointment,Appointment.class)),AppointmentDTO.class);
       // scheduleService.removeBookableTime(appointment);
    }

    public void delete(final int id) {
        appointmentRepository.deleteById(id);
    }


    public AppointmentDTO update(final AppointmentDTO appointmentDTO) {
        Optional<Appointment> dtoOptional = appointmentRepository.findById(appointmentDTO.getId());
        if(dtoOptional.isPresent()) {
            return  (AppointmentDTO)dtoHelper.convertToDTO(appointmentRepository.save((Appointment) dtoHelper.convertToEntity(appointmentDTO,Appointment.class)),AppointmentDTO.class);
        }
        return null;
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void createAppointment(final AppointmentDTO appointment, int doctorID) {
        UserDoctorDTO userDoctorDTO = userDoctorService.get(doctorID);
        if(userDoctorDTO!= null){
            appointment.setDoctor(userDoctorDTO);
            appointment.setComment("notes");
        }
        AppointmentDTO appointmentDTO = create(appointment);
      //  userDoctorDTO.getAppointments().add(appointment);

     //   userDoctorService.update(userDoctorDTO);

        log.info("Appointment saved!!");
    }

    public AppointmentDTO getEventByDoctorID(String title, int doctorId){
        Appointment appointment = appointmentRepository.findByTitleAndAndDoctor_Id(title,doctorId);
        return (AppointmentDTO) dtoHelper.convertToDTO(appointment,AppointmentDTO.class);
    }
}
