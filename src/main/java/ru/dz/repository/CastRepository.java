package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Casts;

/**
 * Created by Admin on 13.12.2016.
 */

@Repository
public interface CastRepository extends JpaRepository<Casts, Long> {
}
