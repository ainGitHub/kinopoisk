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

    @Column
    private String image;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "person")
    private List<Casts> castList;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
           mappedBy = "person")
    private List<PersonCareer> personCareerList;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "person")
    private List<PersonsAward> personsAwardsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Casts> getCastList() {
        return castList;
    }

    public void setCastList(List<Casts> castList) {
        this.castList = castList;
    }

    public List<PersonCareer> getPersonCareerList() {
        return personCareerList;
    }

    public void setPersonCareerList(List<PersonCareer> personCareerList) {
        this.personCareerList = personCareerList;
    }

    public List<PersonsAward> getPersonsAwardsList() {
        return personsAwardsList;
    }

    public void setPersonsAwardsList(List<PersonsAward> personsAwardsList) {
        this.personsAwardsList = personsAwardsList;
    }
}
