package ru.dz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Career;
import ru.dz.repository.CareerRepository;

import java.util.List;

/**
 * Created by Adel on 04.12.2016.
 */
@Service
public class CareerService {
    @Autowired
    CareerRepository careerRepository;

    public List<Career> findCareerByPersonId(Long id) {
        return careerRepository.getCareerByPersonId(id);
    }
}
