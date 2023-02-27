package es.iesjuanbosco.logica;

/**
 * La clase CuentaBancaria es una clase abstracta que representa una cuenta
 * bancaria. Contiene información sobre el titular de la cuenta, el saldo actual
 * y el IBAN de la cuenta.
 * <p>
 * Proporciona métodos para obtener y establecer la información de la cuenta, y
 * devolver la información completa de la cuenta como una cadena.
 * <p>
 * Implementa la interfaz Imprimible
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 * @see Imprimible
 */
public abstract class CuentaBancaria implements Imprimible {

    /**
     * El titular de la cuenta bancaria.
     */
    private Persona titular;
    /**
     * El saldo actual de la cuenta bancaria.
     */
    private double saldo;
    /**
     * El IBAN de la cuenta bancaria.
     */
    private String iban;

    /**
     * Constructor con el titular de la cuenta, el saldo actual y el IBAN de la
     * cuenta.
     *
     * @param titular el titular de la cuenta
     * @param saldo   el saldo actual de la cuenta
     * @param iban    el IBAN de la cuenta
     */
    public CuentaBancaria(Persona titular, double saldo, String iban) {
        this.titular = titular;
        this.saldo = saldo;
        this.iban = iban;
    }

    /**
     * Devuelve el titular de la cuenta.
     *
     * @return el titular de la cuenta
     */
    public Persona getTitular() {
        return titular;
    }

    /**
     * Establece el titular de la cuenta.
     *
     * @param titular el titular de la cuenta
     */
    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    /**
     * Devuelve el saldo actual de la cuenta.
     *
     * @return el saldo actual de la cuenta
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo actual de la cuenta.
     *
     * @param saldo el saldo actual de la cuenta
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Devuelve el IBAN de la cuenta.
     *
     * @return el IBAN de la cuenta
     */
    public String getIban() {
        return iban;
    }

    /**
     * Establece el IBAN de la cuenta.
     *
     * @param iban el IBAN de la cuenta
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Contiene información sobre la cuenta, incluyendo la información del
     * titular de la cuenta, el saldo actual y el IBAN de la cuenta.
     *
     * @return una cadena que contiene información sobre la cuenta
     *
     * @see Imprimible#devolverInfoString()      *
     * {@inheritDoc}
     */
    @Override
    public String devolverInfoString() {
        return "Titular de la cuenta\n" + titular.devolverInfoString()
                + "\nDetalle de la cuenta"
                + "\nSaldo: " + String.format("%.2f", saldo) + '€'
                + "\nIBAN: " + iban;
    }

}
