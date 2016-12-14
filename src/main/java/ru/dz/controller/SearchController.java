package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dz.elastic.FilmSearchService;
import ru.dz.entity.Film;
import ru.dz.entity.Genre;
import ru.dz.services.FilmService;
import ru.dz.services.GenreService;

import java.util.List;
import java.util.Map;

/**
 * Created by ainur on 08.12.2016.
 */
@Controller
public class SearchController {
    private static final String SEARCH_MAPPING = "/search";
    private static final String BY_MAPPING = "/by";
    private static final String SEARCH_BY_GENRE_MAPPING = SEARCH_MAPPING + BY_MAPPING + "/genre";
    private static final String SEARCH_BY_YEAR_MAPPING = SEARCH_MAPPING + BY_MAPPING + "/year";
    private static final String GENRE_MAPPING = "/genre";
    private static final String ALL_MAPPING = "/all";
    private static final String GENRE_ALL_MAPPING = GENRE_MAPPING + ALL_MAPPING;
    @Autowired
    FilmSearchService filmSearchService;

    @Autowired
    FilmService filmService;

    @Autowired
    GenreService genreService;

    @RequestMapping(SEARCH_BY_GENRE_MAPPING)
    public ResponseEntity<List<Film>> filmsByGenre(@RequestParam("genre") Long genre) {
        return ResponseEntity.ok(filmService.findFilmByGenre(genre));
    }

    @RequestMapping(SEARCH_BY_YEAR_MAPPING)
    public ResponseEntity<List<Film>> filmsByGenre(@RequestParam("from") Integer from, @RequestParam("to") Integer to) {
        return ResponseEntity.ok(filmSearchService.findByYear(from, to));
    }

    @RequestMapping(GENRE_ALL_MAPPING)
    public ResponseEntity<List<Genre>> allGenres() {
        List<Genre> genres = genreService.findAll();
        return ResponseEntity.ok(genres);
    }

    @RequestMapping(value = ALL_MAPPING)
    @ResponseBody
    private ResponseEntity<Map> allFilms(ModelMap map) {
        List<Genre> genres = genreService.findAll();
        List<Film> films = filmSearchService.findAll();

        map.put("genres", genres);
        map.put("films", films);

        return ResponseEntity.ok(map);
    }
}
