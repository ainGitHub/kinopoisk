package ru.dz.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dz.elastic.FilmSearchService;
import ru.dz.entity.Film;
import ru.dz.entity.Rating;
import ru.dz.entity.Review;
import ru.dz.services.*;
import org.springframework.http.ResponseEntity;
import ru.dz.services.FilmService;
import ru.dz.services.FirstGenerateFilms;
import java.util.Date;
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

    @RequestMapping(value = "/film/{id}", method = RequestMethod.GET)
    private String filmsPage(@PathVariable("id") Long id,
                             ModelMap map) {
        Film film = filmService.findFilmById(id);
        map.put("film", film);

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null && !(user instanceof String)) {
            request.getSession().setAttribute("user", user);
        }
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
                               ModelMap map) {

        List<Film> films = null;

        if (name != null && !name.isEmpty())
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

        if (description != null && !description.isEmpty())
            films = filmSearchService.matchDescriptionQuery(description);
        else
            films = filmSearchService.findAll();

        map.put("films", films);
        return "films";
        //return "v2/film";
    }

    @RequestMapping(value = "/delete/all")
    private String deleteAll() {
        filmSearchService.deleteAll();
        return "/test/films";
    }

    @ResponseBody
    @RequestMapping(value = "/film/rating/{id}/{rating}", method = RequestMethod.GET)
    private String filmRating(@PathVariable("id") Long id, @PathVariable("rating") Integer rating) {

        Film film = filmService.findFilmById(id);
        Rating rat = new Rating();
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user != null && !(user instanceof String)) {
            System.out.println();
        } else {
            return "non-auto";
        }

        List<Rating> ratings = ratingService.getAllRatings();
        for (Rating r : ratings) {
            if (r.getFilm().equals(film) && r.getUserInfo().equals(userService.getUser((long) 7))) {
                return "already";
            }
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

//    @RequestMapping(value = "/film/review/add/{film_id}/{user_id}", method = RequestMethod.POST)
//    private String addReviewAboutFilm(@PathVariable Long film_id, @PathVariable Long user_id) {
//
//        Review review = new Review();
//        review.setDate(new Date());
//        review.setUserInfo(userService.getUser(user_id));
//        review.setFilm(filmService.findFilmById(film_id));
//        review.setContent(request.getParameter("content"));
//        reviewService.addReview(review);
//
//        return "redirect:/film/" + film_id;
//    }

    @RequestMapping(value = "/film/review/add/{film_id}", method = RequestMethod.POST)
    private String addReviewAboutFilm(@PathVariable Long film_id) {

        Review review = new Review();
        review.setDate(new Date());
        review.setUserInfo(userService.getUser((long) 7));
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

    @RequestMapping(value = "/all")
    @ResponseBody
    private ResponseEntity<List<Film>> allFilms() {
        return ResponseEntity.ok(filmSearchService.findAll());
    }
}
