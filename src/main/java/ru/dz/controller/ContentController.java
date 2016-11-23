package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.Person;
import ru.dz.services.FilmService;

/**
 * Created by Adel on 06.11.2016.
 */
@Controller
public class ContentController {
    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String renderPersonPage(ModelMap model) {
        model.put("person", new Person());
        return "actor";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String renderProfilePage(ModelMap model) {
        Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;


        if (userObj != null && !(userObj instanceof String))
            user = (User) userObj;

        model.put("user", user);
        return "profile";
    }

}
