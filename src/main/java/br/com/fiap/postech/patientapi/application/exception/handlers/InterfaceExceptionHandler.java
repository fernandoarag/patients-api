package br.com.fiap.postech.patientapi.application.exception.handlers;


import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponseImpl;
import br.com.fiap.postech.patientapi.application.exception.ErrorType;
import br.com.fiap.postech.patientapi.application.exception.custom.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * Handler for interface-related exceptions.
 */
@Component
public class InterfaceExceptionHandler {

    private static final ErrorType INVALID_INPUT = ErrorType.INVALID_INPUT;
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    /**
     * Handles InvalidInputException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleInvalidInputException(InvalidInputException e) {
        return new ApiErrorResponseImpl(
                INVALID_INPUT.name(),
                INVALID_INPUT.getTitle(),
                BAD_REQUEST.value(),
                e.getMessage()
        );
    }

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param e the exception to handle
     * @return the API error response
     */
    public ApiErrorResponseImpl handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder("Validation failed for: ");
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }
        return new ApiErrorResponseImpl(
                INVALID_INPUT.name(),
                INVALID_INPUT.getTitle(),
                BAD_REQUEST.value(),
                errorMessage.toString()
        );
    }
}