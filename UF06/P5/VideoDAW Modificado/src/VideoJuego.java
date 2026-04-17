import java.io.Serializable;
import java.util.Objects;

/**
 * Representa un videojuego como un artículo específico del catálogo.
 *
 * Esta clase extiende la clase {@link Articulo} e implementa {@link Serializable},
 * permitiendo la persistencia de objetos VideoJuego mediante serialización.
 *
 * Un videojuego se caracteriza por tener un título (heredado de Articulo) y un
 * género específico que lo clasifica dentro de las categorías disponibles.
 *
 * @author Marco
 * @version 1.0
 * @see Articulo
 * @see GeneroVideoJuego
 * @since 1.0
 */
public class VideoJuego extends Articulo implements Serializable {

    /** Identificador de versión para la serialización. */
    private static final long serialVersionUID = 6591866930818108302L;

    /**
     * Género del videojuego que define su categoría.
     *
     * @see GeneroVideoJuego
     */
    private GeneroVideoJuego genero;

    /**
     * Construye un nuevo objeto VideoJuego con el título y género especificados.
     *
     * @param titulo el título del videojuego, pasado al constructor de la clase padre
     * @param genero el {@link GeneroVideoJuego} al que pertenece este videojuego
     * @throws NullPointerException si alguno de los parámetros es null
     */
    public VideoJuego(String titulo, GeneroVideoJuego genero) {
        super(titulo);
        this.genero = genero;
    }

    /**
     * Obtiene el género del videojuego como una cadena de texto.
     *
     * @return una {@link String} que representa el género del videojuego
     */
    public String getGenero() {
        return genero.toString();
    }

    /**
     * Retorna una representación en cadena de texto del videojuego.
     *
     * El formato incluye la información del artículo padre (título y código) junto
     * con el género del videojuego.
     *
     * @return una cadena formateada con los datos del videojuego
     */
    @Override
    public String toString() {
        String info;
        info = String.format( "VideoJuego --> [ " + super.toString() + "\t|\tGenero: %S ]", this.getGenero());
        return info;
    }

    /**
     * Compara este videojuego con otro objeto para determinar igualdad.
     *
     * Dos videojuegos se consideran iguales si pertenecen a la misma clase y tienen
     * el mismo código de artículo (heredado de {@link Articulo}).
     *
     * @param o el objeto a comparar con este videojuego
     * @return {@code true} si el objeto es un VideoJuego con el mismo código; {@code false} en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VideoJuego videojuego = (VideoJuego) o;
        return Objects.equals(this.cod, videojuego.cod);
    }

    /**
     * Retorna el código hash para este videojuego.
     *
     * El código hash se calcula basándose en el código del artículo, permitiendo
     * que los objetos VideoJuego se utilicen correctamente en estructuras basadas en hash.
     *
     * @return un valor hash basado en el código del artículo
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.cod);
    }
}
