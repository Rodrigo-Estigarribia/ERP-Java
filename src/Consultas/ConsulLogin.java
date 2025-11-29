package Consultas;

import Controlador.Conexion;
import Modelo.Usuarios;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsulLogin {

    Connection conexion = Conexion.conector();
    PreparedStatement consultar = null;
    ResultSet resultado = null;
    private String consulta;

    public boolean validacion(Usuarios usuario) throws SQLException {
        boolean acceder = false;
        consulta = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";

        try {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, Usuarios.getUsuario());
            consultar.setString(2, usuario.getContraseña());
            resultado = consultar.executeQuery();
            acceder = resultado.next();

        } catch (SQLException e) {
            System.out.println(e);
        }finally{
            conexion.close();
            consultar.close();
            resultado.close();
        }

        return acceder;
    }

}
