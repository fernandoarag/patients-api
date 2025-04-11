package br.com.fiap.postech.patientapi.application.exception.handlers;

import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.patientapi.application.exception.custom.InvalidPatientDataException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientCpfAlreadyExistsException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientExceptionHandlerTest {

    private final PatientExceptionHandler patientExceptionHandler = new PatientExceptionHandler();

    @Test
    void shouldReturnApiErrorResponseForPatientNotFoundException() {
        PatientNotFoundException exception = new PatientNotFoundException("ID", "123");
        ApiErrorResponseImpl response = patientExceptionHandler.handlePatientNotFoundException(exception);

        assertEquals("NOT_FOUND", response.getType());
        assertEquals("Patient Not Found", response.getTitle());
        assertEquals(404, response.getStatus());
        assertEquals("Patient with ID: '123' not found!", response.getDetail());
    }

    @Test
    void shouldReturnApiErrorResponseForPatientCpfAlreadyExistsException() {
        PatientCpfAlreadyExistsException exception = new PatientCpfAlreadyExistsException("123.456.789-09");
        ApiErrorResponseImpl response = patientExceptionHandler.handlePatientAlreadyExistsException(exception);

        assertEquals("EMAIL_CANNOT_BE_CHANGED", response.getType());
        assertEquals("Email Cannot Be Changed", response.getTitle());
        assertEquals(409, response.getStatus());
        assertEquals("Patient with cpf: '123.456.789-09' already exists!", response.getDetail());
    }

    @Test
    void shouldReturnApiErrorResponseForInvalidPatientDataException() {
        InvalidPatientDataException exception = new InvalidPatientDataException("Invalid data");
        ApiErrorResponseImpl response = patientExceptionHandler.handleInvalidPatientDataException(exception);

        assertEquals("INVALID_JSON", response.getType());
        assertEquals("Invalid Request Body", response.getTitle());
        assertEquals(406, response.getStatus());
        assertEquals("Invalid data", response.getDetail());
    }

    @Test
    void shouldReturnApiErrorResponseForDataIntegrityViolationException() {
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Database error");
        ApiErrorResponseImpl response = patientExceptionHandler.handleDatabaseIntegrityViolation(exception);

        assertEquals("INTERNAL_SERVER_ERROR", response.getType());
        assertEquals("Internal Server Error", response.getTitle());
        assertEquals(400, response.getStatus());
        assertEquals("Database error", response.getDetail());
    }
}