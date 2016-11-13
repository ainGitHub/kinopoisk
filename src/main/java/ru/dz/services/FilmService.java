package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Film;
import ru.dz.repository.FilmRepository;

import java.util.List;

/**
 * Created by ainur on 10.11.2016.
 */
@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<Film> findAllChangedFilms() {
        return filmRepository.findAllChanged();
    }


    public List<Film> findAllDeletedFilms() {
        return filmRepository.findAllDeleted();
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }
}
