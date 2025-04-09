package com.pszeniczny.backend.model.dto;

import java.util.List;

public record ScreeningDTO(
        String screeningRoomName,
        List<String> availableSeats) {
}
