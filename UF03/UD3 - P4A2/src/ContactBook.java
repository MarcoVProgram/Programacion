public class ContactBook {

    //Variables
    private String name;
    private Contact[] myContacts;
    private int numContacts;

    //Constructor
    public ContactBook(String name) {
        this.name = name;
        myContacts = new Contact[10];
        numContacts = 0;
    }

    // Getters & Setters
    public String getName() {
        return this.name;
    }

    public Contact getContact(int index) {
        return this.myContacts[index];
    }

    // ToString
    @Override
    public String toString() {
        return this.name
                + "\nNumber of Contacts: " + this.numContacts;
    }

    //Methods
    public boolean addContact(Contact c) {
        boolean result = false;
        boolean valid;

        //If exists
        if (c != null) {

            //Checks length
            if (this.numContacts >= this.myContacts.length) {
                Contact[] aux = new Contact[this.myContacts.length + 10];
                System.arraycopy(this.myContacts, 0, aux, 0, this.numContacts);
                this.myContacts = aux;
            }

            //Checks if exists
            valid = !contactExists(c.getName());

            //Adds if it can be added
            if (valid) {
                this.myContacts[numContacts] = c;
                numContacts++;
                result = true;
            }
        }

        return result;
    }

    public boolean contactExists(String name) {
        boolean result = false;

        for (int i = 0; i < this.numContacts; i++) {
            if (this.myContacts[i].getName().equalsIgnoreCase(name)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public int searchContact(String name) {
        int index = -1;

        for (int i = 0; i < this.numContacts; i++) {
            if (this.myContacts[i].getName().equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public boolean deleteContact(Contact c) {
        boolean result = false;
        int index;

        //Checks if exists
        if (c != null) {

            //Searches for its index
            index = this.searchContact(c.getName());

            //If the index is found, deletes that index
            if (index != -1) {
                this.myContacts[index] = this.myContacts[this.numContacts -1];
                this.myContacts[this.numContacts -1] = null;
                this.numContacts--;
                result = true;
            }
        }

        return result;
    }

    public String contactListToString() {
        String result = "No contacts exist";

        if  (this.numContacts > 0) {
            result = "";
            for  (int i = 0; i < this.numContacts; i++) {
                result += "\n" + this.myContacts[i].toString() + "\n";
            }
        }
        return result;
    }
}