package ru.dz.services;

import com.google.common.base.Charsets;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.elastic.FilmSearchService;
import ru.dz.entity.Film;
import ru.dz.repository.FilmRepository;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ainur on 13.11.2016.
 */
@Service
public class FirstGenerateFilms {
    boolean generated = false;
    Logger logger = LoggerFactory.logger(FirstGenerateFilms.class);

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    FilmSearchService filmSearchService;

    private List<Film> films = new ArrayList<>();

    public void generateFilms() {
        if (generated)
            return;

        String filmsStr = getResource("films.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Film[] filmList = objectMapper.readValue(filmsStr, Film[].class);
            films = Arrays.asList(filmList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeDBWithGeneratedFilms();
        generated = true;
    }

    private void initializeDBWithGeneratedFilms() {
        filmRepository.save(films);
        films.stream().forEach(f -> filmSearchService.add(f));
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
