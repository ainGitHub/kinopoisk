package ru.dz.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table
public class Film {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Date year;

    @Column
    private String country;

    @Column
    private String ageLimit;

    @Column
    private String image = "/resources/images/photo/unknown.gif";

    @Column
    private Integer duration;

    @Column(length = 2000)
    private String description;

    @Column
    private String trailer;

    @Column
    @Transient
    private Double rating;

    @Column
    private Integer voters;

    @Column
    private boolean changed = true;

    @Column
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "film")
    @Transient
    private List<Casts> castList;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "film")
    @Transient
    private List<FilmGenre> filmsGenres;

    @OneToMany(cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY,
            mappedBy = "film")
    @Transient
    private List<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public List<Casts> getCastList() {
        return castList;
    }

    public void setCastList(List<Casts> castList) {
        this.castList = castList;
    }

    public List<FilmGenre> getFilmsGenres() {
        return filmsGenres;
    }

    public void setFilmsGenres(List<FilmGenre> filmsGenres) {
        this.filmsGenres = filmsGenres;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVoters() {
        return voters;
    }

    public void setVoters(Integer voters) {
        this.voters = voters;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                ", image='" + image + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", trailer='" + trailer + '\'' +
                ", rating=" + rating +
                ", voters=" + voters +
                ", changed=" + changed +
                ", deleted=" + deleted +
                '}';
    }
}
