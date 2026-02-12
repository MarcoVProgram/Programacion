import java.util.Comparator;

//Class
public class CompadorLibro implements Comparator<Libro> {

    public int compare(Libro o1, Libro o2) {
        return o1.getISBN().compareTo(o2.getISBN());
    }
}
