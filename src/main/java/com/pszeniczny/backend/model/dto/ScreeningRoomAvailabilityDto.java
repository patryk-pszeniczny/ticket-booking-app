package com.pszeniczny.backend.model.dto;

import java.util.List;

public record ScreeningRoomAvailabilityDto(
        String screeningRoomName,
        List<SeatDto> availableSeats
) {
}
