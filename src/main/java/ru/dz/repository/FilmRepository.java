package ru.dz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Film;

import java.util.List;

/**
 * Created by ainur on 10.11.2016.
 */

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("select f from Film f where f.deleted = true")
    public List<Film> findAllDeleted();

    @Query("select f from Film f where f.changed = true")
    public List<Film> findAllChanged();

    @Query("select f from Film f where f.added = true")
    public List<Film> findAllAdded();

    public Film findFilmById(Long id);

    @Query("select f from Film f inner join f.filmsGenres fg where fg.genre.id=:id")
    public List<Film> findFilmByGenre(@Param("id") Long genre);
}
