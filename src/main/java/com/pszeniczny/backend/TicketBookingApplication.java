package com.pszeniczny.backend;

import com.pszeniczny.backend.repository.MovieRepository;
import com.pszeniczny.backend.repository.ScreeningRepository;
import com.pszeniczny.backend.repository.ScreeningRoomRepository;
import com.pszeniczny.backend.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(MovieRepository movieRepository, ScreeningRepository screeningRepository, ScreeningRoomRepository screeningRoomRepository, SeatRepository seatRepository) {
		return args -> {
			// MOVIES
//			Movie movie1 = new Movie(1, "Avengers: Infinity War", "The Avengers must stop Thanos...", "Action", 8.5, 160);
//			Movie movie2 = new Movie(2, "The Godfather", "The aging patriarch of an organized crime dynasty...", "Drama", 9.2, 125);
//			Movie movie3 = new Movie(3, "Forrest Gump", "The presidencies of Kennedy and Johnson...", "Drama", 8.8, 110);
//			movieRepository.save(movie1);
//			movieRepository.save(movie2);
//			movieRepository.save(movie3);
//
//			// SCREENING ROOMS
//			ScreeningRoom room1 = new ScreeningRoom(1, "Room A");
//			ScreeningRoom room2 = new ScreeningRoom(2, "Room B");
//			ScreeningRoom room3 = new ScreeningRoom(3, "Room C");
//
//			screeningRoomRepository.save(room1);
//			screeningRoomRepository.save(room2);
//			screeningRoomRepository.save(room3);
//
//			// SCREENINGS
//			screeningRepository.save(new Screening(1, movie1, room1, LocalDateTime.of(2023, 12, 10, 15, 0, 0)));
//			screeningRepository.save(new Screening(2, movie2, room2, LocalDateTime.of(2023, 12, 10, 20, 0, 0)));
//			screeningRepository.save(new Screening(3, movie3, room3, LocalDateTime.of(2023, 12, 10, 13, 0, 0)));
//			screeningRepository.save(new Screening(4, movie1, room1, LocalDateTime.of(2023, 12, 10, 10, 0, 0)));
//
//			// SEATS
//			Seat seat1 = new Seat(1, room1, "I", 1);
//			Seat seat2 = new Seat(2, room1, "I", 2);
//			Seat seat3 = new Seat(3, room1, "I", 3);
//			Seat seat4 = new Seat(4, room1, "I", 4);
//			Seat seat5 = new Seat(5, room1, "I", 5);
//
//			seatRepository.save(seat1);
//			seatRepository.save(seat2);
//			seatRepository.save(seat3);
//			seatRepository.save(seat4);
//			seatRepository.save(seat5);
		};
	}

}
