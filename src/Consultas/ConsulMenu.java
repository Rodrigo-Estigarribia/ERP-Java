
package Consultas;

import Controlador.Conexion;
import Modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConsulMenu {
    Connection conexion = Conexion.conector();
    PreparedStatement consultar = null;
    ResultSet resultado = null;
    String consulta;
    
    public void VerificarNivel(Usuarios usuarios) throws SQLException{
        consulta = "SELECT ID_ROL FROM usuarios WHERE USUARIO = ?";
        
        try {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, Usuarios.getUsuario());
            resultado = consultar.executeQuery();
            while (resultado.next()) {                
                usuarios.setNivel(resultado.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            consultar.close();
            conexion.close();
            resultado.close();
        }
    }
}
