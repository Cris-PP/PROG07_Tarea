package es.iesjuanbosco.logica;

/**
 * La clase Banco representa un banco que almacena cuentas bancarias y realiza
 * operaciones con ellas. Las cuentas bancarias se almacenan en un array de
 * cuentas.
 * <p>
 * Proporciona métodos para abrir, listar e interactuar con cuentas bancarias,
 * como ingresos, retiradas y obtención de información de cuentas. También
 * permite comprobar si una cuenta existe en el banco.
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 */
public class Banco {

    /**
     * Array que almacena todas las cuentas bancarias del banco.
     */
    private final CuentaBancaria[] CUENTA;

    /**
     * Número máximo de cuentas bancarias que puede almacenar el banco.
     */
    private final int NUM_MAXIMO_CUENTAS = 100;

    /**
     * Contador que lleva el número actual de cuentas almacenadas en el banco.
     */
    private int contadorCuentas;

    /**
     * Crea una instancia de Banco, inicializando el array de cuentas bancarias
     * y el contador de cuentas.
     */
    public Banco() {
        this.CUENTA = new CuentaBancaria[this.NUM_MAXIMO_CUENTAS];
        this.contadorCuentas = 0;
    }

    /**
     * Devuelve el número actual de cuentas almacenadas en el banco.
     *
     * @return el número actual de cuentas
     */
    public int getContadorCuentas() {
        return contadorCuentas;
    }

    /**
     * Agrega una cuenta bancaria al array de cuentas del banco.
     *
     * @param cuenta la cuenta bancaria a agregar
     *
     * @return true si la cuenta fue agregada con éxito, false si el número
     *         máximo de cuentas ya se alcanzó
     */
    public boolean abrirCuenta(CuentaBancaria cuenta) {
        // Si se ha llegado al máximo de cuentas (100) devuelve false y no se ejecutará el resto
        if (this.contadorCuentas == this.NUM_MAXIMO_CUENTAS) {
            return false;
        }
        /*
         * Se añade al array utilizando como posición el contador la cuenta
         * pasada por parámetros, se incrementa el contador y devuelve true
         */
        this.CUENTA[this.contadorCuentas] = cuenta;
        this.contadorCuentas++;
        return true;
    }

    /**
     * Devuelve un array de Strings con información sobre todas las cuentas
     * almacenadas en el banco.
     *
     * @return un array de Strings con información sobre todas las cuentas
     */
    public String[] listadoCuentas() {
        String[] listado = new String[this.contadorCuentas];
        for (int i = 0; i < this.contadorCuentas; i++) {
            listado[i] = this.CUENTA[i].devolverInfoString();
        }
        return listado;
    }

    /**
     * Devuelve información sobre una cuenta bancaria específica a partir de su
     * IBAN.
     *
     * @param iban el IBAN de la cuenta bancaria
     *
     * @return un String con información sobre la cuenta bancaria, o null si la
     *         cuenta no existe
     */
    public String informacionCuenta(String iban) {
        if (this.esCuentaExistente(iban)) {
            return this.buscadorCuenta(iban).devolverInfoString();
        }
        return null;
    }

    /**
     * Realiza un ingreso en una cuenta bancaria específica a partir de su IBAN.
     *
     * @param iban     el IBAN de la cuenta en la que se desea realizar el
     *                 ingreso
     * @param cantidad la cantidad que se desea ingresar en la cuenta
     *
     * @return true si la operación se realiza correctamente, false si no es
     *         posible realizar la operación
     */
    public boolean ingresoCuenta(String iban, double cantidad) {
        CuentaBancaria cuenta = this.buscadorCuenta(iban);
        /*
         * Descartamos cantidades negativas. Esto también se comprueba en el
         * main para volver a solicitar una cantidad correcta, por lo que no se
         * ejecutará, pero lo pongo por si se hace de manera "directa" o se
         * cambia en el menú, de modo que no se permita ingresar cantidades
         * negativas bajo ningún concepto
         */
        if (cantidad <= 0) {
            System.out.println("¡La cantidad a ingresar no puede ser 0 o negativa!");
            return false;
            /*
             * Comprobamos si la cuenta existe. De nuevo esto también se
             * comprueba en el main y en condiciones normales jamás se
             * ejecutará, pero lo pongo por las mismas razones que he explicado
             * más arriba
             */
        } else if (!esCuentaExistente(iban)) {
            System.out.println("¡La cuenta ingresada no existe!");
            return false;
        } else {
            /*
             * Una vez comprobado que la cuenta existe y que la cantidad es
             * positiva se llama al método setSaldo y se le suma al saldo actual
             * (getSaldo) la cantidad introducida. Se muestra un mensaje y
             * devuelve true
             */
            cuenta.setSaldo(cuenta.getSaldo() + cantidad);
            System.out.println("Se ha ingresado la cantidad de: " + cantidad
                    + "\nEl nuevo saldo de la cuenta es: " + cuenta.getSaldo());
            return true;
        }
    }

    /**
     * Realiza una retirada de una cantidad de dinero en una cuenta bancaria
     * específica a partir de su IBAN. Si la cuenta es de empresa y la retirada
     * de dinero provoca que la cuenta quede al descubierto, se aplicará una
     * comisión fija por descubierto.
     *
     * @param iban     el IBAN de la cuenta bancaria
     * @param cantidad la cantidad de dinero a retirar
     *
     * @return true si la operación se realiza correctamente, false en caso
     *         contrario
     */
    public boolean retiradaCuenta(String iban, double cantidad) {
        CuentaBancaria cuenta = this.buscadorCuenta(iban);
        /*
         * Descartamos cantidades negativas. Esto también se comprueba en el
         * main para volver a solicitar una cantidad correcta.
         */
        if (cantidad <= 0) {
            return false;
        }
        /*
         * Comprobamos que la cuenta exista para evitar excepciones por Null
         * Pointer. En realidad la cuenta existirá siempre ya que en el método
         * main he implementado ya una comprobación para volver a solicitar el
         * IBAN en caso de que no exista, pero así evitamos el warning en el IDE
         * de posible Null Pointer, además de evitar posible excepción en caso
         * de hacer una prueba "manual" del método o se utiliza en otro lugar en
         * un futuro sin hacer dicha comprobación
         */
        if (cuenta != null) {
            /*
             * Si la cuenta es de empresa puede haber descubiertos
             *
             * ¡No debe usarse instanceof <pattern>! ¡Provoca excepción! Hay que
             * hacer cast explícito para poder trabajar con cuentaEmpresa a
             * posteriori
             */
            if (cuenta instanceof CuentaCorrienteEmpresa) {
                CuentaCorrienteEmpresa cuentaEmpresa = (CuentaCorrienteEmpresa) cuenta;
                // Cálculo del monto máximo disponible (saldo disponible + saldo máximo descubierto)
                double disponibleMaximo = cuentaEmpresa.getSaldo() + cuentaEmpresa.getMaxDescubierto();
                // Si la cantidad es superior al máximo disponible no se puede realizar la operación
                if (cantidad > disponibleMaximo) {
                    System.out.println("La cantidad introducida es superior al saldo disponible + descubierto máximo");
                    return false;
                    /*
                     * Si la cantidad deja al descubierto la cuenta, se muestra
                     * mensaje y se aplica la comisión fija por descubierto.
                     *
                     * Esta comisión suele ser un porcentaje el cual tiene un
                     * mínimo prefijado (por ejemplo 4,5% con un mínimo de 15€)
                     * pero como no se especifica nada en el enunciado lo trato
                     * como si fuera una cantidad predefinida. De querer
                     * aplicarlo así no sería complicado, se declararía una
                     * constante con la comisión mínima, se compararía con la
                     * comisión con porcentaje, y si esta es inferior se
                     * restaría la comisión mínima en lugar de la comisión
                     * calculada.
                     */
                } else if (cantidad > cuentaEmpresa.getSaldo()) {
                    cuentaEmpresa.setSaldo(cuentaEmpresa.getSaldo() - cantidad - cuentaEmpresa.getComisionFijaDescubierto());
                    System.out.println("""
                                   La cuenta ha quedado al descubierto.
                                   Esta operación tiene una comisión fija por descubierto de:\s"""
                            + cuentaEmpresa.getComisionFijaDescubierto() + '€'
                            + "\nEl nuevo saldo de la cuenta es: " + String.format("%.2f", cuentaEmpresa.getSaldo()) + '€');
                    return true;

                }
                // Sólo puede haber descubiertos en una cuenta de empresa 
            }
            if ((cuenta.getSaldo() - cantidad) < 0) {
                System.out.println("No hay saldo suficiente en la cuenta");
                return false;
                // Si la cuenta no es de Empresa y hay saldo suficiente se retira esa cantidad
            } else {
                cuenta.setSaldo(cuenta.getSaldo() - cantidad);
                System.out.println("Se ha retirado la cantidad de: " + String.format("%.2f", cantidad) + '€'
                        + "\nEl nuevo saldo de la cuenta es: " + String.format("%.2f", cuenta.getSaldo()) + '€');
                return true;
            }

        }
        return false;
    }

    /**
     * Devuelve el saldo de la cuenta bancaria específica a partir de su IBAN.
     *
     * @param iban el IBAN de la cuenta bancaria de la que se quiere conocer su
     *             saldo
     *
     * @return el saldo de la cuenta especificada si existe, -1 si no existe
     */
    public double obtenerSaldo(String iban) {
        CuentaBancaria cuenta = this.buscadorCuenta(iban);
        if (esCuentaExistente(iban)) {
            return cuenta.getSaldo();
        }
        return -1;
    }

    /**
     * Busca una cuenta bancaria específica en el array de cuentas bancarias del
     * banco a partir de su IBAN.
     *
     * @param iban el IBAN de la cuenta bancaria a buscar.
     *
     * @return la cuenta bancaria encontrada, o null si no se encontró ninguna
     *         cuenta con el IBAN especificado.
     */
    private CuentaBancaria buscadorCuenta(String iban) {
        for (int i = 0; i < this.contadorCuentas; i++) {
            if (this.CUENTA[i].getIban().equals(iban)) {
                return this.CUENTA[i];
            }
        }
        return null;
    }

    /**
     * Comprueba si existe una cuenta bancaria a partir de su IBAN.
     *
     * @param iban el IBAN de la cuenta bancaria a comprobar
     *
     * @return true si la cuenta bancaria existe, false en caso contrario
     */
    public boolean esCuentaExistente(String iban) {
        CuentaBancaria cuenta = this.buscadorCuenta(iban);
        return cuenta != null;
    }
}
