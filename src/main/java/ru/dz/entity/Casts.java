package ru.dz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class Casts {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film", referencedColumnName = "id")
    @JsonIgnore
    private Film film;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person", referencedColumnName = "id")
    @JsonIgnore
    private Person person;

    @ManyToOne(targetEntity = Career.class)
    @JoinColumn(name = "role", referencedColumnName = "id")
    @JsonIgnore
    private Career role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Career getRole() {
        return role;
    }

    public void setRole(Career role) {
        this.role = role;
    }

    public Casts() {
    }

    public Casts(Film film, Person person, Career role) {
        this.film = film;
        this.person = person;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Casts{" +
                "id=" + id +
                ", film=" + film +
                ", person=" + person +
                ", role=" + role +
                '}';
    }
}
