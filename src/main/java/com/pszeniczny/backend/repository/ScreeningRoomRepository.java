package com.pszeniczny.backend.repository;

import com.pszeniczny.backend.model.ScreeningRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRoomRepository extends JpaRepository<ScreeningRoom, Integer> {
}
