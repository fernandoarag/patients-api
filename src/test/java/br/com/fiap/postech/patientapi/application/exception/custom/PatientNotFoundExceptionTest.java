package br.com.fiap.postech.patientapi.application.exception.custom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientNotFoundExceptionTest {

    @Test
    void shouldReturnCorrectMessageWhenExceptionIsThrown() {
        String type = "ID";
        String typeValue = "123";
        PatientNotFoundException exception = new PatientNotFoundException(type, typeValue);
        assertEquals("Patient with ID: '123' not found!", exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        PatientNotFoundException exception = new PatientNotFoundException("email", "example@example.com");
        assertEquals("NOT_FOUND", exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        PatientNotFoundException exception = new PatientNotFoundException("email", "example@example.com");
        assertEquals("Patient Not Found", exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatusCode() {
        PatientNotFoundException exception = new PatientNotFoundException("email", "example@example.com");
        assertEquals(404, exception.getStatus());
    }
}