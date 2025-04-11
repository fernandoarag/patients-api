package br.com.fiap.postech.patientapi.interfaces.rest;


import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.*;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.adapters.PatientRestAdapter;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientRequestDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientResponseDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientUpdateDTO;
import br.com.fiap.postech.patientapi.validation.annotation.ValidCpf;
import br.com.fiap.postech.patientapi.validation.annotation.ValidPhone;
import br.com.fiap.postech.patientapi.validation.annotation.ValidZipCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para operações de CRUD de pacientes
 */
@Slf4j
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final CreatePatientUseCase createPatientUseCase;
    private final FindAllPatientsUseCase findAllPatientsUseCase;
    private final FindPatientByIdUseCase findPatientByIdUseCase;
    private final FindPatientByCpfUseCase findPatientByCpfUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final DeletePatientUseCase deletePatientUseCase;
    private final PatientRestAdapter patientRestAdapter;

    /**
     * Cria um novo paciente
     *
     * @param dto Dados do paciente
     * @return Patient criado
     */
    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(
            @Valid
            @Validated({ValidCpf.class, ValidZipCode.class, ValidPhone.class})
            @RequestBody
            PatientRequestDTO dto) {
        Patient patient = patientRestAdapter.toDomain(dto);
        Patient patientCreated = createPatientUseCase.execute(patient);
        PatientResponseDTO responseDTO = patientRestAdapter.toDto(patientCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Busca todos os pacientes cadastrados
     *
     * @return Lista de pacientes
     */
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAll() {
        List<Patient> patients = findAllPatientsUseCase.execute();
        List<PatientResponseDTO> responseDTOs = patientRestAdapter.toDtoList(patients);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * Busca um paciente pelo ID
     *
     * @param id ID do paciente
     * @return Patient encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable Long id) {
        Patient patient = findPatientByIdUseCase.execute(id);
        PatientResponseDTO responseDTO = patientRestAdapter.toDto(patient);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Busca um paciente pelo CPF
     *
     * @param cpf CPF do paciente
     * @return Patient encontrado
     */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PatientResponseDTO> getByCpf(@PathVariable String cpf) {
        Patient patient = findPatientByCpfUseCase.execute(cpf);
        PatientResponseDTO responseDTO = patientRestAdapter.toDto(patient);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Atualiza os dados de um paciente
     *
     * @param id  ID do paciente
     * @param dto Novos dados do paciente
     * @return Patient atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(
            @PathVariable Long id,
            @RequestBody PatientUpdateDTO dto) {
        Patient patient = patientRestAdapter.toDomain(dto);
        Patient patientUpdated = updatePatientUseCase.execute(id, patient);
        PatientResponseDTO responseDTO = patientRestAdapter.toDto(patientUpdated);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Exclui um paciente
     *
     * @param id ID do paciente
     * @return Sem conteúdo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deletePatientUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}