import java.time.LocalDate;

public class Bird extends Mascot {

    private String beak;
    private boolean flight;

    public Bird(String name, double age, String status, LocalDate birthday, String beak, boolean flight) {
        super(name, age, status, birthday);
        this.beak = beak;
        this.flight = flight;
    }

    public String getBeak() {
        return beak;
    }

    public boolean hasFlight() {
        return flight;
    }

    public void setFlight(boolean flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nBeak: " + this.beak +
                "\nHas Flight: " + this.flight;
    }

}