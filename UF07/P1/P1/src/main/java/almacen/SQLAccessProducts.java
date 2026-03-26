package almacen;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SQLAccessProducts {

    public static List<Tipo> obtenerTipos() {
        List<Tipo> tipos = new LinkedList<>();

        String sql = "SELECT * FROM tipos ORDER BY id ASC;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id =  resultSet.getInt(1);
                String nombre = resultSet.getString(2);

                tipos.add(new Tipo(id,nombre));
            }

        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return tipos;
    }

    public static List<Producto> obtenerProductos() {
        List<Producto> productos = new LinkedList<>();

        String sql = "SELECT * FROM productos p;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id =  resultSet.getInt(1);
                String referencia = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String descripcion = resultSet.getString(4);
                int idTipo = resultSet.getInt(5);
                int cantidad = resultSet.getInt(6);
                double precio = resultSet.getDouble(7);
                int descuento = resultSet.getInt(8);
                int IVA = resultSet.getInt(9);
                boolean activo = resultSet.getBoolean(10);

                productos.add(new Producto(id, referencia, nombre, descripcion, idTipo, cantidad, precio, descuento, IVA, activo));

            }


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return productos;
    }

    public static Producto buscarProductoPorRef(String ref) {
        Producto resultado = null;

        String sql = "SELECT * FROM productos p WHERE p.referencia = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, ref);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id =  resultSet.getInt(1);
                String nombre = resultSet.getString(3);
                String descripcion = resultSet.getString(4);
                int tipo = resultSet.getInt(5);
                int cantidad = resultSet.getInt(6);
                double precio = resultSet.getDouble(7);
                int descuento = resultSet.getInt(8);
                int IVA = resultSet.getInt(9);
                boolean activo = resultSet.getBoolean(10);

                resultado = new Producto(id, ref, nombre, descripcion, tipo, cantidad, precio, descuento, IVA, activo);

            }


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return resultado;
    }

    public static boolean referenciaExiste(String ref) {
        boolean resultado = true;

        String sql = "SELECT * FROM productos p WHERE p.referencia = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, ref);

            ResultSet resultSet = statement.executeQuery();

            resultado = resultSet.next();


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return resultado;
    }

    public static List<Producto> buscarProductoPorTipo(int idTipo) {
        List<Producto> resultado = new LinkedList<>();

        String sql = "SELECT * FROM productos p WHERE p.idTipo = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, idTipo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id =  resultSet.getInt(1);
                String referencia = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String descripcion = resultSet.getString(4);
                int cantidad = resultSet.getInt(6);
                double precio = resultSet.getDouble(7);
                int descuento = resultSet.getInt(8);
                int IVA = resultSet.getInt(9);
                boolean activo = resultSet.getBoolean(10);

                resultado.add(new Producto(id, referencia, nombre, descripcion, idTipo, cantidad, precio, descuento, IVA, activo));

            }


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return resultado;
    }

    public static List<Producto> buscarProductoPorCantidad(int cantidad) {
        List<Producto> resultado = new LinkedList<>();

        String sql = "SELECT * FROM productos p WHERE p.cantidad = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, cantidad);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id =  resultSet.getInt(1);
                String referencia = resultSet.getString(2);
                String nombre = resultSet.getString(3);
                String descripcion = resultSet.getString(4);
                int idTipo = resultSet.getInt(5);
                double precio = resultSet.getDouble(7);
                int descuento = resultSet.getInt(8);
                int IVA = resultSet.getInt(9);
                boolean activo = resultSet.getBoolean(10);

                resultado.add(new Producto(id, referencia, nombre, descripcion, idTipo, cantidad, precio, descuento, IVA, activo));

            }


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return resultado;
    }

    public static int borrarProductoPorRef(String ref) {
        int elemento = -1;

        String sql = "DELETE FROM productos p WHERE p.referencia = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statementProd = connection.prepareStatement(sql);) {


            statementProd.setString(1, ref);

            elemento = statementProd.executeUpdate();
            //elemento += statementProd2.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return elemento;
    }

    public static int insertarProducto(Producto producto) {
        int id = -1;

        String insert = "INSERT INTO productos (referencia, nombre, descripcion, idTipo, cantidad, precio, descuento, IVA, aplicarDto)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        String consult = "SELECT p.id FROM productos p WHERE p.referencia = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statementInsert = connection.prepareStatement(insert);
            PreparedStatement statementConsult = connection.prepareStatement(consult)) {

            statementInsert.setString(1, producto.getReferencia());
            statementInsert.setString(2, producto.getNombre());
            statementInsert.setNString(3, producto.getDescripcion());
            statementInsert.setInt(4, producto.getTipo());
            statementInsert.setInt(5, producto.getCantidad());
            statementInsert.setDouble(6, producto.getPrecio());
            statementInsert.setInt(7, producto.getDescuento());
            statementInsert.setInt(8, producto.getIVA());
            statementInsert.setBoolean(9, producto.isAplicandoDto());

            statementInsert.executeUpdate();

            statementConsult.setString(1, producto.getReferencia());
            ResultSet myObj = statementConsult.executeQuery();
            while (myObj.next()) {
                id = myObj.getInt(1);
            }


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }



        return id;
    }

    public static int insertarTipoProducto (Tipo tipo) {
        int id = -1;

        String insert = "INSERT INTO tipos (nombre) VALUES (?);";
        String consult = "SELECT t.id FROM tipos t WHERE t.nombre = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statementInsert = connection.prepareStatement(insert);
            PreparedStatement statementConsult = connection.prepareStatement(consult)) {

            statementInsert.setString(1, tipo.getTipoNombre());

            statementInsert.executeUpdate();

            statementConsult.setString(1, tipo.getTipoNombre());
            ResultSet myObj = statementConsult.executeQuery();
            while (myObj.next()) {
                id = myObj.getInt(1);
            }


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return id;
    }

    public static Tipo buscarTipoPorId(int id) {
        Tipo resultado = null;

        String sql = "SELECT * FROM tipos t WHERE t.id = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String nombre = resultSet.getString(2);

                resultado = new Tipo(id, nombre);

            }


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }

        return resultado;
    }

    public static int actualizarProducto(Producto producto) {
        int element = -1;

        String update = "UPDATE productos p SET p.cantidad = ?, p.precio = ?, p.descuento = ?, p.IVA = ?, p.aplicarDto = ? WHERE p.id = ?;";

        try (Connection connection = SQLDataManager.getConnection();
            PreparedStatement statementUpdate = connection.prepareStatement(update);) {

            statementUpdate.setInt(1, producto.getCantidad());
            statementUpdate.setDouble(2, producto.getPrecio());
            statementUpdate.setInt(3, producto.getDescuento());
            statementUpdate.setInt(4, producto.getIVA());
            statementUpdate.setBoolean(5, producto.isAplicandoDto());
            statementUpdate.setInt(6, producto.getId());

            element = statementUpdate.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error al conectar al banco de datos: " + e.getMessage());
        }



        return element;
    }
}
