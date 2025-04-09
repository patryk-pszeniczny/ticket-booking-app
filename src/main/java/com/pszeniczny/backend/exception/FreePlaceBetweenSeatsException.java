package com.pszeniczny.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class FreePlaceBetweenSeatsException extends RuntimeException {
        public FreePlaceBetweenSeatsException(String message) {
            super(message);
        }
}
