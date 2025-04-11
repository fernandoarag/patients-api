package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

/**
 * Interface para o caso de uso de exclusão de paciente.
 * Define o método execute que deve ser implementado para excluir um paciente pelo ID.
 */
public interface DeletePatientUseCase {
    void execute(Long id);
}