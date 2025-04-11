package br.com.fiap.postech.patientapi.util;

public final class CpfUtil {

    /**
     * Construtor privado para evitar instanciação
     */
    private CpfUtil() {
        throw new UnsupportedOperationException("Classe utilitária não deve ser instanciada");
    }

    /**
     * Remove todos os caracteres não numéricos do CPF
     */
    public static String removeFormatting(String cpf) {
        if (cpf == null) {
            return null;
        }
        return cpf.replaceAll("[^0-9]", "");
    }

    /**
     * Formata o CPF no padrão XXX.XXX.XXX-XX
     */
    public static String format(String cpf) {
        if (cpf == null) {
            return null;
        }

        // Remove formatação caso já exista
        String numericCpf = removeFormatting(cpf);

        // Verifica se tem 11 dígitos
        if (numericCpf.length() != 11) {
            return cpf; // Retorna como está se não for um CPF válido
        }

        // Formata o CPF
        return numericCpf.substring(0, 3) + "." +
                numericCpf.substring(3, 6) + "." +
                numericCpf.substring(6, 9) + "-" +
                numericCpf.substring(9);
    }

    /**
     * Valida se um CPF possui formato válido
     */
    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        String numericCpf = removeFormatting(cpf);
        return numericCpf.length() == 11 && numericCpf.matches("\\d+");
    }
}