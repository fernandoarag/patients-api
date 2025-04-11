package br.com.fiap.postech.patientapi.util;

public final class PhoneUtil {
    /**
     * Construtor privado para evitar instanciação
     */
    private PhoneUtil() {
        throw new UnsupportedOperationException("Classe utilitária não deve ser instanciada");
    }

    /**
     * Remove todos os caracteres não numéricos do telefone
     */
    public static String removeFormatting(String phone) {
        if (phone == null) {
            return null;
        }
        return phone.replaceAll("[^0-9]", "");
    }

    /**
     * Formata o telefone no padrão (XX) XXXXX-XXXX ou (XX) XXXX-XXXX
     */
    public static String format(String phone) {
        if (phone == null) {
            return null;
        }

        // Remove formatação caso já exista
        String numericPhone = removeFormatting(phone);

        // Verifica o tamanho para determinar o formato
        int length = numericPhone.length();

        if (length == 11) {
            // Celular: (XX) XXXXX-XXXX
            return "(" + numericPhone.substring(0, 2) + ") " +
                    numericPhone.substring(2, 7) + "-" +
                    numericPhone.substring(7);
        } else if (length == 10) {
            // Telefone fixo: (XX) XXXX-XXXX
            return "(" + numericPhone.substring(0, 2) + ") " +
                    numericPhone.substring(2, 6) + "-" +
                    numericPhone.substring(6);
        } else {
            // Se não estiver no formato esperado, retorna como está
            return phone;
        }
    }

    /**
     * Valida se um telefone possui formato válido
     */
    public static boolean isValid(String phone) {
        if (phone == null) {
            return false;
        }

        String numericPhone = removeFormatting(phone);
        // Verifica se tem entre 10 e 11 dígitos (telefone fixo ou celular)
        return (numericPhone.length() == 10 || numericPhone.length() == 11) &&
                numericPhone.matches("\\d+");
    }
}