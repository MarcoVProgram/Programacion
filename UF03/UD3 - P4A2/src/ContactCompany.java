public class ContactCompany extends Contact {

    //Variables
    String webpage;

    //Constructor
    public ContactCompany(String name, String phoneNumber, String webpage) {
        super(name, phoneNumber);
        this.webpage = webpage;
    }

    //Getter
    public String getWebpage() {
        return this.webpage;
    }

    //ToString
    @Override
    public String toString() {
        return super.toString()
                + "\nWeb page: " + this.webpage;
    }
}
