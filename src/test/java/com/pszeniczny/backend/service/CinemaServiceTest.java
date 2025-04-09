package com.pszeniczny.backend.service;

import com.pszeniczny.backend.repository.ReservationRepository;
import com.pszeniczny.backend.repository.ScreeningRepository;
import com.pszeniczny.backend.repository.SeatRepository;
import com.pszeniczny.backend.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CinemaServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private SeatRepository seatRepository;
    private CinemaService underTest;

    @Test
    void itShouldGetAllScreenings() {
        // Given
    }
}