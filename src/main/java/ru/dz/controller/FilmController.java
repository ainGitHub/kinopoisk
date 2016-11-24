package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dz.elastic.FilmSearchService;
import ru.dz.entity.Film;
import ru.dz.services.FilmService;
import ru.dz.services.FirstGenerateFilms;

import java.util.List;

/**
 * Created by ainur on 13.11.2016.
 */
@Controller
public class FilmController {

    @Autowired
    FilmService filmService;

    @Autowired
    FirstGenerateFilms firstGenerateFilms;

    @Autowired
    FilmSearchService filmSearchService;

    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    private String filmsPage(@PathVariable("id") Long id,
                             ModelMap map) {
        Film film = filmService.findFilmById(id);
        map.put("film", film);
        return "film";
    }

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    private String filmsPage(ModelMap map) {
        List<Film> films = filmService.findAll();
        map.put("films", films);
        return "films";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    private String generateFilms(ModelAndView modelAndView) {
        firstGenerateFilms.generateFilms();
        return "redirect:/";
    }

    @RequestMapping(value = "/v2/film", method = RequestMethod.GET)
    private ModelAndView ilmsPage(ModelMap map) {
        List<Film> films = filmService.findAll();
        map.put("films", films);
        return new ModelAndView("v2/film");
    }


    @RequestMapping(value = "/search/films/name", method = RequestMethod.GET)
    public String searchByName(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String description,
                               ModelMap map) {

        List<Film> films = null;

        if (name != null && name.isEmpty())
            films = filmSearchService.matchNameQuery(name);
        else
            films = filmSearchService.findAll();

        map.put("films", films);
        return "films";
        //return "v2/film";
    }

    @RequestMapping(value = "/search/films/description", method = RequestMethod.GET)
    public String searchByDescription(@RequestParam(required = false) String description,
                                      ModelMap map) {

        List<Film> films = null;

        if (description != null && description.isEmpty())
            films = filmSearchService.matchNameQuery(description);
        else
            films = filmSearchService.findAll();

        map.put("films", films);
        return "films";
        //return "v2/film";
    }

    @RequestMapping(value = "delete/all")
    private String deleteAll() {
        filmSearchService.deleteAll();
        return "/test/films";
    }
}
