package com.pszeniczny.backend.repository;

import com.pszeniczny.backend.AbstractContainers;
import com.pszeniczny.backend.model.ScreeningRoom;
import com.pszeniczny.backend.model.Seat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SeatRepositoryTest extends AbstractContainers {

    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ScreeningRoomRepository screeningRoomRepository;

    @Test
    @DirtiesContext
    void itShouldGetSeatByRowAndNumber() {
        // Given
        ScreeningRoom screeningRoom = new ScreeningRoom("Room A");
        screeningRoomRepository.save(screeningRoom);

        String row = "I";
        int number = 1;
        Seat seat = new Seat(screeningRoom, row, number);
        seatRepository.save(seat);

        // When
        var result = seatRepository.findByRowAndNumber(row, number);

        // Then
        assertThat(result).isEqualTo(seat);
    }

    @Test
    @DirtiesContext
    void itShouldGetAllSeatsByScreeningRoom() {
        // Given
        ScreeningRoom screeningRoom = new ScreeningRoom("Room A");
        screeningRoomRepository.save(screeningRoom);

        Seat seat1 = new Seat(screeningRoom, "I", 1);
        Seat seat2 = new Seat(screeningRoom, "I", 2);
        Seat seat3 = new Seat(screeningRoom, "I", 3);
        seatRepository.save(seat1);
        seatRepository.save(seat2);
        seatRepository.save(seat3);

        // When
        var result = seatRepository.findAllByScreeningRoom(screeningRoom);

        // Then
        assertThat(result).containsExactlyInAnyOrder(seat1, seat2, seat3);
    }
}