package almacen;

import java.util.LinkedList;
import java.util.List;

public class ColeccionTipos {

    //Variables estaticas
    private LinkedList<Tipo> listaTipos = new LinkedList<>();

    //Funciones estaticas
    public boolean add(Tipo t) {
        boolean respuesta = false;

        if (t != null) {
            listaTipos.addLast(t);
            respuesta = true;
        }

        return respuesta;
    }

    public List<String> getStringListaTipos() {
        List<String> tipos = new LinkedList<>();

        for  (Tipo t : listaTipos) {
            tipos.add(t.toString());
        }

        return tipos;
    }

    public List<Tipo> getListaTipos() {
        List<Tipo> tipos = new LinkedList<>();

        tipos.addAll(listaTipos);

        return tipos;
    }

    public Tipo searchTipo(int idTipo) {

        return listaTipos.get(idTipo);
    }

    public boolean tipoExists(String nombreTipo) {

        for (Tipo tipo : listaTipos) {
            if (tipo.getTipoNombre().equalsIgnoreCase(nombreTipo)) {
                return true;
            }
        }

        return false;
    }
}
