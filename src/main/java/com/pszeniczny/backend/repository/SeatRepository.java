package com.pszeniczny.backend.repository;

import com.pszeniczny.backend.model.ScreeningRoom;
import com.pszeniczny.backend.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findAllByScreeningRoom(ScreeningRoom screeningRoom);

    Seat findByRowAndNumber(String row, Integer number);


}
