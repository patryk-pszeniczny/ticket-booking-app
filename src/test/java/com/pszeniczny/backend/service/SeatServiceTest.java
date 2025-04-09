package com.pszeniczny.backend.service;

import com.pszeniczny.backend.model.Screening;
import com.pszeniczny.backend.model.ScreeningRoom;
import com.pszeniczny.backend.model.Seat;
import com.pszeniczny.backend.model.Ticket;
import com.pszeniczny.backend.model.ticketshandler.TicketType;
import com.pszeniczny.backend.repository.SeatRepository;
import com.pszeniczny.backend.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;
    @Mock
    private TicketRepository ticketRepository;
    private SeatService underTest;

    @BeforeEach
    void setup() {
        underTest = new SeatService(seatRepository, ticketRepository);
    }

    @Test
    void itShouldGetAvailableSeatsByScreening() {
        // Given
        Screening screening = mock(Screening.class);
        Seat seat1 = new Seat(screening.getScreeningRoom(), "I", 1);
        Seat seat2 = new Seat(screening.getScreeningRoom(), "I", 2);
        Seat seat3 = new Seat(screening.getScreeningRoom(), "I", 3);

        Ticket ticket1 = new Ticket(null, seat1, screening, TicketType.ADULT);
        Ticket ticket2 = new Ticket(null, seat2, screening, TicketType.ADULT);
        given(screening.getScreeningRoom()).willReturn(new ScreeningRoom(1, "A"));
        given(seatRepository.findAllByScreeningRoom(screening.getScreeningRoom())).willReturn(List.of(seat1, seat2, seat3));
        given(ticketRepository.findAllByScreening(screening)).willReturn(List.of(ticket1, ticket2));

        // When
        List<Seat> actual = underTest.getAvailableSeatsByScreening(screening);

        // Then
        assertThat(actual).hasSize(1);
        assertThat(actual).containsExactlyInAnyOrder(seat3);

    }
}