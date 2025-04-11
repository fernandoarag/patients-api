package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;

/**
 * Caso de uso para buscar um paciente pelo ID
 */
public interface FindPatientByIdUseCase {
    Patient execute(Long id);
}