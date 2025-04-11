package br.com.fiap.postech.patientapi.infrastructure.converter;

import br.com.fiap.postech.patientapi.util.ZipCodeUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ZipCodeConverter implements AttributeConverter<String, String> {

    /**
     * Converte o valor do atributo (String) para o valor do banco de dados (String).
     * Remove a formatação do CEP.
     *
     * @param zipCode o valor do atributo
     * @return o valor do banco de dados
     */
    @Override
    public String convertToDatabaseColumn(String zipCode) {
        return ZipCodeUtil.removeFormatting(zipCode);
    }

    @Override
    public String convertToEntityAttribute(String zipCodeDB) {
        // Retorna o valor do banco como está (sem formatação)
        return zipCodeDB;
    }
}
