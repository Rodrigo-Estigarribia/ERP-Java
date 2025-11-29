package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public Connection con = null;

    public static Connection conector() {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria",
                    "root",
                    "12345");
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return con;
    }
}
