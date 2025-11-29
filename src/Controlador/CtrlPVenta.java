package Controlador;

import Consultas.ConsulVenta;
import Modelo.Articulos;
import Modelo.Clientes;
import Modelo.Factura;
import Modelo.Usuarios;
import Vista.PVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlPVenta implements ActionListener, KeyListener {

    public void calculos() {
        DefaultTableModel modelo = (DefaultTableModel) venta.JTableVenta.getModel();
        Double totalpagar = 0.0;
        Double iva = 0.10;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            totalpagar += Double.parseDouble((String) modelo.getValueAt(i, 3));
        }
        iva = totalpagar * iva;
        //totalpagar = totalpagar + iva;
        venta.JIva.setText(String.valueOf(iva));
        venta.JTotalPagar.setText(String.valueOf(totalpagar));
    }

    public void rellenar() throws SQLException {
        if (venta.JDescripcionProducto.getText().isEmpty()) {
            consulta.RellenarArticulo(venta);
        }
    }

    public void limpiar() {
        DefaultTableModel modelo = (DefaultTableModel) venta.JTableVenta.getModel();
        modelo.setRowCount(0);
        venta.JNombreVenta.setText(null);
        venta.JRUC.setText(null);
        venta.JCedulaVenta.setText(null);
        venta.JDescripcionProducto.setText(null);
        venta.JCantidadVenta.setText(null);
        venta.JNombreVenta.setText(null);
        venta.JPrecioUnitarioVenta.setText(null);
        venta.JStockVenta.setText(null);
        venta.MontoEntregado.setText(null);
        venta.MetodoDePago.setSelectedIndex(0);
        venta.Vuelto.setText(null);
        venta.JIva.setText(null);
        venta.JTotalPagar.setText(null);
        consulta.NumeroFactura(venta);

    }

    Articulos articulos = new Articulos();
    Clientes clientes = new Clientes();
    PVenta venta = new PVenta();
    ConsulVenta consulta = new ConsulVenta();
    Usuarios usuarios = new Usuarios();
    Factura factura = new Factura();

    public CtrlPVenta(PVenta venta) throws SQLException {
        this.venta = venta;
        rellenar();
        consulta.NumeroFactura(venta);
        consulta.IdentificarUsuario(usuarios, venta);
        this.venta.JBuscarClienteVenta.addActionListener(this);
        this.venta.JAgregarProductoVennta.addActionListener(this);
        this.venta.JCobrarVenta.addActionListener(this);
        this.venta.JEliminarProducto.addActionListener(this);
        this.venta.JDescripcionProducto.addKeyListener(this);
        this.venta.añadir.addActionListener(this);
        this.venta.MontoEntregado.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel modelo = (DefaultTableModel) venta.JTableVenta.getModel();

        if (e.getSource().equals(venta.JBuscarClienteVenta)) {try {
            //Boton Buscar Cliente
            consulta.RellenarDatosCliente(venta, clientes);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlPVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (clientes.getNOMBRE() == null) {
                JOptionPane.showMessageDialog(null, "No se encontro resultados para: " + venta.JCedulaVenta.getText());
            } else {
                venta.JNombreVenta.setText(clientes.getNOMBRE() + " " + clientes.getAPELLIDO());
            }
        }
        if (e.getSource().equals(venta.añadir)) {
            int fila = venta.Articulos.getSelectedRow();
            venta.JDescripcionProducto.setText((String) venta.Articulos.getValueAt(fila, 1));
            try {
                consulta.RellenarDatosProducto(venta);
            } catch (SQLException ex) {
            }
        }
        if (e.getSource().equals(venta.JAgregarProductoVennta)) {//Boton Agregar
            if (!venta.JCantidadVenta.getText().equals("")) {

                String nombre = venta.JDescripcionProducto.getText();
                int preciounitario = Integer.parseInt(venta.JPrecioUnitarioVenta.getText());
                int cantidad = Integer.parseInt(venta.JCantidadVenta.getText());
                int total = cantidad * preciounitario;
                if (cantidad > Integer.parseInt(venta.JStockVenta.getText())) {
                    JOptionPane.showMessageDialog(null, "La cantidad supera la disponible en stock");
                } else {
                    modelo.addRow(new String[]{
                        nombre, String.valueOf(preciounitario), String.valueOf(cantidad), String.valueOf(total)
                    });
                    calculos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Indicar cantidad antes de agregar");
            }
        }
        if (e.getSource().equals(venta.JCobrarVenta)) {//Guarda la factura en la base de datos
            int resta = Integer.parseInt(venta.JStockVenta.getText()) - Integer.parseInt(venta.JCantidadVenta.getText());
            try {
                consulta.GuardarFactura(venta, clientes, usuarios);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlPVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                consulta.RestarStock(venta, resta);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlPVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
            limpiar();
        }
        if (e.getSource().equals(venta.JEliminarProducto)) {//Elimina un producto ya cargado
            modelo.removeRow(venta.JTableVenta.getSelectedRow());
            calculos();
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
        if (e.getSource().equals(venta.JDescripcionProducto)) {
            try {
                rellenar();
            } catch (SQLException ex) {
                Logger.getLogger(CtrlPVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(venta.MontoEntregado)) {
            double vuelto = Double.parseDouble(venta.MontoEntregado.getText()) - Double.parseDouble(venta.JTotalPagar.getText());
            venta.Vuelto.setText(String.valueOf(vuelto));
        }
    }
}
