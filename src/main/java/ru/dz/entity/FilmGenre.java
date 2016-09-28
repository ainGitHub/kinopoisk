package ru.dz.entity;

import javax.persistence.*;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class FilmGenre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film_id;

    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Film film_id) {
        this.film_id = film_id;
    }

    public Genre getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Genre genre_id) {
        this.genre_id = genre_id;
    }
}
