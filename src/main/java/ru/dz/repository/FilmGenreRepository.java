package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.FilmGenre;

/**
 * Created by Admin on 14.12.2016.
 */

@Repository
public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long> {
}
