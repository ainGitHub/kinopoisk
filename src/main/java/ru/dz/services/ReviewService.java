package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Review;
import ru.dz.repository.ReviewRepository;

import java.util.List;

/**
 * Created by Admin on 01.12.2016.
 */

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public void addReview(Review review){
        reviewRepository.save(review);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id){
        return reviewRepository.findOne(id);
    }

    public void deleteReviewById(Long id){
        reviewRepository.delete(id);
    }
}
