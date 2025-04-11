package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;

import java.util.List;

/**
 * Caso de uso para buscar todos os pacientes
 */
public interface FindAllPatientsUseCase {
    List<Patient> execute();
}