package br.com.fiap.postech.patientapi.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApiErrorResponseImplTest {

    @Test
    void shouldReturnNonNullTimestampWhenCreated() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("ERROR_TYPE", "Error Title", 500, "Error detail");
        assertNotNull(response.getTimestamp());
    }

    @Test
    void shouldReturnCorrectDetailWhenProvided() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("ERROR_TYPE", "Error Title", 500, "Specific error detail");
        assertEquals("Specific error detail", response.getDetail());
    }

    @Test
    void shouldHandleNullDetailWithoutThrowingException() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("ERROR_TYPE", "Error Title", 500, null);
        assertNull(response.getDetail());
    }

    @Test
    void shouldReturnCorrectStatusCodeForError() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("ERROR_TYPE", "Error Title", 500, "Error detail");
        assertEquals(500, response.getStatus());
    }

    @Test
    void shouldReturnCorrectTypeForError() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("ERROR_TYPE", "Error Title", 500, "Error detail");
        assertEquals("ERROR_TYPE", response.getType());
    }
}
