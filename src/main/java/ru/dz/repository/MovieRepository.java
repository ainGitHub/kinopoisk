package ru.dz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Movie;

/**
 * Created by Aydar Farrakhov on 11.09.16.
 */
@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
