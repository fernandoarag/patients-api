package br.com.fiap.postech.patientapi.infrastructure.mapper;


import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.infrastructure.entity.PatientEntity;

import java.util.List;

/**
 * Mapper responsável por converter entre as entidades de domínio e JPA.
 */
public interface PatientMapper {

    Patient toDomain(PatientEntity patientEntity);

    PatientEntity toEntity(Patient patient);

    List<Patient> toDomainList(List<PatientEntity> patientEntities);
}