package br.com.fiap.postech.patientapi.interfaces.gateway.impl;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.infrastructure.entity.PatientEntity;
import br.com.fiap.postech.patientapi.infrastructure.mapper.PatientMapper;
import br.com.fiap.postech.patientapi.infrastructure.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientJpaGatewayTest {

    private PatientJpaGateway patientJpaGateway;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientJpaGateway = new PatientJpaGateway(patientRepository, patientMapper);
    }

    @Test
    void shouldSavePatientSuccessfully() {
        Patient patient = new Patient();
        PatientEntity patientEntity = new PatientEntity();
        when(patientMapper.toEntity(patient)).thenReturn(patientEntity);
        when(patientRepository.save(patientEntity)).thenReturn(patientEntity);
        when(patientMapper.toDomain(patientEntity)).thenReturn(patient);

        Patient savedPatient = patientJpaGateway.save(patient);

        assertNotNull(savedPatient);
        verify(patientMapper, times(1)).toEntity(patient);
        verify(patientRepository, times(1)).save(patientEntity);
        verify(patientMapper, times(1)).toDomain(patientEntity);
    }

    @Test
    void shouldFindAllPatientsSuccessfully() {
        List<PatientEntity> entities = List.of(new PatientEntity(), new PatientEntity());
        List<Patient> patients = List.of(new Patient(), new Patient());
        when(patientRepository.findAll()).thenReturn(entities);
        when(patientMapper.toDomainList(entities)).thenReturn(patients);

        List<Patient> result = patientJpaGateway.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(patientRepository, times(1)).findAll();
        verify(patientMapper, times(1)).toDomainList(entities);
    }

    @Test
    void shouldFindPatientByIdSuccessfully() {
        PatientEntity entity = new PatientEntity();
        Patient patient = new Patient();
        when(patientRepository.findPatientEntityById(1L)).thenReturn(Optional.of(entity));
        when(patientMapper.toDomain(entity)).thenReturn(patient);

        Optional<Patient> result = patientJpaGateway.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(patient, result.get());
        verify(patientRepository, times(1)).findPatientEntityById(1L);
        verify(patientMapper, times(1)).toDomain(entity);
    }

    @Test
    void shouldReturnEmptyOptionalWhenPatientNotFoundById() {
        when(patientRepository.findPatientEntityById(99L)).thenReturn(Optional.empty());

        Optional<Patient> result = patientJpaGateway.findById(99L);

        assertTrue(result.isEmpty());
        verify(patientRepository, times(1)).findPatientEntityById(99L);
    }

    @Test
    void shouldFindPatientByCpfSuccessfully() {
        PatientEntity entity = new PatientEntity();
        Patient patient = new Patient();
        when(patientRepository.findByCpf("123.456.789-00")).thenReturn(Optional.of(entity));
        when(patientMapper.toDomain(entity)).thenReturn(patient);

        Optional<Patient> result = patientJpaGateway.findByCpf("123.456.789-00");

        assertTrue(result.isPresent());
        assertEquals(patient, result.get());
        verify(patientRepository, times(1)).findByCpf("123.456.789-00");
        verify(patientMapper, times(1)).toDomain(entity);
    }

    @Test
    void shouldReturnEmptyOptionalWhenFindingByNonExistentCpf() {
        when(patientRepository.findByCpf("000.000.000-00")).thenReturn(Optional.empty());

        Optional<Patient> result = patientJpaGateway.findByCpf("000.000.000-00");

        assertFalse(result.isPresent());
        verify(patientRepository, times(1)).findByCpf("000.000.000-00");
    }

    @Test
    void shouldReturnTrueWhenPatientExistsByCpf() {
        when(patientRepository.existsByCpf("123.456.789-09")).thenReturn(true);

        boolean exists = patientJpaGateway.existsByCpf("123.456.789-09");

        assertTrue(exists);
        verify(patientRepository, times(1)).existsByCpf("123.456.789-09");
    }

    @Test
    void shouldReturnFalseWhenCheckingExistenceByNonExistentCpf() {
        when(patientRepository.existsByCpf("000.000.000-00")).thenReturn(false);

        boolean exists = patientJpaGateway.existsByCpf("000.000.000-00");

        assertFalse(exists);
        verify(patientRepository, times(1)).existsByCpf("000.000.000-00");
    }

    @Test
    void shouldDeletePatientByIdSuccessfully() {
        doNothing().when(patientRepository).deleteById(1L);

        patientJpaGateway.deleteById(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingByNullId() {
        assertThrows(IllegalArgumentException.class, () -> patientJpaGateway.deleteById(null));
    }
}