package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Film;
import ru.dz.entity.FilmGenre;
import ru.dz.entity.Genre;
import ru.dz.repository.FilmGenreRepository;
import ru.dz.repository.GenreRepository;

import java.util.List;

/**
 * Created by Admin on 14.12.2016.
 */

@Service
public class FilmGenreService {

    @Autowired
    FilmGenreRepository filmGenreRepository;

    @Autowired
    GenreRepository genreRepository;

    public void addGenreToFilm(Film film, Genre genre) {
        List<FilmGenre> filmGenres = filmGenreRepository.findAll();
        List<Genre> genres = genreRepository.getGenreByFilmId(film.getId());
        int j = 0;
        for (Genre g : genres) {
            if (g.getId().equals(genre.getId())) {
                j++;
            }
        }
        if (j == 0) {
            Long i = Long.valueOf(0);
            for (FilmGenre f : filmGenres) {
                if (f.getId() > i) {
                    i = f.getId();
                }
            }
            FilmGenre filmGenre = new FilmGenre();
            filmGenre.setFilm(film);
            filmGenre.setGenre(genre);
            filmGenre.setId(i + 1);
            filmGenreRepository.save(filmGenre);
        }
    }

    public void deleteFilmGenre(Film film, Genre genre) {
        List<FilmGenre> filmGenreList = film.getFilmsGenres();
        for (FilmGenre g : filmGenreList) {
            if (g.getGenre().getId().equals(genre.getId())) {
                filmGenreRepository.delete(g);
            }
        }
    }
}
