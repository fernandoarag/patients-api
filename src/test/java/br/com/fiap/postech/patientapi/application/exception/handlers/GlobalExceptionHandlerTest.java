package br.com.fiap.postech.patientapi.application.exception.handlers;

import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.patientapi.application.exception.custom.InvalidPatientDataException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientCpfAlreadyExistsException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final PatientExceptionHandler patientExceptionHandler = mock(PatientExceptionHandler.class);
    private final InterfaceExceptionHandler interfaceExceptionHandler = mock(InterfaceExceptionHandler.class);
    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler(patientExceptionHandler, interfaceExceptionHandler);

    @Test
    void shouldHandlePatientNotFoundExceptionAndReturnApiErrorResponse() {
        PatientNotFoundException exception = new PatientNotFoundException("ID", "123");
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl("NOT_FOUND", "Not Found", 404, "Patient with ID: '123' not found!");

        when(patientExceptionHandler.handlePatientNotFoundException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handlePatientNotFoundException(exception);

        assertEquals(expectedResponse, response);
        verify(patientExceptionHandler, times(1)).handlePatientNotFoundException(exception);
    }

    @Test
    void shouldHandlePatientCpfAlreadyExistsExceptionAndReturnApiErrorResponse() {
        PatientCpfAlreadyExistsException exception = new PatientCpfAlreadyExistsException("123.456.789-09");
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl("ALREADY_EXISTS", "Already Exists", 409, "Patient with cpf: '123.456.789-09' already exists!");

        when(patientExceptionHandler.handlePatientAlreadyExistsException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handlePatientAlreadyExistsException(exception);

        assertEquals(expectedResponse, response);
        verify(patientExceptionHandler, times(1)).handlePatientAlreadyExistsException(exception);
    }

    @Test
    void shouldHandleInvalidPatientDataExceptionAndReturnApiErrorResponse() {
        InvalidPatientDataException exception = new InvalidPatientDataException("Invalid data");
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl("INVALID_JSON", "Invalid JSON", 406, "Invalid data");

        when(patientExceptionHandler.handleInvalidPatientDataException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handlePatientEmailCannotBeBlankException(exception);

        assertEquals(expectedResponse, response);
        verify(patientExceptionHandler, times(1)).handleInvalidPatientDataException(exception);
    }

    @Test
    void shouldHandleDataIntegrityViolationExceptionAndReturnApiErrorResponse() {
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Database error");
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl("DATABASE_ERROR", "Database Error", 500, "Database error");

        when(patientExceptionHandler.handleDatabaseIntegrityViolation(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleDatabaseException(exception);

        assertEquals(expectedResponse, response);
        verify(patientExceptionHandler, times(1)).handleDatabaseIntegrityViolation(exception);
    }

    @Test
    void shouldHandleMethodArgumentNotValidExceptionAndReturnApiErrorResponse() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        ApiErrorResponseImpl expectedResponse = new ApiErrorResponseImpl("VALIDATION_ERROR", "Validation Error", 400, "Invalid method argument");

        when(interfaceExceptionHandler.handleMethodArgumentNotValidException(exception)).thenReturn(expectedResponse);

        ApiErrorResponseImpl response = globalExceptionHandler.handleMethodArgumentNotValidException(exception);

        assertEquals(expectedResponse, response);
        verify(interfaceExceptionHandler, times(1)).handleMethodArgumentNotValidException(exception);
    }
}
