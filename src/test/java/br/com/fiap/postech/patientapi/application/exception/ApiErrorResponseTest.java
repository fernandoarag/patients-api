package br.com.fiap.postech.patientapi.application.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApiErrorResponseTest {

    @Test
    void shouldReturnCorrectType() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("NOT_FOUND", "Not Found", 404, "Resource not found");
        assertEquals("NOT_FOUND", response.getType());
    }

    @Test
    void shouldReturnCorrectTitle() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("NOT_FOUND", "Not Found", 404, "Resource not found");
        assertEquals("Not Found", response.getTitle());
    }

    @Test
    void shouldReturnCorrectStatus() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("NOT_FOUND", "Not Found", 404, "Resource not found");
        assertEquals(404, response.getStatus());
    }

    @Test
    void shouldHandleEmptyMessageGracefully() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("BAD_REQUEST", "Bad Request", 400, "");
        assertEquals("", response.getDetail());
    }

    @Test
    void shouldHandleNullMessageGracefully() {
        ApiErrorResponseImpl response = new ApiErrorResponseImpl("BAD_REQUEST", "Bad Request", 400, null);
        assertNull(response.getDetail());
    }
}