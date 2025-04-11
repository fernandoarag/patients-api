package br.com.fiap.postech.patientapi.application.exception;

import lombok.Getter;

/**
 * Enum representing different types of errors.
 */
@Getter
public enum ErrorType {
    INVALID_INPUT("Invalid Input"),
    NOT_FOUND("Patient Not Found"),
    INVALID_JSON("Invalid Request Body"),
    ALREADY_EXISTS("Patient Already Exists"),
    DATABASE_INTEGRITY("Database Integrity Error"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    EMAIL_CANNOT_BE_CHANGED("Email Cannot Be Changed");

    private final String title;

    /**
     * Constructs a new ErrorType with the specified title.
     *
     * @param title the title of the error type
     */
    ErrorType(String title) {
        this.title = title;
    }
}