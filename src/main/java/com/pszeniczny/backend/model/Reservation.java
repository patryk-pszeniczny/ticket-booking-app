package com.pszeniczny.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*", message = "Name must start with a capital letter and only contain letters")
    private String name;

    @Column(nullable = false)
    @Size(min = 3, message = "Surname must be at least 3 characters long")
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*(-[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*)?$", message = "Surname must start with a capital letter and can contain a hyphen for compound surnames")

    private String surname;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @Column(nullable = false)
    private LocalDateTime expirationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//, cascade = CascadeType.ALL)
    @NotEmpty(message = "You must select at least one ticket")
    private Set<Ticket> tickets = new HashSet<>();

    private BigDecimal totalPrice;

    public Reservation() {
    }

    public Reservation(Integer id, String name, String surname, LocalDateTime reservationTime, LocalDateTime expirationTime, Screening screening, Set<Ticket> tickets, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.reservationTime = reservationTime;
        this.expirationTime = expirationTime;
        this.screening = screening;
        this.tickets = tickets;
        this.totalPrice = totalPrice;
    }

    public Reservation(String name, String surname, LocalDateTime reservationTime, LocalDateTime expirationTime, Screening screening, Set<Ticket> tickets, BigDecimal totalPrice) {
        this.name = name;
        this.surname = surname;
        this.reservationTime = reservationTime;
        this.expirationTime = expirationTime;
        this.screening = screening;
        this.tickets = tickets;
        this.totalPrice = totalPrice;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    //    public void checkExpirationDate() {
//        LocalDateTime quarterAfterReservationTime = this.reservationTime.plusMinutes(15);
//        LocalDateTime quarterBeforeScreeningTime = screeningStartTime.minusMinutes(15);
//        this.expirationTime = quarterAfterReservationTime.isBefore(quarterBeforeScreeningTime) ? quarterAfterReservationTime : quarterBeforeScreeningTime;
//        if (LocalDateTime.now().isAfter(this.expirationTime)) {
//            throw new ReservationAlreadyExpiredException();
//        }
//    }


}
