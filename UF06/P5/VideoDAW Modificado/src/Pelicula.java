import java.io.Serializable;
import java.util.Objects;

/**
 * Representa una película disponible para alquiler en el videoclub.
 * Extiende {@link Articulo} e implementa {@link Serializable} para permitir su persistencia.
 *
 * <p>Añade a los atributos heredados de {@link Articulo} el género cinematográfico
 * de la película, definido mediante el enumerado {@link GeneroPeli}.</p>
 *
 * @author Marco
 * @version 1.0
 * @see Articulo
 * @see GeneroPeli
 */
public class Pelicula extends Articulo implements Serializable {
    private static final long serialVersionUID = 1467871391905134582L;

    /**
     * Género cinematográfico de la película.
     */
    private GeneroPeli genero;

    /**
     * Construye una nueva {@code Pelicula} con el título y género proporcionados.
     *
     * <p>El código identificador se genera automáticamente a través del constructor
     * de {@link Articulo}.</p>
     *
     * @param titulo  Título de la película.
     * @param genero  Género cinematográfico de la película, definido en {@link GeneroPeli}.
     */
    public Pelicula(String titulo, GeneroPeli genero) {
        super(titulo);
        this.genero = genero;
    }

    /**
     * Devuelve el género cinematográfico de la película como cadena de texto.
     *
     * @return El nombre del género obtenido a partir de {@link GeneroPeli#toString()}.
     */
    public String getGenero() {
        return genero.toString();
    }

    /**
     * Devuelve una representación en cadena de la película con toda su información,
     * incluyendo los datos heredados de {@link Articulo} y el género cinematográfico.
     *
     * @return Un {@link String} con la información completa de la película.
     */
    @Override
    public String toString() {
        String info;

        info = String.format("Pelicula -> [ " + super.toString() + "\t|\tGenero: %S ]", this.getGenero());

        return info;
    }

    /**
     * Compara esta película con otro objeto para determinar si son iguales.
     *
     * <p>Dos películas se consideran iguales si comparten el mismo código identificador.</p>
     *
     * @param o  El objeto con el que comparar.
     * @return {@code true} si los objetos son iguales; {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(this.cod, pelicula.cod);
    }

    /**
     * Devuelve el código hash de la película, calculado a partir de su código identificador.
     *
     * @return El código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.cod);
    }
}