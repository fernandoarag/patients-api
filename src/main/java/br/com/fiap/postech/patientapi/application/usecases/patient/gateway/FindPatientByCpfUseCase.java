package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;

/**
 * Caso de uso para buscar um paciente pelo CPF
 */
public interface FindPatientByCpfUseCase {
    Patient execute(String cpf);
}