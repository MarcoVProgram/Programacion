public class Banco {

    //Variables Privadas
    private String nombre;
    private String codigoPostal;
    private String ID;
    private static int codigo = 0;

    //Arrays
    private CuentaBancaria[] listaDeCuentasBancarias;
    private int numCuentasBancarias;
    private int cuentasIniciales = 10;
    private String[] coleccionIBAN;

    //Constructor
    Banco(String nombre, String codigoPostal, String ID) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.ID = ID;
        this.listaDeCuentasBancarias = new CuentaBancaria[cuentasIniciales];
        this.numCuentasBancarias = 0;

        this.coleccionIBAN = new String[cuentasIniciales];
    }
    Banco(String nombre, String codigoPostal) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.listaDeCuentasBancarias = new CuentaBancaria[cuentasIniciales];
        this.numCuentasBancarias = 0;

        char letra = (char) Math.floor(Math.random()*26+65);

        //Generacion automatica del codigo, teniendo informacion verificadora con el codigo postal de que es real.
        this.ID = letra + ++codigo + codigoPostal;
        this.coleccionIBAN = new String[cuentasIniciales];
    }

    //Getters y Setters
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCodigoPostal() {
        return this.codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public String getID() {
        return this.ID;
    }
    public int getNumCuentasBancarias() {
        return this.numCuentasBancarias;
    }
    public String[] getColeccionIBAN() {
        return this.coleccionIBAN;
    }

    public boolean crearCuentaBancaria(CuentaBancaria nuevaCuenta) {
        boolean exitoso = false;

        //Construccion del Objeto
        if (nuevaCuenta != null) {
            if (this.numCuentasBancarias < this.listaDeCuentasBancarias.length) {
                exitoso = addCuenta(nuevaCuenta);
            }
            else {
                ampliarListaDeCuentasBancarias();
                if (this.numCuentasBancarias < this.listaDeCuentasBancarias.length) {
                    exitoso = addCuenta(nuevaCuenta);
                }
            }
        }
        return exitoso;
    }

    private boolean addCuenta(CuentaBancaria nuevaCuenta) {
        this.listaDeCuentasBancarias[this.numCuentasBancarias] = nuevaCuenta;
        this.coleccionIBAN[this.numCuentasBancarias] = nuevaCuenta.getIBAN();
        this.numCuentasBancarias++;
        boolean exitoso = true;

        return exitoso;
    }

    private void ampliarListaDeCuentasBancarias() {
        CuentaBancaria[] nuevaLista = new CuentaBancaria[this.listaDeCuentasBancarias.length*2];
        String[] nuevaColeccionIBAN = new String[this.coleccionIBAN.length*2];
        for (int i = 0; i < this.listaDeCuentasBancarias.length; i++) {
            nuevaLista[i] = this.listaDeCuentasBancarias[i];
            nuevaColeccionIBAN[i] = this.coleccionIBAN[i];
        }

        this.listaDeCuentasBancarias = nuevaLista;
        this.coleccionIBAN =  nuevaColeccionIBAN;
    }

    public String mostrarTodasLasCuentas() {
        String todasCuentas = "No hay cuentas registradas.";
        if (this.numCuentasBancarias > 0) {
            todasCuentas = "";
            for (int i = 0; i < this.numCuentasBancarias; i++) {
                if (this.listaDeCuentasBancarias[i] != null) {
                    todasCuentas += this.listaDeCuentasBancarias[i].mostrarCuentaBancaria() + "\n\n";
                }
            }
        }
        return todasCuentas;
    }

    public String mostrarTodosLosHistoriales() {
        String todosLosHistoriales = "No tienes historial de transacciones";
        if (this.numCuentasBancarias > 0) {
            todosLosHistoriales = "";
            for (int i = 0; i < this.numCuentasBancarias; i++) {
                if (this.listaDeCuentasBancarias[i] != null) {
                    todosLosHistoriales += "IBAN: " + this.listaDeCuentasBancarias[i].getIBAN() + "\n";
                    todosLosHistoriales += this.listaDeCuentasBancarias[i].mostrarHistorial();
                }
            }
        }
        return todosLosHistoriales;
    }

    public CuentaBancaria obtenerCuentaBancaria(String IBAN) {
        CuentaBancaria resultado = null;
        if (this.listaDeCuentasBancarias.length > 0) {
            for (int i = 0; i < this.numCuentasBancarias; i++) {
                if (this.listaDeCuentasBancarias[i] != null) {
                    if (this.listaDeCuentasBancarias[i].getIBAN() == IBAN) {
                        resultado = this.listaDeCuentasBancarias[i];
                    }
                }
            }
        }
        return resultado;
    }

    public String buscarTodasTransacciones(int ID) {
        String resultado = "No se ha encontrado esa transaccion";
        if (this.numCuentasBancarias > 0) {
            for (int i = 0; i < this.numCuentasBancarias; i++) {
                if (this.listaDeCuentasBancarias[i] != null) {
                    resultado = this.listaDeCuentasBancarias[i].buscarTransaccionesPorID(ID);
                }
            }
        }
        return resultado;
    }

    public String infoBanco() {
        String infoBanco;
        infoBanco = String.format("Nombre: %s\nCodigo Postal: %S\nID: %S", this.nombre, this.codigoPostal, this.ID);
        return infoBanco;
    }
}
