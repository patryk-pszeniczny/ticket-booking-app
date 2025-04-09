package com.pszeniczny.backend.repository;


import com.pszeniczny.backend.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

    //@Query("SELECT s FROM Screening s JOIN s.movie m WHERE s.screeningStartTime BETWEEN :start AND :end ORDER BY m.title ASC")
    List<Screening> findAllByScreeningStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
