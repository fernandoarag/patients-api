package br.com.fiap.postech.patientapi.application.exception.custom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidPatientDataExceptionTest {

    @Test
    void shouldReturnCorrectMessageWhenExceptionIsThrown() {
        String message = "Invalid patient data provided";
        InvalidPatientDataException exception = new InvalidPatientDataException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        InvalidPatientDataException exception = new InvalidPatientDataException("Invalid data");
        assertEquals("INVALID_JSON", exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        InvalidPatientDataException exception = new InvalidPatientDataException("Invalid data");
        assertEquals("Invalid Request Body", exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatusCode() {
        InvalidPatientDataException exception = new InvalidPatientDataException("Invalid data");
        assertEquals(406, exception.getStatus());
    }
}