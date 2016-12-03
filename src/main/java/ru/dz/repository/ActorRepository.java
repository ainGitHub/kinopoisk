package ru.dz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Person;

/**
 * Created by Adel on 04.12.2016.
 */
@Repository
public interface ActorRepository extends CrudRepository<Person, Long> {
    Person findPersonById(Long id);
}
