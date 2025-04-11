package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindPatientByIdUseCaseTest {

    @Test
    void shouldReturnPatientWhenIdIsValid() {
        FindPatientByIdUseCase useCase = id -> new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");
        Patient patient = useCase.execute(1L);

        assertEquals(1L, patient.getId());
        assertEquals("John", patient.getFirstName());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        FindPatientByIdUseCase useCase = id -> {
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            return null;
        };

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));
    }

    @Test
    void shouldThrowExceptionWhenIdIsNegative() {
        FindPatientByIdUseCase useCase = id -> {
            if (id < 0) {
                throw new IllegalArgumentException("ID cannot be negative");
            }
            return null;
        };

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(-1L));
    }
}