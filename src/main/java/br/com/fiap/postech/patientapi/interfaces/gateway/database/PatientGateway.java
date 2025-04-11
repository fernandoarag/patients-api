package br.com.fiap.postech.patientapi.interfaces.gateway.database;


import br.com.fiap.postech.patientapi.domain.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientGateway {
    /**
     * Salva um paciente no repositório
     *
     * @param patient Paciente a ser salvo
     * @return Paciente salvo com ID gerado
     */
    Patient save(final Patient patient);

    /**
     * Busca todos os pacientes cadastrados
     *
     * @return Lista de todos os pacientes
     */
    List<Patient> findAll();

    /**
     * Busca um paciente pelo ID
     *
     * @param id ID do paciente
     * @return Optional contendo o paciente se encontrado
     */
    Optional<Patient> findById(final Long id);

    /**
     * Busca um paciente pelo CPF
     *
     * @param cpf CPF do paciente
     * @return Optional contendo o paciente se encontrado
     */
    Optional<Patient> findByCpf(final String cpf);

    /**
     * Verifica se existe um paciente com o CPF informado
     *
     * @param cpf CPF a ser verificado
     * @return true se existir, false caso contrário
     */
    boolean existsByCpf(final String cpf);

    /**
     * Exclui um paciente pelo ID
     *
     * @param id ID do paciente a ser excluído
     */
    void deleteById(final Long id);
}