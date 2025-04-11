package br.com.fiap.postech.patientapi.interfaces.adapters;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientRequestDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientResponseDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientUpdateDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PatientRestAdapterTest {

    private final PatientRestAdapterImpl adapter = new PatientRestAdapterImpl();

    @Test
    void shouldConvertPatientRequestDtoToDomainCorrectly() {
        PatientRequestDTO dto = new PatientRequestDTO("John", "Doe", "email@email.com", "123.456.789-09", LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "12345678");

        Patient patient = adapter.toDomain(dto);

        assertEquals("John", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals("12345678909", patient.getCpf());
    }

    @Test
    void shouldConvertPatientUpdateDtoToDomainCorrectly() {
        PatientUpdateDTO dto = new PatientUpdateDTO("Jane", "Smith", "email@email.com", "987.654.321-00", LocalDate.of(1995, 5, 5), "123456789", "456", "Avenida", "Centro", "Cidade Nova", "Estado Novo", "87654321");

        Patient patient = adapter.toDomain(dto);

        assertEquals("Jane", patient.getFirstName());
        assertEquals("Smith", patient.getLastName());
        assertEquals("98765432100", patient.getCpf());
    }

    @Test
    void shouldConvertDomainToPatientResponseDtoCorrectly() {
        Patient patient = new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09", LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "12345678");

        PatientResponseDTO dto = adapter.toDto(patient);

        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("123.456.789-09", dto.getCpf());
    }

    @Test
    void shouldConvertDomainListToPatientResponseDtoListCorrectly() {
        List<Patient> patients = List.of(new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-09", LocalDate.of(1990, 1, 1), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "12345678"), new Patient(2L, "Jane", "Smith", "jane.smith@email.com", "987.654.321-00", LocalDate.of(1995, 5, 5), "123456789", "456", "Avenida", "Centro", "Cidade Nova", "Estado Novo", "87654321"));

        List<PatientResponseDTO> dtos = adapter.toDtoList(patients);

        assertEquals(2, dtos.size());
        assertEquals("John", dtos.get(0).getFirstName());
        assertEquals("Jane", dtos.get(1).getFirstName());
    }

    @Test
    void shouldReturnNullWhenConvertingNullPatientRequestDtoToDomain() {
        Patient patient = adapter.toDomain((PatientRequestDTO) null);
        assertNull(patient);
    }

    @Test
    void shouldReturnNullWhenConvertingNullPatientUpdateDtoToDomain() {
        Patient patient = adapter.toDomain((PatientUpdateDTO) null);
        assertNull(patient);
    }

    @Test
    void shouldReturnNullWhenConvertingNullDomainToPatientResponseDto() {
        PatientResponseDTO dto = adapter.toDto(null);
        assertNull(dto);
    }

    @Test
    void shouldReturnEmptyListWhenConvertingEmptyDomainListToPatientResponseDtoList() {
        List<PatientResponseDTO> dtos = adapter.toDtoList(List.of());
        assertEquals(0, dtos.size());
    }

    @Test
    void shouldReturnNullWhenConvertingNullDomainListToPatientResponseDtoList() {
        List<PatientResponseDTO> dtos = adapter.toDtoList(null);
        assertEquals(Collections.emptyList(), dtos);
    }
}