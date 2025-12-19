import java.util.regex.Pattern;

public class ContactBookProgram {

    public static void main(String[] args) {
        String menu[] = new String[5];
        menu[0] = "Add Contact";
        menu[1] = "Search Contact";
        menu[2] = "Show All Contacts";
        menu[3] = "Delete Contact";
        menu[4] = "Exit";

        String rawInput;
        int option;

        MyUtils.print(myContactBook.toString());

        do {
            MyUtils.createMenu("Use Your Contact Book",menu,"Insert an option");
            rawInput = MyUtils.requestDataWithPattern(MENU_PATTERN,"Invalid number, choose one from 1 to 5");
            option = Integer.parseInt(rawInput);

            switch (option) {
                case 1:
                    registerContact();
                    break;
                case 2:
                    searchContact();
                    break;
                case 3:
                    showAllContacts();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    MyUtils.print("Exiting...");
                    break;
                default:
                    MyUtils.print("Invalid option, try again");
                    break;
            }
        } while (option != 5);
    }

    //Variable
    private static ContactBook myContactBook = new ContactBook("Personal Contact Book");

    //Constant
    private final static Pattern MENU_PATTERN = Pattern.compile("[1-5]");//Only number 1 to 5
    private final static Pattern ONLY_TEXT = Pattern.compile("[a-z A-Z]+");//Only letters or spaces
    private final static Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[0-9]{9}");//Only 9 digits of a number

    private static void registerContact() {
        MyUtils.print("========================");
        MyUtils.print("Creating a new Contact\n");

        MyUtils.print("Please enter the name: ");
        String name = MyUtils.requestDataWithPattern(ONLY_TEXT,"Invalid name, try using only alphabetical characters");

        MyUtils.print("Please enter the phone number: ");
        String phoneNumber = MyUtils.requestDataWithPattern(PHONE_NUMBER_PATTERN,"Invalid phone number, try using 9 numbers");

        Contact newContact = new Contact(name, phoneNumber);

        if (myContactBook.addContact(newContact)) {
            MyUtils.print("Contact added successfully");
            MyUtils.print(newContact.toString());
        }
        else {
            MyUtils.print("Contact already exists or otherwise was unable to be added");
        }

        MyUtils.print("========================");
        MyUtils.pause();
    }

    private static void searchContact() {
        MyUtils.print("========================");
        MyUtils.print("Finding an existing contact by name\n");

        MyUtils.print("Please enter the name: ");
        String name = MyUtils.requestDataWithPattern(ONLY_TEXT,"Invalid name, try using only alphabetical characters");

        int index = myContactBook.searchContact(name);

        if (index > -1) {
            MyUtils.print("Contact Found");
            Contact contactSelected = myContactBook.getContact(index);

            MyUtils.print(contactSelected.toString());
        }
        else {
            MyUtils.print("Contact could not be found");
        }

        MyUtils.print("========================");
        MyUtils.pause();
    }

    private static void showAllContacts() {
        MyUtils.print("========================");
        MyUtils.print("Showing all contacts:\n");

        MyUtils.print(myContactBook.contactListToString());

        MyUtils.print("========================");
        MyUtils.pause();
    }

    private static void deleteContact() {
        MyUtils.print("========================");
        MyUtils.print("Deleting an existing contact by name\n");

        MyUtils.print("Please enter the name to delete: ");
        String name = MyUtils.requestDataWithPattern(ONLY_TEXT,"Invalid name, try again");

        int index = myContactBook.searchContact(name);

        if (index > -1) {
            MyUtils.print("Deleting Contact");
            Contact contactSelected = myContactBook.getContact(index);

            if (myContactBook.deleteContact(contactSelected)) {
                MyUtils.print("Contact deleted successfully");
            }
            else {
                MyUtils.print("Contact could not be deleted");
            }
        }
        else {
            MyUtils.print("Contact could not be found, exiting deletion");
        }

        MyUtils.print("========================");
        MyUtils.pause();
    }
}