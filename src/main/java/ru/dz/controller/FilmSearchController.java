package ru.dz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dz.elastic.FilmSearchService;
import ru.dz.entity.Film;

import java.util.List;

/**
 * Created by ainur on 22.11.2016.
 */
@Controller
public class FilmSearchController {
    Logger logger = LoggerFactory.getLogger(FilmSearchController.class);

    @Autowired
    FilmSearchService filmSearchService;

    @RequestMapping(value = "/test/films")
    public String search(ModelMap map) {
        List<Film> allFilms = filmSearchService.findAll();
        map.put("films", allFilms);
        return "film";
        //return "v2/film";
    }

    @RequestMapping(value = "/test/search/films", method = RequestMethod.GET)
    public String searchBYQuery(@RequestParam String query, ModelMap map) {
        List<Film> films = filmSearchService.matchPhraseQuery(query);
        map.put("films", films);
        return "film";
        //return "v2/film";
    }
}
