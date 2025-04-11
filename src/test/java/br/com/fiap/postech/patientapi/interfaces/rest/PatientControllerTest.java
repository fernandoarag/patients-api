package br.com.fiap.postech.patientapi.interfaces.rest;

import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.*;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.adapters.PatientRestAdapter;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientRequestDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientResponseDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    private PatientController patientController;

    @Mock
    private CreatePatientUseCase createPatientUseCase;

    @Mock
    private FindAllPatientsUseCase findAllPatientsUseCase;

    @Mock
    private FindPatientByIdUseCase findPatientByIdUseCase;

    @Mock
    private FindPatientByCpfUseCase findPatientByCpfUseCase;

    @Mock
    private UpdatePatientUseCase updatePatientUseCase;

    @Mock
    private DeletePatientUseCase deletePatientUseCase;

    @Mock
    private PatientRestAdapter patientRestAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientController = new PatientController(
                createPatientUseCase,
                findAllPatientsUseCase,
                findPatientByIdUseCase,
                findPatientByCpfUseCase,
                updatePatientUseCase,
                deletePatientUseCase,
                patientRestAdapter
        );
    }

    @Test
    void shouldCreatePatientSuccessfully() {
        PatientRequestDTO requestDTO = new PatientRequestDTO("John", "Doe", "email@email.com", "123.456.789-00", LocalDate.now(), "123456789", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");
        Patient patient = new Patient();
        Patient createdPatient = new Patient();
        PatientResponseDTO responseDTO = new PatientResponseDTO();

        when(patientRestAdapter.toDomain(requestDTO)).thenReturn(patient);
        when(createPatientUseCase.execute(patient)).thenReturn(createdPatient);
        when(patientRestAdapter.toDto(createdPatient)).thenReturn(responseDTO);

        ResponseEntity<PatientResponseDTO> response = patientController.create(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(patientRestAdapter, times(1)).toDomain(requestDTO);
        verify(createPatientUseCase, times(1)).execute(patient);
        verify(patientRestAdapter, times(1)).toDto(createdPatient);
    }

    @Test
    void shouldGetAllPatientsSuccessfully() {
        List<Patient> patients = List.of(new Patient(), new Patient());
        List<PatientResponseDTO> responseDTOs = List.of(new PatientResponseDTO(), new PatientResponseDTO());

        when(findAllPatientsUseCase.execute()).thenReturn(patients);
        when(patientRestAdapter.toDtoList(patients)).thenReturn(responseDTOs);

        ResponseEntity<List<PatientResponseDTO>> response = patientController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTOs, response.getBody());
        verify(findAllPatientsUseCase, times(1)).execute();
        verify(patientRestAdapter, times(1)).toDtoList(patients);
    }

    @Test
    void shouldGetPatientByIdSuccessfully() {
        Patient patient = new Patient();
        PatientResponseDTO responseDTO = new PatientResponseDTO();

        when(findPatientByIdUseCase.execute(1L)).thenReturn(patient);
        when(patientRestAdapter.toDto(patient)).thenReturn(responseDTO);

        ResponseEntity<PatientResponseDTO> response = patientController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(findPatientByIdUseCase, times(1)).execute(1L);
        verify(patientRestAdapter, times(1)).toDto(patient);
    }

    @Test
    void shouldGetPatientByCpfSuccessfully() {
        Patient patient = new Patient();
        PatientResponseDTO responseDTO = new PatientResponseDTO();

        when(findPatientByCpfUseCase.execute("123.456.789-00")).thenReturn(patient);
        when(patientRestAdapter.toDto(patient)).thenReturn(responseDTO);

        ResponseEntity<PatientResponseDTO> response = patientController.getByCpf("123.456.789-00");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(findPatientByCpfUseCase, times(1)).execute("123.456.789-00");
        verify(patientRestAdapter, times(1)).toDto(patient);
    }

    @Test
    void shouldUpdatePatientSuccessfully() {
        PatientUpdateDTO updateDTO = new PatientUpdateDTO("Jane", "Smith", "email@email.com", "12345678910", LocalDate.now(), "38999999999", "456", "Avenida", "Centro", "Nova Cidade", "Novo Estado", "11111111");
        Patient patient = new Patient();
        Patient updatedPatient = new Patient();
        PatientResponseDTO responseDTO = new PatientResponseDTO();

        when(patientRestAdapter.toDomain(updateDTO)).thenReturn(patient);
        when(updatePatientUseCase.execute(1L, patient)).thenReturn(updatedPatient);
        when(patientRestAdapter.toDto(updatedPatient)).thenReturn(responseDTO);

        ResponseEntity<PatientResponseDTO> response = patientController.update(1L, updateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(patientRestAdapter, times(1)).toDomain(updateDTO);
        verify(updatePatientUseCase, times(1)).execute(1L, patient);
        verify(patientRestAdapter, times(1)).toDto(updatedPatient);
    }

    @Test
    void shouldDeletePatientSuccessfully() {
        doNothing().when(deletePatientUseCase).execute(1L);

        ResponseEntity<Void> response = patientController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletePatientUseCase, times(1)).execute(1L);
    }
}