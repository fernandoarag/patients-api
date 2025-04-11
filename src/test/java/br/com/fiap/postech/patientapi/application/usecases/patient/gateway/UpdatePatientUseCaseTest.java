package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdatePatientUseCaseTest {

    @Test
    void shouldUpdatePatientSuccessfullyWhenIdAndUpdatedPatientAreValid() {
        UpdatePatientUseCase useCase = (id, updatedPatient) -> updatedPatient;
        Patient updatedPatient = new Patient(1L, "Updated Name", "Doe", "email@email.com", "123.456.789-09", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");
        Patient result = useCase.execute(1L, updatedPatient);

        assertEquals("Updated Name", result.getFirstName());
        assertEquals("123.456.789-09", result.getCpf());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        UpdatePatientUseCase useCase = (id, updatedPatient) -> {
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            return null;
        };

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(null, new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000")));
    }

    @Test
    void shouldThrowExceptionWhenUpdatedPatientIsNull() {
        UpdatePatientUseCase useCase = (id, updatedPatient) -> {
            if (updatedPatient == null) {
                throw new IllegalArgumentException("Updated patient cannot be null");
            }
            return null;
        };

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(1L, null));
    }
}