package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Genre;
import ru.dz.repository.GenreRepository;

import java.util.List;

/**
 * Created by Adel on 04.12.2016.
 */
@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public List<Genre> getGenreByPersonId(Long id) {
        return genreRepository.getGenreByPersonId(id);
    }
}
