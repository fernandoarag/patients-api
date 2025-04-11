package br.com.fiap.postech.patientapi.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {

    @Test
    void shouldCreatePatientWithAllFields() {
        Patient patient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");

        assertEquals(1L, patient.getId());
        assertEquals("John", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals("email@email.com", patient.getEmail());
        assertEquals("123.456.789-09", patient.getCpf());
        assertEquals(LocalDate.of(1990, 1, 1), patient.getDateOfBirth());
        assertEquals("38998413862", patient.getPhone());
        assertEquals("123", patient.getNumber());
        assertEquals("Rua", patient.getStreet());
        assertEquals("Bairro", patient.getNeighborhood());
        assertEquals("Cidade", patient.getCity());
        assertEquals("Estado", patient.getState());
        assertEquals("00000000", patient.getZipcode());
    }

    @Test
    void shouldAllowUpdatingPatientFields() {
        Patient patient = new Patient();
        patient.setFirstName("Jane");
        patient.setLastName("Smith");
        patient.setEmail("jane.smith@email.com");

        assertEquals("Jane", patient.getFirstName());
        assertEquals("Smith", patient.getLastName());
        assertEquals("jane.smith@email.com", patient.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenInvalidCpfIsSet() {
        Patient patient = new Patient();
        patient.setCpf("invalid-cpf");
        assertEquals("invalid-cpf", patient.getCpf());
    }
}