package com.project.raluca.service;
import com.project.raluca.dao.UserDoctorDAO;
import com.project.raluca.dto.AppointmentDTO;
import com.project.raluca.dto.NotificationDTO;
import com.project.raluca.dto.ReviewDTO;
import com.project.raluca.dto.UserPacientDTO;
import com.project.raluca.model.Address;
import com.project.raluca.model.Authorities;
import com.project.raluca.model.Institution;
import com.project.raluca.model.Notification;
import com.project.raluca.model.Pacient;
import com.project.raluca.model.Review;
import com.project.raluca.model.Users;
import com.project.raluca.model.enums.AppointmentStatus;
import com.project.raluca.model.enums.City;
import com.project.raluca.model.enums.Gender;
import com.project.raluca.model.enums.NotificationType;
import com.project.raluca.model.enums.Range;
import com.project.raluca.model.enums.Speciality;
import com.project.raluca.repository.AuthoritiesRepository;
import com.project.raluca.repository.ReviewRepository;
import com.project.raluca.repository.UserDoctorRepository;
import com.project.raluca.repository.UserPacientRepository;
import com.project.raluca.repository.UserRepository;
import com.project.raluca.utils.GeneralUtils;
import com.project.raluca.utils.GenericMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.model.Doctor;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDoctorService {
    private final UserDoctorRepository userDoctorRepository;
    private final UserDoctorDAO userDoctorDAO;
    private AppointmentService appointmentService;
    private UserPacientService userPacientService;
    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserDoctorService.class);
    private GenericMapper dtoHelper = new GenericMapper(Doctor.class, UserDoctorDTO.class);

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserPacientRepository userPacientRepository;

    @Autowired
    public UserDoctorService(final UserDoctorRepository userDoctorRepository, final UserDoctorDAO userDoctorDAO
            , AppointmentService appointmentService, UserPacientService userPacientService, UserRepository userRepository) {
        this.userDoctorRepository = userDoctorRepository;
        this.userDoctorDAO = userDoctorDAO;
        this.appointmentService = appointmentService;
        this.userPacientService = userPacientService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void bootstrap() {
        ///create doctor with user
//
        Users user = new Users();
        user.setUsername("raluca.plugariu");
        user.setPassword("{noop}123");
        user.setEnabled(true);

        Institution institution = new Institution();
        institution.setLocation(new Address("RO", City.Iasi, "str", "2"));
        institution.setName("arcadiatest");

        Review review = new Review();
        review.setStarsNumber(2);
        review.setContent("!!!!!??");

        Review review2 = new Review();
        review2.setContent("??????");
        review2.setStarsNumber(5);

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        reviewList.add(review2);

        Doctor userDoctor = new Doctor();
        userDoctor.setFirstName("test");
        userDoctor.setLastName("plugariu");
        userDoctor.setGender(Gender.FEMALE);
        userDoctor.setUser(user);
        userDoctor.setRange(Range.PROFESOR);
        userDoctor.setSpecialityList(Arrays.asList(Speciality.NEUROLOG, Speciality.STOMATOLOG));
        userDoctor.setInstitution(institution);
        // userDoctorDTO.setReviewList(reviewList);
        Pacient pacient = GeneralUtils.bootstrapDummyPacient("Ion","Dumbrava");
        userDoctor.setPacientsList(Arrays.asList(pacient));
        Doctor saved = userDoctorRepository.findByUserUsername(userDoctor.getUser().getUsername());

        if (saved == null) {
            userDoctorRepository.save(userDoctor);
        }
        Authorities authorities = new Authorities();
        authorities.setAuthority("ROLE_DOCTOR");
        authorities.setUsername("raluca.plugariu");

        Authorities savedAuthorities = authoritiesRepository.findByUsername(authorities.getUsername());

        if (savedAuthorities == null) {
            authoritiesRepository.save(authorities);
        }

        Authorities authority = new Authorities();
        authorities.setAuthority("ROLE_PACIENT");
        authorities.setUsername(pacient.getFirstName());

        Authorities savedAuthority = authoritiesRepository.findByUsername(authority.getUsername());

        if (savedAuthority == null) {
            authoritiesRepository.save(authority);
        }

        log.info("user + doctor");


    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public UserDoctorDTO create(final UserDoctorDTO doctor) {
        Doctor userDoctor = (Doctor) dtoHelper.convertToEntity(doctor, Doctor.class);
        return (UserDoctorDTO) dtoHelper.convertToDTO(userDoctorRepository.save(userDoctor), UserDoctorDTO.class);

    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public UserDoctorDTO get(final int id) {
        return (UserDoctorDTO) dtoHelper.convertToDTO(userDoctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")), UserDoctorDTO.class);
    }

    public List<UserDoctorDTO> getAll() {
        List<Doctor> list = StreamSupport.stream(userDoctorRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return dtoHelper.convertToDtoList(list, UserDoctorDTO.class);
    }

    public UserDoctorDTO update(final UserDoctorDTO doctor) {
        Optional<Doctor> dtoOptional = userDoctorRepository.findById(doctor.getId());
        if (dtoOptional.isPresent()) {
            return (UserDoctorDTO) dtoHelper.convertToDTO(userDoctorRepository.save((Doctor) dtoHelper.convertToEntity(doctor, Doctor.class)), UserDoctorDTO.class);
        }
        return doctor;
    }

    public void delete(final int id) {
        userDoctorRepository.deleteById(id);
    }

    public UserDoctorDTO rejectAppointment(UserPacientDTO userPacientDTO, UserDoctorDTO doctor, AppointmentDTO appointmentDTO) {
        appointmentDTO.setAppointmentStatus(AppointmentStatus.REJECTED);
        appointmentService.update(appointmentDTO);

        Notification notification = new Notification();
        notification.setType(NotificationType.APPOINTMENT_REJECTED);
        notification.setContent("You appointment request for doctor " + doctor.getUser().getUsername() + " has been rejected");
        notification.setCreateDate(new Date());
        notification.setSeen(Boolean.FALSE);
        userPacientDTO.getNotificationList().add(new NotificationDTO());

        userPacientDTO.getAppointmentList().removeIf(appointmentDTO1 -> appointmentDTO1.getId() == appointmentDTO.getId());
        userPacientService.update(userPacientDTO);

        doctor.getAppointments().removeIf(appointmentDTO1 -> appointmentDTO1.getId() == appointmentDTO.getId());
        return this.update(doctor);
    }

    public UserDoctorDTO acceptAppointment(int userPacientID, int doctorID, AppointmentDTO appointmentDTO) {
        appointmentDTO.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointmentService.update(appointmentDTO);
        UserDoctorDTO doctor = this.get(doctorID);
        Notification notification = new Notification();
        notification.setType(NotificationType.APPOINTMENT_REJECTED);
        notification.setContent("You appointment request for doctor " + doctor.getUser().getUsername() + " has been accepted");
        notification.setCreateDate(new Date());
        notification.setSeen(Boolean.FALSE);
        UserPacientDTO userPacientDTO = userPacientService.get(userPacientID);
        userPacientDTO.getNotificationList().add(new NotificationDTO());

        //delete from bookableTimes

        userPacientService.update(userPacientDTO);
        return this.update(doctor);
    }

    public UserDoctorDTO findDoctorByUsername(String username) {
        Doctor d =userDoctorRepository.findByUserUsername(username);
        return (UserDoctorDTO) dtoHelper.convertToDTO(d, UserDoctorDTO.class);

    }

    public double getStarRate(UserDoctorDTO userDoctorDTO) {
        double starRate = 0;
        for (ReviewDTO review : userDoctorDTO.getReviewList()) {
            starRate += review.getStarsNumber();
        }
        return starRate / userDoctorDTO.getReviewList().size();
    }
}
