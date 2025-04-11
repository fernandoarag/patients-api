package br.com.fiap.postech.patientapi.infrastructure.mapper;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.infrastructure.entity.PatientEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PatientMapperImplTest {

    private final PatientMapper patientMapper = new PatientMapperImpl();

    @Test
    void shouldReturnEmptyListWhenMappingNullPatientEntityListToDomainList() {
        List<Patient> patients = patientMapper.toDomainList(null);
        assertEquals(0, patients.size());
    }

    @Test
    void shouldHandleNullFieldsWhenMappingPatientEntityToDomain() {
        PatientEntity patientEntity = new PatientEntity(null, null, null, null, null, null, null, null, null, null, null, null, null);
        Patient patient = patientMapper.toDomain(patientEntity);

        assertNull(patient.getId());
        assertNull(patient.getFirstName());
        assertNull(patient.getLastName());
        assertNull(patient.getEmail());
        assertNull(patient.getCpf());
        assertNull(patient.getDateOfBirth());
        assertNull(patient.getPhone());
        assertNull(patient.getNumber());
        assertNull(patient.getStreet());
        assertNull(patient.getNeighborhood());
        assertNull(patient.getCity());
        assertNull(patient.getState());
        assertNull(patient.getZipcode());
    }

    @Test
    void shouldHandleNullFieldsWhenMappingPatientToEntity() {
        Patient patient = new Patient(null, null, null, null, null, null, null, null, null, null, null, null, null);
        PatientEntity patientEntity = patientMapper.toEntity(patient);

        assertNull(patientEntity.getId());
        assertNull(patientEntity.getFirstName());
        assertNull(patientEntity.getLastName());
        assertNull(patientEntity.getEmail());
        assertNull(patientEntity.getCpf());
        assertNull(patientEntity.getDateOfBirth());
        assertNull(patientEntity.getPhone());
        assertNull(patientEntity.getNumber());
        assertNull(patientEntity.getStreet());
        assertNull(patientEntity.getNeighborhood());
        assertNull(patientEntity.getCity());
        assertNull(patientEntity.getState());
        assertNull(patientEntity.getZipcode());
    }
}