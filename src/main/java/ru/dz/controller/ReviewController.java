package ru.dz.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.Review;
import ru.dz.entity.UserInfo;
import ru.dz.services.FilmService;
import ru.dz.services.ReviewService;
import ru.dz.services.UserService;

import java.util.Date;

/**
 * Created by Admin on 07.12.2016.
 */

@Controller
public class ReviewController {

    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/film/review/add/{film_id}", method = RequestMethod.POST)
    private String addReviewAboutFilm(@PathVariable Long film_id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userService.findUserByVkID(Integer.parseInt(user.getUsername()));

        Review review = new Review();
        review.setDate(new Date());
        review.setUserInfo(userInfo);
        review.setFilm(filmService.findFilmById(film_id));
        review.setContent(request.getParameter("content"));
        reviewService.addReview(review);

        return "redirect:/film/" + film_id;
    }

    @RequestMapping(value = "/review/add/{user_id}", method = RequestMethod.POST)
    private String addReviewAboutFilmFromProfile(@PathVariable Long user_id) {
        String film = request.getParameter("film");
        String content = request.getParameter("content");
        if (film.equals("none")) {
            return "redirect:/profile";
        }

        Review review = new Review();
        review.setDate(new Date());
        review.setUserInfo(userService.getUser(user_id));
        review.setFilm(filmService.findFilmById(Long.valueOf(film)));
        review.setContent(content);
        reviewService.addReview(review);

        return "redirect:/profile";
    }

    @RequestMapping(value = "/review/remove/{id}", method = RequestMethod.GET)
    private String deleteReviewAboutFilmFromProfile(@PathVariable Long id) {
        reviewService.deleteReviewById(id);
        return "redirect:/profile";
    }

}
