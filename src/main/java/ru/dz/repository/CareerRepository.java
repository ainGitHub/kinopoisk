package ru.dz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dz.entity.Career;

import java.util.List;

/**
 * Created by Adel on 04.12.2016.
 */
@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    @Query("select pc.career from PersonCareer pc " +
            "where pc.person.id =:id")
    public List<Career> getCareerByPersonId(@Param("id") Long id);
}
