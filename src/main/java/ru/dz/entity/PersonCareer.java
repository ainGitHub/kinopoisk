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
    @JoinColumn(name = "personId", referencedColumnName = "id")
    private Person personId;

    @ManyToOne(targetEntity = Career.class)
    @JoinColumn(name = "careerId", referencedColumnName = "id")
    private Career careerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Career getCareerId() {
        return careerId;
    }

    public void setCareerId(Career careerId) {
        this.careerId = careerId;
    }
}
