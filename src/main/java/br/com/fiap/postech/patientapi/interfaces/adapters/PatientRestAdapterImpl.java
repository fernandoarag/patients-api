package br.com.fiap.postech.patientapi.interfaces.adapters;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientRequestDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientResponseDTO;
import br.com.fiap.postech.patientapi.interfaces.dtos.PatientUpdateDTO;
import br.com.fiap.postech.patientapi.util.CpfUtil;
import br.com.fiap.postech.patientapi.util.PhoneUtil;
import br.com.fiap.postech.patientapi.util.ZipCodeUtil;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Implementação manual do mapper responsável por converter entre as entidades de domínio e JPA.
 */
@Component
public class PatientRestAdapterImpl implements PatientRestAdapter {

    @Override
    public Patient toDomain(PatientRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Patient.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .cpf(CpfUtil.removeFormatting(dto.getCpf()))
                .dateOfBirth(dto.getDateOfBirth())
                .phone(PhoneUtil.removeFormatting(dto.getPhone()))
                .number(dto.getNumber())
                .street(dto.getStreet())
                .neighborhood(dto.getNeighborhood())
                .city(dto.getCity())
                .state(dto.getState())
                .zipcode(ZipCodeUtil.removeFormatting(dto.getZipcode()))
                .build();
    }

    @Override
    public Patient toDomain(PatientUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        return Patient.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .cpf(CpfUtil.removeFormatting(dto.getCpf()))
                .dateOfBirth(dto.getDateOfBirth())
                .phone(PhoneUtil.removeFormatting(dto.getPhone()))
                .number(dto.getNumber())
                .street(dto.getStreet())
                .neighborhood(dto.getNeighborhood())
                .city(dto.getCity())
                .state(dto.getState())
                .zipcode(ZipCodeUtil.removeFormatting(dto.getZipcode()))
                .build();
    }

    @Override
    public PatientResponseDTO toDto(Patient patient) {
        if (patient == null) {
            return null;
        }

        return PatientResponseDTO.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .cpf(CpfUtil.format(patient.getCpf()))
                .dateOfBirth(patient.getDateOfBirth())
                .phone(PhoneUtil.format(patient.getPhone()))
                .number(patient.getNumber())
                .street(patient.getStreet())
                .neighborhood(patient.getNeighborhood())
                .city(patient.getCity())
                .state(patient.getState())
                .zipcode(ZipCodeUtil.format(patient.getZipcode()))
                .build();
    }

    @Override
    public List<PatientResponseDTO> toDtoList(List<Patient> domains) {
        if (domains == null) {
            return Collections.emptyList();
        }

        return domains.stream()
                .map(this::toDto)
                .toList();
    }
}