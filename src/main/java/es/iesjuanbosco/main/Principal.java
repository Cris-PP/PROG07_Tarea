package es.iesjuanbosco.main;

import es.iesjuanbosco.logica.*;
import java.util.*;
import static es.iesjuanbosco.util.Utilidades.*;

/**
 * Clase que contiene el método main de un programa básico de gestión de cuentas
 * bancarias. Permite crear nuevas cuentas, listar las cuentas disponibles,
 * obtener los datos de una cuenta concreta, realizar ingresos y retiradas de
 * dinero y consultar el saldo actual de una cuenta.
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 */
public class Principal {

    /**
     * Scanner para la entrada de datos por teclado.
     *
     * @hidden
     */
    private static final Scanner SC = new Scanner(System.in).useLocale(Locale.forLanguageTag("es-ES"));

    /**
     * Instancia de Banco que gestiona y almacena las cuentas
     *
     * @hidden
     */
    private static final Banco BANCO = new Banco();

    /**
     * Método que solicita al usuario el IBAN y verifica si es correcto.
     *
     * @return String con el IBAN.
     */
    private static String pedirIBAN() {
        String iban = "";
        /*
         * Se utiliza el método esCorrectoIBAN para controlar el bucle, se
         * repite hasta que el iban introducido no tiene el formato correcto
         * (ES+20DIGITOS consecutivos según el enunciado)
         */
        while (!esCorrectoIBAN(iban)) {
            System.out.print("Introduce el IBAN (ES+20 DIGITOS) o \"X\" para cancelar: ");
            iban = SC.nextLine().toUpperCase().trim();
            /*
             * Si se desea cancelar la operación se puede escribir x o X en este
             * punto y se saldrá del bucle
             */
            if (iban.equals("X")) {

                break;

            } else if (!esCorrectoIBAN(iban)) {
                // Si el IBAN introducido no tiene el formato correcto se muestra un mensaje
                System.out.println("El IBAN introducido no es correcto");
            }
        }
        return iban;
    }

    /**
     * Muestra un formulario para dar de alta una nueva cuenta en el banco. Una
     * vez recopilados los datos necesarios, se llama al método 'abrirCuenta' de
     * la clase Banco para añadir la cuenta a un array.
     */
    private static void formularioNuevaCuenta() {

        // Constantes para mejorar la lectura del código en el SWITCH
        final int CUENTA_AHORRO = 1;
        final int CC_PERSONAL = 2;
        final int CC_EMPRESA = 3;
        // Flag de control de bucles
        boolean flag = true;
        /*
         * Variables para almacenar los datos introducidos por teclados
         * temporalmente y crear los objetos con ellas
         */
        String nombre, apellidos, dni, iban, listaEntidades;
        double saldoInicial = 0, tipoInteresCA = 0, comisionMantenimiento = 0, maxDescubierto = 0;
        double tipoInteresDescubierto = 0, comisionFijaDescubierto = 0;
        int tipoCuenta = 0;
        Persona titular;
        CuentaBancaria nuevaCuenta;
        // Solicitud de datos del cliente para el objeto titular de tipo Persona
        System.out.print("""
                         
                            -------------------------
                            |   DATOS DEL CLIENTE   |
                            -------------------------
                            Nombre:\s""");
        /*
         * Para el nombre y los apellidos se usa el método formatoNombreApellido
         * de la clase Utilidades para mejorar la impresión y consistencia de
         * los datos
         */
        nombre = formatoNombreApellido(SC.nextLine());

        System.out.print("Apellido/s:\s");
        apellidos = formatoNombreApellido(SC.nextLine());

        /*
         * Se utiliza el método esCorrectoDNI para controlar que el DNI tenga un
         * formato correcto (No se comprueba que la letra sea la correcta)
         */
        do {
            System.out.print("DNI:\s");
            dni = SC.nextLine().toUpperCase().trim();
            // Si no es correcto se muestra un mensaje
            if (!esCorrectoDNI(dni)) {
                System.out.println("El DNI introducido no es válido, debe estar compuesto por 7-8 números y una letra");
            }
        } while (!esCorrectoDNI(dni));

        // Instanciación del objeto titular de tipo Persona
        titular = new Persona(nombre, apellidos, dni);
        // Bucle de solicitud del tipo de cuenta 
        do {
            boolean flagNoNumerico = true;
            try {
                System.out.print("""
                                 
                                   ------------------------
                                   |    TIPO DE CUENTA    |
                                   ------------------------
                                   | (1) Cuenta AHORRO    |
                                   | (2) Cuenta PERSONAL  |
                                   | (3) Cuenta EMPRESA   |
                                   ------------------------
                                   Introduce el tipo de cuenta:\s""");
                tipoCuenta = SC.nextInt();
                SC.nextLine();
                // Si el dato introducido no es un número entero se muestra un mensaje
            } catch (InputMismatchException e) {
                System.out.println("\n¡El valor introducido no es válido!");
                SC.nextLine();
                flagNoNumerico = false;
            }
            /*
             * Si el valor introducido es un número entero pero inferior a 1 y
             * superior a 3 se muestra un mensaje
             */
            if (!(tipoCuenta >= 1 && tipoCuenta <= 3) && flagNoNumerico) {
                System.out.println("\n¡Valor fuera de rango (1-3)!");
            }
            /*
             * Mientras que la variable tipoCuenta no tenga un valor entre 1 y
             * 3, ambos inclusive, se repetirá el bucle
             */
        } while (!(tipoCuenta >= 1 && tipoCuenta <= 3));

        // Bucle de petición de saldoInicial, se utiliza una flag para el control
        while (flag) {
            try {
                System.out.print("Introduce el saldo inicial (€): ");
                saldoInicial = SC.nextDouble();
                SC.nextLine();
                // Si el valor introducido es positivo salimos del bucle
                if (saldoInicial >= 0) {
                    flag = false;
                } else { // En caso contrario se muestra un mensaje
                    System.out.println("El saldo inicial ha de ser mayor o igual a 0");
                }
                // Si el dato introducido no es un número se muestra un mensaje
            } catch (InputMismatchException e) {
                System.out.println("¡El valor introducido no es válido!");
                SC.nextLine();
            }
        }
        flag = true;
        // Petición de IBAN
        do {
            // Se llama al método pedirIBAN
            iban = pedirIBAN();
            /*
             * Si el usuario introdujo x o X (la entrada utiliza .toUpperCase)
             * salimos del bucle
             */
            if (iban.equals("X")) {
                break;
                // Si existe ese número de IBAN en las cuentas creadas se muestra un mensaje
            } else if (BANCO.esCuentaExistente(iban)) {
                System.out.println("¡Ya existe una cuenta con ese IBAN!");
            }
            // Si el IBAN introducido existe se vuelve a repetir el bucle
        } while (BANCO.esCuentaExistente(iban));
        // Si el usuario introdujo x o X al solicitar el IBAN esto no se ejecuta
        if (!iban.equals("X")) {

            switch (tipoCuenta) {
                // Si se eligió tipo de cuenta AHORRO
                case CUENTA_AHORRO -> {
                    // Se utiliza una flag para el control de bucle
                    while (flag) {
                        try {
                            System.out.print("Introduce el tipo de interés de la cuenta de ahorro (%): ");
                            tipoInteresCA = SC.nextDouble();
                            SC.nextLine();
                            // El tipo de interés no puede ser negativo (pero puede ser 0)
                            if (tipoInteresCA >= 0) {
                                flag = false;
                            } else {
                                // Si es negativo se muestra un mensaje
                                System.out.println("El tipo de interés de la cuenta de ahorro ha de ser mayor o igual a 0");
                            }
                            /*
                             * Si el dato introducido no es un número se captura
                             * la excepción, se muestra un mensaje y se consume
                             * el token del Scanner
                             */
                        } catch (InputMismatchException e) {
                            System.out.println("¡El valor introducido no es válido!");
                            SC.nextLine();
                        }
                    }
                    // Creamos el objeto de la subclase CuentaAhorro
                    nuevaCuenta = new CuentaAhorro(titular, saldoInicial, iban, tipoInteresCA);
                    /*
                     * Usamos el método abrirCuenta para añadir la nueva cuenta
                     * al array y mostramos un mensaje si ha tenido éxito o no
                     */
                    System.out.println(BANCO.abrirCuenta(nuevaCuenta) ? "¡La cuenta se ha creado con éxito!" : "¡Se ha alcanzado el número máximo de cuentas!");
                }

                // Si se eligió tipo de cuenta corriente personal
                case CC_PERSONAL -> {
                    // Se utiliza una flag para el control del bucle
                    while (flag) {
                        try {
                            System.out.print("Introduce la comisión de mantenimiento anual de la cuenta personal (€): ");
                            comisionMantenimiento = SC.nextDouble();
                            SC.nextLine();
                            // La comisión de mantenimiento no puede ser negativa
                            if (comisionMantenimiento >= 0) {
                                flag = false;
                                // Se muestra mensaje si es negativa
                            } else {
                                System.out.println("La comisión de mantenimiento ha de ser mayor o igual a 0");
                            }
                            /*
                             * Si el dato introducido no es un número se captura
                             * la excepción, se muestra un mensaje y se consume
                             * el token del Scanner
                             */
                        } catch (InputMismatchException e) {
                            System.out.println("¡El valor introducido no es válido!");
                            SC.nextLine();
                        }

                    }
                    /*
                     * Se solicitan las entidades autorizadas (atributo de clase
                     * CuentaCorriente) y se almacenan en mayúsculas para
                     * mejorar la impresión y consistencia de los datos
                     * almacenados
                     */
                    System.out.print("Introduce las entidades autorizadas para cobrar recibos de la cuenta: ");
                    listaEntidades = SC.nextLine().toUpperCase().trim();
                    // Creamos el objeto de subclase CuentaCorrientePersonal
                    nuevaCuenta = new CuentaCorrientePersonal(titular, saldoInicial, iban, listaEntidades, comisionMantenimiento);
                    /*
                     * Usamos el método abrirCuenta para añadir la nueva cuenta
                     * al array y mostramos un mensaje si ha tenido éxito o no
                     */
                    System.out.println(BANCO.abrirCuenta(nuevaCuenta) ? "¡La cuenta se ha creado con éxito!" : "¡Se ha alcanzado el número máximo de cuentas!");
                }
                // Si se eligió el tipo de cuenta corriente empresa
                case CC_EMPRESA -> {
                    // Se utiliza flag para controlar el bucle
                    while (flag) {
                        try {
                            System.out.print("Introduce el máximo descubierto permitido de la cuenta de empresa (€): ");
                            maxDescubierto = SC.nextDouble();
                            SC.nextLine();
                            // El máximo descubierto no puede ser negativo
                            if (maxDescubierto >= 0) {
                                flag = false;
                            } else { // Si es negativo se muestra un mensaje
                                System.out.println("El máximo descubierto ha de ser mayor o igual a 0");
                            }
                            /*
                             * Si el dato introducido no es un número se captura
                             * la excepción, se muestra un mensaje y se consume
                             * el token del Scanner
                             */
                        } catch (InputMismatchException e) {
                            System.out.println("¡El valor introducido no es válido!");
                            SC.nextLine();
                        }
                    }
                    // Volvemos a configurar la flag para el siguiente bucle
                    flag = true;
                    while (flag) {
                        try {
                            System.out.print("Introduce el tipo de interés por descubierto (%): ");
                            tipoInteresDescubierto = SC.nextDouble();
                            SC.nextLine();
                            // El tipo de interés no puede ser negativo
                            if (tipoInteresDescubierto >= 0) {
                                flag = false;
                            } else { // Si es negativo se muestra un mensaje
                                System.out.println("El tipo de interés ha de ser mayor o igual a 0");
                            }
                            /*
                             * Si el dato introducido no es un número se captura
                             * la excepción, se muestra un mensaje y se consume
                             * el token del Scanner
                             */
                        } catch (InputMismatchException e) {
                            System.out.println("¡El valor introducido no es válido!");
                            SC.nextLine();
                        }
                    }

                    // Volvemos a configurar la flag para el siguiente bucle
                    flag = true;
                    while (flag) {
                        try {
                            System.out.print("Introduce la comisión fija por cada descubierto (€): ");
                            comisionFijaDescubierto = SC.nextDouble();
                            SC.nextLine();
                            // La comisión fija por descubierto no puede ser negativa
                            if (comisionFijaDescubierto >= 0) {
                                flag = false;
                            } else { // Si es negativa se muestra un mensaje
                                System.out.println("La comisión ha de ser mayor o igual a 0");
                            }
                            /*
                             * Si el dato introducido no es un número se captura
                             * la excepción, se muestra un mensaje y se consume
                             * el token del Scanner
                             */
                        } catch (InputMismatchException e) {
                            System.out.println("¡El valor introducido no es válido!");
                            SC.nextLine();
                        }
                    }
                    /*
                     * Se solicitan las entidades autorizadas (atributo de clase
                     * CuentaCorriente) y se almacenan en mayúsculas para
                     * mejorar la impresión y consistencia de los datos
                     * almacenados
                     */
                    System.out.print("Introduce las entidades autorizadas para cobrar recibos de la cuenta: ");
                    listaEntidades = SC.nextLine().toUpperCase().trim();
                    // Se instancia un objeto de subclase CuentaCorrienteEmpresa
                    nuevaCuenta = new CuentaCorrienteEmpresa(titular, saldoInicial, iban, listaEntidades, maxDescubierto, tipoInteresDescubierto, comisionFijaDescubierto);
                    /*
                     * Utilizamos el método abrirCuenta para añadir la nueva
                     * cuenta al array y mostramos un mensaje si ha tenido éxito
                     * o no
                     */
                    System.out.println(BANCO.abrirCuenta(nuevaCuenta) ? "¡La cuenta se ha creado con éxito!" : "¡Se ha alcanzado el número máximo de cuentas!");
                }
            }
        } else {
            System.out.println("¡Operación cancelada!");
        }
    }

    /**
     * Método principal del programa, que inicia la aplicación y muestra un menú
     * de opciones para interactuar con el banco.
     *
     * @param args argumentos de línea de comandos (no se utilizan en este
     *             programa)
     */
    public static void main(String[] args) {

//        // DESCOMENTAR PARA CREAR OBJETOS PARA PRUEBAS
        Persona titular = new Persona("Juan", "Pérez", "12345678A");
        CuentaAhorro cuentaAhorro = new CuentaAhorro(titular, 1000.0, "ES12345678901234567890", 1.5);
        BANCO.abrirCuenta(cuentaAhorro);
        CuentaCorrientePersonal cuentaCorrientePersonal = new CuentaCorrientePersonal(titular, 2000.0, "ES12345678901234567891", "BBVA", 50.0);
        BANCO.abrirCuenta(cuentaCorrientePersonal);
        CuentaCorrienteEmpresa cuentaCorrienteEmpresa = new CuentaCorrienteEmpresa(titular, 5000.0, "ES12345678901234567892", "BANCO SANTANDER", 2000.0, 4.5, 30.0);
        BANCO.abrirCuenta(cuentaCorrienteEmpresa);
        // Variables para almacenar temporalmente datos introducidos por el usuario
        String iban;
        double cantidad = 0;
        int opcion = 0;
        // Flag de control de bucles
        boolean flag;

        // Constante de una cadena de caracteres con el menú completo
        final String MENU_COMPLETO = """
                                    --------------------------------------------------
                                    |                 MENU PRINCIPAL                 |
                                    --------------------------------------------------
                                    | (1) Abrir una nueva cuenta.                    |
                                    | (2) Ver un listado de las cuentas disponibles. |
                                    | (3) Obtener los datos de una cuenta concreta.  |
                                    | (4) Realizar un ingreso en una cuenta.         |
                                    | (5) Retirar efectivo de una cuenta.            |
                                    | (6) Consultar el saldo actual de una cuenta.   |
                                    | (7) Salir de la aplicación.                    |
                                    --------------------------------------------------
                                    Introduce la opción:\s""";

        // Constante de cadena de caracteres con un mensaje acotado
        final String MENSAJE_OPCION_CORTO = """
                                    \nEscribe 0 para volver a mostrar el menú principal.
                                    Introduce tu opción:\s""";

        // Se muestra el menú completo y después empieza el bucle principal
        System.out.print(MENU_COMPLETO);
        do {
            try {
                // Se solicita y almacena la opción
                opcion = SC.nextInt();
                SC.nextLine();
                switch (opcion) {
                    /*
                     * Abrir nueva cuenta - Se llama al método
                     * formularioNuevaCuenta() declarado en esta misma clase
                     */
                    case 1 ->
                        formularioNuevaCuenta();

                    case 2 -> { // Listado de cuentas
                        /*
                         * Obtenemos el número de cuentas creadas. También
                         * podría usarse el método getContadorCuentas de la
                         * clase Banco
                         */
                        int numCuentas = BANCO.listadoCuentas().length;
                        // Si no hay cuentas creadas no se ejecuta esto
                        if (numCuentas > 0) {
                            /*
                             * Se utiliza la condición (numCuentas > 1) con un
                             * ternario para mostrar un mensaje u otro
                             * dependiendo de si hay sólo 1 cuenta o varias,
                             * para respetar si el número de cuentas es plural o
                             * singular
                             */
                            System.out.println("""
                                           \n-------------------------
                                           |   LISTADO DE CUENTAS  |
                                           -------------------------
                                           
                                           Hay\s""" + numCuentas
                                    + ((numCuentas > 1) ? " cuentas creadas" : " cuenta creada"));
                            /*
                             * Se recorre y se muestra el array de tipo String
                             * devuelto por el método listadoCuentas(), este
                             * método a su vez utiliza el método
                             * devolverInfoString() para devolver toda la
                             * información de cada cuenta
                             */
                            for (String cuenta : BANCO.listadoCuentas()) {
                                System.out.println("\n" + cuenta);
                            }
                        } else { // Si no hay cuentas creadas se muestra un mensaje
                            System.out.println("¡Aún no hay cuentas creadas!");
                        }
                    }
                    // Datos de cuenta
                    case 3 -> {
                        System.out.println("""
                                           \n----------------------
                                           |   DATOS DE CUENTA  |
                                           ----------------------""");
                        /*
                         * Llamamos al método pedirIBAN() para solicitar al
                         * usuario el IBAN del cual se quieren los datos
                         */
                        iban = pedirIBAN();
                        /*
                         * Si el usuario canceló la operación introduciendo X
                         * (como da la opción el propio método) esto no se
                         * ejecutará
                         */
                        if (!iban.equals("X")) {
                            System.out.println(BANCO.esCuentaExistente(iban) ? "\n" + BANCO.informacionCuenta(iban)
                                    : "\n¡No existe ninguna cuenta con ese IBAN!");
                        } else { // Si el usuario canceló la operación se muestra un mensaje
                            System.out.println("¡Operación cancelada!");
                        }

                    }
                    // Ingresar efectivo en cuenta
                    case 4 -> {
                        // Si no hay cuentas creadas esto no se ejecutará
                        if (BANCO.getContadorCuentas() > 0) {
                            System.out.println("""
                                               \n-------------------------
                                               |   INGRESAR EN CUENTA  |
                                               -------------------------""");

                            do {
                                // Llamamos al método pedirIBAN()
                                iban = pedirIBAN();
                                /*
                                 * Si el usuario ha cancelado la operación se
                                 * muestra un mensaje y salimos del bucle
                                 */
                                if (iban.equals("X")) {
                                    System.out.println("¡Operación cancelada!");
                                    break;
                                    /*
                                     * Si el IBAN introducido no pertenece a
                                     * ninguna cuenta se muestra un mensaje
                                     */
                                } else if (!BANCO.esCuentaExistente(iban)) {
                                    System.out.println("¡No existe ninguna cuenta con ese IBAN!");
                                }
                                /*
                                 * Este bucle se ejecuta hasta que el IBAN
                                 * introducido pertenezca a una cuenta existente
                                 * o se cancele la operación
                                 */
                            } while (!BANCO.esCuentaExistente(iban));
                            // Si el usuario canceló la operación esto no se ejecutará
                            if (!iban.equals("X")) {
                                // Se utiliza una flag para el control del bucle
                                flag = true;
                                do {
                                    try {
                                        System.out.print("Introduce la cantidad: ");
                                        cantidad = SC.nextDouble();
                                        SC.nextLine();
                                        // Si la cantidad introducida es positiva salimos del bucle
                                        if (cantidad > 0) {
                                            flag = false;
                                        } else { // Si la cantidad es negativa o 0 se muestra un mensaje
                                            System.out.println("La cantidad a ingresar ha de ser mayor a 0");
                                        }

                                        /*
                                         * Si el dato introducido no es un
                                         * número se captura la excepción, se
                                         * muestra un mensaje y se consume el
                                         * token del Scanner
                                         */
                                    } catch (InputMismatchException e) {
                                        System.out.println("¡El valor introducido no es válido!");
                                        SC.nextLine();
                                    }
                                } while (flag);
                                BANCO.ingresoCuenta(iban, cantidad);
                            }
                        } else { // Si no hay cuentas creadas se muestra un mensaje
                            System.out.println("¡Aún no hay cuentas creadas!");
                        }
                    }

                    // Retirada de efectivo
                    case 5 -> {
                        if (BANCO.getContadorCuentas() > 0) {
                            System.out.println("""
                                               \n----------------------------
                                               |   RETIRADA DE EFECTIVO   |
                                               ----------------------------""");
                            do {
                                // Llamamos al método pedirIBAN()
                                iban = pedirIBAN();
                                /*
                                 * Si el usuario ha cancelado la operación se
                                 * muestra un mensaje y salimos del bucle
                                 */
                                if (iban.equals("X")) {
                                    System.out.println("¡Operación cancelada!");
                                    break;
                                }
                                // Si la cuenta no existe se muestra un mensaje
                                if (!BANCO.esCuentaExistente(iban)) {
                                    System.out.println("¡No existe ninguna cuenta con ese IBAN!");
                                }
                                // Cuando la cuenta introducida exista salimos del bucle
                            } while (!BANCO.esCuentaExistente(iban));

                            // Si el usuario canceló la operación esto no se ejecutará
                            if (!iban.equals("X")) {
                                // El siguiente bucle utiliza una flag para su control
                                flag = true;
                                do {
                                    try {
                                        System.out.print("Introduce la cantidad: ");
                                        cantidad = SC.nextDouble();
                                        SC.nextLine();
                                        // La cantidad a retirar ha de ser positiva
                                        if (cantidad > 0) {
                                            flag = false;
                                            // Si la cantidad no es positiva se muestra un mensaje
                                        } else {
                                            System.out.println("La cantidad a retirar ha de ser mayor a 0");
                                        }
                                        /*
                                         * Si el dato introducido no es un
                                         * número se captura la excepción, se
                                         * muestra un mensaje y se consume el
                                         * token del Scanner
                                         */
                                    } catch (InputMismatchException e) {
                                        System.out.println("¡El valor introducido no es válido!");
                                        SC.nextLine();
                                    }
                                } while (flag);
                                /*
                                 * Una vez validados el IBAN y la cantidad se
                                 * llama al método retiradaCuenta de la clase
                                 * Banco, este método ya tiene mensajes para
                                 * cada salida (si es cuenta empresa y se queda
                                 * al descubierto, si se ha retirado el
                                 * efectivo, el nuevo saldo de la cuenta...)
                                 */
                                BANCO.retiradaCuenta(iban, cantidad);
                            }
                            // Si no hay cuentas creadas se muestra un mensaje
                        } else {
                            System.out.println("¡Aún no hay cuentas creadas!");
                        }
                    }

                    // Consultar Saldo de una cuenta
                    case 6 -> {
                        // Si no hay cuentas creadas esto no se ejecutará
                        if (BANCO.getContadorCuentas() > 0) {
                            System.out.println("""
                                               \n-----------------------
                                               |   CONSULTAR SALDO   |
                                               -----------------------""");
                            do {
                                // Llamada al método pedirIBAN() para solicitar el IBAN
                                iban = pedirIBAN();
                                /*
                                 * Si el usuario cancela la operación se muestra
                                 * un mensaje y salimos del bucle
                                 */
                                if (iban.equals("X")) {
                                    System.out.println("¡Operación cancelada!");
                                    break;
                                }
                                // Si la cuenta introducida no existe se muestra un mensaje
                                if (!BANCO.esCuentaExistente(iban)) {
                                    System.out.println("¡No existe ninguna cuenta con ese IBAN!");
                                }
                                // Este bucle se ejecuta mientras la cuenta introducida no exista
                            } while (!BANCO.esCuentaExistente(iban));
                            /*
                             * Una vez comprobado que el IBAN existe y si el
                             * usuario no cancela la operación se llama al
                             * método obtenerSaldo
                             */
                            System.out.println("Saldo actual: " + String.format("%.2f€", BANCO.obtenerSaldo(iban)));
                            // Si no hay cuentas creadas se muestra un mensaje
                        } else {
                            System.out.println("¡Aún no hay cuentas creadas!");
                        }
                    }
                    // Salir
                    case 7 -> {
                        // Se muestra un mensaje (La condición para salir está en el while)
                        System.out.println("Programa finalizado.");
                    }
                    case 0 -> // Vuelve a mostrar el menú completo
                        System.out.print("\n" + MENU_COMPLETO);
                    /*
                     * Si se introduce un número que no esté comprendido entre
                     * el 0 y el 7 se muestra un mensaje
                     */
                    default -> {
                        System.out.println("La opción debe estar comprendida entre el 1 y el 7");

                    }
                }
                /*
                 * Si el dato introducido no es un número se captura la
                 * excepción, se muestra un mensaje y se consume el token del
                 * Scanner
                 */
            } catch (InputMismatchException e) {
                System.out.println("\n¡El valor introducido no es válido!");
                SC.nextLine();
                System.out.print(MENSAJE_OPCION_CORTO);
            }
            /*
             * Si la opción introducida no es 0 ni 7 (mostrar menú completo y
             * salir), se muestra el mensaje corto.
             */
            if (opcion != 7 && opcion != 0) {
                System.out.print(MENSAJE_OPCION_CORTO);
            }
            // Si el usuario introduce la opción 7 salimos del bucle y el programa finaliza
        } while (opcion != 7);

    }

    /**
     * @hidden
     */
    private Principal() {
        // Ocultar el constructor vacío (Así no aparece en la documentación ni se puede instanciar)
    }
}
