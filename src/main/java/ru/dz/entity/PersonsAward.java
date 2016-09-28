package ru.dz.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class PersonsAward {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date year;

    @Column
    private String nomination;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person_id;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film_id;

    @ManyToOne(targetEntity = Award.class)
    @JoinColumn(name = "award_id", referencedColumnName = "id")
    private Award award_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }

    public Film getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Film film_id) {
        this.film_id = film_id;
    }

    public Award getAward_id() {
        return award_id;
    }

    public void setAward_id(Award award_id) {
        this.award_id = award_id;
    }
}
