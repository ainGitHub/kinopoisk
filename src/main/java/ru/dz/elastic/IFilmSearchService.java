package ru.dz.elastic;

import org.springframework.data.domain.Pageable;
import ru.dz.entity.Film;
import ru.dz.entity.Movie;

import java.util.List;

/**
 * Created by ainur on 10.11.2016.
 */
public interface IFilmSearchService {
    void add(Film film);

    void delete(Long id);

    void update(Film film);

    void updateAll(List<Film> films);

    void deleteAll(List<Film> films);

    List<Movie> matchQuery(String q, Pageable page);

    List<Movie> matchPhraseQuery(String q);

    List<Movie> matchPhrasePrefixQuery(String q);

    List<Movie> fuzzyQuery(String q);

    String autocomplete(String q);
}
