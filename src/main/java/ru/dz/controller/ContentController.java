package ru.dz.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.Film;
import ru.dz.entity.Person;
import ru.dz.entity.Review;
import ru.dz.entity.UserInfo;
import ru.dz.services.FilmService;
import ru.dz.services.ReviewService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adel on 06.11.2016.
 */
@Controller
public class ContentController {
    @Autowired
    FilmService filmService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    HttpServletRequest request;

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

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        List<Review> reviews = reviewService.getAllReviews();
        ArrayList<Film> films = (ArrayList<Film>) filmService.findAll();
        ArrayList<Review> rev = new ArrayList<>();
        if (userInfo != null) {
            for(Review r: reviews){
                if(r.getUserInfo().getId().equals(userInfo.getId())){
                    rev.add(r);
                }
            }
            model.put("films", films);
            model.put("review", rev);
            model.put("user", userInfo);
            return "profile";
        }
        model.put("review", rev);
        model.put("user", user);
        return "profile";
    }
}
