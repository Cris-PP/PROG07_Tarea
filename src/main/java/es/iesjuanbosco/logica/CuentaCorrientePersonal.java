package es.iesjuanbosco.logica;

/**
 * Esta subclase representa una cuenta corriente personal. Esta clase hereda de
 * la clase abstracta CuentaCorriente. Tiene un atributo adicional (comisión de
 * mantenimiento anual).
 * <p>
 * Proporciona métodos para obtener y establecer la comisión de mantenimiento
 * anual y devolver la información completa de la cuenta como una cadena.
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 * @see CuentaCorriente
 */
public class CuentaCorrientePersonal extends CuentaCorriente {

    /**
     * Comisión de mantenimiento anual de la cuenta corriente personal.
     */
    private double comisionMantenimientoAnual;

    /**
     * Constructor de la clase CuentaCorrientePersonal.
     *
     * @param titular                    el titular de la cuenta
     * @param saldo                      el saldo inicial de la cuenta
     * @param iban                       el IBAN de la cuenta
     * @param listaEntidades             la lista de entidades autorizadas
     * @param comisionMantenimientoAnual la comisión de mantenimiento anual de
     *                                   la cuenta
     */
    public CuentaCorrientePersonal(Persona titular, double saldo, String iban, String listaEntidades, double comisionMantenimientoAnual) {
        super(listaEntidades, titular, saldo, iban);
        this.comisionMantenimientoAnual = comisionMantenimientoAnual;
    }

    /**
     * Devuelve la comisión de mantenimiento anual de la cuenta.
     *
     * @return la comisión de mantenimiento anual
     */
    public double getComisionMantenimientoAnual() {
        return comisionMantenimientoAnual;
    }

    /**
     * Establece la comisión de mantenimiento anual de la cuenta.
     *
     * @param comisionMantenimientoAnual la comisión de mantenimiento anual a
     *                                   establecer
     */
    public void setComisionMantenimientoAnual(double comisionMantenimientoAnual) {
        this.comisionMantenimientoAnual = comisionMantenimientoAnual;
    }

    /**
     * Contiene información completa sobre la Cuenta Corriente Personal,
     * incluyendo la información del titular de la cuenta, el saldo actual, el
     * IBAN de la cuenta, la lista de entidades autorizadas, el tipo de cuenta
     * (Personal) y la comisión de mantenimiento anual.
     *
     * @return una cadena que contiene información completa sobre la cuenta
     *
     * @see CuentaCorriente#devolverInfoString() 
     * {@inheritDoc}
     */
    @Override
    public String devolverInfoString() {
        return super.devolverInfoString()
                + "\nTipo de cuenta: PERSONAL"
                + "\nComisión de mantenimiento anual: " + String.format("%.2f", comisionMantenimientoAnual) + '€';
    }
}
