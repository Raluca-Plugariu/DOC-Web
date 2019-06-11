package com.project.raluca.utils;

import com.project.raluca.model.Authorities;
import com.project.raluca.model.Pacient;
import com.project.raluca.model.PacientDetails;
import com.project.raluca.model.Users;
import com.project.raluca.model.enums.Gender;
import com.project.raluca.repository.AuthoritiesRepository;
import java.util.Calendar;
import com.project.raluca.dto.BookableTimeDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;


public class GeneralUtils {


    @Autowired
    private static AuthoritiesRepository authoritiesRepository;

    public static boolean compareCalendarDates(BookableTimeDTO bk1, BookableTimeDTO bk2){
//        Calendar obj1 = bk1.getDateIn();
//        Calendar obj2 = bk2.getDateIn();
//    if(obj1.get(Calendar.YEAR) == obj2.get(Calendar.YEAR) && obj1.get(Calendar.DAY_OF_MONTH) == obj2.get(Calendar.DAY_OF_MONTH)
//            && obj1.get(Calendar.MONTH)==obj2.get(Calendar.MONTH) && obj1.get(Calendar.HOUR)==obj2.get(Calendar.HOUR)
//            && obj1.get(Calendar.MINUTE)==obj2.get(Calendar.MINUTE))
//        return true;
//
    return false;
   }

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
}
