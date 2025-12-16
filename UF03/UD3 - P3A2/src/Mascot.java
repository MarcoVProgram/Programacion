import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Mascot {

    private String name;
    private double age;
    private String status;
    private LocalDate birthday;


    public Mascot(String name, double age, String status, LocalDate birthday) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.birthday = birthday;
    }

    public String getName() {
        return this.name;
    }

    public double getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getBirthday() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.birthday);
    }

    public String die() {
        this.status = "Dead";
        return "Rest in Peace, " + getName() + " at date " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
    }

    public String speak() {
        return "";
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nAge: " + this.age +
                "\nStatus: " + this.status +
                "\nBirthday: " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(this.birthday);
    }
}
