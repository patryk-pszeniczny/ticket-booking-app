package com.pszeniczny.backend.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "screening_rooms")
public class ScreeningRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    public ScreeningRoom() {
    }

    public ScreeningRoom(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ScreeningRoom(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
