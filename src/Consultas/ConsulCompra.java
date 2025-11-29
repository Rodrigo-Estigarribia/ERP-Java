package Consultas;

import Controlador.Conexion;
import Modelo.Proveedores;
import Vista.PCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

public class ConsulCompra {

    PreparedStatement consultar = null;
    ResultSet resultado = null;
    String consulta = null;

    public void RellenarDatosProveedor(PCompra compra, Proveedores proveedores) throws SQLException {
        consulta = "SELECT ID_PROVEEDOR, NOMBRE FROM proveedores WHERE RUC = ?";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, compra.JRuc.getText());
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                proveedores.setIdProveedores(resultado.getInt(1));
                proveedores.setNombre(resultado.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void RellenarDatosProducto(PCompra compra) throws SQLException {
        consulta = "SELECT PRECIO_ARTICULO, STOCK FROM articulo WHERE NOMBRE = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setString(1, compra.JDescripcionProducto.getText());
            resultado = consultar.executeQuery();
            while (resultado.next()) {
                compra.PrecioCompra.setText(resultado.getString(1));
                compra.enStock.setText(resultado.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            consultar.close();
            resultado.close();
        }
    }

    public void SumarStock(PCompra compra, int suma) throws SQLException {
        consulta = "UPDATE articulo SET STOCK = ? WHERE NOMBRE = ?";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            consultar.setInt(1, suma);
            consultar.setString(2, compra.JDescripcionProducto.getText());
            consultar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public DefaultTableModel RellenarArticulo(PCompra compra) throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) compra.JTableCompra.getModel();
        modelo.setRowCount(0);
        String Datos[] = new String[2];
        consulta = "SELECT ID_ARTICULO, NOMBRE FROM articulo";

        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            resultado = consultar.executeQuery();

            while (resultado.next()) {
                for (int i = 0; i < 2; i++) {
                    Datos[i] = resultado.getString(i + 1);
                }
                modelo.addRow(Datos);
            }
            modelo.addRow(Datos);
        } catch (SQLException e) {
        }
        return modelo;
    }

    public void GuardarFactura(PCompra compra, Proveedores proveedores) throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) compra.JTableCompra.getModel();
        java.util.Date fecha = compra.jDate.getDate();
        Date fechasql = new Date(fecha.getTime());
        consulta = "INSERT INTO factura_compra (N°Factura, ID_PROVEEDOR, Fecha, CompraTotal)"
                + "VALUES (?, ?, ?, ?)";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
            consultar.setInt(1, Integer.parseInt(compra.NroFactura.getText()));
            consultar.setInt(2, proveedores.getIdProveedores());
            consultar.setDate(3, fechasql);
            consultar.setDouble(4, Double.parseDouble(compra.JTotalCompra.getText()));
            consultar.executeUpdate();
            resultado = consultar.getGeneratedKeys();
            int id_factura_compra = 0;
            if (resultado.next()) {
                id_factura_compra = resultado.getInt(1);
            }
            consultar.close();
            resultado.close();

            //Detalle_Factura
            consulta = "INSERT INTO detalle_factura_compra (ID_Factura_Compra, ID_Articulos, PrecioUnitario, Cantidad, Total)"
                    + "VALUES (?, (SELECT ID_ARTICULO FROM articulo WHERE NOMBRE = ?), ?, ?, ?)";

            consultar = conexion.prepareStatement(consulta);
            for (int i = 0; i < modelo.getRowCount(); i++) {
                consultar.setInt(1, id_factura_compra);
                consultar.setString(2, (String) modelo.getValueAt(i, 0));
                consultar.setInt(3, Integer.parseInt((String) modelo.getValueAt(i, 1)));
                consultar.setDouble(4, Double.parseDouble((String) modelo.getValueAt(i, 2)));
                consultar.setDouble(5, Double.parseDouble((String) modelo.getValueAt(i, 3)));
                consultar.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void CambiarPrecio(PCompra compra) throws SQLException {
        DefaultTableModel modelo = (DefaultTableModel) compra.JTableCompra.getModel();
        Double[] precioventa = new Double[0];
        String nombre;
        
        for (int i = 0; i < modelo.getRowCount(); i++) {
                precioventa[i] = Double.parseDouble((String) modelo.getValueAt(i, 1)) * 0.40;
                
                
            }
                
        consulta = "UPDATE articulo SET PRECIO_ARTICULO WHERE NOMBRE = ?";
        try (Connection conexion = Conexion.conector()) {
            consultar = conexion.prepareStatement(consulta);
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
