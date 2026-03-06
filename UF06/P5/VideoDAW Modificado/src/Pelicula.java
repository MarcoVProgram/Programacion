import java.io.Serializable;
import java.util.Objects;

public class Pelicula extends Articulo implements Serializable {
    private static final long serialVersionUID = 1467871391905134582L;

    //Variables Privadas
    private GeneroPeli genero;

    //Constructor
    public Pelicula(String titulo, GeneroPeli genero) {
        super(titulo);
        this.genero = genero;
    }

    //Getter
    public String getGenero() {
        return genero.toString();
    }

    //ToString
    @Override
    public String toString() {
        String info;

        //String final
        info = String.format("Pelicula -> [ " + super.toString() + "\t|\tGenero: %S ]", this.getGenero());

        return info;
    }

    //Equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(this.cod, pelicula.cod);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.cod);
    }
}
