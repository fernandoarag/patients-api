package br.com.fiap.postech.patientapi.infrastructure.mapper;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.infrastructure.entity.PatientEntity;
import br.com.fiap.postech.patientapi.util.CpfUtil;
import br.com.fiap.postech.patientapi.util.PhoneUtil;
import br.com.fiap.postech.patientapi.util.ZipCodeUtil;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Mapper responsável por converter entre as entidades de domínio e JPA.
 */
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public Patient toDomain(PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }

        return Patient.builder()
                .id(patientEntity.getId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .email(patientEntity.getEmail())
                .cpf(CpfUtil.format(patientEntity.getCpf()))
                .dateOfBirth(patientEntity.getDateOfBirth())
                .phone(PhoneUtil.format(patientEntity.getPhone()))
                .number(patientEntity.getNumber())
                .street(patientEntity.getStreet())
                .neighborhood(patientEntity.getNeighborhood())
                .city(patientEntity.getCity())
                .state(patientEntity.getState())
                .zipcode(ZipCodeUtil.format(patientEntity.getZipcode()))
                .build();
    }

    @Override
    public PatientEntity toEntity(Patient patient) {
        if (patient == null) {
            return null;
        }

        return PatientEntity.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .cpf(CpfUtil.removeFormatting(patient.getCpf()))
                .dateOfBirth(patient.getDateOfBirth())
                .phone(PhoneUtil.removeFormatting(patient.getPhone()))
                .number(patient.getNumber())
                .street(patient.getStreet())
                .neighborhood(patient.getNeighborhood())
                .city(patient.getCity())
                .state(patient.getState())
                .zipcode(ZipCodeUtil.removeFormatting(patient.getZipcode()))
                .build();
    }

    @Override
    public List<Patient> toDomainList(List<PatientEntity> patientEntities) {
        if (patientEntities == null) {
            return Collections.emptyList();
        }

        return patientEntities.stream()
                .map(this::toDomain)
                .toList();
    }

}