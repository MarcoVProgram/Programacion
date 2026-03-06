import java.io.Serializable;
import java.util.Objects;

public class VideoJuego extends Articulo implements Serializable {
    private static final long serialVersionUID = 6591866930818108302L;

    //Variables Privadas
    private GeneroVideoJuego genero;

    //Constructor
    public VideoJuego(String titulo, GeneroVideoJuego genero) {
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
        info = String.format( "VideoJuego --> [ " + super.toString() + "\tGenero: %S ]", this.getGenero());

        return info;
    }

    //Equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VideoJuego videojuego = (VideoJuego) o;
        return Objects.equals(this.cod, videojuego.cod);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.cod);
    }
}
