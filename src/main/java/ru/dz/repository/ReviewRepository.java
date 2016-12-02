package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Review;

/**
 * Created by Admin on 29.11.2016.
 */

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
