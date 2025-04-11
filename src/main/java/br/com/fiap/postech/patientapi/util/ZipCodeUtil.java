package br.com.fiap.postech.patientapi.util;

public final class ZipCodeUtil {

    /**
     * Construtor privado para evitar instanciação
     */
    private ZipCodeUtil() {
        throw new UnsupportedOperationException("Classe utilitária não deve ser instanciada");
    }

    /**
     * Remove todos os caracteres não numéricos do CEP
     */
    public static String removeFormatting(String zipCode) {
        if (zipCode == null) {
            return null;
        }
        return zipCode.replaceAll("[^0-9]", "");
    }

    /**
     * Formata o CEP no padrão XXXXX-XXX
     */
    public static String format(String zipCode) {
        if (zipCode == null) {
            return null;
        }

        // Remove formatação caso já exista
        String numericZipCode = removeFormatting(zipCode);

        // Verifica se tem 8 dígitos
        if (numericZipCode.length() != 8) {
            return zipCode; // Retorna como está se não for um CEP válido
        }

        // Formata o CEP no padrão XXXXX-XXX
        return numericZipCode.substring(0, 5) + "-" + numericZipCode.substring(5);
    }

    /**
     * Valida se um CEP possui formato válido
     */
    public static boolean isValid(String zipCode) {
        if (zipCode == null) {
            return false;
        }

        String numericZipCode = removeFormatting(zipCode);
        return numericZipCode.length() == 8 && numericZipCode.matches("\\d+");
    }
}