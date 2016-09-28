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
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film_id;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Film film_id) {
        this.film_id = film_id;
    }

    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }
}
