import java.time.LocalDate;

public class Parrot extends Bird {

    private String origin;
    private boolean speaks;

    public Parrot(String name, double age, String status, LocalDate birthday, String beak, boolean flight, String origin, boolean speaks) {
        super(name, age, status, birthday, beak, flight);
        this.origin = origin;
        this.speaks = speaks;
    }

    public boolean canSpeak() {
        return speaks;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "Parrot:\n" +
                super.toString() +
                "\nOrigin: " + this.origin +
                "\nCan Speak: " + this.speaks;
    }

    @Override
    public String speak() {
        return "Squark!";
    }
}
