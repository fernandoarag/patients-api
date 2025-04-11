package br.com.fiap.postech.patientapi.infrastructure.converter;

import br.com.fiap.postech.patientapi.util.CpfUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CpfConverter implements AttributeConverter<String, String> {

    /**
     * Converte o CPF para o formato sem formatação ao salvar no banco de dados.
     *
     * @param cpf o CPF a ser convertido
     * @return o CPF sem formatação
     */
    @Override
    public String convertToDatabaseColumn(String cpf) {
        return CpfUtil.removeFormatting(cpf);
    }

    @Override
    public String convertToEntityAttribute(String cpfDB) {
        // Retorna o valor do banco como está (sem formatação)
        return cpfDB;
    }
}
