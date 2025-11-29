package Consultas;

import Controlador.Conexion;
import Modelo.Clientes;
import Vista.PClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConsulClientes {

    PreparedStatement consultar = null;
    ResultSet resultado = null;
    String consulta;
    //Estas variables son requeridas en ExtraetDatos() y BuscarRegistros()
    String[] datos = new String[9];
    String[] columnas = {"CI", "Nombres", "Apellidos", "RUC", "Direccion", "Telefono", "Ciudad", "Email", "Fecha de Nacimiento"};
    DefaultTableModel TablaC = new DefaultTableModel(null, columnas);
    int uno = 1;

    public void CargrDatosCliente(Clientes clientes) throws SQLException {
        TablaC.setRowCount(0);
        consulta = "INSERT INTO clientes (`CEDULA`, `NOMBRE`, `APELLIDO`, `RUC`, `DIRECCION`, `TELEFONO`, `ID_CIUDAD`, `EMAIL`, `FECHA_NACIMIENTO`)"
                + " VALUES (?, ? , ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, clientes.getCEDULA());
            consultar.setString(2, clientes.getNOMBRE());
            consultar.setString(3, clientes.getAPELLIDO());
            consultar.setString(4, clientes.getRUC());
            consultar.setString(5, clientes.getDIRECCION());
            consultar.setString(6, clientes.getTELEFONO());
            consultar.setInt(7, clientes.getCiudad());
            consultar.setString(8, clientes.getEMAIL());
            consultar.setDate(9, clientes.getFECHA_NACIMIENTO());

            consultar.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean VerificarDatoExistente(Clientes clientes) throws SQLException {
        consulta = "SELECT * FROM clientes WHERE CEDULA = ?";
        boolean YaExiste = false;

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, clientes.getCEDULA());
            resultado = consultar.executeQuery();
            YaExiste = resultado.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return YaExiste;
    }

    public DefaultTableModel ExtraerDatos() throws SQLException {
        consulta = "SELECT * FROM clientes";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
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

    public void EliminarRegistro(String ci) throws SQLException {
        TablaC.setRowCount(0);
        consulta = "DELETE FROM clientes WHERE CEDULA = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, ci);
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Se elimino correctamente");
    }

    public void ModificarRegistro(Clientes clientes, String ci) throws SQLException {
        TablaC.setRowCount(0);
        consulta = "UPDATE clientes SET CEDULA = ?, NOMBRE = ?, APELLIDO = ?, RUC = ?, DIRECCION = ?, TELEFONO = ?, ID_CIUDAD = ?, EMAIL = ?, FECHA_NACIMIENTO = ? WHERE CEDULA = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, clientes.getCEDULA());
            consultar.setString(2, clientes.getNOMBRE());
            consultar.setString(3, clientes.getAPELLIDO());
            consultar.setString(4, clientes.getRUC());
            consultar.setString(5, clientes.getDIRECCION());
            consultar.setString(6, clientes.getTELEFONO());
            consultar.setInt(7, clientes.getCiudad());
            consultar.setString(8, clientes.getEMAIL());
            consultar.setDate(9, clientes.getFECHA_NACIMIENTO());
            consultar.setString(10, ci);
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public DefaultTableModel BuscarRegistro(String nombre) throws SQLException {
        TablaC.setRowCount(0);
        consulta = "SELECT * FROM clientes WHERE NOMBRE LIKE ?";

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

    public void RellenarComponentes(PClientes pclientes, String ci) throws SQLException {
        consulta = "SELECT * FROM clientes WHERE CEDULA = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, ci);
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                pclientes.JCICliente.setText(resultado.getString(2));
                pclientes.JNombreCliente.setText(resultado.getString(3));
                pclientes.JApellidoCliente.setText(resultado.getString(4));
                pclientes.jTextRUC.setText(resultado.getString(5));
                pclientes.JDireccionCliente.setText(resultado.getString(6));
                pclientes.JTelefonoCliente.setText(resultado.getString(7));
                pclientes.jComboCiudad.setSelectedItem(resultado.getInt(8));
                pclientes.JEmailCliente.setText(resultado.getString(9));
                pclientes.jDateNacimiento.setDate(resultado.getDate(10));
            }
            rellenar = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
