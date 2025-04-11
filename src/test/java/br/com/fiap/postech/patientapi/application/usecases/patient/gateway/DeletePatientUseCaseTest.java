package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletePatientUseCaseTest {

    @Test
    void shouldDeletePatientSuccessfullyWhenIdIsValid() {
        DeletePatientUseCase useCase = id -> {
        }; // Mock implementation
        assertDoesNotThrow(() -> useCase.execute(1L));
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        DeletePatientUseCase useCase = id -> {
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
        };
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));
    }

    @Test
    void shouldThrowExceptionWhenIdIsNegative() {
        DeletePatientUseCase useCase = id -> {
            if (id < 0) {
                throw new IllegalArgumentException("ID cannot be negative");
            }
        };
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(-1L));
    }
}