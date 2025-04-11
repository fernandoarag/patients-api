package br.com.fiap.postech.patientapi.interfaces.gateway.impl;


import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.infrastructure.entity.PatientEntity;
import br.com.fiap.postech.patientapi.infrastructure.mapper.PatientMapper;
import br.com.fiap.postech.patientapi.infrastructure.repository.PatientRepository;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CustomerGateway interface using JPA.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PatientJpaGateway implements PatientGateway {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public Patient save(Patient patient) {
        final PatientEntity patientEntity = patientMapper.toEntity(patient);
        return patientMapper.toDomain(patientRepository.save(patientEntity));
    }

    @Override
    public List<Patient> findAll() {
        return patientMapper.toDomainList(patientRepository.findAll());
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findPatientEntityById(id)
                .map(patientMapper::toDomain);
    }

    @Override
    public Optional<Patient> findByCpf(String cpf) {
        return patientRepository.findByCpf(cpf)
                .map(patientMapper::toDomain);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return patientRepository.existsByCpf(cpf);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        patientRepository.deleteById(id);
    }
}