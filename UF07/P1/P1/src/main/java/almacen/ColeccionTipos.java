package almacen;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ColeccionTipos {

    //Variables estaticas
    private Map<Integer, Tipo> listaTipos = new LinkedHashMap<>();

    //Funciones estaticas
    public boolean add(Tipo t) {
        boolean respuesta = false;

        Tipo placeholder = listaTipos.putIfAbsent(t.getIdTipo(), t);

        if (placeholder == null) {
            respuesta = true;
        }

        return respuesta;
    }

    public List<String> getStringListaTipos() {
        List<String> tipos = new LinkedList<>();

        for  (Integer key : listaTipos.keySet()) {
            tipos.add("< ID Tipo: " + key + "\t|\tNombre Tipo: " + listaTipos.get(key) + " >");
        }

        return tipos;
    }

    public List<Tipo> getListaTipos() {
        List<Tipo> tipos = new LinkedList<>();

        tipos.addAll(listaTipos.values());

        return tipos;
    }

    public Tipo searchTipo(int idTipo) {

        return listaTipos.get(idTipo);
    }

    public boolean tipoExists(String nombreTipo) {

        for (Tipo tipo : listaTipos.values()) {
            if (tipo.getTipoNombre().equalsIgnoreCase(nombreTipo)) {
                return true;
            }
        }

        return false;
    }
}
