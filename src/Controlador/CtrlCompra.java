package Controlador;

import Consultas.ConsulCompra;
import Modelo.Articulos;
import Modelo.Proveedores;
import Vista.PCompra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlCompra implements ActionListener, KeyListener {

    public void calculos() {
        DefaultTableModel modelo = (DefaultTableModel) compra.JTableCompra.getModel();
        Double totalpagar = 0.0;
        Double iva = 0.10;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            totalpagar += Double.parseDouble((String) modelo.getValueAt(i, 3));
        }
        iva = totalpagar * iva;
        //totalpagar = totalpagar + iva;
        compra.JIva.setText(String.valueOf(iva));
        compra.JTotalCompra.setText(String.valueOf(totalpagar));
    }
    
    public void rellenar() throws SQLException {
        if (compra.JDescripcionProducto.getText().isEmpty()) {
            consulta.RellenarArticulo(compra);
        }
    }
    
    public void limpiar() {
        DefaultTableModel modelo = (DefaultTableModel) compra.JTableCompra.getModel();
        modelo.setRowCount(0);
        compra.JProveedor.setText(null);
        compra.JRuc.setText(null);
        compra.JDescripcionProducto.setText(null);
        compra.JCantidadCompra.setText(null);
        compra.PrecioCompra.setText(null);
        compra.enStock.setText(null);
        compra.JIva.setText(null);
        compra.JTotalCompra.setText(null);
        compra.NroFactura.setText(null);
        compra.jDate.setDate(null);
    }
    
    Articulos articulos = new Articulos();
    Proveedores proveedor = new Proveedores();
    PCompra compra = new PCompra();
    ConsulCompra consulta = new ConsulCompra();
    
    public CtrlCompra(PCompra compra) throws SQLException {
        this.compra = compra;
        
        rellenar();
        this.compra.JBuscarProveedor.addActionListener(this);
        this.compra.JAgregarProductoVennta.addActionListener(this);
        this.compra.JGuardar.addActionListener(this);
        this.compra.JEliminarProducto.addActionListener(this);
        this.compra.JBuscarProveedor.addActionListener(this);
        this.compra.añadir.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel modelo = (DefaultTableModel) compra.JTableCompra.getModel();
        
        if (e.getSource().equals(compra.JBuscarProveedor)) {//Boton Buscar Cliente
            try {
                consulta.RellenarDatosProveedor(compra, proveedor);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlCompra.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                
            }
            if (proveedor.getNombre() == null) {
                JOptionPane.showMessageDialog(null, "No se encontro resultados para: " + compra.JRuc.getText());
            } else {
                compra.JProveedor.setText(proveedor.getNombre());
            }
        }
        if (e.getSource().equals(compra.añadir)) {
            int fila = compra.Articulos.getSelectedRow();
            compra.JDescripcionProducto.setText((String) compra.Articulos.getValueAt(fila, 1));
            try {
                consulta.RellenarDatosProducto(compra);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(compra.JAgregarProductoVennta)) {//Boton Agregar
            if (!compra.JCantidadCompra.getText().equals("")) {
                
                String nombre = compra.JDescripcionProducto.getText();
                int preciounitario = Integer.parseInt(compra.PrecioCompra.getText());
                int cantidad = Integer.parseInt(compra.JCantidadCompra.getText());
                int total = cantidad * preciounitario;
                modelo.addRow(new String[]{
                    nombre, String.valueOf(preciounitario), String.valueOf(cantidad), String.valueOf(total)
                });
                calculos();
            } else {
                JOptionPane.showMessageDialog(null, "Indicar cantidad antes de agregar");
            }
        }
        if (e.getSource().equals(compra.JGuardar)) {
            int sumar = Integer.parseInt(compra.enStock.getText()) + Integer.parseInt(compra.JCantidadCompra.getText());
            try {
                consulta.GuardarFactura(compra, proveedor);
                consulta.SumarStock(compra, sumar);
                limpiar();
            } catch (SQLException ex) {
                Logger.getLogger(CtrlCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(compra.JEliminarProducto)) {
            modelo.removeRow(compra.JTableCompra.getSelectedRow());
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(compra.JDescripcionProducto)) {
            try {
                rellenar();
            } catch (SQLException ex) {
                Logger.getLogger(CtrlCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
