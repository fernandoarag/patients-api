package br.com.fiap.postech.patientapi.interfaces.gateway.database;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientGatewayTest {

    @Mock
    private PatientGateway patientGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSavePatientSuccessfully() {
        Patient patient = new Patient();
        patient.setId(1L);
        when(patientGateway.save(patient)).thenReturn(patient);

        Patient savedPatient = patientGateway.save(patient);

        assertNotNull(savedPatient);
        assertEquals(1L, savedPatient.getId());
        verify(patientGateway, times(1)).save(patient);
    }

    @Test
    void shouldFindAllPatientsSuccessfully() {
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();
        when(patientGateway.findAll()).thenReturn(List.of(patient1, patient2));

        List<Patient> patients = patientGateway.findAll();

        assertNotNull(patients);
        assertEquals(2, patients.size());
        verify(patientGateway, times(1)).findAll();
    }

    @Test
    void shouldFindPatientByIdSuccessfully() {
        Patient patient = new Patient();
        patient.setId(1L);
        when(patientGateway.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> foundPatient = patientGateway.findById(1L);

        assertTrue(foundPatient.isPresent());
        assertEquals(1L, foundPatient.get().getId());
        verify(patientGateway, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyOptionalWhenPatientNotFoundById() {
        when(patientGateway.findById(99L)).thenReturn(Optional.empty());

        Optional<Patient> result = patientGateway.findById(99L);

        assertTrue(result.isEmpty());
        verify(patientGateway, times(1)).findById(99L);
    }

    @Test
    void shouldFindPatientByCpfSuccessfully() {
        Patient patient = new Patient();
        patient.setCpf("123.456.789-00");
        when(patientGateway.findByCpf("123.456.789-00")).thenReturn(Optional.of(patient));

        Optional<Patient> foundPatient = patientGateway.findByCpf("123.456.789-00");

        assertTrue(foundPatient.isPresent());
        assertEquals("123.456.789-00", foundPatient.get().getCpf());
        verify(patientGateway, times(1)).findByCpf("123.456.789-00");
    }

    @Test
    void shouldReturnEmptyOptionalWhenPatientNotFoundByCpf() {
        when(patientGateway.findByCpf("000.000.000-00")).thenReturn(Optional.empty());

        Optional<Patient> result = patientGateway.findByCpf("000.000.000-00");

        assertTrue(result.isEmpty());
        verify(patientGateway, times(1)).findByCpf("000.000.000-00");
    }

    @Test
    void shouldReturnTrueWhenPatientExistsByCpf() {
        when(patientGateway.existsByCpf("123.456.789-09")).thenReturn(true);

        boolean exists = patientGateway.existsByCpf("123.456.789-09");

        assertTrue(exists);
        verify(patientGateway, times(1)).existsByCpf("123.456.789-09");
    }

    @Test
    void shouldReturnFalseWhenPatientDoesNotExistByCpf() {
        when(patientGateway.existsByCpf("000.000.000-00")).thenReturn(false);

        boolean exists = patientGateway.existsByCpf("000.000.000-00");

        assertFalse(exists);
        verify(patientGateway, times(1)).existsByCpf("000.000.000-00");
    }

    @Test
    void shouldDeletePatientByIdSuccessfully() {
        doNothing().when(patientGateway).deleteById(1L);

        patientGateway.deleteById(1L);

        verify(patientGateway, times(1)).deleteById(1L);
    }
}