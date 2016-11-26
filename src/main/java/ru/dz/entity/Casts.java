package ru.dz.entity;

import javax.persistence.*;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class Casts {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film", referencedColumnName = "id")
    private Film film;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person", referencedColumnName = "id")
    private Person person;

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
}
