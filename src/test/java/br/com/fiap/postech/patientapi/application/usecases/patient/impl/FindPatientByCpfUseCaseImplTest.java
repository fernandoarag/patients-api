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

class FindPatientByCpfUseCaseImplTest {

    private final PatientGateway patientGateway = Mockito.mock(PatientGateway.class);
    private final FindPatientByCpfUseCaseImpl useCase = new FindPatientByCpfUseCaseImpl(patientGateway);

    @Test
    void shouldReturnPatientWhenCpfExists() {
        Patient mockPatient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-00", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");

        when(patientGateway.findByCpf("123.456.789-00")).thenReturn(java.util.Optional.of(mockPatient));

        Patient result = useCase.execute("123.456.789-00");

        assertEquals("123.456.789-00", result.getCpf());
        assertEquals("John", result.getFirstName());
    }

    @Test
    void shouldThrowExceptionWhenCpfDoesNotExist() {
        when(patientGateway.findByCpf("000.000.000-00")).thenReturn(java.util.Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> useCase.execute("000.000.000-00"));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsNull() {
        assertThrows(PatientNotFoundException.class, () -> useCase.execute(null));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsInvalid() {
        assertThrows(PatientNotFoundException.class, () -> useCase.execute("invalid-cpf"));
    }
}