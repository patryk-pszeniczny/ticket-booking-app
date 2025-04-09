package com.pszeniczny.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReservationTooLateException extends RuntimeException{
    public ReservationTooLateException(String message) {
        super(message);
    }
}
