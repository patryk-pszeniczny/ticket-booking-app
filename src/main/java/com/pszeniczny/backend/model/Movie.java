package com.pszeniczny.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies", uniqueConstraints = {
        @UniqueConstraint(name = "movie_title_unique", columnNames = "title")
})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private Integer durationTime;

    public Movie() {
    }

    public Movie(Integer id, String title, String description, String genre, Double rating, Integer durationTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.durationTime = durationTime;
    }

    public Movie(String title, String description, String genre, Double rating, Integer durationTime) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.durationTime = durationTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }


}
