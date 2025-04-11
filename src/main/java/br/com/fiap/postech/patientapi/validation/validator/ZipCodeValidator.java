package br.com.fiap.postech.patientapi.validation.validator;

import br.com.fiap.postech.patientapi.util.ZipCodeUtil;
import br.com.fiap.postech.patientapi.validation.annotation.ValidZipCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ValidZipCode, String> {
    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext context) {
        if (zipCode == null || zipCode.trim().isEmpty()) {
            return true; // CEP pode ser opcional
        }
        return ZipCodeUtil.isValid(zipCode);
    }
}