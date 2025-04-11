package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.exception.custom.InvalidPatientDataException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientCpfAlreadyExistsException;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CreatePatientUseCaseImplTest {

    private final PatientGateway patientGateway = Mockito.mock(PatientGateway.class);
    private final CreatePatientUseCaseImpl useCase = new CreatePatientUseCaseImpl(patientGateway);

    @Test
    void shouldCreatePatientSuccessfullyWhenDataIsValid() {
        Patient patient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");

        when(patientGateway.existsByCpf(patient.getCpf())).thenReturn(false);
        when(patientGateway.save(patient)).thenReturn(patient);

        Patient createdPatient = useCase.execute(patient);

        assertNotNull(createdPatient);
        assertNotNull(createdPatient.getId());
    }

    @Test
    void shouldThrowExceptionWhenPatientCpfAlreadyExists() {
        Patient patient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");

        when(patientGateway.existsByCpf(patient.getCpf())).thenReturn(true);

        assertThrows(PatientCpfAlreadyExistsException.class, () -> useCase.execute(patient));
    }

    @Test
    void shouldThrowExceptionWhenPatientFirstNameIsNull() {
        Patient patient = new Patient(1L, null, "Doe", "email@email.com", "123.456.789-09",
                LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");

        assertThrows(InvalidPatientDataException.class, () -> useCase.execute(patient));
    }

    @Test
    void shouldThrowExceptionWhenPatientCpfIsNull() {
        Patient patient = new Patient(1L, "John", "Doe", "email@email.com", null,
                LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");

        assertThrows(InvalidPatientDataException.class, () -> useCase.execute(patient));
    }

    @Test
    void shouldThrowExceptionWhenPatientDateOfBirthIsNull() {
        Patient patient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                null, "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");

        assertThrows(InvalidPatientDataException.class, () -> useCase.execute(patient));
    }
}