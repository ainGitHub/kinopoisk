package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Person;
import ru.dz.repository.ActorRepository;

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
}
