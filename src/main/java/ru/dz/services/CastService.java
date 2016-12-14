package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Casts;
import ru.dz.entity.Film;
import ru.dz.entity.Person;
import ru.dz.repository.CareerRepository;
import ru.dz.repository.CastRepository;

import java.util.List;

/**
 * Created by Admin on 13.12.2016.
 */

@Service
public class CastService {

    @Autowired
    CareerRepository careerRepository;

    @Autowired
    CastRepository castRepository;

    public void changeScreenWriterOfFilm(Film film, Person writer) {
        List<Casts> casts = film.getCastList();
        int i = 0;
        for (Casts c : casts) {
            if (c.getRole().getId() == 3 && c.getPerson().getId().equals(writer.getId())) {
                i++;
            }
        }
        if (i == 0) {
            Long a = Long.valueOf(0);
            Casts cast = new Casts();
            cast.setPerson(writer);
            cast.setFilm(film);
            cast.setRole(careerRepository.getOne(Long.valueOf(3)));
            List<Casts> castses = castRepository.findAll();
            for (Casts c : castses) {
                if (c.getId() > a) {
                    a = c.getId();
                }
            }
            cast.setId(a + 1);
            castRepository.save(cast);
        }
    }

    public void changeDirectorOfFilm(Film film, Person director) {
        List<Casts> casts = film.getCastList();
        int i = 0;
        for (Casts c : casts) {
            if (c.getRole().getId() == 4 && c.getPerson().getId().equals(director.getId())) {
                i++;
            }
        }
        if (i == 0) {
            Long a = Long.valueOf(0);
            Casts cast = new Casts();
            cast.setPerson(director);
            cast.setFilm(film);
            cast.setRole(careerRepository.getOne(Long.valueOf(4)));
            List<Casts> castses = castRepository.findAll();
            for (Casts c : castses) {
                if (c.getId() > a) {
                    a = c.getId();
                }
            }
            cast.setId(a + 1);
            castRepository.save(cast);
        }
    }

    public void deleteFilmDirector(Film film, Person director) {
        List<Casts> casts = film.getCastList();
        for (Casts c : casts) {
            if (c.getPerson().getId().equals(director.getId()) && c.getRole().getId() == 4) {
                castRepository.delete(c);
            }
        }
    }

    public void deleteFilmWriter(Film film, Person director) {
        List<Casts> casts = film.getCastList();
        for (Casts c : casts) {
            if (c.getPerson().getId().equals(director.getId()) && c.getRole().getId() == 3) {
                castRepository.delete(c);
            }
        }
    }

    public void deleteFilmActor(Film film, Person actor) {
        List<Casts> casts = film.getCastList();
        for (Casts c : casts) {
            if (c.getPerson().getId().equals(actor.getId()) && c.getRole().getId() == 1) {
                castRepository.delete(c);
            }
        }
    }

    public void addFilmActor(Film film, Person actor) {
        List<Casts> castes = film.getCastList();
        int i = 0;
        for (Casts c : castes) {
            if (c.getRole().getId() == 1 && c.getPerson().getId().equals(actor.getId())) {
                i++;
            }
        }
        if (i == 0) {
            Long a = Long.valueOf(0);
            Casts casts = new Casts();
            casts.setPerson(actor);
            casts.setFilm(film);
            casts.setRole(careerRepository.getOne(Long.valueOf(1)));
            List<Casts> castses = castRepository.findAll();
            for (Casts c : castses) {
                if (c.getId() > a) {
                    a = c.getId();
                }
            }
            casts.setId(a + 1);
            castRepository.save(casts);
        }
    }
}