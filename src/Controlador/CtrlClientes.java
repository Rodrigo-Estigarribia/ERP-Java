 package Controlador;

import Consultas.ConsulClientes;
import Modelo.Clientes;
import Vista.PClientes;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CtrlClientes  implements ActionListener, ListSelectionListener, FocusListener {

    PClientes pclientes = new PClientes();
    ConsulClientes consulta = new ConsulClientes();
    Clientes clientes = new Clientes();

    public CtrlClientes(PClientes pclientes) throws SQLException {
        this.pclientes = pclientes;
        pclientes.jTableClientes.setModel(consulta.ExtraerDatos());

        pclientes.jTableClientes.getSelectionModel().addListSelectionListener(this);
        this.pclientes.Guardar.addActionListener(this);
        this.pclientes.Eliminar.addActionListener(this);
        this.pclientes.Modificar.addActionListener(this);
        this.pclientes.Nuevo.addActionListener(this);
        this.pclientes.Buscar.addActionListener(this);
        this.pclientes.jTextBuscar.addFocusListener(this);
    }

    public void Datos() {
        java.util.Date fecha = pclientes.jDateNacimiento.getDate();
        Date sqlfecha = new Date(fecha.getTime());//Se realiza una conversion del tiempo en milisegundos

        clientes.setCEDULA(pclientes.JCICliente.getText());
        clientes.setNOMBRE(pclientes.JNombreCliente.getText());
        clientes.setAPELLIDO(pclientes.JApellidoCliente.getText());
        clientes.setRUC(pclientes.jTextRUC.getText());
        clientes.setTELEFONO(pclientes.JTelefonoCliente.getText());
        clientes.setDIRECCION(pclientes.JDireccionCliente.getText());
        clientes.setCiudad(pclientes.jComboCiudad.getSelectedIndex());
        clientes.setFECHA_NACIMIENTO(sqlfecha);
        clientes.setEMAIL(pclientes.JEmailCliente.getText());
    }
    
    
    public void GuardarDatosClientes() throws SQLException{
        Datos();
        
        if (consulta.VerificarDatoExistente(clientes)) {//Verifica si los datos ingresados ya existen o no
            JOptionPane.showMessageDialog(null, "Los datos ingresados ya se encuentran en la Base de Datos");
        } else {
            consulta.CargrDatosCliente(clientes);
            System.out.println("Se ha Gurardao Correctamente al cliente " + pclientes.JNombreCliente.getText());
        }
    }

    //Eventos de Botones 
    @Override
    public void actionPerformed(ActionEvent e) {//Agregar nuevo Cliente
        
        if (e.getSource().equals(pclientes.Guardar)) {
            if (consulta.rellenar == false) {
                try {
                    GuardarDatosClientes();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    pclientes.jTableClientes.setModel(consulta.ExtraerDatos());
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Si desea guardar cambios click en 'Modificar'");
                JOptionPane.showMessageDialog(null, "Click en 'Nuevo' para cargar un nuevo Cliente");
            }
        }

        if (e.getSource().equals(pclientes.Eliminar)) {//Eliminar Clientes
            int fila = pclientes.jTableClientes.getSelectedRow();
            String ci = (String) pclientes.jTableClientes.getValueAt(fila, 0);
            String nombre = (String) pclientes.jTableClientes.getValueAt(fila, 1);
            int opcion = JOptionPane.showConfirmDialog(null, "Se eliminara permanentemente al cliente " + nombre
                    + "\n¿Desea continuar?");
            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    consulta.EliminarRegistro(ci);
                    pclientes.jTableClientes.setModel(consulta.ExtraerDatos());
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (e.getSource().equals(pclientes.Modificar)) {
            int fila = pclientes.jTableClientes.getSelectedRow();
            String ci = (String) pclientes.jTableClientes.getValueAt(fila, 0);
            Datos();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?");
            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    consulta.ModificarRegistro(clientes, ci);
                    pclientes.jTableClientes.setModel(consulta.ExtraerDatos());
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }

        if (e.getSource().equals(pclientes.Nuevo)) {
            pclientes.JCICliente.setText(null);
            pclientes.JNombreCliente.setText(null);
            pclientes.JApellidoCliente.setText(null);
            pclientes.jTextRUC.setText(null);
            pclientes.JDireccionCliente.setText(null);
            pclientes.JTelefonoCliente.setText(null);
            pclientes.jComboCiudad.setSelectedIndex(0);
            pclientes.JEmailCliente.setText(null);
            pclientes.jDateNacimiento.setDate(null);
            consulta.rellenar = false;
        }

        if (e.getSource().equals(pclientes.Buscar)) {
            if (pclientes.jTextBuscar.getText().equals("") || pclientes.jTextBuscar.getText().equals("Buscar...")) {
                JOptionPane.showMessageDialog(null, "Favor ingrese un nombre para realizar la busqueda");
            } else {
                String nombre = pclientes.jTextBuscar.getText();
                try {
                    pclientes.jTableClientes.setModel(consulta.BuscarRegistro(nombre));
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (pclientes.jTableClientes.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No hubo resultados para: " + nombre);
                    try {
                        pclientes.jTableClientes.setModel(consulta.ExtraerDatos()
                        );
                    } catch (SQLException ex) {
                        Logger.getLogger(CtrlClientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int fila = pclientes.jTableClientes.getSelectedRow();
        if (fila >= 0) {
            String ci = (String) pclientes.jTableClientes.getValueAt(fila, 0);
            try {
                consulta.RellenarComponentes(pclientes, ci);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (pclientes.jTextBuscar.getText().equals("Buscar...")) {
            pclientes.jTextBuscar.setText("");
            pclientes.jTextBuscar.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (pclientes.jTextBuscar.getText().isEmpty()) {
            pclientes.jTextBuscar.setText("Buscar...");
            pclientes.jTextBuscar.setForeground(Color.LIGHT_GRAY);
        }
    }
}
