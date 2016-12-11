package ru.dz.elastic;

import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.dz.config.ElasticConfig;
import ru.dz.entity.Film;
import java.util.List;

/**
 * Created by ainur on 05.12.2016.
 */
@Service
public class FilmElasticService {
    Logger logger = LoggerFactory.getLogger(FilmElasticService.class);

    private static final String DESCRIPTION_FIELD = "description";
    private static final String NAME_FIELD = "name";

    @Autowired
    private Client client;

    private static final ObjectMapper mapper = new ObjectMapper();


    /* public void updateFilms(List<Film> films){
         if (films == null || films.isEmpty())
             return;

         Film film = films.get(0);
         try {
             UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate(ElasticConfig.FILM_CORP_INDEX, ElasticConfig.FILM_TYPE, film.getId().toString())
                     .setDoc(mapper.writeValueAsString(FilmDTO.convertFilmToFilmDTO(film)).getBytes());
             updateRequestBuilder
                     .setRefresh(false)
                     .setTimeout(new TimeValue(10000))
                     .execute()
                     .actionGet();
         } catch (Exception e) {
             e.printStackTrace();
         }


     *//*    BulkRequestBuilder bulkRequest = client.prepareBulk();
        films.stream().forEach(film -> {
            try {
                bulkRequest
                        .add(client.prepareUpdate(ElasticConfig.FILM_CORP_INDEX, ElasticConfig.FILM_TYPE, film.getId().toString())
                                .setSource(mapper.writeValueAsString(FilmDTO.convertFilmToFilmDTO(film)).getBytes()));
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        });

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            logger.error("Can't to update films");
        }*//*

    }
*/
    public void deleteFilms(List<Film> films) {

    }

    public void addFilms(List<Film> films) {
        if (films == null || films.isEmpty())
            return;

        BulkRequestBuilder bulkRequest = client.prepareBulk();

        films.stream().forEach(film -> {
            try {
                bulkRequest
                        .add(client.prepareIndex(ElasticConfig.FILM_CORP_INDEX, ElasticConfig.FILM_TYPE, film.getId().toString())
                                .setSource(mapper.writeValueAsString(film).getBytes()));
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        });

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            logger.error("Can't to add films");
        }
    }
}
