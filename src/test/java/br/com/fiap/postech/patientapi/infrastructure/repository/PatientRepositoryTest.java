package br.com.fiap.postech.patientapi.infrastructure.repository;

import br.com.fiap.postech.patientapi.infrastructure.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PatientRepositoryTest {

    private final PatientRepository patientRepository = Mockito.mock(PatientRepository.class);

    @Test
    void shouldReturnPatientEntityWhenIdExists() {
        PatientEntity mockPatient = new PatientEntity(1L, "John", "Doe", "email@email.com", "123.456.789-09",
                null, null, null, null, null, null, null, null);
        when(patientRepository.findPatientEntityById(1L)).thenReturn(Optional.of(mockPatient));

        Optional<PatientEntity> result = patientRepository.findPatientEntityById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void shouldReturnEmptyOptionalWhenIdDoesNotExist() {
        when(patientRepository.findPatientEntityById(99L)).thenReturn(Optional.empty());

        Optional<PatientEntity> result = patientRepository.findPatientEntityById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnPatientEntityWhenCpfExists() {
        PatientEntity mockPatient = new PatientEntity(1L, "Jane", "Smith", "jane.smith@email.com", "987.654.321-00",
                null, null, null, null, null, null, null, null);
        when(patientRepository.findByCpf("987.654.321-00")).thenReturn(Optional.of(mockPatient));

        Optional<PatientEntity> result = patientRepository.findByCpf("987.654.321-00");

        assertTrue(result.isPresent());
        assertEquals("987.654.321-00", result.get().getCpf());
    }

    @Test
    void shouldReturnEmptyOptionalWhenCpfDoesNotExist() {
        when(patientRepository.findByCpf("000.000.000-00")).thenReturn(Optional.empty());

        Optional<PatientEntity> result = patientRepository.findByCpf("000.000.000-00");

        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueWhenCpfExists() {
        when(patientRepository.existsByCpf("123.456.789-09")).thenReturn(true);

        boolean result = patientRepository.existsByCpf("123.456.789-09");

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCpfDoesNotExist() {
        when(patientRepository.existsByCpf("000.000.000-00")).thenReturn(false);

        boolean result = patientRepository.existsByCpf("000.000.000-00");

        assertFalse(result);
    }
}