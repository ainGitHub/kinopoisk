package ru.dz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Film;
import ru.dz.entity.UserInfo;

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

    public Film findFilmById(Long id);
}
