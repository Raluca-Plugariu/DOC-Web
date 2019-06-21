package com.project.raluca.controller;

import com.project.raluca.dto.GeoLocation;
import com.project.raluca.dto.SearchFilter;
import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.service.GeoIPv4;
import com.project.raluca.service.UserDoctorService;
import com.project.raluca.service.UserPacientService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@ViewScoped
@Named("searchController")
public class SearchController {


    @Autowired
    private UserDoctorService userDoctorService;

    @Autowired
    private UserPacientService userPacientService;

    public SearchFilter searchFilter = new SearchFilter();

    public List<UserDoctorDTO> doctorDTOS = new ArrayList<>();

    public Map<Double, UserDoctorDTO> doctorDistance = new TreeMap<>();

    private static final Logger log = LoggerFactory.getLogger(UserDoctorService.class);

    public double latitude;
    public double longitude;

    @PostConstruct
    public void init() throws IOException {
        getGeoLocation();
    }

    public void searchDoctors(){
        List<UserDoctorDTO> doctors = userDoctorService.searchForDoctors(this.searchFilter);
        if(searchFilter.isLocalization())
            doctors = sortByLocalization(doctors);
        this.doctorDTOS = doctors;
        this.searchFilter = new SearchFilter();


    }

    public UserDoctorService getUserDoctorService() {
        return userDoctorService;
    }

    public void setUserDoctorService(UserDoctorService userDoctorService) {
        this.userDoctorService = userDoctorService;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public List<UserDoctorDTO> getDoctorDTOS() {
        return doctorDTOS;
    }

    public void setDoctorDTOS(List<UserDoctorDTO> doctorDTOS) {
        this.doctorDTOS = doctorDTOS;
    }


    public String getGeoLocation() throws IOException {
        String address = getPublicIpAddress();
        GeoLocation location = GeoIPv4.getLocation(address);
        this.latitude = location.latitude;
        this.longitude = location.longitude;
        log.info("get geoLocation");
        return  location.getLatitude() +", "+ location.getLongitude();
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

    public List<UserDoctorDTO> sortByLocalization(List<UserDoctorDTO> doctorDTOS){
        Map<Double,UserDoctorDTO> map = new HashMap<>();

        for (UserDoctorDTO doc: doctorDTOS) {
          double distance =  getDistance(latitude, longitude, doc.getInstitution().getLocations().getLatitude(),doc.getInstitution().getLocations().getLongitude());
             map.put(distance,doc);
        }
        Map<Double, UserDoctorDTO> treeMap = new TreeMap<>(map);
        this.doctorDistance = treeMap;
        return  treeMap.values().stream().collect(Collectors.toList());
    }

    private double getDistance(final double lat1,final double lon1,final double lat2,final double lon2){
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
                dist = dist * 1.609344;

            return (dist);
        }
    }


}
