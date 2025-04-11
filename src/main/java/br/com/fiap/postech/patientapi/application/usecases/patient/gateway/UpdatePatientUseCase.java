package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;

/**
 * Caso de uso para atualizar os dados de um paciente
 */
public interface UpdatePatientUseCase {
    Patient execute(Long id, Patient updatedPatient);
}