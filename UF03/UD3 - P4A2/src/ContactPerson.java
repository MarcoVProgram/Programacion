import java.time.LocalDate;

public class ContactPerson extends Contact {

    //Variables
    LocalDate birthday;

    //Constructor
    public ContactPerson(String name, String phoneNumber, LocalDate birthday) {
        super(name, phoneNumber);
        this.birthday = birthday;
    }

    //Getters
    public LocalDate getBirthday() {
        return birthday;
    }

    //ToString
    @Override
    public String toString() {
        return super.toString()
                + "\nBirthday: " + MyUtils.formatDate("dd/MM/yyyy", birthday);
    }
}
