package com.project.raluca.utils;

import com.project.raluca.model.Address;
import com.project.raluca.model.Authorities;
import com.project.raluca.model.Doctor;
import com.project.raluca.model.Institution;
import com.project.raluca.model.Pacient;
import com.project.raluca.model.PacientDetails;
import com.project.raluca.model.Users;
import com.project.raluca.model.enums.City;
import com.project.raluca.model.enums.Gender;
import com.project.raluca.model.enums.Range;
import com.project.raluca.model.enums.Speciality;
import com.project.raluca.repository.AuthoritiesRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import com.project.raluca.dto.BookableTimeDTO;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;


public class GeneralUtils {


    @Autowired
    private static AuthoritiesRepository authoritiesRepository;

    public static Pacient bootstrapDummyPacient(final String firstName, final String lastName) {
        Pacient userPacient = new Pacient();
        userPacient.setFirstName(firstName);
        userPacient.setLastName(lastName);
        Gender gender = Gender.FEMALE;
        userPacient.setGender(gender);

        PacientDetails pacientDetails = new PacientDetails();
        pacientDetails.setCnp(UUID.randomUUID().toString());

        userPacient.setPacientDetails(pacientDetails);
        Users user = new Users(userPacient.getFirstName(), "{noop}qwerty", true);
        userPacient.setUser(user);

        return userPacient;
    }

    public static Doctor mockDoctor(){
        Doctor doctor = new Doctor();
        doctor.setFirstName("Andrei");
        doctor.setLastName("Popescu");
        doctor.setStarRate(3);


        Institution institution = new Institution();
        Address address = new Address("RO", City.Iasi, "str", "2");
        address.setLatitude(47.158034);
        address.setLongitude(27.561975);
        institution.setLocation(address);
        institution.setName("arcadiatest");

        doctor.setInstitution(institution);

        doctor.setGender(Gender.FEMALE);
        doctor.setRange(Range.PROFESOR);
        doctor.setSpeciality(Speciality.NEUROLOG);
        return doctor;

    }

    public static Date convertLocalDateToDate(LocalDate startTime) {
        return Date.from(startTime.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static LocalDate convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
    public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
