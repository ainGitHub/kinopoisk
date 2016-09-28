package ru.dz.entity;

import javax.persistence.*;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class Rating {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer rating;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film_id;

    @ManyToOne(targetEntity = UserInfo.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Film getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Film film_id) {
        this.film_id = film_id;
    }

    public UserInfo getUser_id() {
        return user_id;
    }

    public void setUser_id(UserInfo user_id) {
        this.user_id = user_id;
    }
}
