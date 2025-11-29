package Consultas;

import Controlador.Conexion;
import Modelo.Articulos;
import Vista.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConsulProducto {

    private PreparedStatement consultar = null;
    private ResultSet resultado = null;
    private String consulta;
    //Estas variables son requeridas en ExtraetDatos() y BuscarRegistros()
    String[] datos = new String[5];
    String[] columnas = {"ID", "Nombre", "Proveedor", "Precio", "En Stock"};
    DefaultTableModel TablaC = new DefaultTableModel(null, columnas);
    int uno = 1;

    public void CargrDatosProveedor(Articulos articulos) throws SQLException {
        TablaC.setRowCount(0);
        consulta = "INSERT INTO articulo (`NOMBRE`, `ID_PROVEEDOR`,`PRECIO_ARTICULO`, `STOCK`)"
                + " VALUES (?, ? , ?, ?)";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, articulos.getNOMBRE());
            consultar.setInt(2, articulos.getProveedor());
            consultar.setFloat(3, articulos.getPRECIO());
            consultar.setInt(4, articulos.getSTOCK());
            consultar.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean VerificarDatoExistente(Articulos articulos) {
        consulta = "SELECT * FROM articulo WHERE NOMBRE = ?";
        boolean YaExiste = false;

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, articulos.getNOMBRE());
            resultado = consultar.executeQuery();
            YaExiste = resultado.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return YaExiste;
    }

    public DefaultTableModel ExtraerDatos() {
        consulta = "SELECT * FROM articulo";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                for (int i = 0; i < 5; i++) {
                    datos[i] = resultado.getString(i + 1);
                }
                TablaC.addRow(datos);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return TablaC;
    }

    public void EliminarRegistro(Articulos articulos) {
        TablaC.setRowCount(0);
        consulta = "DELETE FROM proveedores WHERE ID_PROVEEDOR = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setInt(1, articulos.getID_ARTICULO());
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Se elimino correctamente");
    }

    public void ModificarRegistro(Articulos articulos) {
        TablaC.setRowCount(0);
        consulta = "UPDATE articulo SET NOMBRE = ?, ID_PROVEEDOR = ?, PRECIO_ARTICULO = ?, STOCK = ? WHERE ID_ARTICULO = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, articulos.getNOMBRE());
            consultar.setInt(2, articulos.getProveedor());
            consultar.setInt(3, (int) articulos.getPRECIO());
            consultar.setInt(4, articulos.getID_ARTICULO());
            consultar.setInt(5, articulos.getSTOCK());
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public DefaultTableModel BuscarRegistro(String nombre) {
        TablaC.setRowCount(0);
        consulta = "SELECT * FROM articulo WHERE NOMBRE LIKE ?";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, nombre + "%");
            resultado = consultar.executeQuery();

            while (resultado.next()) {
                for (int i = 0; i < 3; i++) {

                    datos[i] = resultado.getString(i + 1);
                }
                TablaC.addRow(datos);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return TablaC;
    }
    public boolean rellenar = false;

    public void RellenarComponentes(Productos productos, Articulos articulos) {
        consulta = "SELECT * FROM articulo WHERE ID_ARTICULO = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setInt(1, articulos.getID_ARTICULO());
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                productos.JNombreProducto.setText(resultado.getString(2));
                productos.JComboProveedor.setSelectedItem(resultado.getString(3));
                productos.JPrecioProducto.setText(resultado.getString(4));
                productos.jtxtCantidad.setText(resultado.getString(5));

            }
            rellenar = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
