package com.pszeniczny.backend.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservationResponse(
        Integer reservationId,
        BigDecimal totalPrice,
        LocalDateTime expirationTime
) {
}
