package ru.dz.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dz.elastic.FilmSearchService;
import ru.dz.entity.Film;
import ru.dz.services.*;

import java.util.List;

/**
 * Created by ainur on 13.11.2016.
 */
@Controller
public class FilmController {
    private static final String FILM_MAPPING = "/film";
    private static final String FILMS_MAPPING = "/films";
    private static final String FILMS_GENERATOR_MAPPING = "/generate";
    private static final String DELETE_ALL_FILMS_MAPPING = "/delete/all";
    private static final String SEARCH_FILM_MAPPING = "/search" + FILM_MAPPING;
    private static final String SEARCH_FILMS_MAPPING = "/search" + FILMS_MAPPING;
    private static final String AUTOCOMPLETE_MAPPING = "/autocomplete";
    @Autowired
    UserService userService;
    @Autowired
    RatingService ratingService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    FilmService filmService;
    @Autowired
    FirstGenerateFilms firstGenerateFilms;
    @Autowired
    FilmSearchService filmSearchService;
    @Autowired
    GenreService genreService;
    @Autowired
    ActorService actorService;

    @RequestMapping(value = FILM_MAPPING + "/{id}", method = RequestMethod.GET)
    private String filmsPage(@PathVariable("id") Long id,
                             ModelMap map) {
        Film film = filmService.findFilmById(id);
        map.put("film", film);
        map.put("genres", genreService.getGenreByFilmId(id));
        map.put("actors", actorService.getActorByFilmId(id));
        map.put("directors", actorService.getDirectorsByFilmId(id));
        map.put("screenwriters", actorService.getScreenWritersByFilmId(id));

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null && !(user instanceof String)) {
            request.getSession().setAttribute("user", user);
        }
        return "film";
    }

    @RequestMapping(value = FILMS_MAPPING, method = RequestMethod.GET)
    private String filmsPage(ModelMap map) {
        List<Film> films = filmSearchService.findAll();
        map.put("films", films);
        return "films";
    }

    @RequestMapping(value = FILMS_GENERATOR_MAPPING, method = RequestMethod.GET)
    private String generateFilms(ModelAndView modelAndView) {
        firstGenerateFilms.generateFilms();
        return "redirect:/";
    }

    @RequestMapping(value = DELETE_ALL_FILMS_MAPPING)
    private String deleteAll() {
        filmSearchService.deleteAll();
        return "redirect:/films";
    }


    @RequestMapping(value = SEARCH_FILM_MAPPING)
    private ResponseEntity<List<Film>> searchFilms(@RequestParam(required = false) String name) {

        List<Film> films = null;

        if (name != null && !name.isEmpty())
            films = filmSearchService.matchNameQuery(name);
        else
            films = filmSearchService.findAll();

        return ResponseEntity.ok(films);
    }


    @RequestMapping(method = RequestMethod.GET, value = AUTOCOMPLETE_MAPPING)
    @ResponseBody
    public ResponseEntity<String> searchAuto(@RequestParam String q) {
        String response = filmSearchService.autocomplete(q);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = SEARCH_FILMS_MAPPING)
    public ResponseEntity<List<Film>> searchDesc(String q) {
        return ResponseEntity.ok(filmSearchService.matchPhrasePrefixQuery(q));
    }
}
