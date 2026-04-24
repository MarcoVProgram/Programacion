package manager.apppersonasfx.model;

public class Persona {

    private String dni;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;

    public Persona(String dni, String name, String surname, int age, String email, String phone) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public Persona(PersonaBuilder p) {
        this.dni = p.getDni();
        this.name = p.getName();
        this.surname = p.getSurname();
        this.age = p.getAge();
        this.email = p.getEmail();
        this.phone = p.getPhone();
    }

    public Persona() {}

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

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Persona{");
        sb.append("dni='").append(dni).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", age=").append(age);
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static PersonaBuilder builder() {
        return new PersonaBuilder();
    }
}
