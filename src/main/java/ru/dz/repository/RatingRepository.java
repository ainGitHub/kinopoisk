package ru.dz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Rating;

/**
 * Created by Admin on 01.12.2016.
 */

@Repository
public interface RatingRepository  extends CrudRepository<Rating, Long> {
}
