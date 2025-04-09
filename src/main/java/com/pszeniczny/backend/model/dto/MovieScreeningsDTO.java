package com.pszeniczny.backend.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MovieScreeningsDTO(
        String title,
        List<LocalDateTime> screeningTimes) {
}
