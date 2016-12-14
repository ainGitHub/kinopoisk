package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Film;
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

    public List<Rating> getAllRatings() {
        return (List<Rating>) ratingRepository.findAll();
    }

    public Film clearFilmRating(Film film) {
        List<Rating> ratings = (List<Rating>) ratingRepository.findAll();
        for (Rating r : ratings) {
            if (r.getFilm().getId().equals(film.getId())) {
                ratingRepository.delete(r.getId());
            }
        }
        film.setRating(null);
        film.setVoters(null);
        return film;
    }
}
