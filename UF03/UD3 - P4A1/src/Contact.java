public class Contact {


    //Variable
    private String name;
    private String phoneNumber;

    //Constructor
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    // toString
    @Override
    public String toString() {
        return "Name: " + name +
                "\nPhone Number: " + phoneNumber;
    }
}