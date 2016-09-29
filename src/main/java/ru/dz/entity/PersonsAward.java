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
    @JoinColumn(name = "personId", referencedColumnName = "id")
    private Person personId;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "filmId", referencedColumnName = "id")
    private Film filmId;

    @ManyToOne(targetEntity = Award.class)
    @JoinColumn(name = "awardId", referencedColumnName = "id")
    private Award awardId;

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

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Film getFilmId() {
        return filmId;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }

    public Award getAwardId() {
        return awardId;
    }

    public void setAwardId(Award awardId) {
        this.awardId = awardId;
    }
}
