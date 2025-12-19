import java.time.LocalDate;
import java.time.Year;
import java.util.regex.Pattern;

public class ContactBookProgram {

    public static void main(String[] args) {

        String menu[] = new String[6];
        menu[0] = "Add a Person to Contacts";
        menu[1] = "Add a Company to Contacts";
        menu[2] = "Search Contact";
        menu[3] = "Show All Contacts";
        menu[4] = "Delete Contact";
        menu[5] = "Exit";

        String rawInput;
        int option;

        MyUtils.print(myContactBook.toString());

        do {
            MyUtils.createMenu("Use Your Contact Book",menu,"Insert an option");
            rawInput = MyUtils.requestDataWithPattern(MENU_PATTERN,"Invalid number, choose one from 1 to 6");
            option = Integer.parseInt(rawInput);

            switch (option) {
                case 1:
                    registerPerson();
                    break;
                case 2:
                    registerCompany();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    showAllContacts();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    MyUtils.print("Exiting...");
                    break;
                default:
                    MyUtils.print("Invalid option, try again");
                    break;
            }
        } while (option != 6);
    }

    //Variable
    private static ContactBook myContactBook = new ContactBook("Personal Contact Book");

    //Constant
    private final static Pattern MENU_PATTERN = Pattern.compile("[1-6]");//Only number 1 to 6
    private final static Pattern ONLY_TEXT = Pattern.compile("[a-z A-Z]+");//Only letters or spaces
    private final static Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[0-9]{9}");//Only 9 digits of a number
    private final static Pattern WEBPAGE_PATTERN = Pattern.compile("[a-zA-Z1-9]+[.][a-z]+");//Any alphanumerics, a dot, and a domain

    private static String[] requestRequiredData() {
        String[] requiredData;
        requiredData = new String[2];

        MyUtils.print("Please enter the name: ");
        String name = MyUtils.requestDataWithPattern(ONLY_TEXT,"Invalid name, try using only alphabetical characters");

        MyUtils.print("Please enter the phone number: ");
        String phoneNumber = MyUtils.requestDataWithPattern(PHONE_NUMBER_PATTERN,"Invalid phone number, try using 9 numbers");
        requiredData[0] = name;
        requiredData[1] = phoneNumber;

        return requiredData;
    }

    private static void registerPerson() {
        MyUtils.print("========================");
        MyUtils.print("Registering a new Person to your contacts:\n");

        String[] nameAndNumber = requestRequiredData();

        MyUtils.print("Now, choose the birthday:");
        LocalDate birthday = MyUtils.requestDate(1900, Year.now().getValue());

        ContactPerson person = new ContactPerson(nameAndNumber[0],nameAndNumber[1],birthday);

        if (myContactBook.addContact(person)) {
            MyUtils.print("Person has been successfully registered");
            MyUtils.print(person.toString());
        }
        else {
            MyUtils.print("Person could not be registered, check it doesn't already exist");
        }

        MyUtils.print("========================");
        MyUtils.pause();
    }

    private static void registerCompany() {
        MyUtils.print("========================");
        MyUtils.print("Registering a new Company to your contacts:\n");

        String[] nameAndNumber = requestRequiredData();

        MyUtils.print("Now, insert the web page of the company:");
        String web = MyUtils.requestDataWithPattern(WEBPAGE_PATTERN,"Invalid web page, try again");

        ContactCompany company = new ContactCompany(nameAndNumber[0],nameAndNumber[1],web);

        if (myContactBook.addContact(company)) {
            MyUtils.print("Company has been successfully registered");
            MyUtils.print(company.toString());
        }
        else {
            MyUtils.print("Company could not be registered, check it doesn't already exist");
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
        MyUtils.print("Showing all contacts:");

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