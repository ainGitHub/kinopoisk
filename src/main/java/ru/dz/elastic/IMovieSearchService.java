package ru.dz.elastic;

import org.springframework.data.domain.Pageable;
import ru.dz.entity.Movie;

import java.util.List;

/**
 * Created by Aydar Farrakhov on 11.09.16.
 */
public interface IMovieSearchService {

    void add(Movie movie);


    /**
     * TODO: метод удаления объекта из elasticsearch
     *
     * @param id - id объекта
     */
    void delete(Long id);

    /**
     * TODO: метод обновления объекта в elasticSearch
     *
     * @param movie
     */
    void update(Movie movie);

    /**
     * TODO: Добавить любое новое поле в {@link Movie}
     * Искать по описанию и по этому полю
     *
     * @param q - запрос
     * @param page - пагинация
     * @return список найденных фильмов
     */
    List<Movie> matchQuery(String q, Pageable page);

    List<Movie> matchPhraseQuery(String q);

    List<Movie> matchPhrasePrefixQuery(String q);

    List<Movie> fuzzyQuery(String q);

    String autocomplete(String q);
}
