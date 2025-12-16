import java.time.LocalDate;

public class Dog extends Mascot {

    private String race;
    private boolean flea;

    public Dog(String name, double age, String status, LocalDate birthday, String race, boolean flea) {
        super(name, age, status, birthday);
        this.race = race;
        this.flea = flea;
    }

    public String getRace() {
        return race;
    }

    public boolean hasFlea() {
        return flea;
    }

    public void setFlea(boolean flea) {
        this.flea = flea;
    }

    @Override
    public String toString() {
        return "Dog:\n" +
                super.toString() +
                "\nRace: " + this.race +
                "\nHas Flea: " + this.flea;
    }

    @Override
    public String speak() {
        return "Woof!";
    }
}
