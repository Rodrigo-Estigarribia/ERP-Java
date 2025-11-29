package Consultas;

import Controlador.Conexion;
import Modelo.Proveedores;
import Vista.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConsulProveedor {

    private PreparedStatement consultar = null;
    private ResultSet resultado = null;
    private String consulta;
    //Estas variables son requeridas en ExtraetDatos() y BuscarRegistros()
    String[] datos = new String[6];
    String[] columnas = {"ID", "Nombres", "RUC", "Direccion", "Telefono", "Email"};
    DefaultTableModel TablaC = new DefaultTableModel(null, columnas);
    int uno = 1;

    public void CargrDatosProveedor(Proveedores proveedores) {
        TablaC.setRowCount(0);
        consulta = "INSERT INTO proveedores (`NOMBRE`, `RUC`, `DIRECCION`, `TELEFONO`, `EMAIL`)"
                + " VALUES (?, ? , ?, ?, ? )";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, proveedores.getNombre());
            consultar.setString(2, proveedores.getRuc());
            consultar.setString(3, proveedores.getDireccion());
            consultar.setString(4, proveedores.getTelefono());
            consultar.setString(5, proveedores.getEmail());

            consultar.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean VerificarDatoExistente(Proveedores preoveedores) {
        consulta = "SELECT * FROM proveedores WHERE RUC = ?";
        boolean YaExiste = false;

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, preoveedores.getRuc());
            resultado = consultar.executeQuery();
            YaExiste = resultado.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return YaExiste;
    }

    public DefaultTableModel ExtraerDatos() {
        consulta = "SELECT * FROM proveedores";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                for (int i = 0; i < 6; i++) {
                    if (i == 0) {
                        uno = 2;
                    }
                    datos[i] = resultado.getString(i + 1);
                }
                TablaC.addRow(datos);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return TablaC;
    }

    public void EliminarRegistro(Proveedores proveedores) {
        TablaC.setRowCount(0);
        consulta = "DELETE FROM proveedores WHERE ID_PROVEEDOR = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setInt(1, proveedores.getIdProveedores());
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Se elimino correctamente");
    }

    public void ModificarRegistro(Proveedores proveedores) {
        TablaC.setRowCount(0);
        consulta = "UPDATE proveedores SET NOMBRE = ?, RUC = ?, DIRECCION = ?, TELEFONO = ?, EMAIL = ? WHERE ID_PROVEEDOR = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, proveedores.getNombre());
            consultar.setString(2, proveedores.getRuc());
            consultar.setString(3, proveedores.getDireccion());
            consultar.setString(4, proveedores.getTelefono());
            consultar.setString(5, proveedores.getEmail());
            consultar.setInt(6, proveedores.getIdProveedores());
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public DefaultTableModel BuscarRegistro(String nombre) {
        TablaC.setRowCount(0);
        consulta = "SELECT * FROM proveedores WHERE NOMBRE LIKE ?";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, nombre + "%");
            resultado = consultar.executeQuery();

            while (resultado.next()) {
                for (int i = 0; i < 9; i++) {
                    if (i == 0) {
                        uno = 2;
                    }
                    datos[i] = resultado.getString(i + uno);
                }
                TablaC.addRow(datos);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return TablaC;
    }
    public boolean rellenar = false;

    public void RellenarComponentes(Proveedor proveedor, Proveedores proveedores) {
        consulta = "SELECT * FROM proveedores WHERE ID_PROVEEDOR = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setInt(1, proveedores.getIdProveedores());
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                proveedor.JNombreProveedor.setText(resultado.getString(2));
                proveedor.JRUCProveedor.setText(resultado.getString(3));
                proveedor.JDireccionProveedor.setText(resultado.getString(4));
                proveedor.JTelefonoProveedor.setText(resultado.getString(5));
                proveedor.JEmailProveedor.setText(resultado.getString(6));
            }
            rellenar = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
