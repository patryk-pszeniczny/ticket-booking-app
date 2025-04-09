package com.pszeniczny.backend.model.dto;

import com.pszeniczny.backend.model.ticketshandler.TicketType;

public record TicketRequest(
        Integer seatId,
        TicketType ticketType
) {
}
