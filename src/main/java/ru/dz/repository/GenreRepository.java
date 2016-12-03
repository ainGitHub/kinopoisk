package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Genre;

import java.util.List;

/**
 * Created by Adel on 04.12.2016.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("select fg.genre from FilmGenre fg " +
            "where fg.film.id = " +
            "(select c.film.id from Casts c " +
            "where c.person.id =:id)")
    public List<Genre> getGenreByPersonId(@Param("id") Long id);
}
