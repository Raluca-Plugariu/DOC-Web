package com.project.raluca.controller;

import com.project.raluca.dto.AppointmentDTO;
import com.project.raluca.dto.BookableTimeDTO;
import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.dto.UserPacientDTO;
import com.project.raluca.service.AppointmentService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;


@ViewScoped
@Named("scheduleView")
public class ScheduleController {

    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private String[] selectedPacients;

    private ScheduleEvent event = new DefaultScheduleEvent();

    private BookableTimeDTO evtime = new BookableTimeDTO();

    private AppointmentDTO appointmentDTO = new AppointmentDTO();


    @Autowired
    UserDoctorController userDoctorController;

    @Autowired
    AppointmentService appointmentService;

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        this.appointmentDTO.setPacient(new UserPacientDTO());
        UserDoctorDTO userDoctorDTO = userDoctorController.getCurrentUserDoctor();
        if(userDoctorDTO != null) {
            for (AppointmentDTO appointmentDTO : userDoctorDTO.getAppointments()) {
                eventModel.addEvent(new DefaultScheduleEvent(appointmentDTO.getTitle(),
                       appointmentDTO.getDate().getStartTime(),
                       appointmentDTO.getDate().getEndTime()));

            }
        }
        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }
        };
    }

    private Date convertLocalDateToDate(LocalDate startTime) {
        return Date.from(startTime.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    private LocalDate convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }


    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {

        if (event.getId() == null) {
            List<UserPacientDTO> pacientDTOS = new ArrayList<>();
            for (String x : selectedPacients) {
                StringTokenizer st = new StringTokenizer(x, " ");
                int i = 0;
                String[] names = new String[10];
                while (st.hasMoreTokens()) {
                    names[i] = st.nextToken();
                    i++;
                }
                for (UserPacientDTO e : userDoctorController.getCurrentUserDoctor().getPacientsList()) {
                    if (names[0].equals(e.getFirstName()) && names[1].equals(e.getLastName()))
                        pacientDTOS.add(e);
                }
                if (pacientDTOS.size() > 0) {
                    appointmentDTO.setPacient(pacientDTOS.get(0));
                }
                BookableTimeDTO bookableTimeDTO = new BookableTimeDTO();
                bookableTimeDTO.setStartTime(event.getStartDate());
                bookableTimeDTO.setEndTime(event.getEndDate());
                appointmentDTO.setDate(bookableTimeDTO);
                appointmentService.createAppointment(appointmentDTO, userDoctorController.getCurrentUserDoctor().getId());
            }

            event = new DefaultScheduleEvent(appointmentDTO.getTitle(), event.getStartDate(),
                    event.getEndDate());
            eventModel.addEvent(event);
            event = new DefaultScheduleEvent();
//            evtime = new BookableTimeDT
          appointmentDTO = new AppointmentDTO();
        }
    }
    public void onEventSelect(SelectEvent selectEvent)
    {
        this.event = (ScheduleEvent) selectEvent.getObject();
//        this.appointmentDTO.setTitle(event.getTitle());
//        this.evtime.setEndTime(event.getEndDate());
//        this.evtime.setStartTime(event.getStartDate());
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        AppointmentDTO appointmentDTO = appointmentService.getEventByDoctorID(event.getScheduleEvent().getTitle(),userDoctorController.getCurrentUserDoctor().getId());
        appointmentDTO.getDate().setStartTime(event.getScheduleEvent().getStartDate());
        appointmentDTO.getDate().setEndTime(event.getScheduleEvent().getEndDate());
        appointmentService.update(appointmentDTO);
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        AppointmentDTO appointmentDTO = appointmentService.getEventByDoctorID(event.getScheduleEvent().getTitle(),userDoctorController.getCurrentUserDoctor().getId());
        appointmentDTO.getDate().setStartTime(event.getScheduleEvent().getStartDate());
        appointmentDTO.getDate().setEndTime(event.getScheduleEvent().getEndDate());
        appointmentService.update(appointmentDTO);
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public AppointmentDTO getAppointmentDTO() {
        return appointmentDTO;
    }

    public void setAppointmentDTO(AppointmentDTO appointmentDTO) {
        this.appointmentDTO = appointmentDTO;
    }

    public String[] getSelectedPacients() {
        return selectedPacients;
    }

    public void setSelectedPacients(String[] selectedPacients) {
        this.selectedPacients = selectedPacients;
    }


    public BookableTimeDTO getEvtime() {
        return evtime;
    }

    public void setEvtime(BookableTimeDTO evtime) {
        this.evtime = evtime;
    }

    public List<UserPacientDTO> getAllPacients(){
        return userDoctorController.getCurrentUserDoctor().getPacientsList();
    }



    }

