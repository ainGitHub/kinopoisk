package ru.dz.elastic;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.dz.entity.Film;
import ru.dz.entity.Movie;

import java.util.List;

/**
 * Created by ainur on 10.11.2016.
 */
@Service
public class FilmSearchService implements IFilmSearchService {
    @Autowired
    private Client client;

    @Override
    public void add(Film film) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Film film) {

    }

    @Override
    public void updateAll(List<Film> films) {

    }

    @Override
    public void deleteAll(List<Film> films) {

    }

    @Override
    public List<Movie> matchQuery(String q, Pageable page) {
        return null;
    }

    @Override
    public List<Movie> matchPhraseQuery(String q) {
        return null;
    }

    @Override
    public List<Movie> matchPhrasePrefixQuery(String q) {
        return null;
    }

    @Override
    public List<Movie> fuzzyQuery(String q) {
        return null;
    }

    @Override
    public String autocomplete(String q) {
        return null;
    }
}
