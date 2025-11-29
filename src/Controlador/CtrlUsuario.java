 package Controlador;

import Consultas.ConsulUsuarios;
import Modelo.Usuarios;
import Vista.PUsuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class CtrlUsuario implements ActionListener, ListSelectionListener, FocusListener {

   PUsuario pusuario = new PUsuario();
   Usuarios usuarios = new Usuarios();
   ConsulUsuarios consulta = new ConsulUsuarios();

    public CtrlUsuario(PUsuario pusuario) {
        this.pusuario = pusuario;
        pusuario.jTableUsuarios.setModel(consulta.ExtraerDatos());

        pusuario.jTableUsuarios.getSelectionModel().addListSelectionListener(this);
        this.pusuario.Guardar.addActionListener(this);
        this.pusuario.Eliminar.addActionListener(this);
        this.pusuario.Modificar.addActionListener(this);
        this.pusuario.Nuevo.addActionListener(this);
        this.pusuario.Buscar.addActionListener(this);
        this.pusuario.jTextBuscar.addFocusListener(this);
    }

    public void Datos() {
        java.util.Date fecha = pusuario.jDateNacimientoUsuario.getDate();
        Date sqlfecha = new Date(fecha.getTime());//Se realiza una conversion del tiempo en milisegundos

        usuarios.setCedula(pusuario.JCIUsuario.getText());
        usuarios.setNombre(pusuario.JNombreUsuario.getText());
        usuarios.setApellido(pusuario.JApellidoUsuario.getText());
        usuarios.setRuc(pusuario.jTexRucUsuario.getText());
        usuarios.setTelefono(pusuario.JTelefonoUsuario.getText());
        usuarios.setDireccion(pusuario.JDireccionUsuario.getText());
        usuarios.setCiudad(pusuario.jComboCiudad.getSelectedIndex());
        usuarios.setFecha_nacimiento(sqlfecha);
        usuarios.setEmail(pusuario.JEmailUsuario.getText());
        usuarios.setUsuario(pusuario.jTextUsuario.getText());
        usuarios.setContraseña(pusuario.jPasswordContraseña.getText());
        usuarios.setNivel(pusuario.jComboRol.getSelectedIndex());
    }

    public void GuardarDatosClientes() {
        Datos();
        if (consulta.VerificarDatoExistente(usuarios)) {//Verifica si los datos ingresados ya existen o no
            JOptionPane.showMessageDialog(null, "Los datos ingresados ya se encuentran en la Base de Datos");
        } else if (pusuario.jPasswordContraseña.getText().equals(pusuario.jPasswordValidar.getText())) {
            consulta.CargrDatosUsuario(usuarios);
            System.out.println("Se ha Gurardao Correctamente al Usuario " + pusuario.JNombreUsuario.getText());
        }else {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinsiden \n Intente de nuevo");
            DefaultTableModel modelo = (DefaultTableModel) pusuario.jTableUsuarios.getModel();
            modelo.setRowCount(0);
            pusuario.jTableUsuarios.setModel(modelo);
        }
    }

    //Eventos de Botones 
    @Override
    public void actionPerformed(ActionEvent e) {//Agregar nuevo Usuario
        if (e.getSource().equals(pusuario.Guardar)) {
            if (consulta.rellenar == false) {
                GuardarDatosClientes();
                pusuario.jTableUsuarios.setModel(consulta.ExtraerDatos());
            } else {
                JOptionPane.showMessageDialog(null, "Si desea guardar cambios click en 'Modificar'");
                JOptionPane.showMessageDialog(null, "Click en 'Nuevo' para cargar un nuevo Cliente");
            }
        }

        if (e.getSource().equals(pusuario.Eliminar)) {//Eliminar Usuario
            int fila = pusuario.jTableUsuarios.getSelectedRow();
            String ci = (String) pusuario.jTableUsuarios.getValueAt(fila, 0);
            String nombre = (String) pusuario.jTableUsuarios.getValueAt(fila, 1);
            int opcion = JOptionPane.showConfirmDialog(null, "Se eliminara permanentemente al cliente " + nombre
                    + "\n¿Desea continuar?");
            if (opcion == JOptionPane.YES_OPTION) {
                consulta.EliminarRegistro(ci);
                pusuario.jTableUsuarios.setModel(consulta.ExtraerDatos());
            }
        }

        if (e.getSource().equals(pusuario.Modificar)) {
            int fila = pusuario.jTableUsuarios.getSelectedRow();
            String ci = (String) pusuario.jTableUsuarios.getValueAt(fila, 0);
            Datos();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?");
            if (opcion == JOptionPane.YES_OPTION) {
                consulta.ModificarRegistro(usuarios, ci);
                pusuario.jTableUsuarios.setModel(consulta.ExtraerDatos());
            }
        }

        if (e.getSource().equals(pusuario.Nuevo)) {
            pusuario.JCIUsuario.setText(null);
            pusuario.JNombreUsuario.setText(null);
            pusuario.JApellidoUsuario.setText(null);
            pusuario.jTexRucUsuario.setText(null);
            pusuario.JDireccionUsuario.setText(null);
            pusuario.JTelefonoUsuario.setText(null);
            pusuario.jComboCiudad.setSelectedIndex(0);
            pusuario.JEmailUsuario.setText(null);
            pusuario.jDateNacimientoUsuario.setDate(null);
            pusuario.jTextUsuario.setText(null);
            pusuario.jPasswordContraseña.setText(null);
            pusuario.jPasswordValidar.setText(null);
            pusuario.jComboRol.setSelectedIndex(0);
            consulta.rellenar = false;
        }

        if (e.getSource().equals(pusuario.Buscar)) {
            if (pusuario.jTextBuscar.getText().equals("") || pusuario.jTextBuscar.getText().equals("Buscar...")) {
                JOptionPane.showMessageDialog(null, "Favor ingrese un nombre para realizar la busqueda");
            } else {
                String nombre = pusuario.jTextBuscar.getText();
                pusuario.jTableUsuarios.setModel(consulta.BuscarRegistro(nombre));
                if (pusuario.jTableUsuarios.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No hubo resultados para: " + nombre);
                    pusuario.jTableUsuarios.setModel(consulta.ExtraerDatos()
                    );
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int fila = pusuario.jTableUsuarios.getSelectedRow();
        if (fila >= 0) {
            String ci = (String) pusuario.jTableUsuarios.getValueAt(fila, 0);
            consulta.RellenarComponentes(pusuario, ci);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (pusuario.jTextBuscar.getText().equals("Buscar...")) {
            pusuario.jTextBuscar.setText("");
            pusuario.jTextBuscar.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (pusuario.jTextBuscar.getText().isEmpty()) {
            pusuario.jTextBuscar.setText("Buscar...");
            pusuario.jTextBuscar.setForeground(Color.LIGHT_GRAY);
        }
    }
}