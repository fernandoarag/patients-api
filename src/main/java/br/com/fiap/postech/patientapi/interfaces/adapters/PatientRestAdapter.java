package br.com.fiap.postech.patientapi.interfaces.adapters;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientRequestDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientResponseDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientUpdateDTO;

import java.util.List;

public interface PatientRestAdapter {

    Patient toDomain(PatientRequestDTO dto);

    Patient toDomain(PatientUpdateDTO dto);

    PatientResponseDTO toDto(Patient domain);

    List<PatientResponseDTO> toDtoList(List<Patient> domains);
}
