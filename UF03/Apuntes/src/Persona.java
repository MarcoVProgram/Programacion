public class Persona {


    //Los atrtibutos se ponen todos en private
    private String nombre;
    private int edad;
    private String apellido;
    private String correo;
    private String DNI;

    public Persona(String nombre, String apellido, int edad, String correo, String DNI) {
        this.nombre = nombre;
        this.edad = edad;
        this.apellido = apellido;
        this.correo = correo;
        this.DNI = DNI;
    }

    public Persona(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }
    public String getDNI() {
        return DNI;
    }
    public int getEdad() {
        return edad;
    }
    public String getApellido() {
        return apellido;
    }
    public String getCorreo() {
        return correo;
    }

    public void cumpleEdad() {
        this.edad++;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String showAll() {
        String infoPersona = "";
        infoPersona = "Nombre: " + this.nombre;
        return infoPersona;
    }
}
