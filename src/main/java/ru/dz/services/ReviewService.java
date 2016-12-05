package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Review;
import ru.dz.entity.UserInfo;
import ru.dz.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 01.12.2016.
 */

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    public List<Review> getAllReviewsByUser(UserInfo user) {
        List<Review> allReviews = reviewRepository.findAll();
        ArrayList<Review> userReviews = new ArrayList<>();
        for (Review r : allReviews) {
            if (r.getUserInfo().getId().equals(user.getId())) {
                userReviews.add(r);
            }
        }
        return userReviews;
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findOne(id);
    }

    public void deleteReviewById(Long id) {
        reviewRepository.delete(id);
    }
}
