package Consultas;

import Controlador.Conexion;
import Modelo.Usuarios;
import Vista.PUsuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConsulUsuarios {

    PreparedStatement consultar = null;
    ResultSet resultado = null;
    String consulta;
    //Estas variables son requeridas en ExtraetDatos() y BuscarRegistros()
    String[] datos = new String[12];
    String[] columnas = {"CI", "Nombres", "Apellidos", "Usuario", "Contraseña", "ROL", "RUC", "Direccion", "Telefono", "Ciudad", "Email", "Fecha de Nacimiento"};
    DefaultTableModel TablaC = new DefaultTableModel(null, columnas);
    int uno = 1;

    public void CargrDatosUsuario(Usuarios usuarios) {
        TablaC.setRowCount(0);
        consulta = "INSERT INTO usuarios (`CEDULA`, `NOMBRE`, `APELLIDO`, `USUARIO`, `CONTRASEÑA`, `ID_ROL`, `RUC`, `DIRECCION`, `TELEFONO`, `ID_CIUDAD`, `EMAIL`, `FECHA_NACIMIENTO`)"
                + " VALUES (?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, usuarios.getCedula());
            consultar.setString(2, usuarios.getNombre());
            consultar.setString(3, usuarios.getApellido());
            consultar.setString(4, Usuarios.getUsuario());
            consultar.setString(5, usuarios.getContraseña());
            consultar.setInt(6, usuarios.getNivel());
            consultar.setString(7, usuarios.getRuc());
            consultar.setString(8, usuarios.getDireccion());
            consultar.setString(9, usuarios.getTelefono());
            consultar.setInt(10, usuarios.getCiudad());
            consultar.setString(11, usuarios.getEmail());
            consultar.setDate(12, (Date) usuarios.getFecha_nacimiento());

            consultar.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean VerificarDatoExistente(Usuarios usuarios) {
        consulta = "SELECT * FROM usuarios WHERE CEDULA = ?";
        boolean YaExiste = false;

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, usuarios.getCedula());
            resultado = consultar.executeQuery();
            YaExiste = resultado.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return YaExiste;
    }

    public DefaultTableModel ExtraerDatos() {
        consulta = "SELECT * FROM usuarios";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                for (int i = 0; i < 12; i++) {
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

    public void EliminarRegistro(String ci) {
        TablaC.setRowCount(0);
        consulta = "DELETE FROM usuarios WHERE CEDULA = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, ci);
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Se elimino correctamente");
    }

    public void ModificarRegistro(Usuarios usuarios, String ci) {
        TablaC.setRowCount(0);
        consulta = "UPDATE usuarios SET CEDULA = ?, NOMBRE = ?, APELLIDO = ?, USUARIO = ?, CONTRASEÑA = ?, ID_ROL = ?, RUC = ?, DIRECCION = ?, TELEFONO = ?, ID_CIUDAD = ?, EMAIL = ?, FECHA_NACIMIENTO = ? WHERE CEDULA = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, usuarios.getCedula());
            consultar.setString(2, usuarios.getNombre());
            consultar.setString(3, usuarios.getApellido());
            consultar.setString(4, Usuarios.getUsuario());
            consultar.setString(5, usuarios.getContraseña());
            consultar.setInt(6, usuarios.getNivel());
            consultar.setString(7, usuarios.getRuc());
            consultar.setString(8, usuarios.getDireccion());
            consultar.setString(9, usuarios.getTelefono());
            consultar.setInt(10, usuarios.getCiudad());
            consultar.setString(11, usuarios.getEmail());
            consultar.setDate(12, (Date) usuarios.getFecha_nacimiento());
            consultar.setString(13, ci);
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public DefaultTableModel BuscarRegistro(String nombre) {
        TablaC.setRowCount(0);
        consulta = "SELECT * FROM usuarios WHERE NOMBRE LIKE ?";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, nombre + "%");
            resultado = consultar.executeQuery();

            while (resultado.next()) {
                for (int i = 0; i < 12; i++) {
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

    public void RellenarComponentes(PUsuario pusuario, String ci) {
        consulta = "SELECT * FROM usuarios WHERE CEDULA = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, ci);
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                pusuario.JCIUsuario.setText(resultado.getString(2));
                pusuario.JNombreUsuario.setText(resultado.getString(3));
                pusuario.JApellidoUsuario.setText(resultado.getString(4));
                pusuario.jTextUsuario.setText(resultado.getString(5));
                pusuario.jPasswordContraseña.setText(resultado.getString(6));
                pusuario.jComboRol.setSelectedItem(resultado.getString(7));
                pusuario.jTexRucUsuario.setText(resultado.getString(8));
                pusuario.JDireccionUsuario.setText(resultado.getString(9));
                pusuario.JTelefonoUsuario.setText(resultado.getString(10));
                pusuario.jComboCiudad.setSelectedItem(resultado.getInt(11));
                pusuario.JEmailUsuario.setText(resultado.getString(12));
                pusuario.jDateNacimientoUsuario.setDate(resultado.getDate(13));
            }
            rellenar = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
