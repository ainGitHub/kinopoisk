package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dz.elastic.IMovieSearchService;
import ru.dz.elastic.SearchType;
import ru.dz.entity.Movie;
import ru.dz.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aydar Farrakhov on 11.09.16.
 */
@RepositoryRestController
public class MovieController {

    private static final String MOVIES_MAPPING = "/movies";
    private static final String SEARCH_MAPPING = MOVIES_MAPPING + "/search";
    private static final String SEARCH_AUTOCOMPLETE_MAPPING = MOVIES_MAPPING + "/autocomplete";

    @Autowired
    private IMovieSearchService movieSearchService;

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(method = RequestMethod.POST, value = MOVIES_MAPPING)
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        movie = movieRepository.save(movie);
        movieSearchService.add(movie);
        return ResponseEntity.ok(movie);
    }


    @RequestMapping(method = RequestMethod.GET, value = SEARCH_MAPPING)
    public ResponseEntity<List<Movie>> search(@RequestParam String q, @RequestParam SearchType type,
                                              Pageable page){
        List<Movie> result = new ArrayList<>();
        switch (type){
            case MATCH:
                result = movieSearchService.matchQuery(q, page);
                break;
            case MATCH_PHRASE:
                result = movieSearchService.matchPhraseQuery(q);
                break;
            case MATCH_PHRASE_PREFIX:
                result = movieSearchService.matchPhrasePrefixQuery(q);
                break;
            case FUZZY:
                result = movieSearchService.fuzzyQuery(q);
                break;
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = SEARCH_AUTOCOMPLETE_MAPPING)
    public ResponseEntity<String> searchAuto(@RequestParam String q){
        return ResponseEntity.ok(movieSearchService.autocomplete(q));
    }

}
