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

    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    private String filmsPage(ModelMap map) {
        List<Film> films = filmSearchService.findAll();
        map.put("films", films);
        return "films";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    private String generateFilms(ModelAndView modelAndView) {
        firstGenerateFilms.generateFilms();
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/all")
    private String deleteAll() {
        filmSearchService.deleteAll();
        return "redirect:/films";
    }


    @RequestMapping(value = "/search/film")
    private ResponseEntity<List<Film>> searchFilms(@RequestParam(required = false) String name) {

        List<Film> films = null;

        if (name != null && !name.isEmpty())
            films = filmSearchService.matchNameQuery(name);
        else
            films = filmSearchService.findAll();

        return ResponseEntity.ok(films);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/autocomplete")
    @ResponseBody
    public ResponseEntity<String> searchAuto(@RequestParam String q) {
        String response = filmSearchService.autocomplete(q);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/films")
    public ResponseEntity<List<Film>> searchDesc(String q) {
        return ResponseEntity.ok(filmSearchService.matchPhrasePrefixQuery(q));
    }
}
