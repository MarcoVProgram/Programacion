import java.util.Comparator;

//Class
public class ComparadorLibroTitulo implements Comparator<Libro> {

    @Override
    public int compare(Libro o1, Libro o2) {
        return o1.getTitulo().compareTo(o2.getTitulo());
    }
}
