package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    private String filmsPage(ModelMap map) {
        List<Film> films = filmService.findAll();
        map.put("films", films);
        return "film";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    private String generateFilms(ModelAndView modelAndView) {
        firstGenerateFilms.generateFilms();
        return "redirect:/";
    }

    @RequestMapping(value = "/v3/film", method = RequestMethod.GET)
    private ModelAndView ilmsPage(ModelMap map) {
        List<Film> films = filmService.findAll();
        map.put("films", films);
        return new ModelAndView("v2/film");
    }
}
