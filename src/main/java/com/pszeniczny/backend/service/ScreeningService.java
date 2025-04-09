package com.pszeniczny.backend.service;

import com.pszeniczny.backend.exception.ResourceNotFoundException;
import com.pszeniczny.backend.model.Screening;
import com.pszeniczny.backend.repository.ScreeningRepository;
import com.pszeniczny.backend.service.interfaces.IScreeningService;
import org.springframework.stereotype.Service;

@Service
public class ScreeningService implements IScreeningService {
    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public Screening getScreeningById(Integer id) {
        return screeningRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Screening with id: " + id + ", not found"));
    }
}
