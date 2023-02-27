package es.iesjuanbosco.logica;

/**
 * La clase CuentaCorriente es una subclase abstracta de CuentaBancaria y
 * representa una cuenta corriente bancaria. Además de la información sobre el
 * titular, el saldo y el IBAN, posee un atributo adicional que es la lista de
 * entidades de cobro autorizadas.
 * <p>
 * Proporciona métodos para obtener y establecer la lista de entidades
 * autorizadas y devolver la información completa de la cuenta como una cadena
 * de caracteres. De ella heredan CuentaCorrientePersonal y
 * CuentaCorrienteEmpresa
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 * @see CuentaBancaria
 */
public abstract class CuentaCorriente extends CuentaBancaria {


    /*
     * Para listaEntidades creo que sería mejor un Array, Enum... Pero el
     * enunciado dice expresamente "Para guardar las entidades autorizadas en
     * las cuentas corrientes utiliza una cadena de caracteres". Por lo que
     * entiendo que se quiere un String.
     */
    /**
     * Lista de entidades autorizadas para cobrar recibos en la cuenta
     */
    private String listaEntidades;

    /**
     * Crea una nueva instancia de la clase CuentaCorriente con la lista de
     * entidades autorizadas, el titular, el saldo y el IBAN de la cuenta
     * especificados.
     *
     * @param listaEntidades la lista de entidades autorizadas para cobrar
     *                       recibos en la cuenta
     * @param titular        el titular de la cuenta
     * @param saldo          el saldo actual de la cuenta
     * @param iban           el IBAN de la cuenta
     */
    public CuentaCorriente(String listaEntidades, Persona titular, double saldo, String iban) {
        super(titular, saldo, iban);
        this.listaEntidades = listaEntidades;
    }

    /**
     * Devuelve la lista de entidades autorizadas para cobrar recibos en la
     * cuenta.
     *
     * @return String Lista de entidades autorizadas para cobrar recibos en la
     *         cuenta
     */
    public String getListaEntidades() {
        return listaEntidades;
    }

    /**
     * Establece la lista de entidades autorizadas para cobrar recibos en la
     * cuenta
     *
     * @param listaEntidades la nueva lista de entidades autorizadas para cobrar
     *                       recibos en la cuenta
     */
    public void setListaEntidades(String listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    /**
     * Contiene información sobre la cuenta, incluyendo la información del
     * titular de la cuenta, el saldo actual, el IBAN de la cuenta y la lista de
     * entidades autorizadas cobrar recibos en la cuenta.
     *
     * @return String que contiene información sobre la cuenta
     */
    @Override
    public String devolverInfoString() {
        return super.devolverInfoString()
                + "\nLista de entidades autorizadas: " + listaEntidades;
    }
}
