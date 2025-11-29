package Controlador;

import Consultas.ConsulProducto;
import Modelo.Articulos;
import Vista.Productos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CtrlProducto implements ActionListener, ListSelectionListener, FocusListener {

    Productos producto = new Productos();
    ConsulProducto consulta = new ConsulProducto();
    Articulos articulos = new Articulos();

    public CtrlProducto(Productos producto) {
        this.producto = producto;
        producto.jTableProducto.setModel(consulta.ExtraerDatos());

        producto.jTableProducto.getSelectionModel().addListSelectionListener(this);
        this.producto.Guardar.addActionListener(this);
        this.producto.Eliminar.addActionListener(this);
        this.producto.Modificar.addActionListener(this);
        this.producto.Nuevo.addActionListener(this);
        this.producto.Buscar.addActionListener(this);
        this.producto.jTextBuscar.addFocusListener(this);
    }

    public void Datos() {
        articulos.setNOMBRE(producto.JNombreProducto.getText());
        articulos.setPRECIO(Integer.parseInt(producto.JPrecioProducto.getText()));
        articulos.setProveedor((producto.JComboProveedor.getSelectedIndex()));
        articulos.setSTOCK(Integer.parseInt(producto.jtxtCantidad.getText()));
    }

    public void GuardarDatosClientes() throws SQLException {
        Datos();
        if (consulta.VerificarDatoExistente(articulos)) {//Verifica si los datos ingresados ya existen o no
            JOptionPane.showMessageDialog(null, "Los datos ingresados ya se encuentran en la Base de Datos");
        } else {
            consulta.CargrDatosProveedor(articulos);
            System.out.println("Se ha Gurardao Correctamente al cliente " + producto.JNombreProducto.getText());
        }
    }

    //Eventos de Botones 
    @Override
    public void actionPerformed(ActionEvent e) {//Agregar nuevo Articulo
        if (e.getSource().equals(producto.Guardar)) {
            if (consulta.rellenar == false) {
                try {
                    GuardarDatosClientes();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
                producto.jTableProducto.setModel(consulta.ExtraerDatos());
            } else {
                JOptionPane.showMessageDialog(null, "Si desea guardar cambios click en 'Modificar'");
                JOptionPane.showMessageDialog(null, "Click en 'Nuevo' para cargar un nuevo Articulo");
            }

        }
        if (e.getSource().equals(producto.Eliminar)) {//Eliminar Articulo
            int fila = producto.jTableProducto.getSelectedRow();
            articulos.setID_ARTICULO(Integer.parseInt((String) producto.jTableProducto.getValueAt(fila, 0)));
            int opcion = JOptionPane.showConfirmDialog(null, "Se eliminara permanentemente al cliente " + articulos.getNOMBRE()
                    + "\n¿Desea continuar?");
            if (opcion == JOptionPane.YES_OPTION) {
                consulta.EliminarRegistro(articulos);
                producto.jTableProducto.setModel(consulta.ExtraerDatos());
            }
        }

        if (e.getSource().equals(producto.Modificar)) {
            int fila = producto.jTableProducto.getSelectedRow();
            articulos.setID_ARTICULO(Integer.parseInt((String) producto.jTableProducto.getValueAt(fila, 0)));
            Datos();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?");
            if (opcion == JOptionPane.YES_OPTION) {
                consulta.ModificarRegistro(articulos);
                producto.jTableProducto.setModel(consulta.ExtraerDatos());
            }
        }

        if (e.getSource().equals(producto.Nuevo)) {
            producto.JNombreProducto.setText(null);
            producto.JPrecioProducto.setText(null);
            producto.JComboProveedor.setSelectedIndex(0);
            producto.jtxtCantidad.setText(null);
            consulta.rellenar = false;
        }

        if (e.getSource().equals(producto.Buscar)) {
            if (producto.jTextBuscar.getText().equals("") || producto.jTextBuscar.getText().equals("Buscar...")) {
                JOptionPane.showMessageDialog(null, "Favor ingrese un nombre para realizar la busqueda");
            } else {
                String nombre = producto.jTextBuscar.getText();
                producto.jTableProducto.setModel(consulta.BuscarRegistro(nombre));
                if (producto.jTableProducto.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No hubo resultados para: " + nombre);
                    producto.jTableProducto.setModel(consulta.ExtraerDatos()
                    );
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int fila = producto.jTableProducto.getSelectedRow();
        if (fila >= 0) {
            articulos.setID_ARTICULO(Integer.parseInt((String) producto.jTableProducto.getValueAt(fila, 0)));
            consulta.RellenarComponentes(producto, articulos);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (producto.jTextBuscar.getText().equals("Buscar...")) {
            producto.jTextBuscar.setText("");
            producto.jTextBuscar.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (producto.jTextBuscar.getText().isEmpty()) {
            producto.jTextBuscar.setText("Buscar...");
            producto.jTextBuscar.setForeground(Color.LIGHT_GRAY);
        }
    }
}
