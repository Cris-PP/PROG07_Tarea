package es.iesjuanbosco.logica;

/**
 * La clase CuentaAhorro es una subclase de CuentaBancaria y representa una
 * cuenta bancaria de ahorro remunerada. Además de la información sobre el
 * titular, el saldo y el IBAN, esta cuenta posee un atributo adicional que es
 * el tipo de interés anual.
 * <p>
 * Proporciona métodos para obtener y establecer el tipo de interés anual y
 * devolver la información completa de la cuenta como una cadena.
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 * @see CuentaBancaria
 */
public class CuentaAhorro extends CuentaBancaria {

    /**
     * El tipo de interes anual de la cuenta de ahorro
     */
    private double tipoInteresAnual;

    /**
     * Construye una instancia de CuentaAhorro con el titular de la cuenta, el
     * saldo actual, el IBAN de la cuenta y el tipo de interés anual
     * especificado.
     *
     * @param titular          el titular de la cuenta
     * @param saldo            el saldo actual de la cuenta
     * @param iban             el IBAN de la cuenta
     * @param tipoInteresAnual el tipo de interés anual de la cuenta
     */
    public CuentaAhorro(Persona titular, double saldo, String iban, double tipoInteresAnual) {
        super(titular, saldo, iban);
        this.tipoInteresAnual = tipoInteresAnual;
    }

    /**
     * Devuelve el tipo de interés anual de la cuenta.
     *
     * @return el tipo de interés anual de la cuenta
     */
    public double getTipoInteresAnual() {
        return tipoInteresAnual;
    }

    /**
     * Establece el tipo de interés anual de la cuenta.
     *
     * @param tipoInteresAnual el tipo de interés anual de la cuenta
     */
    public void setTipoInteresAnual(double tipoInteresAnual) {
        this.tipoInteresAnual = tipoInteresAnual;
    }

    /**
     * Contiene información completa sobre la Cuenta Corriente Personal,
     * incluyendo la información del titular de la cuenta, el saldo actual, el
     * IBAN de la cuenta, el tipo de cuenta (Ahorro) y el tipo de interés anual.
     *
     * @return String que contiene la información completa sobre la cuenta de
     *         ahorro
     */
    @Override
    public String devolverInfoString() {
        return super.devolverInfoString()
                + "\nTipo de cuenta: AHORRO"
                + "\nTipo de Interés anual: "
                + String.format("%.2f%%", tipoInteresAnual);
    }

}
