package com.project.raluca.controller;

import com.project.raluca.dto.ReviewDTO;
import com.project.raluca.service.ReviewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "review"
)
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(final ReviewService reviewService){
        this.reviewService = reviewService;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "getAll"
    )
    public List<ReviewDTO> getAll() {
        return reviewService.getAll();
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "create"
    )
    public ResponseEntity<?> create(@RequestBody ReviewDTO reviewDTO) {
        reviewService.create(reviewDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "get/{id}"
    )
    public ReviewDTO getAppointment(@PathVariable final int id) {
        return reviewService.get(id);
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "delete/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable final int id) {
        reviewService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
