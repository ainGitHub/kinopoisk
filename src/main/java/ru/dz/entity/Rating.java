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

}
