package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;

/**
 * Use case for creating a patient.
 */
public interface CreatePatientUseCase {

    /**
     * Executes the use case to create a patient.
     *
     * @param patient the patient to create
     * @return the created patient
     */
    Patient execute(Patient patient);
}