package br.com.fiap.postech.patientapi.application.exception.custom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidInputExceptionTest {

    @Test
    void shouldReturnCorrectMessageWhenExceptionIsThrown() {
        String message = "Invalid input provided";
        InvalidInputException exception = new InvalidInputException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        InvalidInputException exception = new InvalidInputException("Invalid input");
        assertEquals("INVALID_INPUT", exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        InvalidInputException exception = new InvalidInputException("Invalid input");
        assertEquals("Invalid Input", exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatusCode() {
        InvalidInputException exception = new InvalidInputException("Invalid input");
        assertEquals(400, exception.getStatus());
    }
}