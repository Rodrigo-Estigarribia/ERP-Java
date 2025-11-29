package Controlador;

import Consultas.ConsulProveedor;
import Modelo.Proveedores;
import Vista.Proveedor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CtrlProveedor implements ActionListener, ListSelectionListener, FocusListener {

    Proveedor proveedor = new Proveedor();
    ConsulProveedor consulta = new ConsulProveedor();
    Proveedores proveedores = new Proveedores();

    public CtrlProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        proveedor.jTableProveedor.setModel(consulta.ExtraerDatos());

        proveedor.jTableProveedor.getSelectionModel().addListSelectionListener(this);
        this.proveedor.Guardar.addActionListener(this);
        this.proveedor.Eliminar.addActionListener(this);
        this.proveedor.Modificar.addActionListener(this);
        this.proveedor.Nuevo.addActionListener(this);
        this.proveedor.Buscar.addActionListener(this);
        this.proveedor.jTextBuscar.addFocusListener(this);
    }

    public void Datos() {
        proveedores.setNombre(proveedor.JNombreProveedor.getText());
        proveedores.setRuc(proveedor.JRUCProveedor.getText());
        proveedores.setTelefono(proveedor.JTelefonoProveedor.getText());
        proveedores.setDireccion(proveedor.JDireccionProveedor.getText());
        proveedores.setEmail(proveedor.JEmailProveedor.getText());
    }

    public void GuardarDatosClientes() {
        Datos();
        if (consulta.VerificarDatoExistente(proveedores)) {//Verifica si los datos ingresados ya existen o no
            JOptionPane.showMessageDialog(null, "Los datos ingresados ya se encuentran en la Base de Datos");
        } else {
            consulta.CargrDatosProveedor(proveedores);
            System.out.println("Se ha Gurardao Correctamente al cliente " + proveedor.JNombreProveedor.getText());
        }
    }

    //Eventos de Botones 
    @Override
    public void actionPerformed(ActionEvent e) {//Agregar nuevo Proveedor
        if (e.getSource().equals(proveedor.Guardar)) {
            if (consulta.rellenar == false) {
                GuardarDatosClientes();
            proveedor.jTableProveedor.setModel(consulta.ExtraerDatos());
            } else {
                JOptionPane.showMessageDialog(null, "Si desea guardar cambios click en 'Modificar'");
                JOptionPane.showMessageDialog(null, "Click en 'Nuevo' para cargar un nuevo Proveedor");
            }
        }

        if (e.getSource().equals(proveedor.Eliminar)) {//Eliminar Proveedor
            int fila = proveedor.jTableProveedor.getSelectedRow();
            proveedores.setIdProveedores(Integer.parseInt((String) proveedor.jTableProveedor.getValueAt(fila, 0)));
            int opcion = JOptionPane.showConfirmDialog(null, "Se eliminara permanentemente al cliente " + proveedores.getNombre()
                    + "\n¿Desea continuar?");
            if (opcion == JOptionPane.YES_OPTION) {
                consulta.EliminarRegistro(proveedores);
                proveedor.jTableProveedor.setModel(consulta.ExtraerDatos());
            }
        }

        if (e.getSource().equals(proveedor.Modificar)) {
            int fila = proveedor.jTableProveedor.getSelectedRow();
            proveedores.setIdProveedores(Integer.parseInt((String) proveedor.jTableProveedor.getValueAt(fila, 0)));
            Datos();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?");
            if (opcion == JOptionPane.YES_OPTION) {
                consulta.ModificarRegistro(proveedores);
                proveedor.jTableProveedor.setModel(consulta.ExtraerDatos());
            }
        }

        if (e.getSource().equals(proveedor.Nuevo)) {
            proveedor.JNombreProveedor.setText(null);
            proveedor.JRUCProveedor.setText(null);
            proveedor.JDireccionProveedor.setText(null);
            proveedor.JEmailProveedor.setText(null);
            proveedor.JTelefonoProveedor.setText(null);
            consulta.rellenar = false;
        }

        if (e.getSource().equals(proveedor.Buscar)) {
            if (proveedor.jTextBuscar.getText().equals("") || proveedor.jTextBuscar.getText().equals("Buscar...")) {
                JOptionPane.showMessageDialog(null, "Favor ingrese un nombre para realizar la busqueda");
            }else {
                String nombre = proveedor.jTextBuscar.getText();
                proveedor.jTableProveedor.setModel(consulta.BuscarRegistro(nombre));
                if (proveedor.jTableProveedor.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No hubo resultados para: " + nombre);
                    proveedor.jTableProveedor.setModel(consulta.ExtraerDatos()
                    );
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int fila = proveedor.jTableProveedor.getSelectedRow();
        if (fila >= 0) {
            proveedores.setIdProveedores(Integer.parseInt((String) proveedor.jTableProveedor.getValueAt(fila, 0)));
            consulta.RellenarComponentes(proveedor,proveedores);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (proveedor.jTextBuscar.getText().equals("Buscar...")) {
            proveedor.jTextBuscar.setText("");
            proveedor.jTextBuscar.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (proveedor.jTextBuscar.getText().isEmpty()) {
            proveedor.jTextBuscar.setText("Buscar...");
            proveedor.jTextBuscar.setForeground(Color.LIGHT_GRAY);
        }
    }
}