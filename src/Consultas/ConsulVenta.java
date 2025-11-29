package Consultas;

import Controlador.Conexion;
import Modelo.Clientes;
import Modelo.Usuarios;
import Vista.PVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

public class ConsulVenta {

    Connection conexion = Conexion.conector();
    PreparedStatement consultar = null;
    ResultSet resultado = null;
    String consulta = null;

    public void IdentificarUsuario(Usuarios usuarios, PVenta pventa) throws SQLException {
        consulta = "SELECT u.ID_USUARIO, u.NOMBRE, u.APELLIDO, r.NIVEL_ACCESO FROM usuarios u INNER JOIN rol r ON r.ID_ROL = u.ID_ROL WHERE u.USUARIO = ?";

        try {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, Usuarios.getUsuario());
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                usuarios.setId(resultado.getInt(1));
                pventa.NombreUsuario.setText(resultado.getString(2) + " " + resultado.getString(3));
                pventa.Nivel.setText(resultado.getString(4));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            consultar.close();
            resultado.close();
        }
    }

    public void RellenarDatosCliente(PVenta venta, Clientes clientes) throws SQLException {
        consulta = "SELECT ID_CLIENTE, NOMBRE, APELLIDO, RUC FROM clientes WHERE CEDULA = ?";

        try {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, venta.JCedulaVenta.getText());
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                clientes.setID_CLIENTE(resultado.getInt(1));
                clientes.setNOMBRE(resultado.getString(2));
                clientes.setAPELLIDO(resultado.getString(3));
                venta.JRUC.setText(resultado.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally{
            consultar.close();
            resultado.close();
        }
    }

    public void RellenarDatosProducto(PVenta venta) throws SQLException {
        consulta = "SELECT PRECIO_ARTICULO, STOCK FROM articulo WHERE NOMBRE = ?";
        try {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, venta.JDescripcionProducto.getText());
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                venta.JPrecioUnitarioVenta.setText(resultado.getString(1));
                venta.JStockVenta.setText(resultado.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally{
            consultar.close();
            resultado.close();
        }
    }

    public void RestarStock(PVenta venta, int resta) throws SQLException {
        consulta = "UPDATE articulo SET STOCK = ? WHERE NOMBRE = ?";

        try {
            consultar = conexion.prepareStatement(consulta);
            consultar.setInt(1, resta);
            consultar.setString(2, venta.JDescripcionProducto.getText());
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            consultar.close();
        }
    }


    public DefaultTableModel RellenarArticulo(PVenta venta) throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) venta.Articulos.getModel();
        modelo.setRowCount(0);
        String Datos[] = new String[2];
        consulta = "SELECT ID_ARTICULO, NOMBRE FROM articulo";

        try {
            consultar = conexion.prepareStatement(consulta);
            resultado = consultar.executeQuery();

            while (resultado.next()) {
                for (int i = 0; i < 2; i++) {
                    Datos[i] = resultado.getString(i + 1);
                }
                modelo.addRow(Datos);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            consultar.close();
            resultado.close();
        }
        return modelo;
    }

    public void NumeroFactura(PVenta pventa) {
        consulta = "SELECT MAX(N°Factura) FROM factura_venta";
        int numero = 0;

        try {
            consultar = conexion.prepareStatement(consulta);
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                numero = resultado.getInt(1) + 1;
            }
            pventa.NroFactura.setText(String.valueOf(numero));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void GuardarFactura(PVenta pventa, Clientes clientes, Usuarios usuarios) throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) pventa.JTableVenta.getModel();
        LocalDate fecha = LocalDate.now();
        Date fechasql = Date.valueOf(fecha);
        consulta = "INSERT INTO factura_venta (N°Factura, ID_Clientes, ID_Usuario, Fecha, Total)"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            consultar = conexion.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
            consultar.setInt(1, Integer.parseInt(pventa.NroFactura.getText()));
            consultar.setInt(2, clientes.getID_CLIENTE());
            consultar.setInt(3, usuarios.getId());
            consultar.setDate(4, fechasql);
            consultar.setDouble(5, Double.parseDouble(pventa.JTotalPagar.getText()));
            consultar.executeUpdate();
            resultado = consultar.getGeneratedKeys();
            int id_factura_venta = 0;
            if (resultado.next()) {
                id_factura_venta = resultado.getInt(1);
            }
            consultar.close();
            resultado.close();
            
            //Detalle_Factura
            consulta = "INSERT INTO detalle_factura_venta (ID_Factura_Venta, ID_Articulos, PrecioUnitario, Cantidad, Total)"
                    + "VALUES (?, (SELECT ID_ARTICULO FROM articulo WHERE NOMBRE = ?), ?, ?, ?)";
            
            consultar = conexion.prepareStatement(consulta);
            for (int i = 0; i < modelo.getRowCount(); i++) {
                consultar.setInt(1, id_factura_venta);
                consultar.setString(2, (String) modelo.getValueAt(i, 0));
                consultar.setInt(3, Integer.parseInt((String) modelo.getValueAt(i, 1)));
                consultar.setDouble(4, Double.parseDouble((String) modelo.getValueAt(i, 2)));
                consultar.setDouble(5, Double.parseDouble((String) modelo.getValueAt(i, 3)));
                consultar.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            consultar.close();
            resultado.close();
        }
    }
}
