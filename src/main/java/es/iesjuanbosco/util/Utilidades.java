package es.iesjuanbosco.util;

/**
 * Clase que proporciona utilidades tales como comprobar DNIs y códigos IBAN con
 * expresiones regulares y formatear nombres o apellidos
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 */
public class Utilidades {

    /**
     * Expresión regular para validar DNIs
     */
    final static String DNI_REGEX = "^[0-9]{7,8}[a-zA-Z]{1}+$";
    /**
     * Expresión regular para validar IBANs
     */
    final static String IBAN_REGEX = "^ES[0-9]{20}+$";

    /**
     * Verifica si un IBAN es válido
     *
     * @param iban el IBAN a comprobar
     *
     * @return true si el IBAN es válido, false en caso contrario
     */
    public static boolean esCorrectoIBAN(String iban) {
        return iban.matches(IBAN_REGEX);
    }

    /**
     * Verifica si un DNI es válido
     *
     * @param dni el DNI a comprobar
     *
     * @return true si el DNI es válido, false en caso contrario
     */
    public static boolean esCorrectoDNI(String dni) {
        return dni.matches(DNI_REGEX);
    }

    /**
     * Formatea el nombre o apellido dado con la primera letra de cada palabra
     * en mayúscula, excepto en el caso de que la palabra sea "de" o "del".
     * Todas las demás letras de cada palabra se convertirán a minúsculas.
     *
     * @param nombreApellido Nombre o apellido a formatear
     *
     * @return Cadena con el nombre o apellido formateado con la primera letra
     *         en mayúscula
     */
    public static String formatoNombreApellido(String nombreApellido) {
        String nombreApellidoMinusculas = nombreApellido.trim().toLowerCase();
        String[] nomApellidoString = nombreApellidoMinusculas.split("\\s+");
        StringBuilder sb = new StringBuilder(16);
        for (String nomAp : nomApellidoString) {
            // Si el nombre o apellido compuesto contiene "de" o "del" estos no se capitalizan
            if (nomAp.equals("de") || nomAp.equals("del")) {
                sb.append(nomAp).append(" ");
            } else {
                sb.append(nomAp.substring(0, 1)
                        .toUpperCase()).append(nomAp.substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
    }

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private Utilidades() {
    }

}
