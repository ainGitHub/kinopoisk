package ru.dz.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.Film;
import ru.dz.entity.Genre;
import ru.dz.entity.Person;
import ru.dz.services.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 09.12.2016.
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private static final String FILM_MAPPING = "/film";
    private static final String DELETE_MAPPING = "/delete";
    private static final String DELETE_REVIEW_MAPPING = "/review" + DELETE_MAPPING;
    private static final String DELETE_RATING_MAPPING = "/rating" + DELETE_MAPPING;
    private static final String DELETE_FILM_MAPPING = DELETE_MAPPING + FILM_MAPPING;
    private static final String DELETE_DIRECTOR_MAPPING = DELETE_MAPPING + "/director";
    private static final String DELETE_WRITER_MAPPING = DELETE_MAPPING + "/writer";
    private static final String DELETE_GENRE_MAPPING = DELETE_MAPPING + "/genre";
    private static final String DELETE_ACTOR_MAPPING = DELETE_MAPPING + "/actor";
    private static final String CHANGE_MAPPING = "change";
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
    GenreService genreService;
    @Autowired
    ActorService actorService;
    @Autowired
    CastService castService;
    @Autowired
    FilmGenreService filmGenreService;

    @RequestMapping(method = RequestMethod.GET)
    private String getAllFilms(ModelMap map) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals("anonymousUser")) {
            List<Film> films = filmService.findAll();
            map.put("films", films);
            return "admin";
        }
        return "redirect:/";
    }

    @RequestMapping(value = FILM_MAPPING + "/{id}", method = RequestMethod.GET)
    private String filmSettingsPage(@PathVariable("id") Long id, ModelMap map) {
        Film film = filmService.findFilmById(id);
        map.put("film", film);
        map.put("genres", genreService.getGenreByFilmId(id));
        map.put("actors", actorService.getActorByFilmId(id));
        map.put("directors", actorService.getDirectorsByFilmId(id));
        map.put("screenwriters", actorService.getScreenWritersByFilmId(id));

        map.put("persons", actorService.getAllActors());
        map.put("producers", actorService.getAllDirectors());
        map.put("writers", actorService.getAllWriters());
        map.put("janres", genreService.findAll());
        return "filmSettings";
    }

    @RequestMapping(value = DELETE_REVIEW_MAPPING + "/{review_id}/{film_id}", method = RequestMethod.GET)
    private String deleteFilmReview(@PathVariable Long review_id, @PathVariable Long film_id) {
        reviewService.deleteReviewById(review_id);
        return "redirect:/admin/film/" + film_id;
    }

    @RequestMapping(value = DELETE_RATING_MAPPING + "/{film_id}", method = RequestMethod.GET)
    private String deleteFilmRating(@PathVariable Long film_id) {
        Film film = ratingService.clearFilmRating(filmService.findFilmById(film_id));
        filmService.addFilm(film);
        return "redirect:/admin/film/" + film_id;
    }

    @RequestMapping(value = DELETE_FILM_MAPPING + "/{id}", method = RequestMethod.GET)
    private String deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = DELETE_DIRECTOR_MAPPING + "/{film_id}/{director_id}", method = RequestMethod.GET)
    private String deleteFilmDirector(@PathVariable Long film_id, @PathVariable Long director_id) {
        Film film = filmService.findFilmById(film_id);
        Person director = actorService.findPersonById(director_id);
        castService.deleteFilmDirector(film, director);
        return "redirect:/admin/film/" + film_id;
    }

    @RequestMapping(value = DELETE_WRITER_MAPPING + "/{film_id}/{writer_id}", method = RequestMethod.GET)
    private String deleteFilmWriter(@PathVariable Long film_id, @PathVariable Long writer_id) {
        Film film = filmService.findFilmById(film_id);
        Person writer = actorService.findPersonById(writer_id);
        castService.deleteFilmWriter(film, writer);
        return "redirect:/admin/film/" + film_id;
    }

    @RequestMapping(value = DELETE_GENRE_MAPPING + "/{film_id}/{genre_id}", method = RequestMethod.GET)
    private String deleteFilmGenre(@PathVariable Long film_id, @PathVariable Long genre_id) {
        Film film = filmService.findFilmById(film_id);
        Genre gen = genreService.getGenreById(genre_id);
        filmGenreService.deleteFilmGenre(film, gen);
        return "redirect:/admin/film/" + film_id;
    }


    @RequestMapping(value = DELETE_ACTOR_MAPPING + "/{film_id}/{actor_id}", method = RequestMethod.GET)
    private String deleteFilmActor(@PathVariable Long film_id, @PathVariable Long actor_id) {
        Film film = filmService.findFilmById(film_id);
        Person director = actorService.findPersonById(actor_id);
        castService.deleteFilmActor(film, director);
        return "redirect:/admin/film/" + film_id;
    }

    @RequestMapping(value = CHANGE_MAPPING + "/{id}", method = RequestMethod.GET)
    private String changingFilmInformation(@PathVariable Long id) throws ParseException {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String duration = request.getParameter("duration");
        String age = request.getParameter("age");
        String year = request.getParameter("year");
        String dir = request.getParameter("director");
        String screenWriter = request.getParameter("writer");
        String actor = request.getParameter("actor");
        String genre = request.getParameter("genre");
        String description = request.getParameter("description");
        Film film = filmService.findFilmById(id);

        Date day = film.getYear();
        if (!year.equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            day = dateFormat.parse(year);
        }

        if (!actor.equals("nothing")) {
            Person acter = actorService.findPersonById(Long.valueOf(actor));
            castService.addFilmActor(film, acter);
        }
        if (!genre.equals("nothing")) {
            Genre ganr = genreService.getGenreById(Long.valueOf(genre));
            filmGenreService.addGenreToFilm(film, ganr);
        }
        if (!dir.equals("nothing")) {
            Person director = actorService.findPersonById(Long.valueOf(dir));
            castService.changeDirectorOfFilm(film, director);
        }
        if (!screenWriter.equals("nothing")) {
            Person writer = actorService.findPersonById(Long.valueOf(screenWriter));
            castService.changeScreenWriterOfFilm(film, writer);
        }

        film.setName(name);
        film.setCountry(country);
        film.setDuration(Integer.valueOf(duration));
        film.setAgeLimit(age);
        film.setYear(day);
        film.setDescription(description);
        filmService.addFilm(film);
        return "redirect:/admin/film/" + id;
    }
}
