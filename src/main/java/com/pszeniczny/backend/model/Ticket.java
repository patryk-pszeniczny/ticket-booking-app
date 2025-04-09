package com.pszeniczny.backend.model;

import com.pszeniczny.backend.model.ticketshandler.TicketType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne//(fetch = FetchType.LAZY)
    //@JoinColumn(name="reservation_id")
    private Reservation reservation;

    @ManyToOne//(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    private Screening screening;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketType ticketType;


    public Ticket() {
    }

    public Ticket(Integer id, Reservation reservation, Seat seat, Screening screening, TicketType ticketType) {
        this.id = id;
        this.reservation = reservation;
        this.seat = seat;
        this.screening = screening;
        this.ticketType = ticketType;
    }

    public Ticket(Reservation reservation, Seat seat, Screening screening, TicketType ticketType) {
        this.reservation = reservation;
        this.seat = seat;
        this.screening = screening;
        this.ticketType = ticketType;
    }

    public BigDecimal getPrice() {
        return ticketType.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }
}
