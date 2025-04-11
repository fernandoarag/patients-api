package br.com.fiap.postech.patientapi.application.exception.custom;

import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponse;
import br.com.fiap.postech.patientapi.application.exception.ErrorType;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a customer is not found.
 */
public class PatientNotFoundException extends RuntimeException implements ApiErrorResponse {

    private static final ErrorType type = ErrorType.NOT_FOUND;
    private static final HttpStatus status = HttpStatus.NOT_FOUND;

    /**
     * Constructs a new CustomerNotFoundException with the specified type and type value.
     *
     * @param type      the type of identifier (e.g., "email", "ID")
     * @param typeValue the value of the identifier
     */
    public PatientNotFoundException(final String type, final String typeValue) {
        super("Patient with " + type + ": '" + typeValue + "' not found!");
    }

    /**
     * Returns the type of error.
     *
     * @return the error type as a string
     */
    @Override
    public String getType() {
        return type.name();
    }

    /**
     * Returns the title of the error.
     *
     * @return the error title as a string
     */
    @Override
    public String getTitle() {
        return type.getTitle();
    }

    /**
     * Returns the HTTP status code for the error.
     *
     * @return the HTTP status code as an integer
     */
    @Override
    public int getStatus() {
        return status.value();
    }

}