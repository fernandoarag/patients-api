package br.com.fiap.postech.patientapi.validation.validator;

import br.com.fiap.postech.patientapi.util.CpfUtil;
import br.com.fiap.postech.patientapi.validation.annotation.ValidCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCpf, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false; // CPF é obrigatório
        }
        return CpfUtil.isValid(cpf);
    }
}