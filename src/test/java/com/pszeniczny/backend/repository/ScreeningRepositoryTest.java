package com.pszeniczny.backend.repository;

import com.pszeniczny.backend.AbstractContainers;
import com.pszeniczny.backend.model.Movie;
import com.pszeniczny.backend.model.Screening;
import com.pszeniczny.backend.model.ScreeningRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ScreeningRepositoryTest extends AbstractContainers {

    @Autowired
    private ScreeningRepository underTest;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreeningRoomRepository screeningRoomRepository;

    @Test
    @DirtiesContext
    void itShouldReturnAllScreeningsBetweenTimeInterval() {
        // Given
        Movie movie = new Movie( "TestTitle", "TestDescription", "TestGenre", 8.5, 120);
        movieRepository.save(movie);
        ScreeningRoom screeningRoom = new ScreeningRoom(1, "A");
        screeningRoomRepository.save(screeningRoom);
        LocalDateTime timeNow = LocalDateTime.now();
        Screening screening1 = new Screening(movie, screeningRoom, timeNow);
        Screening screening2 = new Screening(movie, screeningRoom, timeNow.plusHours(2));
        Screening screening3 = new Screening(movie, screeningRoom, timeNow.plusHours(5));
        underTest.save(screening1);
        underTest.save(screening2);
        underTest.save(screening3);

        // When
        var screenings = underTest.findAllByScreeningStartTimeBetween(timeNow, timeNow.plusHours(3));
        System.out.println(screenings.size());

        // Then
        assertThat(screenings).hasSize(2);
    }
}