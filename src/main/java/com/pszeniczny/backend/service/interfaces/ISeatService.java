package com.pszeniczny.backend.service.interfaces;

import com.pszeniczny.backend.model.Screening;
import com.pszeniczny.backend.model.Seat;

import java.util.List;

public interface ISeatService {

    List<Seat> getAvailableSeatsByScreening(Screening screening);
}
