package com.pszeniczny.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screening_room_id", nullable = false)
    private ScreeningRoom screeningRoom;

    @Column(nullable = false)
    private LocalDateTime screeningStartTime;

    public Screening() {
    }

    public Screening(Integer id, Movie movie, ScreeningRoom screeningRoom, LocalDateTime screeningStartTime) {
        this.id = id;
        this.movie = movie;
        this.screeningRoom = screeningRoom;
        this.screeningStartTime = screeningStartTime;
    }

    public Screening(Movie movie, ScreeningRoom screeningRoom, LocalDateTime screeningStartTime) {
        this.movie = movie;
        this.screeningRoom = screeningRoom;
        this.screeningStartTime = screeningStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    public LocalDateTime getScreeningStartTime() {
        return screeningStartTime;
    }

    public void setScreeningStartTime(LocalDateTime screeningTime) {
        this.screeningStartTime = screeningTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }




}
