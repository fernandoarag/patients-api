package br.com.fiap.postech.patientapi.interfaces.adapters;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientRequestDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientResponseDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientUpdateDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PatientRestAdapterImplTest {
    
    private final PatientRestAdapterImpl adapter = new PatientRestAdapterImpl();

    @Test
    void shouldReturnEmptyListWhenConvertingNullDomainListToPatientResponseDtoList() {
        List<PatientResponseDTO> dtos = adapter.toDtoList(null);
        assertEquals(0, dtos.size());
    }

    @Test
    void shouldHandleNullFieldsWhenConvertingPatientRequestDtoToDomain() {
        PatientRequestDTO dto = new PatientRequestDTO(null, null, null, null, null, null, null, null, null, null, null, null);

        Patient patient = adapter.toDomain(dto);

        assertNull(patient.getFirstName());
        assertNull(patient.getLastName());
        assertNull(patient.getCpf());
        assertNull(patient.getDateOfBirth());
        assertNull(patient.getPhone());
        assertNull(patient.getZipcode());
    }

    @Test
    void shouldHandleNullFieldsWhenConvertingPatientUpdateDtoToDomain() {
        PatientUpdateDTO dto = new PatientUpdateDTO(null, null, null, null, null, null, null, null, null, null, null, null);

        Patient patient = adapter.toDomain(dto);

        assertNull(patient.getFirstName());
        assertNull(patient.getLastName());
        assertNull(patient.getCpf());
        assertNull(patient.getDateOfBirth());
        assertNull(patient.getPhone());
        assertNull(patient.getZipcode());
    }

    @Test
    void shouldHandleNullFieldsWhenConvertingDomainToPatientResponseDto() {
        Patient patient = new Patient(null, null, null, null, null, null, null, null, null, null, null, null, null);

        PatientResponseDTO dto = adapter.toDto(patient);

        assertNull(dto.getFirstName());
        assertNull(dto.getLastName());
        assertNull(dto.getCpf());
        assertNull(dto.getDateOfBirth());
        assertNull(dto.getPhone());
        assertNull(dto.getZipcode());
    }
}