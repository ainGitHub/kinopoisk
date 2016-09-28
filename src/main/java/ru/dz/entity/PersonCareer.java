package ru.dz.entity;

import javax.persistence.*;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class PersonCareer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person_id;

    @ManyToOne(targetEntity = Career.class)
    @JoinColumn(name = "career_id", referencedColumnName = "id")
    private Career career_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }

    public Career getCareer_id() {
        return career_id;
    }

    public void setCareer_id(Career career_id) {
        this.career_id = career_id;
    }
}
