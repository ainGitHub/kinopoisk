package ru.dz.services;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Film;
import ru.dz.repository.FilmRepository;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ainur on 13.11.2016.
 */
@Service
public class FirstGenerateFilms {
    Logger logger = LoggerFactory.getLogger(FirstGenerateFilms.class);

    @Autowired
    FilmRepository filmRepository;

    private List<Film> films = new ArrayList<>();

    public void generateFilms() {
        String filmsStr = getResource("films.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Film[] filmList = objectMapper.readValue(filmsStr, Film[].class);
            films = Arrays.asList(filmList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeDBWithGeneratedFilms();
    }

    public void initializeDBWithGeneratedFilms() {
        filmRepository.save(films);
    }

    private String getResource(String file) {
        URL url = Resources.getResource(file);
        try {
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }
}
