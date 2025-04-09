package com.pszeniczny.backend.service.interfaces;

import com.pszeniczny.backend.model.dto.MovieScreeningsDTO;
import com.pszeniczny.backend.model.dto.ReservationRequest;
import com.pszeniczny.backend.model.dto.ReservationResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ICinemaService {

        List<MovieScreeningsDTO> getAllScreenings(LocalDateTime start, LocalDateTime end);
        ReservationResponse createReservation(ReservationRequest request);

}
