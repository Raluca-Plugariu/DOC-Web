package com.project.raluca.controller;

import com.project.raluca.dto.AddressDTO;
import com.project.raluca.dto.AppointmentDTO;
import com.project.raluca.dto.GeoLocation;
import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.dto.UserPacientDTO;
import com.project.raluca.service.GeoIPv4;
import com.project.raluca.service.UserDoctorService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Named("userDoctorController")
public class UserDoctorController {

    @Autowired
    private UserDoctorService userDoctorService;

    public UserDoctorDTO userDoctorDTO;

    private static final Logger log = LoggerFactory.getLogger(UserDoctorService.class);



    public List<UserDoctorDTO> getAll() {
        List<UserDoctorDTO> list = userDoctorService.getAll();
        return list;
    }



    public ResponseEntity<?> create(UserDoctorDTO doctorDTO) {
        userDoctorService.create(doctorDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public UserDoctorDTO getDoctor(final int id) {
        return userDoctorService.get(id);
    }



    public UserDoctorDTO update(UserDoctorDTO doctorDTO) {
        return  userDoctorService.update(doctorDTO);
}


    public ResponseEntity<?> delete(final int id) {
        userDoctorService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public UserDoctorDTO acceptAppointment(int pacientId, int doctorId, AppointmentDTO appointmentDTO){
        return userDoctorService.acceptAppointment(pacientId,doctorId,appointmentDTO);
    }

    public UserDoctorDTO getCurrentUserDoctor()
    {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return userDoctorService.findDoctorByUsername(authentication.getName());
    }

    public UserDoctorDTO getUserDoctorDTO(){
       return this.userDoctorDTO;
    }

    public void setCurrentUserDoctor(){
        this.userDoctorDTO = getCurrentUserDoctor();
        AddressDTO addressDTO = userDoctorDTO.getInstitution().getLocations();
        if(addressDTO != null) {
            this.userDoctorDTO.setWorkAdress(Optional.ofNullable(addressDTO.getCountry()).orElse(" ") + " "
                    + Optional.ofNullable(addressDTO.getCity()).orElse(" ") + " "
                    + Optional.ofNullable(addressDTO.getStreet()).orElse(" ") + " "
                    + Optional.ofNullable(addressDTO.getNumber()).orElse(" "));
        }
        this.userDoctorDTO.setStarRate(userDoctorService.getStarRate(this.userDoctorDTO));
        log.info("setcurrentdoctor");
      //  this.allNotifications = getNotifications();
    }

    public List<AppointmentDTO> getPacientsForToday() throws IOException {
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
       return userDoctorDTO.getAppointments().stream()
               .filter(appointmentDTO -> appointmentDTO.getDate().getStartTime().getDayOfWeek().equals(LocalDate.now().getDayOfWeek()))
               .collect(Collectors.toList());

}

public GeoLocation getGeoLocation() throws IOException {
        String address = getPublicIpAddress();
    GeoLocation location = GeoIPv4.getLocation(address);
    log.info("get geoLocation");
    return  location;


}

    private String getPublicIpAddress() {
        String ip = "";
        try {

            URL whatismyip;

            whatismyip = new URL("http://checkip.amazonaws.com");

            BufferedReader in = null;

            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));

            ip = in.readLine();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ip;
    }

}
