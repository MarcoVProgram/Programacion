import java.util.Comparator;

public class ComparadorPersonaDNI implements Comparator<Persona> {

    public int compare(Persona o1, Persona o2) {
        return o1.getDNI().compareTo(o2.getDNI());
    }
}
