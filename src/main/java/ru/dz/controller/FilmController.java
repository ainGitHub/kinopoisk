package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.Film;
import ru.dz.services.FilmService;

import java.util.List;

/**
 * Created by ainur on 13.11.2016.
 */
@Controller
public class FilmController {

    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    private String filmsPage(ModelMap map) {
        List<Film> films = filmService.findAll();
        map.put("films", films);
        return "film";
    }
}
