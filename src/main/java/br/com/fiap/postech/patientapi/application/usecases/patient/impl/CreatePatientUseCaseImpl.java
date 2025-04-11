package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.exception.custom.InvalidPatientDataException;
import br.com.fiap.postech.patientapi.application.exception.custom.PatientCpfAlreadyExistsException;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.CreatePatientUseCase;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation of the CreatePatientUseCase interface.
 * Handles the creation of a new patient.
 */
@Service
@RequiredArgsConstructor
public class CreatePatientUseCaseImpl implements CreatePatientUseCase {

    private final PatientGateway patientGateway;

    public Patient execute(Patient patient) {
        validPatient(patient);

        if (patientGateway.existsByCpf(patient.getCpf())) {
            throw new PatientCpfAlreadyExistsException(patient.getCpf());
        }

        return patientGateway.save(patient);
    }

    private void validPatient(Patient patient) {
        if (patient.getFirstName() == null || patient.getFirstName().trim().isEmpty()) {
            throw new InvalidPatientDataException("The name of patient is required!");
        }

        if (patient.getCpf() == null || patient.getCpf().trim().isEmpty()) {
            throw new InvalidPatientDataException("The CPF of patient is required!");
        }

        if (patient.getDateOfBirth() == null) {
            throw new InvalidPatientDataException("The date of birth of patient is required!");
        }
    }
}