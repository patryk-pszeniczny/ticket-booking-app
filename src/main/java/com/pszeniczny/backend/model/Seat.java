package com.pszeniczny.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne//(fetch = FetchType.LAZY)
    //@JoinColumn(name = "screening_room_id", nullable = false)
    private ScreeningRoom screeningRoom;

    @Column(nullable = false)
    private String row;

    @Column(nullable = false)
    private Integer number;

    //@Column(nullable = false)
   //private Boolean isAvailable = true;


    public Seat() {
    }

    public Seat(Integer id, ScreeningRoom screeningRoom, String row, Integer number) {
        this.id = id;
        this.screeningRoom = screeningRoom;
        this.row = row;
        this.number = number;
    }

    public Seat(ScreeningRoom screeningRoom, String row, Integer number) {
        this.screeningRoom = screeningRoom;
        this.row = row;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
