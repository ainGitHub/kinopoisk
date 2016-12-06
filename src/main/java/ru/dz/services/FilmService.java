package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Film;
import ru.dz.entity.Rating;
import ru.dz.entity.UserInfo;
import ru.dz.repository.FilmRepository;
import ru.dz.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ainur on 10.11.2016.
 */
@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    RatingRepository ratingRepository;

    public List<Film> findAllChangedFilms() {
        return filmRepository.findAllChanged();
    }


    public List<Film> findAllDeletedFilms() {
        return filmRepository.findAllDeleted();
    }

    public List<Film> findAll() {
        return (List<Film>) filmRepository.findAll();
    }

    public Film findFilmById(Long id) {
        return filmRepository.findFilmById(id);
    }

    public void addFilm(Film film) {
        filmRepository.save(film);
    }

    public boolean isUserAlreadyVoted(UserInfo userInfo, Film film) {
        boolean answer = false;
        ArrayList<Rating> ratings = (ArrayList<Rating>) ratingRepository.findAll();
        for (Rating r : ratings) {
            if (r.getFilm().equals(film) &&
                    r.getUserInfo().equals(userInfo)) {
                answer = true;
            }
        }
        return answer;
    }
}
