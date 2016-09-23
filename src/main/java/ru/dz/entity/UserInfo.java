package ru.dz.entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
public class UserInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column
    private String gender;

    @Column
    private Date birthday;

    @Column
    private String city;

    @Column
    private String login;

    @Column
    private String email;

    @Column
    private String hashPass;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "user_id",
            targetEntity = Review.class)
    private List<Review> reviews;

    public UserInfo() {
    }

}

