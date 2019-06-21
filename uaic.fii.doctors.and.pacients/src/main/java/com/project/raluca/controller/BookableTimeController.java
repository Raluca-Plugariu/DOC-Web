package com.project.raluca.controller;

import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.service.BookableTimeService;
import com.project.raluca.service.UserDoctorService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@ViewScoped
@Named("timeController")
public class BookableTimeController {

    public Date dateForAppointment;

    public UserDoctorDTO userDoctorDTO;

    @Autowired
    BookableTimeService bookableTimeService;

    @Autowired
    UserDoctorService userDoctorService;


    public Date getDateForAppointment() {
        return dateForAppointment;
    }

    public void setDateForAppointment(Date dateForAppointment) {
        this.dateForAppointment = dateForAppointment;
    }

    public UserDoctorDTO getUserDoctorDTO() {
        return userDoctorDTO;
    }

    public void setUserDoctorDTO(UserDoctorDTO userDoctorDTO) {
        this.userDoctorDTO = userDoctorDTO;
    }

    public BookableTimeService getBookableTimeService() {
        return bookableTimeService;
    }

    public void setBookableTimeService(BookableTimeService bookableTimeService) {
        this.bookableTimeService = bookableTimeService;
    }



}
