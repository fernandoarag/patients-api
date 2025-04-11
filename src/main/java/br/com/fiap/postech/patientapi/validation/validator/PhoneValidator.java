package br.com.fiap.postech.patientapi.validation.validator;

import br.com.fiap.postech.patientapi.util.PhoneUtil;
import br.com.fiap.postech.patientapi.validation.annotation.ValidPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.trim().isEmpty()) {
            return true; // Telefone pode ser opcional
        }
        return PhoneUtil.isValid(phone);
    }
}