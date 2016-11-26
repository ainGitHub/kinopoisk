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
    @JoinColumn(name = "film", referencedColumnName = "id")
    private Film film;

    @ManyToOne(targetEntity = UserInfo.class)
    @JoinColumn(name = "userInfo", referencedColumnName = "id")
    private UserInfo userInfo;

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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
