package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.exception.custom.PatientCpfAlreadyExistsException;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.FindPatientByIdUseCase;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UpdatePatientUseCaseImplTest {

    private final PatientGateway patientGateway = Mockito.mock(PatientGateway.class);
    private final FindPatientByIdUseCase findPatientByIdUseCase = Mockito.mock(FindPatientByIdUseCase.class);
    private final UpdatePatientUseCaseImpl useCase = new UpdatePatientUseCaseImpl(patientGateway, findPatientByIdUseCase);

    @Test
    void shouldUpdatePatientSuccessfullyWhenAllFieldsAreValid() {
        Patient existingPatient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");
        Patient updatedPatient = new Patient(null, "Jane", "Smith", null, "123.456.789-09",
                LocalDate.of(1995, 5, 5), null, "456", "Avenida", "Centro", "Nova Cidade", "Novo Estado", "11111111");

        when(findPatientByIdUseCase.execute(1L)).thenReturn(existingPatient);
        when(patientGateway.existsByCpf("123.456.789-09")).thenReturn(false);
        when(patientGateway.save(updatedPatient)).thenReturn(updatedPatient);

        Patient result = useCase.execute(1L, updatedPatient);

        assertEquals("Jane", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("123.456.789-09", result.getCpf());
        assertEquals("456", result.getNumber());
    }

    @Test
    void shouldThrowExceptionWhenCpfAlreadyExists() {
        Patient existingPatient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");
        Patient updatedPatient = new Patient(null, "Jane", "Smith", null, "987.654.321-00",
                LocalDate.of(1995, 5, 5), null, "456", "Avenida", "Centro", "Nova Cidade", "Novo Estado", "11111111");

        when(findPatientByIdUseCase.execute(1L)).thenReturn(existingPatient);
        when(patientGateway.existsByCpf("987.654.321-00")).thenReturn(true);

        assertThrows(PatientCpfAlreadyExistsException.class, () -> useCase.execute(1L, updatedPatient));
    }

    @Test
    void shouldRetainExistingFieldsWhenUpdatedFieldsAreNull() {
        Patient existingPatient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");
        Patient updatedPatient = new Patient(null, null, null, null, "123.456.789-09",
                null, null, null, null, null, null, null, null);

        when(findPatientByIdUseCase.execute(1L)).thenReturn(existingPatient);
        when(patientGateway.existsByCpf("123.456.789-09")).thenReturn(false);
        when(patientGateway.save(updatedPatient)).thenReturn(existingPatient);

        Patient result = useCase.execute(1L, updatedPatient);

        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("123.456.789-09", result.getCpf());
        assertEquals("Rua", result.getStreet());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(NullPointerException.class, () -> useCase.execute(null, new Patient()));
    }

    @Test
    void shouldThrowExceptionWhenUpdatedPatientIsNull() {
        assertThrows(NullPointerException.class, () -> useCase.execute(1L, null));
    }
}