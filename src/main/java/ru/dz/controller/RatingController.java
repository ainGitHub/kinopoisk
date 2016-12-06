package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dz.entity.Film;
import ru.dz.entity.Rating;
import ru.dz.entity.UserInfo;
import ru.dz.services.FilmService;
import ru.dz.services.RatingService;
import ru.dz.services.UserService;

/**
 * Created by Admin on 07.12.2016.
 */

@Controller
public class RatingController {

    @Autowired
    UserService userService;

    @Autowired
    RatingService ratingService;

    @Autowired
    FilmService filmService;

    @ResponseBody
    @RequestMapping(value = "/film/rating/{id}/{rating}", method = RequestMethod.GET)
    private String filmRating(@PathVariable("id") Long id, @PathVariable("rating") Integer rating) {

        Film film = filmService.findFilmById(id);
        Rating rat = new Rating();
        Object userObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user;

        if (userObject != null && !(userObject instanceof String)) {
            user = (User) userObject;
        } else {
            return "non-auto";
        }

        UserInfo userInfo = userService.findUserByVkID(Integer.parseInt(user.getUsername()));
        if (filmService.isUserAlreadyVoted(userInfo, film)) {
            return "already";
        }

        if (film.getVoters() == null) {
            film.setVoters(0);
        }
        if (film.getRating() == null) {
            film.setRating(0.0);
        }
        film.setVoters(film.getVoters() + 1);
        film.setRating(film.getRating() + rating);
        filmService.addFilm(film);

        rat.setRating(rating);
        rat.setFilm(film);
        rat.setUserInfo(userService.getUser((long) 7));
        ratingService.addRating(rat);
        return "good";
    }

}
