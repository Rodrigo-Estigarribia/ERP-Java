package Controlador;

import Consultas.ConsulLogin;
import Modelo.Usuarios;
import Vista.Login;
import Vista.Menu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlLogin implements ActionListener, KeyListener, MouseListener, MouseMotionListener, FocusListener {

    ConsulLogin consultas = new ConsulLogin();
    Login login = new Login();

    public CtrlLogin(Login inicio) {
        this.login = inicio;
       
        this.login.Acceder.addActionListener(this);
        this.login.txtusuario.addKeyListener(this);
        this.login.jpassword.addKeyListener(this);
        this.login.x.addMouseListener(this);
        this.login.barra.addMouseListener(this);
        this.login.barra.addMouseMotionListener(this);
        this.login.txtusuario.addFocusListener(this);
    }

    public void IniciarSesion() throws SQLException {
        Usuarios usuario = new Usuarios();
        Usuarios.setUsuario(login.txtusuario.getText());
        usuario.setContraseña(login.jpassword.getText());
        Menu menu = new Menu();

        if (Usuarios.getUsuario().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese su usuario para poder continuar");
        } else if (usuario.getContraseña().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese la contraseña para poder continuar");

        } else if (consultas.validacion(usuario) == true){
            CtrlMenu ctlrmenu = new CtrlMenu(menu);
            menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
            login.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "El usuario o la contraseña no son correctas");
        }
    }

    //Evento que raaciona a una accion hacia el boton Acceder
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.Acceder) {
            try {
                IniciarSesion();
            } catch (SQLException ex) {
                Logger.getLogger(CtrlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Eventos de tipo KeyEvents, recibe instruccione al cliquear alguna tecla del teclado
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER && !"".equals(login.txtusuario.getText())) {
            login.jpassword.requestFocus();
        } else if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                IniciarSesion();
            } catch (SQLException ex) {
                Logger.getLogger(CtrlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getKeyChar() == KeyEvent.VK_ENTER && e.getSource() == login.jpassword) {
            try {
                IniciarSesion();
            } catch (SQLException ex) {
                Logger.getLogger(CtrlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Eventos de tipo MouseListener, detecta acciones que se realiza con el mouse
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(login.x)) {
            System.exit(0);
        }
    }

    private int MPosicionX, MPosicionY, VPosicionX, VPosicionY, ok = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        int ClikIzquierdo = e.getButton();
        if (ClikIzquierdo == MouseEvent.BUTTON1 && e.getSource().equals(login.barra)) {
            MPosicionX = e.getX();
            MPosicionY = e.getY();
            ok = 1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(login.x)) {
            login.x.setBackground(Color.RED);
            login.x.setForeground(Color.WHITE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(login.x)) {
            login.x.setBackground(Color.WHITE);
            login.x.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        if (ok == 1) {
            VPosicionX = e.getXOnScreen() - MPosicionX;
            VPosicionY = e.getYOnScreen() - MPosicionY;
            login.setLocation(VPosicionX, VPosicionY);            
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (login.txtusuario.getText().equals("Username")) {
            login.txtusuario.setText("");
            login.txtusuario.setForeground(Color.BLACK);
        }
        if (login.jpassword.getText().equals("Password")) {
            login.jpassword.setText("");
            login.jpassword.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (login.txtusuario.getText().isEmpty()) {
            login.txtusuario.setText("Username");
            login.txtusuario.setForeground(Color.LIGHT_GRAY);
        }
        if (login.jpassword.getText().isEmpty()) {
            login.jpassword.setText("Password");
            login.jpassword.setForeground(Color.LIGHT_GRAY);
        }
    }
}
