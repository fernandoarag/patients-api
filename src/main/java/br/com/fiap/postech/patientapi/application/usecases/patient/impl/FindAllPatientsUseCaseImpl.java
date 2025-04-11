package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.FindAllPatientsUseCase;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso para buscar todos os pacientes
 */
@Service
@RequiredArgsConstructor
public class FindAllPatientsUseCaseImpl implements FindAllPatientsUseCase {

    private final PatientGateway patientGateway;

    public List<Patient> execute() {
        return patientGateway.findAll();
    }
}