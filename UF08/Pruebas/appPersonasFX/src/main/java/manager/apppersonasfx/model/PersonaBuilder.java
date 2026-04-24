package manager.apppersonasfx.model;

public class PersonaBuilder {

    private String dni;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public PersonaBuilder dni(String dni) {
        this.dni = dni;
        return this;
    }

    public PersonaBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonaBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonaBuilder age(int age) {
        this.age = age;
        return this;
    }

    public PersonaBuilder email(String email) {
        this.email = email;
        return this;
    }

    public PersonaBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public Persona build() {
        return new Persona(dni, name, surname, age, email, phone);
    }

    public PersonaBuilder() {}
}
