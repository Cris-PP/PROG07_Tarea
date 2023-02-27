package es.iesjuanbosco.logica;

/**
 * <p>
 * Clase que almacena los datos básicos de una persona.
 * <p>
 * Implementa la interfaz Imprimible
 *
 * @author Cristian Palomo Prieto
 * @version 0.1
 * @see Imprimible
 */
public class Persona implements Imprimible {

    /**
     * Nombre, apellidos y DNI de una persona
     */
    private String nombre, apellidos, dni;

    /**
     * Constructor de la clase Persona.
     *
     * @param nombre    el nombre de la persona
     * @param apellidos los apellidos de la persona
     * @param dni       el DNI de la persona
     */
    public Persona(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    /**
     * Método para obtener el nombre de la persona.
     *
     * @return el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para establecer el nombre de la persona.
     *
     * @param nombre el nuevo nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener los apellidos de la persona.
     *
     * @return los apellidos de la persona
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Método para establecer los apellidos de la persona.
     *
     * @param apellidos los nuevos apellidos de la persona
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Método para obtener el DNI de la persona.
     *
     * @return el DNI de la persona
     */
    public String getDni() {
        return dni;
    }

    /**
     * Método para establecer el DNI de la persona.
     *
     * @param dni el nuevo DNI de la persona
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Contiene la información de una persona.
     *
     * @return la información de la persona en forma de cadena de texto (nombre,
     *         apellidos y DNI)
     *
     * @see Imprimible#devolverInfoString() Imprimible.devolverInfoString() 
     */
    @Override
    public String devolverInfoString() {
        return "Nombre: " + nombre + "\nApellidos: " + apellidos + "\nDNI: " + dni;
    }

}
