package com.project.raluca.service;

import com.project.raluca.dto.BookableTimeDTO;
import com.project.raluca.model.BookableTime;
import com.project.raluca.repository.BookableTimeRepository;
import com.project.raluca.utils.GeneralUtils;
import com.project.raluca.utils.GenericMapper;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.primefaces.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookableTimeService {

    private final BookableTimeRepository bookableTimeRepository;
    private GenericMapper dtoHelper = new GenericMapper(BookableTime.class, BookableTimeDTO.class);
    private static final Logger log = LoggerFactory.getLogger(UserDoctorService.class);
    public static final long HOUR = 3600*1000;

    @Autowired
    BookableTimeService(final BookableTimeRepository bookableTimeRepository){
        this.bookableTimeRepository = bookableTimeRepository;
    }


    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public BookableTimeDTO get(final int id) {
        BookableTimeDTO bookableTimeDTO = (BookableTimeDTO)dtoHelper.convertToDTO(bookableTimeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")),BookableTimeDTO.class);
        return bookableTimeDTO;
    }

    public List<BookableTimeDTO> getAll() {
        List<BookableTime>appointmentList = StreamSupport.stream(bookableTimeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return dtoHelper.convertToDtoList(appointmentList,BookableTimeDTO.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public BookableTimeDTO create(final BookableTimeDTO bookableTimeDTO) {
        return (BookableTimeDTO) dtoHelper.convertToDTO(bookableTimeRepository.
                save((BookableTime) dtoHelper.convertToEntity(bookableTimeDTO, BookableTime.class)), BookableTimeDTO.class);

    }
    public void delete(final int id) {
        bookableTimeRepository.deleteById(id);
    }

    public List<Date>calculateHoursBetweenDates(Date date1, Date date2){
        List<Date> dateList = new ArrayList<>();
        long hours = formatElapsedTime((date2.getTime() - date1.getTime())/1000);

        for(int i = 0; i<= (int) hours; i++){
            Date newDate = new Date(date1.getTime() + i* HOUR);

            dateList.add(newDate);
        }

        return dateList;

    }

    public static long formatElapsedTime (long seconds) {

       return TimeUnit.SECONDS.toHours(seconds);
    }

    public int getHour(Date date){
        LocalDateTime localDateTime = GeneralUtils.convertToLocalDateTimeViaInstant(date);
        return localDateTime.getHour();
    }


}
