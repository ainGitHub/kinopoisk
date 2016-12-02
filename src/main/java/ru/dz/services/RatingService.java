package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Rating;
import ru.dz.repository.RatingRepository;

import java.util.List;

/**
 * Created by Admin on 01.12.2016.
 */

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings(){
        return (List<Rating>) ratingRepository.findAll();
    }
}
