package com.project.raluca.controller;

import com.project.raluca.dto.ReviewDTO;
import com.project.raluca.dto.UserDoctorDTO;
import com.project.raluca.dto.UserPacientDTO;
import com.project.raluca.service.ReviewService;
import com.project.raluca.service.UserDoctorService;
import com.project.raluca.service.UserPacientService;
import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Named("reviewController")
public class ReviewController {

    @Autowired
    private  ReviewService reviewService;

    @Autowired
    private UserPacientService userPacientService;

    public ReviewDTO reviewDTO = new ReviewDTO();

    public ReviewDTO getReviewDTO() {
        return reviewDTO;
    }

    public void setReviewDTO(ReviewDTO reviewDTO) {
        this.reviewDTO = reviewDTO;
    }

    public List<ReviewDTO> getAll() {
        return reviewService.getAll();
    }


    public void create(ReviewDTO reviewDTO, UserDoctorDTO userDoctorDTO) {
        reviewDTO.setDoctor(userDoctorDTO);
//        Authentication authentication =
//                SecurityContextHolder.getContext().getAuthentication();
//        UserPacientDTO userPacientDTO1 =  userPacientService.findPacientByUsername(authentication.getName());

        reviewService.create(reviewDTO);
        this.reviewDTO = new ReviewDTO();
    }


    public ReviewDTO getAppointment(@PathVariable final int id) {
        return reviewService.get(id);
    }


    public ResponseEntity<?> delete(@PathVariable final int id) {
        reviewService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
