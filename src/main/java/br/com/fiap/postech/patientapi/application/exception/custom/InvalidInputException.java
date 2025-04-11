package br.com.fiap.postech.patientapi.application.exception.custom;


import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponse;
import br.com.fiap.postech.patientapi.application.exception.ErrorType;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when there is invalid input provided.
 */
public class InvalidInputException extends RuntimeException implements ApiErrorResponse {

    private static final ErrorType type = ErrorType.INVALID_INPUT;
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidInputException(String message) {
        super(message);
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