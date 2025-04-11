package br.com.fiap.postech.patientapi.application.exception;

/**
 * Interface representing an API error response.
 */
public interface ApiErrorResponse {

    /**
     * Returns the type of error.
     *
     * @return the error type as a string
     */
    String getType();

    /**
     * Returns the title of the error.
     *
     * @return the error title as a string
     */
    String getTitle();

    /**
     * Returns the HTTP status code for the error.
     *
     * @return the HTTP status code as an integer
     */
    int getStatus();
}