package ru.dz.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by ainur on 30.10.2016.
 */
@Service
public class ElasticExecutorTask implements Runnable {
    Logger logger = LoggerFactory.getLogger(ElasticExecutorTask.class);


    @Override
    public void run() {
        //// TODO: 30.10.2016  finding deleted or changed films and doing some deletings and changings in elastic
        logger.info("executor working ");
    }
}
