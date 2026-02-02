import java.util.Collection;
import java.util.HashMap;

public class Banco {

    //Variables Privadas
    private String nombre;
    private String codigoPostal;
    private String ID;
    private static int codigo = 0;

    //Coleccion
    private HashMap<String,CuentaBancaria> coleccionCuentasBancarias;

    //Constructor
    public Banco(String nombre, String codigoPostal) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.coleccionCuentasBancarias = new HashMap<>();

        char letra = (char) Math.floor(Math.random()*26+65);

        //Generacion automatica del codigo, teniendo informacion verificadora con el codigo postal de que es real.
        codigo++;
        this.ID = letra + codigo + codigoPostal;
    }

    //Getters y Setters
    public String getNombre() {
        return this.nombre;
    }
    public String getCodigoPostal() {
        return this.codigoPostal;
    }
    public String getID() {
        return this.ID;
    }
    public int getNumCuentasBancarias() {
        return this.coleccionCuentasBancarias.size();
    }
    public String[] getColeccionIBAN() {
        return this.coleccionCuentasBancarias.keySet().toArray(new String[this.coleccionCuentasBancarias.size()]);
    }

    public boolean crearCuentaBancaria(CuentaBancaria nuevaCuenta) throws CuentaException {
        boolean exitoso = false;

        //Pilla de Errores
        if (nuevaCuenta == null) {
            throw new CuentaException("La cuenta no puede ser null");
        }
        if (nuevaCuenta.getIBAN() == null) {
            throw new CuentaException("El IBAN de una Cuenta no puede ser null");
        }

        //Construccion del Objeto
        if (!coleccionCuentasBancarias.containsKey(nuevaCuenta.getIBAN())) {
            coleccionCuentasBancarias.put(nuevaCuenta.getIBAN(), nuevaCuenta);
            exitoso = true;
        }

        return exitoso;
    }

    public String mostrarTodasLasCuentas() {
        String todasCuentas = "No hay cuentas registradas.";
        Collection<CuentaBancaria> cuentas;
        if (!this.coleccionCuentasBancarias.isEmpty()) {
            todasCuentas = "";
            cuentas = this.coleccionCuentasBancarias.values();
            for (CuentaBancaria cuenta : cuentas) {
                todasCuentas += cuenta.toString() + "\n";
            }
        }
        return todasCuentas;
    }

    public String mostrarTodosLosHistoriales() {
        String todosLosHistoriales = "No tienes historial de transacciones";
        Collection<String> coleccionIBAN;
        if (!this.coleccionCuentasBancarias.isEmpty()) {
            todosLosHistoriales = "";
            coleccionIBAN = this.coleccionCuentasBancarias.keySet();

            for (String IBAN : coleccionIBAN) {
                todosLosHistoriales += "IBAN: " + IBAN + "\n";
                todosLosHistoriales += this.coleccionCuentasBancarias.get(IBAN).mostrarHistorial();
            }
        }
        return todosLosHistoriales;
    }

    public CuentaBancaria obtenerCuentaBancaria(String IBAN) {
        return this.coleccionCuentasBancarias.get(IBAN);
    }

    public String buscarTodasTransacciones(int ID) {
        String resultado = "No se ha encontrado esa transaccion";
        Collection<CuentaBancaria> cuentas;
        if (!this.coleccionCuentasBancarias.isEmpty()) {
            cuentas = this.coleccionCuentasBancarias.values();
            for (CuentaBancaria cuenta : cuentas) {
                resultado = cuenta.buscarTransaccionesPorID(ID);
            }
        }
        return resultado;
    }

    //Cliente Existe
    public Cliente clienteExiste(String DNI) {
        Cliente resultado = null;
        if (!this.coleccionCuentasBancarias.isEmpty()) {
            for (CuentaBancaria cuenta : this.coleccionCuentasBancarias.values()) {
                if (cuenta.getTitular().getDNI().equals(DNI)) {
                    resultado = cuenta.getTitular();
                    break;
                }
            }
        }
        return resultado;
    }

    //ToString
    @Override
    public String toString() {
        String infoBanco;
        infoBanco = String.format("Nombre: %s\nCodigo Postal: %S\nID: %S", this.nombre, this.codigoPostal, this.ID);
        return infoBanco;
    }
}
