package ru.dz.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class Genre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "genreId")
    private List<FilmGenre> filmGenres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilmGenre> getFilmGenres() {
        return filmGenres;
    }

    public void setFilmGenres(List<FilmGenre> filmGenres) {
        this.filmGenres = filmGenres;
    }
}
