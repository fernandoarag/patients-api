package br.com.fiap.postech.patientapi.infrastructure.converter;

import br.com.fiap.postech.patientapi.util.PhoneUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PhoneConverter implements AttributeConverter<String, String> {

    /**
     * Converte o número de telefone para o formato armazenado no banco de dados.
     * Remove qualquer caractere não numérico (parênteses, traços, espaços).
     *
     * @param phone o número de telefone a ser convertido
     * @return o número de telefone formatado para o banco de dados
     */
    @Override
    public String convertToDatabaseColumn(String phone) {
        return PhoneUtil.removeFormatting(phone);
    }

    @Override
    public String convertToEntityAttribute(String phoneDB) {
        // Retorna o valor do banco como está (sem formatação)
        return phoneDB;
    }
}
