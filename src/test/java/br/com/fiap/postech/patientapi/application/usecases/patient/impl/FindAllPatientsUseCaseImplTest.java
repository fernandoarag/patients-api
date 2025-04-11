package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class FindAllPatientsUseCaseImplTest {

    private final PatientGateway patientGateway = Mockito.mock(PatientGateway.class);
    private final FindAllPatientsUseCaseImpl useCase = new FindAllPatientsUseCaseImpl(patientGateway);

    @Test
    void shouldReturnEmptyListWhenNoPatientsExist() {
        when(patientGateway.findAll()).thenReturn(Collections.emptyList());

        List<Patient> patients = useCase.execute();

        assertNotNull(patients);
        assertEquals(0, patients.size());
    }

    @Test
    void shouldReturnListOfPatientsWhenPatientsExist() {
        List<Patient> mockPatients = List.of(
                new Patient(1L, "Alice", "Doe", "email1@email.com", "123.456.789-09", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000"),
                new Patient(2L, "Bob", "Doe", "email2@email.com", "987.654.321-00", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000")
        );
        when(patientGateway.findAll()).thenReturn(mockPatients);

        List<Patient> patients = useCase.execute();

        assertNotNull(patients);
        assertEquals(2, patients.size());
        assertEquals("Alice", patients.get(0).getFirstName());
        assertEquals("Bob", patients.get(1).getFirstName());
    }
}