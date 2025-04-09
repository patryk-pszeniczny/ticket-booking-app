package com.pszeniczny.backend.repository;

import com.pszeniczny.backend.AbstractContainers;
import com.pszeniczny.backend.model.*;
import com.pszeniczny.backend.model.ticketshandler.TicketType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketRepositoryTest extends AbstractContainers {

    @Autowired
    private TicketRepository underTest;
    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreeningRoomRepository screeningRoomRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Test
    @DirtiesContext
    void itShouldGetAllTicketsByScreening() {
        // Given
        Movie movie = new Movie( "TestTitle", "TestDescription", "TestGenre", 8.5, 120);
        movieRepository.save(movie);
        ScreeningRoom screeningRoom = new ScreeningRoom("A");
        screeningRoomRepository.save(screeningRoom);
        Screening screening = new Screening(movie, screeningRoom, LocalDateTime.now());
        screeningRepository.save(screening);
        Ticket ticket1 = new Ticket(null, null, screening, TicketType.ADULT);
        Ticket ticket2 = new Ticket(null, null, screening, TicketType.ADULT);
        Ticket ticket3 = new Ticket(null, null, screening, TicketType.ADULT);
        underTest.save(ticket1);
        underTest.save(ticket2);
        underTest.save(ticket3);

        // When
        var result = underTest.findAllByScreening(screening);

        // Then
        assertThat(result).containsExactlyInAnyOrder(ticket1, ticket2, ticket3);
    }

    @Test
    @DirtiesContext
    void itShouldGetAllReservedSeatsByScreening() {
        // Given
        Movie movie = new Movie( "TestTitle", "TestDescription", "TestGenre", 8.5, 120);
        movieRepository.save(movie);
        ScreeningRoom screeningRoom = new ScreeningRoom("A");
        screeningRoomRepository.save(screeningRoom);
        Screening screening = new Screening(movie, screeningRoom, LocalDateTime.now());
        screeningRepository.save(screening);
        Seat seat1 = new Seat(screeningRoom, "I", 1);
        Seat seat2 = new Seat(screeningRoom, "I", 2);
        Seat seat3 = new Seat(screeningRoom, "II", 3);
        seatRepository.save(seat1);
        seatRepository.save(seat2);
        seatRepository.save(seat3);
        Ticket ticket1 = new Ticket(null, seat1, screening, TicketType.ADULT);
        Ticket ticket2 = new Ticket(null, seat2, screening, TicketType.ADULT);
        underTest.save(ticket1);
        underTest.save(ticket2);

        // When
        var result = underTest.findReservedSeatsByScreening(screening);
        var result2 = seatRepository.findAll();

        // Then
        assertThat(result2).hasSize(3);
        assertThat(result).containsExactlyInAnyOrder(seat1, seat2);
    }
}