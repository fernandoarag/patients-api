package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FindPatientByIdUseCaseImplTest {

    private final PatientGateway patientGateway = Mockito.mock(PatientGateway.class);
    private final FindPatientByIdUseCaseImpl useCase = new FindPatientByIdUseCaseImpl(patientGateway);

    @Test
    void shouldReturnPatientWhenIdExists() {
        Patient mockPatient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");

        when(patientGateway.findById(1L)).thenReturn(java.util.Optional.of(mockPatient));

        Patient result = useCase.execute(1L);

        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        when(patientGateway.findById(99L)).thenReturn(java.util.Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> useCase.execute(99L));
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(PatientNotFoundException.class, () -> useCase.execute(null));
    }
}