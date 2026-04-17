import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase abstracta que representa a una persona dentro del sistema.
 * Implementa {@link Serializable} para permitir su persistencia.
 *
 * <p>Define los atributos y comportamientos comunes a todos los tipos de persona,
 * como el DNI, nombre, dirección y fecha de nacimiento. Las subclases deben
 * ampliar esta clase para representar roles concretos dentro del sistema.</p>
 *
 * @author Marco
 * @version 1.0
 */
public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 8581136391764624619L;

    /**
     * Documento Nacional de Identidad de la persona. Actúa como identificador único.
     */
    protected String DNI;

    /**
     * Nombre completo de la persona.
     */
    protected String nombre;

    /**
     * Dirección postal de la persona.
     */
    protected String direccion;

    /**
     * Fecha de nacimiento de la persona.
     */
    protected LocalDate fechaNacimiento;

    /**
     * Construye una nueva {@code Persona} con los datos personales proporcionados.
     *
     * @param DNI              DNI de la persona.
     * @param nombre           Nombre completo de la persona.
     * @param direccion        Dirección postal de la persona.
     * @param fechaNacimiento  Fecha de nacimiento de la persona.
     */
    public Persona(String DNI, String nombre, String direccion, LocalDate fechaNacimiento) {

        this.DNI = DNI;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Devuelve el DNI de la persona.
     *
     * @return El DNI como {@link String}.
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Devuelve el nombre completo de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la dirección postal de la persona.
     *
     * @return La dirección de la persona.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Devuelve la fecha de nacimiento de la persona.
     *
     * @return La {@link LocalDate} de nacimiento.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece el nombre completo de la persona.
     *
     * @param nombre  El nuevo nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la dirección postal de la persona.
     *
     * @param direccion  La nueva dirección a asignar.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve una representación en cadena de la persona con su información personal,
     * incluyendo DNI, nombre, dirección y fecha de nacimiento.
     *
     * @return Un {@link String} con la información de la persona.
     */
    @Override
    public String toString() {
        String info;

        String fomattedNacimiento = MyUtils.formatDate("dd/MM/yyyy", this.fechaNacimiento);

        info = String.format("DNI: %S\t|\tNombre: %s\t|\tDireccion: %s\t|\tFecha de Nacimiento: %s",
                this.DNI, this.nombre, this.direccion, fomattedNacimiento);

        return info;
    }

    /**
     * Compara esta persona con otro objeto para determinar si son iguales.
     *
     * <p>Dos personas se consideran iguales si son instancias de {@link Persona}
     * y comparten el mismo DNI.</p>
     *
     * @param o  El objeto con el que comparar.
     * @return {@code true} si los objetos son iguales; {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(DNI, persona.DNI);
    }

    /**
     * Devuelve el código hash de la persona, calculado a partir de su DNI.
     *
     * @return El código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(DNI);
    }
}