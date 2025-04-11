package br.com.fiap.postech.patientapi.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorTypeTest {

    @Test
    void shouldReturnCorrectTitleForInvalidInput() {
        assertEquals("Invalid Input", ErrorType.INVALID_INPUT.getTitle());
    }

    @Test
    void shouldReturnCorrectTitleForNotFound() {
        assertEquals("Patient Not Found", ErrorType.NOT_FOUND.getTitle());
    }

    @Test
    void shouldReturnCorrectTitleForInvalidJson() {
        assertEquals("Invalid Request Body", ErrorType.INVALID_JSON.getTitle());
    }

    @Test
    void shouldReturnCorrectTitleForAlreadyExists() {
        assertEquals("Patient Already Exists", ErrorType.ALREADY_EXISTS.getTitle());
    }

    @Test
    void shouldReturnCorrectTitleForDatabaseIntegrity() {
        assertEquals("Database Integrity Error", ErrorType.DATABASE_INTEGRITY.getTitle());
    }

    @Test
    void shouldReturnCorrectTitleForInternalServerError() {
        assertEquals("Internal Server Error", ErrorType.INTERNAL_SERVER_ERROR.getTitle());
    }

    @Test
    void shouldReturnCorrectTitleForEmailCannotBeChanged() {
        assertEquals("Email Cannot Be Changed", ErrorType.EMAIL_CANNOT_BE_CHANGED.getTitle());
    }
}