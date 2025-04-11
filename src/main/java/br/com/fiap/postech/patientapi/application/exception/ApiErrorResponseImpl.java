package br.com.fiap.postech.patientapi.application.exception;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Implementation of the ApiErrorResponse interface.
 * Represents an API error response with details about the error.
 */
@Getter
public class ApiErrorResponseImpl implements ApiErrorResponse {
    private final String type;
    private final String title;
    private final int status;
    private final String detail;
    private final LocalDateTime timestamp;

    /**
     * Constructs a new ApiErrorResponseImpl with the specified details.
     *
     * @param type   the type of error
     * @param title  the title of the error
     * @param status the HTTP status code of the error
     * @param detail the detailed message of the error
     */
    public ApiErrorResponseImpl(String type, String title, int status, String detail) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timestamp = LocalDateTime.now();
    }
}