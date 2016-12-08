package ru.dz.elastic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.entity.Film;
import ru.dz.services.FilmService;

import java.util.List;

/**
 * Created by ainur on 30.10.2016.
 */
@Service
public class ElasticExecutorTask implements Runnable {
    Logger logger = LoggerFactory.getLogger(ElasticExecutorTask.class);

    @Autowired
    FilmService filmService;

    @Autowired
    FilmElasticService filmElasticService;

    @Override
    public void run() {
        List<Film> changedFilms = filmService.findAllChangedFilms();
        filmElasticService.updateFilms(changedFilms);

        List<Film> deletedFilms = filmService.findAllDeletedFilms();
        filmElasticService.deleteFilms(deletedFilms);
        //// TODO: 30.10.2016  finding deleted or changed films and doing some deletings and changings in elastic
        logger.info("executor working ");
    }
}
