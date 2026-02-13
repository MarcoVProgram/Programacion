import java.util.Comparator;

//Class
public class ComparadorLibroPublicacion implements Comparator<Libro> {

    @Override
    public int compare(Libro o1, Libro o2) {
        return o1.getRawFechaPublicacion().compareTo(o2.getRawFechaPublicacion());
    }
}
