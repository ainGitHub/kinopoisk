package ru.dz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.Film;
import ru.dz.entity.Person;
import ru.dz.entity.UserInfo;

import java.util.ArrayList;

/**
 * Created by Adel on 06.11.2016.
 */
@Controller
public class ContentController {

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String renderPersonPage(ModelMap model) {
        model.put("person", new Person());
        return "actor";
    }

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public String renderFilmPage(ModelMap model) {
        model.put("film", new Film());
        return "film";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String renderProfilePage(ModelMap model) {
        model.put("user", new UserInfo());
        return "profile";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String renderSearchPage(ModelMap model) {
        model.put("films", new ArrayList<Film>());
        return "searchPage";
    }
}
