package br.com.fiap.postech.patientapi.infrastructure.mapper;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.infrastructure.entity.PatientEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PatientMapperTest {

    private final PatientMapper patientMapper = new PatientMapperImpl();

    @Test
    void shouldMapPatientEntityToDomainCorrectly() {
        PatientEntity patientEntity = new PatientEntity(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "12345678");

        Patient patient = patientMapper.toDomain(patientEntity);

        assertEquals(1L, patient.getId());
        assertEquals("John", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals("email@email.com", patient.getEmail());
        assertEquals("123.456.789-09", patient.getCpf());
        assertEquals(LocalDate.of(1990, 1, 1), patient.getDateOfBirth());
        assertEquals("(38) 99841-3862", patient.getPhone());
        assertEquals("123", patient.getNumber());
        assertEquals("Rua", patient.getStreet());
        assertEquals("Bairro", patient.getNeighborhood());
        assertEquals("Cidade", patient.getCity());
        assertEquals("Estado", patient.getState());
        assertEquals("12345-678", patient.getZipcode());
    }

    @Test
    void shouldReturnNullWhenMappingNullPatientEntityToDomain() {
        Patient patient = patientMapper.toDomain(null);
        assertNull(patient);
    }

    @Test
    void shouldMapPatientToEntityCorrectly() {
        Patient patient = new Patient(1L, "Jane", "Smith", "jane.smith@email.com", "987.654.321-00",
                LocalDate.of(1995, 5, 5), "123456789", "456", "Avenida", "Centro", "Cidade Nova", "Estado Novo", "87654321");

        PatientEntity patientEntity = patientMapper.toEntity(patient);

        assertEquals(1L, patientEntity.getId());
        assertEquals("Jane", patientEntity.getFirstName());
        assertEquals("Smith", patientEntity.getLastName());
        assertEquals("jane.smith@email.com", patientEntity.getEmail());
        assertEquals("98765432100", patientEntity.getCpf());
        assertEquals(LocalDate.of(1995, 5, 5), patientEntity.getDateOfBirth());
        assertEquals("123456789", patientEntity.getPhone());
        assertEquals("456", patientEntity.getNumber());
        assertEquals("Avenida", patientEntity.getStreet());
        assertEquals("Centro", patientEntity.getNeighborhood());
        assertEquals("Cidade Nova", patientEntity.getCity());
        assertEquals("Estado Novo", patientEntity.getState());
        assertEquals("87654321", patientEntity.getZipcode());
    }

    @Test
    void shouldReturnNullWhenMappingNullPatientToEntity() {
        PatientEntity patientEntity = patientMapper.toEntity(null);
        assertNull(patientEntity);
    }

    @Test
    void shouldMapListOfPatientEntitiesToDomainListCorrectly() {
        List<PatientEntity> patientEntities = List.of(
                new PatientEntity(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                        LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "12345678"),
                new PatientEntity(2L, "Jane", "Smith", "jane.smith@email.com", "987.654.321-00",
                        LocalDate.of(1995, 5, 5), "123456789", "456", "Avenida", "Centro", "Cidade Nova", "Estado Novo", "87654321")
        );

        List<Patient> patients = patientMapper.toDomainList(patientEntities);

        assertEquals(2, patients.size());
        assertEquals("John", patients.get(0).getFirstName());
        assertEquals("Jane", patients.get(1).getFirstName());
    }

    @Test
    void shouldReturnEmptyListWhenMappingEmptyPatientEntityListToDomainList() {
        List<Patient> patients = patientMapper.toDomainList(List.of());
        assertEquals(0, patients.size());
    }

    @Test
    void shouldReturnNullWhenMappingNullPatientEntityListToDomainList() {
        List<Patient> patients = patientMapper.toDomainList(null);
        assertEquals(Collections.emptyList(), patients);
    }
}