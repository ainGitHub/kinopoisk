package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Person;
import ru.dz.repository.ActorRepository;

import java.util.List;

/**
 * Created by Adel on 04.12.2016.
 */
@Service
public class ActorService {
    @Autowired
    ActorRepository actorRepository;

    public Person findPersonById(Long id) {
        return actorRepository.findPersonById(id);
    }

    public List<Person> getActorByFilmId(Long id) {
        return actorRepository.getActorByFilmId(id);
    }

    public List<Person> getScreenWritersByFilmId(Long id) {
        return actorRepository.getScreenWritersByFilmId(id);
    }

    public List<Person> getDirectorsByFilmId(Long id) {
        return actorRepository.getDirectorsByFilmId(id);
    }

    public List<Person> getAllActors() {
        return actorRepository.getAllActors();
    }

    public List<Person> getAllDirectors() {
        return actorRepository.getAllDirectors();
    }

    public List<Person> getAllWriters() {
        return actorRepository.getAllWriters();
    }
}