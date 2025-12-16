import java.time.LocalDate;

public class Canary extends Bird {

    private String color;
    private boolean chants;

    public Canary(String name, double age, String status, LocalDate birthday, String beak, boolean flight, String origin, boolean speaks) {
        super(name, age, status, birthday, beak, flight);
        this.color = color;
        this.chants = chants;
    }

    public boolean canChant() {
        return chants;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Parrot:\n" +
                super.toString() +
                "\nColor: " + this.color +
                "\nCan Chant: " + this.chants;
    }

    @Override
    public String speak() {
        return "Pew!";
    }
}