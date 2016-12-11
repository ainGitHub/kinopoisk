package ru.dz.elastic;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.dz.config.ElasticConfig;
import ru.dz.entity.Film;
import org.elasticsearch.action.search.SearchType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ainur on 10.11.2016.
 */
@Service
public class FilmSearchService implements IFilmSearchService {
    Logger logger = LoggerFactory.getLogger(FilmSearchService.class);
    private static final String DESCRIPTION_FIELD = "description";
    private static final String NAME_FIELD = "name";
    private static final String ALL_FIELD = "_all";

    @Autowired
    private Client client;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void add(Film film) {
        try {
            client.prepareIndex(ElasticConfig.FILM_CORP_INDEX, ElasticConfig.FILM_TYPE, String.valueOf(film.getId()))
                    .setSource(mapper.writeValueAsString(film))
                    .get();
        } catch (JsonProcessingException | JsonGenerationException | JsonMappingException e) {
            logger.error("Can't parse object to json");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Film film) {

    }

    @Override
    public void updateAll(List<Film> films) {

    }

    @Override
    public void deleteAll(List<Film> films) {

    }

    @Override
    public List<Film> matchQuery(String q, Pageable page) {
        return null;
    }

    @Override
    public List<Film> matchNameQuery(String name) {
        SearchResponse response = client.prepareSearch(ElasticConfig.FILM_CORP_INDEX)
                .setTypes(ElasticConfig.FILM_TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery(NAME_FIELD, name))
                .execute()
                .actionGet();
        return getResult(response);
    }


    @Override
    public List<Film> matchDescriptionQuery(String description) {
        SearchResponse response = client.prepareSearch(ElasticConfig.FILM_CORP_INDEX)
                .setTypes(ElasticConfig.FILM_TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery(DESCRIPTION_FIELD, description))
                .execute()
                .actionGet();
        return getResult(response);
    }


    @Override
    public List<Film> matchPhrasePrefixQuery(String q) {
        SearchResponse response = client.prepareSearch(ElasticConfig.FILM_CORP_INDEX)
                .setTypes(ElasticConfig.FILM_TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchPhrasePrefixQuery(NAME_FIELD, q))
                .execute()
                .actionGet();


        return getResult(response);
    }

    @Override
    public List<Film> fuzzyQuery(String q) {
        return null;
    }

    @Override
    public String autocomplete(String q) {
        return client.prepareSearch(ElasticConfig.FILM_CORP_INDEX)
                .setTypes(ElasticConfig.FILM_TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery(ALL_FIELD, q).operator(MatchQueryBuilder.Operator.AND))
                .addHighlightedField(NAME_FIELD)
                .setHighlighterRequireFieldMatch(false)
                .execute()
                .actionGet()
                .toString();

    }

    @Override
    public List<Film> findAll() {
        SearchResponse response = client.prepareSearch(ElasticConfig.FILM_CORP_INDEX)
                .setTypes(ElasticConfig.FILM_TYPE)
                .setQuery(QueryBuilders.matchAllQuery())
                .execute()
                .actionGet();

        return getResult(response);
    }

    @Override
    public void deleteAll() {
        client.admin().indices().prepareDelete("_all").get();
    }

    @Override
    public List<Film> searchByNameAndDescription(String name, String description) {
        SearchResponse response = client.prepareSearch(ElasticConfig.FILM_CORP_INDEX)
                .setTypes(ElasticConfig.FILM_TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery(NAME_FIELD, name))
                .setQuery(QueryBuilders.matchQuery(DESCRIPTION_FIELD, description))
                .execute()
                .actionGet();
        return getResult(response);
    }

    private List<Film> getResult(SearchResponse response) {
        List<Film> result = new ArrayList<>();
        response.getHits().forEach(h -> {
            try {
                result.add(mapper.readValue(h.getSourceAsString(), Film.class));
            } catch (IOException ignored) {
            }
        });
        return result;
    }
}
