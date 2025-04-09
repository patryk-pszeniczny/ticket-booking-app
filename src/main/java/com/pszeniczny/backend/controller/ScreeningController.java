package com.pszeniczny.backend.controller;

import com.pszeniczny.backend.model.Screening;
import com.pszeniczny.backend.model.Seat;
import com.pszeniczny.backend.model.dto.ScreeningRoomAvailabilityDto;
import com.pszeniczny.backend.model.dto.SeatDto;
import com.pszeniczny.backend.service.ScreeningService;
import com.pszeniczny.backend.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/multiplex/screenings")
public class ScreeningController {

    private final ScreeningService screeningService;
    private final SeatService seatService;

    public ScreeningController(ScreeningService screeningService, SeatService seatService) {
        this.screeningService = screeningService;
        this.seatService = seatService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{screeningId}/seats")
    public ResponseEntity<ScreeningRoomAvailabilityDto> getScreeningRoomSeats(@PathVariable("screeningId") Integer screeningId) {
        Screening screening = screeningService.getScreeningById(screeningId);
        List<Seat> availableSeats = seatService.getAvailableSeatsByScreening(screening);

        List<SeatDto> seatsDto = availableSeats.stream()
                .map(seat -> new SeatDto(seat.getRow(), seat.getNumber()))
                .collect(Collectors.toList());

        ScreeningRoomAvailabilityDto dto = new ScreeningRoomAvailabilityDto(
                screening.getScreeningRoom().getName(),
                seatsDto
        );

        return ResponseEntity.ok(dto);
    }
}
