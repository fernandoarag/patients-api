package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreatePatientUseCaseTest {

    @Test
    void shouldCreatePatientSuccessfully() {
        CreatePatientUseCase useCase = patient -> patient; // Mock implementation
        Patient patient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");
        Patient createdPatient = useCase.execute(patient);

        assertNotNull(createdPatient);
        assertNotNull(createdPatient.getId());
    }

    @Test
    void shouldThrowExceptionWhenPatientIsNull() {
        CreatePatientUseCase useCase = patient -> {
            if (patient == null) {
                throw new IllegalArgumentException("Patient cannot be null");
            }
            return patient;
        };

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));
    }
}