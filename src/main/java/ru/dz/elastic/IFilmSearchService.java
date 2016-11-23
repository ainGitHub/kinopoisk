package ru.dz.elastic;

import org.springframework.data.domain.Pageable;
import ru.dz.entity.Film;

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

    List<Film> matchQuery(String q, Pageable page);

    List<Film> matchPhraseQuery(String q);

    List<Film> matchPhrasePrefixQuery(String q);

    List<Film> fuzzyQuery(String q);

    String autocomplete(String q);

    List<Film> findAll();

    void deleteAll();
}
