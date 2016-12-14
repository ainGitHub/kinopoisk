package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Film;
import ru.dz.entity.Rating;
import ru.dz.entity.UserInfo;
import ru.dz.repository.FilmRepository;
import ru.dz.repository.RatingRepository;

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

    public List<Film> findAllAddedFilms() {
        return filmRepository.findAllAdded();
    }

    public List<Film> findAllDeletedFilms() {
        return filmRepository.findAllDeleted();
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film findFilmById(Long id) {
        return filmRepository.findFilmById(id);
    }

    public void addFilm(Film film) {
        filmRepository.save(film);
    }

    public boolean isUserAlreadyVoted(UserInfo userInfo, Film film) {
        List<Rating> ratings = (List<Rating>) ratingRepository.findAll();
        for (Rating r : ratings) {
            if (r.getFilm().equals(film) &&
                    r.getUserInfo().equals(userInfo)) {
                return true;
            }
        }

        return false;
    }

    public void addAll(List<Film> films) {
        filmRepository.save(films);
    }

    public void updateAll(List<Film> films) {
        filmRepository.save(films);
    }

    public List<Film> findFilmByGenre(Long genre) {
        return filmRepository.findFilmByGenre(genre);
    }


    /**
     * Подсчитывет и проставляет фильму рейтинг
     * округляет до десятых
     *
     * @param rating
     * @param film
     */
    public void setRatingToFilm(int rating, Film film) {
        Double newRating = ((film.getRating() * film.getVoters() + rating)) / (film.getVoters() + 1);

        int d = (int) (Math.round(newRating * 10));
        newRating = (double) d / 10;

        film.setVoters(film.getVoters() + 1);
        film.setRating(newRating);
        filmRepository.save(film);
    }

    public void deleteFilm(Long id) {
        filmRepository.delete(id);
    }
}
