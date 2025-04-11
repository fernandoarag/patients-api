package br.com.fiap.postech.patientapi.interfaces.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PatientResponseDTOTest {

    @Test
    void shouldCreatePatientResponseDtoWithAllFieldsCorrectly() {
        PatientResponseDTO dto = PatientResponseDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .cpf("123.456.789-00")
                .dateOfBirth(java.time.LocalDate.of(1990, 1, 1))
                .phone("123456789")
                .number("123")
                .street("Rua A")
                .neighborhood("Bairro B")
                .city("Cidade C")
                .state("Estado D")
                .zipcode("12345678")
                .build();

        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("john.doe@example.com", dto.getEmail());
        assertEquals("123.456.789-00", dto.getCpf());
        assertEquals(java.time.LocalDate.of(1990, 1, 1), dto.getDateOfBirth());
        assertEquals("123456789", dto.getPhone());
        assertEquals("123", dto.getNumber());
        assertEquals("Rua A", dto.getStreet());
        assertEquals("Bairro B", dto.getNeighborhood());
        assertEquals("Cidade C", dto.getCity());
        assertEquals("Estado D", dto.getState());
        assertEquals("12345678", dto.getZipcode());
    }

    @Test
    void shouldHandleNullFieldsWhenBuildingPatientResponseDto() {
        PatientResponseDTO dto = PatientResponseDTO.builder()
                .id(null)
                .firstName(null)
                .lastName(null)
                .email(null)
                .cpf(null)
                .dateOfBirth(null)
                .phone(null)
                .number(null)
                .street(null)
                .neighborhood(null)
                .city(null)
                .state(null)
                .zipcode(null)
                .build();

        assertNull(dto.getId());
        assertNull(dto.getFirstName());
        assertNull(dto.getLastName());
        assertNull(dto.getEmail());
        assertNull(dto.getCpf());
        assertNull(dto.getDateOfBirth());
        assertNull(dto.getPhone());
        assertNull(dto.getNumber());
        assertNull(dto.getStreet());
        assertNull(dto.getNeighborhood());
        assertNull(dto.getCity());
        assertNull(dto.getState());
        assertNull(dto.getZipcode());
    }
}