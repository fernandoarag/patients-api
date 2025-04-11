package br.com.fiap.postech.patientapi.infrastructure.repository;


import br.com.fiap.postech.patientapi.infrastructure.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for PatientEntity.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    /**
     * Busca um paciente pelo ID
     *
     * @param id ID do paciente
     * @return Optional contendo o paciente se encontrado
     */
    Optional<PatientEntity> findPatientEntityById(final Long id);

    /**
     * Busca um paciente pelo CPF
     *
     * @param cpf CPF do paciente
     * @return Optional contendo o paciente se encontrado
     */
    Optional<PatientEntity> findByCpf(final String cpf);

    boolean existsByCpf(String cpf);
}