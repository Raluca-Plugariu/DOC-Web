package com.project.raluca.controller;


import com.project.raluca.dto.AddressDTO;
import com.project.raluca.dto.AppointmentDTO;
import com.project.raluca.dto.AppointmentRequestDTO;
import com.project.raluca.dto.BookableTimeDTO;
import com.project.raluca.dto.SearchFilter;
import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.dto.UserPacientDTO;
import com.project.raluca.model.enums.AppointmentStatus;
import com.project.raluca.model.enums.Speciality;
import com.project.raluca.service.AppointmentService;
import com.project.raluca.service.BookableTimeService;
import com.project.raluca.service.UserDoctorService;
import com.project.raluca.service.UserPacientService;
import com.project.raluca.utils.GeneralUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@ViewScoped
@Named("userPacientController")
public class UserPacientController {

    @Autowired
    private  UserPacientService userPacientService;

    @Autowired
    private UserDoctorService userDoctorService;

    @Autowired
    private BookableTimeService bookableTimeService;

    @Autowired
    private AppointmentService appointmentService;


    public  UserDoctorDTO userDoctorDTO;

    public UserPacientDTO userPacientDTO;

    public SearchFilter searchFilter = new SearchFilter();

    public List<UserDoctorDTO> doctorDTOS = new ArrayList<>();

    public Date dateForAppointment;

    public String comment;

    int doctorId;

    List<Date> bookableHours = new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(UserPacientService.class);


    public void init(){
        this.userDoctorDTO = userDoctorService.get(doctorId);

    }


    public Iterable<UserPacientDTO> getAll() {
        return userPacientService.getAll();
    }




    public ResponseEntity<?> create(@RequestBody UserPacientDTO pacientDTO) {
        userPacientService.create(pacientDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public UserPacientDTO getPacient(@PathVariable final int id) {
        return userPacientService.get(id);
    }



    public ResponseEntity<?> update(@PathVariable final int id, @RequestBody UserPacientDTO pacientDTO) {
        userPacientService.update(pacientDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public ResponseEntity<?> delete(@PathVariable final int id) {
        userPacientService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public UserDoctorDTO getUserDoctorDTO() {
        return userDoctorDTO;
    }

    public void setUserDoctorDTO(UserDoctorDTO userDoctorDTO) {
        this.userDoctorDTO = userDoctorDTO;
    }



    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserPacientDTO getUserPacientDTO() {
        return userPacientDTO;
    }

    public void setUserPacientDTO(UserPacientDTO userPacientDTO) {
        this.userPacientDTO = userPacientDTO;
    }

    public List<UserDoctorDTO> getDoctorDTOS() {
        return doctorDTOS;
    }

    public void setDoctorDTOS(List<UserDoctorDTO> doctorDTOS) {
        this.doctorDTOS = doctorDTOS;
    }

    public Date getDateForAppointment() {
        return dateForAppointment;
    }

    public void setDateForAppointment(Date dateForAppointment) {
        this.dateForAppointment = dateForAppointment;
    }

    public void setUserDoctor(int id){
        UserDoctorDTO doctorDTO = userDoctorService.get(id);
        this.userDoctorDTO = doctorDTO;
    }



    public void setCurrentUserPacient(){
        this.userPacientDTO = userPacientService.getCurrentUserPacient();

    }

    public List<Date> getBookableHours() {
        return bookableHours;
    }

    public void setBookableHours(List<Date> bookableHours) {
        this.bookableHours = bookableHours;
    }

    public List<String> getAllDoctors(){
        List<String> doctors = new ArrayList<>();
        userDoctorService.getAll().forEach(userDoctorDTO1 -> doctors.add(userDoctorDTO1.toString()));
        return doctors;

    }

    public List<String> getAllSpecialisation(){
       Speciality[] specialities =  Speciality.values();
       List<String> specialitiesNames = new ArrayList<>();
       for( int i = 0 ; i<specialities.length; i++){
           specialitiesNames.add(specialities[i].name());
       }
       return specialitiesNames;
    }

    public void searchBookableTimes(){
        init();
        if(this.dateForAppointment !=null) {
            List<BookableTimeDTO> bookableTimeDTOList = this.userDoctorDTO.getBookableTimes();
            for (BookableTimeDTO bookableTimeDTO : bookableTimeDTOList) {
                LocalDate localDate = GeneralUtils.convertToLocalDateTime(dateForAppointment);
                LocalDate startTime = GeneralUtils.convertToLocalDateTime(bookableTimeDTO.getStartTime());
                if (localDate.getDayOfMonth() == startTime.getDayOfMonth()){
                    if(!this.bookableHours.contains(bookableTimeDTO.getStartTime()))
                        this.bookableHours.add(bookableTimeDTO.getStartTime());
                }
            }
        }
    }

    public int getHour(Date date){
        LocalDateTime localDateTime = GeneralUtils.convertToLocalDateTimeViaInstant(date);
        return localDateTime.getHour();
    }

    public void requestAppointment(Date date){
        BookableTimeDTO bookableTimeDTO = new BookableTimeDTO();
        bookableTimeDTO.setStartTime(date);
        bookableTimeDTO.setUserDoctorDTO(this.userDoctorDTO);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoctor(this.userDoctorDTO);
        appointmentDTO.setComment(this.comment);
        appointmentDTO.setPacient(this.userPacientDTO);
        appointmentDTO.setAppointmentStatus(AppointmentStatus.REQUESTED);
         appointmentService.create(appointmentDTO);

    }


}
