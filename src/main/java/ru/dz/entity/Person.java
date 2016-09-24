package ru.dz.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class Person {
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
    private Date birthday;

    @Column
    private Integer growth;

    @Column
    private String city;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            targetEntity = Casts.class)
    private List<Casts> castList;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            targetEntity = PersonCareer.class)
    private List<PersonCareer> personCareerList;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            targetEntity = Award.class)
    private List<Award> awardList;


}
