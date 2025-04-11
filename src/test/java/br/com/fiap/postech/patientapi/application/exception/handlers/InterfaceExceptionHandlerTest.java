package br.com.fiap.postech.patientapi.application.exception.handlers;

import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.patientapi.application.exception.custom.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InterfaceExceptionHandlerTest {

    private final InterfaceExceptionHandler interfaceExceptionHandler = new InterfaceExceptionHandler();

    @Test
    void shouldHandleInvalidInputExceptionAndReturnApiErrorResponse() {
        InvalidInputException exception = new InvalidInputException("Invalid input provided");
        ApiErrorResponseImpl response = interfaceExceptionHandler.handleInvalidInputException(exception);

        assertEquals("INVALID_INPUT", response.getType());
        assertEquals("Invalid Input", response.getTitle());
        assertEquals(400, response.getStatus());
        assertEquals("Invalid input provided", response.getDetail());
    }

    @Test
    void shouldHandleMethodArgumentNotValidExceptionAndReturnApiErrorResponse() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "must not be null");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        ApiErrorResponseImpl response = interfaceExceptionHandler.handleMethodArgumentNotValidException(exception);

        assertEquals("INVALID_INPUT", response.getType());
        assertEquals("Invalid Input", response.getTitle());
        assertEquals(400, response.getStatus());
        assertTrue(response.getDetail().contains("fieldName - must not be null"));
    }
}