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

    @Autowired
    FilmSearchService filmSearchService;

    @Autowired
    FilmService filmService;

    @Autowired
    GenreService genreService;

    @RequestMapping("/search/bygenre")
    public ResponseEntity<List<Film>> filmsByGenre(@RequestParam("genre") Long genre) {
        return ResponseEntity.ok(filmService.findFilmByGenre(genre));
    }

    @RequestMapping("/genre/all")
    public ResponseEntity<List<Genre>> allGenres() {
        List<Genre> genres = genreService.findAll();
        return ResponseEntity.ok(genres);
    }

    @RequestMapping(value = "/all")
    @ResponseBody
    private ResponseEntity<Map> allFilms(ModelMap map) {
        List<Genre> genres = genreService.findAll();
        List<Film> films = filmService.findAll();

        map.put("genres", genres);
        map.put("films", films);

        return ResponseEntity.ok(map);
    }
}
