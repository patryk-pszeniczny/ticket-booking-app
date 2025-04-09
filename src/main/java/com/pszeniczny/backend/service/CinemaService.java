package com.pszeniczny.backend.service;

import com.pszeniczny.backend.exception.FreePlaceBetweenSeatsException;
import com.pszeniczny.backend.exception.ReservationTooLateException;
import com.pszeniczny.backend.exception.ResourceNotFoundException;
import com.pszeniczny.backend.exception.SeatAlreadyReservedException;
import com.pszeniczny.backend.model.Reservation;
import com.pszeniczny.backend.model.Screening;
import com.pszeniczny.backend.model.Seat;
import com.pszeniczny.backend.model.Ticket;
import com.pszeniczny.backend.model.dto.MovieScreeningsDTO;
import com.pszeniczny.backend.model.dto.ReservationRequest;
import com.pszeniczny.backend.model.dto.ReservationResponse;
import com.pszeniczny.backend.model.dto.TicketRequest;
import com.pszeniczny.backend.repository.ReservationRepository;
import com.pszeniczny.backend.repository.ScreeningRepository;
import com.pszeniczny.backend.repository.SeatRepository;
import com.pszeniczny.backend.repository.TicketRepository;
import com.pszeniczny.backend.service.interfaces.ICinemaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CinemaService implements ICinemaService {

    private final ScreeningRepository screeningRepository;
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;

    public CinemaService(ScreeningRepository screeningRepository, ReservationRepository reservationRepository, SeatRepository seatRepository, TicketRepository ticketRepository) {
        this.screeningRepository = screeningRepository;
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
        this.ticketRepository = ticketRepository;
    }


    @Override
    public List<MovieScreeningsDTO> getAllScreenings(LocalDateTime start, LocalDateTime end) {
        List<Screening> screenings = screeningRepository.findAllByScreeningStartTimeBetween(start, end);

        screenings.sort(
                Comparator.comparing((Screening s) -> s.getMovie().getTitle())
                        .thenComparing(Screening::getScreeningStartTime)
        );

        final Map<String, List<LocalDateTime>> screeningsMap = screenings.stream()
                .collect(Collectors.groupingBy(
                        screening -> screening.getMovie().getTitle(),
                        TreeMap::new,
                        Collectors.mapping(Screening::getScreeningStartTime, Collectors.toList())
                ));

        return screeningsMap.entrySet().stream()
                .map(entry -> new MovieScreeningsDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ReservationResponse createReservation(ReservationRequest request) {
        Screening screening = screeningRepository.findById(request.screeningId())
                .orElseThrow(() -> new EntityNotFoundException("Screening not found"));

        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(now, screening.getScreeningStartTime()).toMinutes() < 15) {
            throw new ReservationTooLateException("Reservations are only allowed up to 15 minutes before screening start time.");
        }


        List<Seat> selectedSeats = request.tickets().stream()
                .map(ticketRequest -> seatRepository.findById(ticketRequest.seatId())
                        .orElseThrow(() -> new ResourceNotFoundException("Seat not found")))
                .collect(Collectors.toList());

        Set<Seat> reservedSeats = ticketRepository.findReservedSeatsByScreening(screening);


        for (Seat selectedSeat : selectedSeats) {
            if (reservedSeats.contains(selectedSeat)) {
                throw new SeatAlreadyReservedException("Seat " + selectedSeat.getId() + " is already reserved.");
            }
        }

        if (!areSeatsAvailableAndNoSingleGaps(screening, selectedSeats)) {
            throw new FreePlaceBetweenSeatsException("Selected seats are not available or there is a single seat gap.");
        }


        Reservation reservation = new Reservation();
        reservation.setName(request.name());
        reservation.setSurname(request.surname());
        reservation.setScreening(screening);
        reservation.setReservationTime(now);
        reservation.setExpirationTime(screening.getScreeningStartTime().minusMinutes(15));

        List<Ticket> tickets = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (TicketRequest ticketRequest : request.tickets()) {
            Seat seat = selectedSeats.stream()
                    .filter(selectedSeat -> selectedSeat.getId().equals(ticketRequest.seatId()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));
//            Seat seat = seatRepository.findById(ticketRequest.seatId())
//                    .orElseThrow(() -> new EntityNotFoundException("Seat not found"));

            Ticket ticket = new Ticket();
            ticket.setSeat(seat);
            ticket.setScreening(screening);
            ticket.setTicketType(ticketRequest.ticketType());
            ticket.setReservation(reservation);

            totalPrice = totalPrice.add(ticketRequest.ticketType().getPrice());
            tickets.add(ticket);
        }

        reservation.setTickets(new HashSet<>(tickets));
        reservation.setTotalPrice(totalPrice);
        reservationRepository.save(reservation);

        return new ReservationResponse(reservation.getId(), totalPrice, reservation.getExpirationTime());
    }

    private boolean areSeatsAvailableAndNoSingleGaps(Screening screening, List<Seat> selectedSeats) {
        Set<Seat> reservedSeats = ticketRepository.findReservedSeatsByScreening(screening);

        if (selectedSeats.stream().anyMatch(reservedSeats::contains)) {
            return false;
        }

        Map<String, List<Seat>> selectedSeatsByRow = selectedSeats.stream()
                .collect(Collectors.groupingBy(Seat::getRow));

        for (Map.Entry<String, List<Seat>> entry : selectedSeatsByRow.entrySet()) {
            List<Seat> rowSeats = entry.getValue();
            rowSeats.sort(Comparator.comparingInt(Seat::getNumber));

            for (int i = 0; i < rowSeats.size() - 1; i++) {
                Seat currentSeat = rowSeats.get(i);
                Seat nextSeat = rowSeats.get(i + 1);
                int gap = nextSeat.getNumber() - currentSeat.getNumber();

                if (gap > 1) {
                    for (int seatNumber = currentSeat.getNumber() + 1; seatNumber < nextSeat.getNumber(); seatNumber++) {
                        Seat inBetweenSeat = seatRepository.findByRowAndNumber(currentSeat.getRow(), seatNumber);
                        if (inBetweenSeat != null && !reservedSeats.contains(inBetweenSeat)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
