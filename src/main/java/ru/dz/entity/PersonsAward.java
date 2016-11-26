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
    @JoinColumn(name = "person", referencedColumnName = "id")
    private Person person;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film", referencedColumnName = "id")
    private Film film;

    @ManyToOne(targetEntity = Award.class)
    @JoinColumn(name = "award", referencedColumnName = "id")
    private Award award;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }
}
