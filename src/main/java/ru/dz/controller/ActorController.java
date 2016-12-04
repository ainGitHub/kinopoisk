package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.services.ActorService;
import ru.dz.services.CareerService;
import ru.dz.services.GenreService;

/**
 * Created by Adel on 04.12.2016.
 */
@Controller
public class ActorController {
    @Autowired
    ActorService actorService;
    @Autowired
    CareerService careerService;
    @Autowired
    GenreService genreService;

    @RequestMapping(value = "/actor/{id}", method = RequestMethod.GET)
    public String renderPersonPage(@PathVariable("id") Long id,
                                   ModelMap model) {
        model.put("person", actorService.findPersonById(id));
        model.put("careers", careerService.findCareerByPersonId(id));
        model.put("genres", genreService.getGenreByPersonId(id));
        return "actor";
    }
}
