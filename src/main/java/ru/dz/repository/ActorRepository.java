package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Person;

import java.util.List;

/**
 * Created by Adel on 04.12.2016.
 */
@Repository
public interface ActorRepository extends CrudRepository<Person, Long>, JpaRepository<Person, Long> {
    Person findPersonById(Long id);

    @Query("select c.person from Casts c " +
            "where c.film.id =:id and c.role = 1")
    List<Person> getActorByFilmId(@Param("id") Long id);

    @Query("select c.person from Casts c " +
            "where c.film.id =:id and c.role = 3")
    List<Person> getScreenWritersByFilmId(@Param("id") Long id);

    @Query("select c.person from Casts c " +
            "where c.film.id =:id and c.role = 4")
    List<Person> getDirectorsByFilmId(@Param("id") Long id);

    @Query("select distinct c.person from Casts c where c.role = 1")
    List<Person> getAllActors();

    @Query("select distinct c.person from Casts c where c.role = 4")
    List<Person> getAllDirectors();

    @Query("select distinct c.person from Casts c where c.role = 3")
    List<Person> getAllWriters();
}
