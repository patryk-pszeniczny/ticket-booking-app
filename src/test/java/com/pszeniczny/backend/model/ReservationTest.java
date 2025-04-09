package com.pszeniczny.backend.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Validator validator;
    private Reservation reservation;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        reservation = new Reservation();
        reservation.setTickets(Set.of(new Ticket(null, null, null,null)));
    }

    @Test
    void itShouldNotPassValidationIfNameTooShort() {
        // Given
        reservation.setName("Ja");
        // When
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
        // Then
        assertFalse(violations.isEmpty(), "Expected min 3 characters in name");
    }

    @Test
    void itShouldNotPassValidationIfSurnameTooShort() {
        // Given
        reservation.setSurname("Ja");
        // When
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
        // Then
        assertFalse(violations.isEmpty(), "Expected min 3 characters in surname");
    }

    @Test
    void itShouldNotPassValidationIfNameNotStartWithCapitalLetter() {
        // Given
        reservation.setName("pawel");
        // When
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
        // Then
        assertFalse(violations.isEmpty(), "Expected name to start with capital letter");
    }

    @Test
    void itShouldNotPassValidationIfSurnameNotStartWithCapitalLetter() {
        // Given
        reservation.setSurname("baczkowski");
        // When
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
        // Then
        assertFalse(violations.isEmpty(), "Expected surname to start with capital letter");
    }

    @Test
    void itShouldNotPassValidationIfSecondSurnameNotStartWithCapitalLetter() {
        // Given
        reservation.setSurname("Baczkowski-myslowicz");
        // When
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
        // Then
        assertFalse(violations.isEmpty(), "Expected second surname to start with capital letter");
    }

    @Test
    void itShouldNotPassValidationIfThereAreNotAnyTickets() {
        // Given
        Reservation reservation = new Reservation();
        // When
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);
        // Then
        assertFalse(violations.isEmpty(), "Expected minimum one ticket");
    }


}