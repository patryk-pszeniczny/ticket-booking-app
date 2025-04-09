package com.pszeniczny.backend.service;

import com.pszeniczny.backend.exception.ResourceNotFoundException;
import com.pszeniczny.backend.model.Screening;
import com.pszeniczny.backend.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;
    private ScreeningService underTest;

    @BeforeEach
    void setup() {
        underTest = new ScreeningService(screeningRepository);
    }

    @Test
    void itShouldGetScreeningById() {
        // Given
        int id = 1;
        Screening screening = new Screening(id, null, null, null);
        given(screeningRepository.findById(id)).willReturn(Optional.of(screening));

        // When
        Screening actual = underTest.getScreeningById(id);

        // Then
        assertThat(actual).isEqualTo(screening);
    }

    @Test
    void itShouldThrowExceptionWhenGetScreeningNotFound() {
        // Given
        int id = 1;
        given(screeningRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> underTest.getScreeningById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Screening with id: " + id + ", not found");
    }
}