package ru.dz.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class Film {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Date year;

    @Column
    private String country;

    @Column
    private String ageLimit;

    @Column
    private Integer duration;

    @Column
    private String description;

    @Column
    private String trailer;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            targetEntity = Casts.class)
    private List<Casts> castList;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            targetEntity = Genre.class)
    private List<Genre> genres;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            targetEntity = Review.class)
    private List<Review> reviews;
}
