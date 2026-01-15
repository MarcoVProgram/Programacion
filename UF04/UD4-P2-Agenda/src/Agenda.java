import java.util.LinkedList;

public class Agenda {

    //Atributos
    private LinkedList<Contacto> contactos = new LinkedList<>();

    //Constructor
    public Agenda() {}

    //Metodos
    public boolean anadeContacto(Contacto contacto) {
        boolean respuesta = false;

        if  (contacto != null && this.contactos.indexOf(contacto) == -1) {
            respuesta = this.contactos.add(contacto);
        }

        return respuesta;
    }

    public Contacto buscaContacto(String nombre) {
        Contacto contactoOutput = null;
        for  (Contacto contacto : this.contactos) {
            if (contacto.getNombre().equals(nombre)) {
                contactoOutput = contacto;
            }
        }
        return contactoOutput;
    }

    public boolean eliminaContacto(Contacto contacto) {
        boolean respuesta = false;
        int index;
        if  (contacto != null) {
            index = this.contactos.indexOf(contacto);
            if (index != -1) {
                respuesta = this.contactos.remove(contacto);
            }
        }
        return respuesta;
    }

    public String visualizaAgenda() {
        String respuesta = "No hay contactos";
        if (!contactos.isEmpty()) {
            respuesta = "";
            for (Contacto contacto : this.contactos) {
                respuesta += contacto.toString() + "\n";
            }
        }
        return respuesta;
    }

    public int getNumeroContactos() {
        return this.contactos.size();
    }

    //Posible codigo a futuro
    /*public boolean cambiaTelefonoContacto(Contacto contacto, String telefono) {
        boolean respuesta = false;

        if (contacto != null) {
            contacto.setTelefono(telefono);
            respuesta = true;
        }
        return respuesta;
    }

    public boolean cambiaCorreoContacto(Contacto contacto, String correo) {
        boolean respuesta = false;

        if (contacto != null) {
            contacto.setCorreo(correo);
            respuesta = true;
        }
        return respuesta;
    }*/
}