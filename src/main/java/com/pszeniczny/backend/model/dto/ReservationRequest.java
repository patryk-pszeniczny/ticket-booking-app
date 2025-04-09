package com.pszeniczny.backend.model.dto;

import java.util.List;

public record ReservationRequest(
        String name,
        String surname,
        Integer screeningId,
        List<TicketRequest> tickets
) {
}
