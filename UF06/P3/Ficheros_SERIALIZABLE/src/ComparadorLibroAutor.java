import java.util.Comparator;

//Class
public class ComparadorLibroAutor implements Comparator<Libro> {

    @Override
    public int compare(Libro o1, Libro o2) {
        return o1.getAutor().compareTo(o2.getAutor());
    }
}
