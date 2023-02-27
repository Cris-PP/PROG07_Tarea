package es.iesjuanbosco.logica;

/**
 * Esta subclase representa una cuenta corriente de una empresa. Hereda de la clase
 * CuentaCorriente. Posee atributos adicionales para el descubierto permitido,
 * la comisión por descubierto y el tipo de interés por descubierto.
 * <p>
 * Proporciona métodos para obtener y establecer el máximo descubierto
 * permitido, la comisión fija por descubierto, el tipo de interés por
 * descubierto y para devolver la información de la cuenta como cadena
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 * @see CuentaCorriente
 */
public class CuentaCorrienteEmpresa extends CuentaCorriente {

    /**
     * Descubierto máximo permitido de la cuenta.
     */
    private double maxDescubierto;

    /**
     * Tipo de interés aplicado al descubierto.
     */
    private double tipoInteresDescubierto;

    /**
     * Comisión fija aplicada al descubierto.
     */
    private double comisionFijaDescubierto;

    /**
     * Crea una nueva instancia de CuentaCorrienteEmpresa con los datos
     * proporcionados.
     *
     * @param titular                 El titular de la cuenta
     * @param saldo                   El saldo inicial de la cuenta
     * @param iban                    El IBAN de la cuenta
     * @param listaEntidades          La lista de entidades asociadas a la
     *                                cuenta
     * @param maxDescubierto          El descubierto máximo permitido de la
     *                                cuenta
     * @param tipoInteresDescubierto  El tipo de interés por descubierto de la
     *                                cuenta
     * @param comisionFijaDescubierto La comisión fija por descubierto de la
     *                                cuenta
     */
    public CuentaCorrienteEmpresa(Persona titular, double saldo, String iban, String listaEntidades, double maxDescubierto, double tipoInteresDescubierto, double comisionFijaDescubierto) {
        super(listaEntidades, titular, saldo, iban);
        this.maxDescubierto = maxDescubierto;
        this.tipoInteresDescubierto = tipoInteresDescubierto;
        this.comisionFijaDescubierto = comisionFijaDescubierto;
    }

    /**
     * Devuelve el valor del descubierto máximo permitido de la cuenta.
     *
     * @return el valor del descubierto máximo permitido
     */
    public double getMaxDescubierto() {
        return maxDescubierto;
    }

    /**
     * Establece el valor del descubierto máximo permitido de la cuenta.
     *
     * @param maxDescubierto el nuevo valor del descubierto máximo permitido
     */
    public void setMaxDescubierto(double maxDescubierto) {
        this.maxDescubierto = maxDescubierto;
    }

    /**
     * Devuelve el tipo de interés aplicado al descubierto de la cuenta.
     *
     * @return el tipo de interés por descubierto
     */
    public double getTipoInteresDescubierto() {
        return tipoInteresDescubierto;
    }

    /**
     * Establece el tipo de interés aplicable al descubierto de la cuenta.
     *
     * @param tipoInteresDescubierto el tipo de interés por descubierto
     */
    public void setTipoInteresDescubierto(double tipoInteresDescubierto) {
        this.tipoInteresDescubierto = tipoInteresDescubierto;
    }

    /**
     * Devuelve la comisión fija aplicada al descubierto de la cuenta.
     *
     * @return la comisión fija por descubierto
     */
    public double getComisionFijaDescubierto() {
        return comisionFijaDescubierto;
    }

    /**
     * Establece la comisión fija aplicable al descubierto de la cuenta.
     *
     * @param comisionFijaDescubierto la comisión fija por descubierto
     */
    public void setComisionFijaDescubierto(double comisionFijaDescubierto) {
        this.comisionFijaDescubierto = comisionFijaDescubierto;
    }

    /**
     * Contiene información completa sobre la Cuenta Corriente de Empresa,
     * incluyendo la información del titular de la cuenta, el saldo actual, el
     * IBAN de la cuenta, la lista de entidades autorizadas, el tipo de cuenta
     * (Empresa), el descubierto máximo permitido, el tipo de interés por
     * descubierto y la comisión fija por descubierto
     *
     * @return una cadena que contiene información completa sobre la cuenta de
     *         empresa
     */
    @Override
    public String devolverInfoString() {
        return super.devolverInfoString()
                + "\nTipo de cuenta: EMPRESA"
                + "\nDescubierto máximo permitido: " + String.format("%.2f€", maxDescubierto)
                + "\nTipo de interés por descubierto: " + String.format("%.2f%%", tipoInteresDescubierto)
                + "\nComisión fija por descubierto: " + String.format("%.2f€", comisionFijaDescubierto);
    }

}
