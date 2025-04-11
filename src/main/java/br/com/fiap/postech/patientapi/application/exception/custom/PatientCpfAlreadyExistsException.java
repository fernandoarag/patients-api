package br.com.fiap.postech.patientapi.application.exception.custom;

import br.com.fiap.postech.patientapi.application.exception.ApiErrorResponse;
import br.com.fiap.postech.patientapi.application.exception.ErrorType;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a customer with the specified email already exists.
 */
public class PatientCpfAlreadyExistsException extends RuntimeException implements ApiErrorResponse {

    private static final ErrorType type = ErrorType.ALREADY_EXISTS;
    private static final HttpStatus status = HttpStatus.CONFLICT;

    public PatientCpfAlreadyExistsException(final String cpf) {
        super("Patient with cpf: '" + cpf + "' already exists!");
    }

    @Override
    public String getType() {
        return type.name();
    }

    @Override
    public String getTitle() {
        return type.getTitle();
    }

    @Override
    public int getStatus() {
        return status.value();
    }
}
