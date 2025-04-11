package br.com.fiap.postech.patientapi.application.exception.handlers;

import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.patientapi.application.exception.custom.InvalidPatientDataException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientCpfAlreadyExistsException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final PatientExceptionHandler patientExceptionHandler;
    private final InterfaceExceptionHandler interfaceExceptionHandler;

    /**
     * Constructs a new GlobalExceptionHandler with the specified handlers.
     *
     * @param patientExceptionHandler the handler for database-related exceptions
     */
    public GlobalExceptionHandler(PatientExceptionHandler patientExceptionHandler, InterfaceExceptionHandler interfaceExceptionHandler) {
        this.patientExceptionHandler = patientExceptionHandler;
        this.interfaceExceptionHandler = interfaceExceptionHandler;
    }

    /**
     * Handles PatientNotFoundException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(PatientNotFoundException.class)
    public ApiErrorResponseImpl handlePatientNotFoundException(PatientNotFoundException e) {
        return patientExceptionHandler.handlePatientNotFoundException(e);
    }

    /**
     * Handles PatientCpfAlreadyExistsException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(PatientCpfAlreadyExistsException.class)
    public ApiErrorResponseImpl handlePatientAlreadyExistsException(PatientCpfAlreadyExistsException e) {
        return patientExceptionHandler.handlePatientAlreadyExistsException(e);
    }

    /**
     * Handles InvalidPatientDataException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(InvalidPatientDataException.class)
    public ApiErrorResponseImpl handlePatientEmailCannotBeBlankException(InvalidPatientDataException e) {
        return patientExceptionHandler.handleInvalidPatientDataException(e);
    }

    /**
     * Handles DataIntegrityViolationException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiErrorResponseImpl handleDatabaseException(DataIntegrityViolationException e) {
        return patientExceptionHandler.handleDatabaseIntegrityViolation(e);
    }

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponseImpl handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return interfaceExceptionHandler.handleMethodArgumentNotValidException(e);
    }
}