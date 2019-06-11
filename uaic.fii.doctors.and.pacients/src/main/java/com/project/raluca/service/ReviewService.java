package com.project.raluca.service;

import com.project.raluca.repository.ReviewRepository;
import com.project.raluca.utils.GenericMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.project.raluca.dto.ReviewDTO;
import com.project.raluca.model.Review;
import com.project.raluca.utils.DTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private GenericMapper dtoHelper = new GenericMapper(Review.class, ReviewDTO.class);


    @Autowired
    public ReviewService(final ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }


    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public ReviewDTO get(final int id) {
        return(ReviewDTO)dtoHelper.convertToDTO(reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provided id did not find any object")),ReviewDTO.class);
    }

    public List<ReviewDTO> getAll() {
    return  dtoHelper.convertToDtoList(StreamSupport.stream(reviewRepository.findAll().spliterator(), false)
            .collect(Collectors.toList()),ReviewDTO.class);
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    IllegalArgumentException.class,
                    IllegalAccessException.class
            }
    )
    public void create(final ReviewDTO review) {
        reviewRepository.save((Review)dtoHelper.convertToEntity( review,Review.class));
    }

    public void delete(final int id) {
        reviewRepository.deleteById(id);
    }

}
