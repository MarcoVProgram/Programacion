import java.time.LocalDate;

public class Cat extends Mascot {

    private String color;
    private boolean longHair;

    public Cat(String name, double age, String status, LocalDate birthday, String color, boolean longHair) {
        super(name, age, status, birthday);
        this.color = color;
        this.longHair = longHair;
    }

    public String getColor() {
        return color;
    }

    public boolean hasLongHair() {
        return longHair;
    }

    public void setLongHair(boolean longHair) {
        this.longHair = longHair;
    }

    @Override
    public String toString() {
        return "Cat:\n" +
                super.toString() +
                "\nColor: " + this.color +
                "\nHas Long Hair: " + this.longHair;
    }

    @Override
    public String speak() {
        return "Meow!";
    }
}