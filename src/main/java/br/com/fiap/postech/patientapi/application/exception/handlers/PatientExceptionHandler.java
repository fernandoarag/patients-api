package br.com.fiap.postech.patientapi.application.exception.handlers;


import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.patientapi.application.exception.ErrorType;
import br.com.fiap.postech.patientapi.application.exception.custom.InvalidPatientDataException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientCpfAlreadyExistsException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Handler for database-related exceptions.
 */
@Component
public class PatientExceptionHandler {

    private static final ErrorType NOT_FOUND = ErrorType.NOT_FOUND;
    private static final ErrorType INVALID_JSON = ErrorType.INVALID_JSON;
    private static final ErrorType INTERNAL_SERVER_ERROR = ErrorType.INTERNAL_SERVER_ERROR;
    private static final ErrorType EMAIL_CANNOT_BE_CHANGED = ErrorType.EMAIL_CANNOT_BE_CHANGED;
    private static final HttpStatus NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;
    private static final HttpStatus CONFLICT_STATUS = HttpStatus.CONFLICT;
    private static final HttpStatus NOT_ACCEPTABLE_STATUS = HttpStatus.NOT_ACCEPTABLE;
    private static final HttpStatus BAD_REQUEST_STATUS = HttpStatus.BAD_REQUEST;

    /**
     * Handles PatientNotFoundException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handlePatientNotFoundException(PatientNotFoundException e) {
        return new ApiErrorResponseImpl(
                NOT_FOUND.name(),
                NOT_FOUND.getTitle(),
                NOT_FOUND_STATUS.value(),
                e.getMessage()
        );
    }

    /**
     * Handles PatientAlreadyExistsException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handlePatientAlreadyExistsException(PatientCpfAlreadyExistsException e) {
        return new ApiErrorResponseImpl(
                EMAIL_CANNOT_BE_CHANGED.name(),
                EMAIL_CANNOT_BE_CHANGED.getTitle(),
                CONFLICT_STATUS.value(),
                e.getMessage()
        );
    }

    /**
     * Handles PatientEmailCannotBeBlankException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleInvalidPatientDataException(InvalidPatientDataException e) {
        return new ApiErrorResponseImpl(
                INVALID_JSON.name(),
                INVALID_JSON.getTitle(),
                NOT_ACCEPTABLE_STATUS.value(),
                e.getMessage()
        );
    }

    /**
     * Handles DataIntegrityViolationException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleDatabaseIntegrityViolation(DataIntegrityViolationException e) {
        return new ApiErrorResponseImpl(
                INTERNAL_SERVER_ERROR.name(),
                INTERNAL_SERVER_ERROR.getTitle(),
                BAD_REQUEST_STATUS.value(),
                e.getMessage()
        );
    }
}