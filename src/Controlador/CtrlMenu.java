package Controlador;

import Consultas.ConsulMenu;
import Modelo.Usuarios;
import Vista.PClientes;
import Vista.PVenta;
import Vista.PInicio;
import Vista.Menu;
import Vista.PCompra;
import Vista.Productos;
import Vista.PReportes;
import Vista.PUsuario;
import Vista.Proveedor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CtrlMenu implements MouseListener, ActionListener, MouseMotionListener {

    ConsulMenu consulta = new ConsulMenu();
    Menu menu = new Menu();
    PInicio inicio = new PInicio();
    PVenta facturacion = new PVenta();
    PClientes pclientes = new PClientes();
    Productos productos = new Productos();
    PReportes reportes = new PReportes();
    Proveedor proveedor = new Proveedor();
    PCompra compra = new PCompra();
    PUsuario usuario = new PUsuario();
    Usuarios user = new Usuarios();

    public void CambiarPanel(JPanel panel) {//Metodo para ir cambiando de paneles
        panel.setSize(1000, 490);
        panel.setLocation(0, 0);
        menu.PanelContenedor.removeAll();
        menu.PanelContenedor.add(panel, BorderLayout.CENTER);
        menu.PanelContenedor.revalidate();
        menu.PanelContenedor.repaint();
    }

    public void FechaActual() {
        LocalDate fecha = LocalDate.now();
        Locale español = new Locale("es", "PY");
        menu.Calendario.setText(fecha.format(DateTimeFormatter.ofPattern("EEEE dd 'de' MMMM 'del' YYYY   ", español)));
    }

    public CtrlMenu(Menu menu) {
        this.menu = menu;

        FechaActual();
        CambiarPanel(inicio);
        this.menu.jBInicio.addActionListener(this);
        this.menu.PUProveedores.addActionListener(this);
        this.menu.PUClientes.addActionListener(this);
        this.menu.PUArticulos.addActionListener(this);
        this.menu.jBReportes.addActionListener(this);
        this.menu.jBNuevo.addActionListener(this);
        this.menu.jBFacturacion.addActionListener(this);
        this.menu.PUVenta.addActionListener(this);
        this.menu.PUCompra.addActionListener(this);
        this.menu.PUUsuario.addActionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    private int MPosicionX, MPosicionY, ok = 0, VPosicionX, VPosicionY;

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menu.jBInicio)) {
            CambiarPanel(inicio);
        }
        if (e.getSource().equals(menu.PUProveedores)) {
            CambiarPanel(proveedor);
            menu.PUNuevo.setVisible(true);
            CtrlProveedor ctrproveedor = new CtrlProveedor(proveedor);
        }
        if (e.getSource().equals(menu.PUClientes)) {
            CambiarPanel(pclientes);
            menu.PUNuevo.setVisible(true);
            try {
                CtrlClientes ctrlclietes = new CtrlClientes(pclientes);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(menu.PUArticulos)) {
            CambiarPanel(productos);
            menu.PUNuevo.setVisible(true);
            CtrlProducto ctrlpruducto = new CtrlProducto(productos);
        }
        if (e.getSource().equals(menu.PUUsuario)) {
            try {
                consulta.VerificarNivel(user);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (user.getNivel() < 2) {
                CambiarPanel(usuario);
                menu.PUNuevo.setVisible(true);
                CtrlUsuario ctrlusuario = new CtrlUsuario(usuario);
            }else {
                JOptionPane.showMessageDialog(null, "Solo los Administradores pueden acceder a esta seccion");
            }
        }
        if (e.getSource().equals(menu.jBReportes)) {
            //CtrlReportes ctrlreportes = new CtrlReportes(reportes);
            CambiarPanel(reportes);
        }
        if (e.getSource().equals(menu.PUVenta)) {
            try {
                CambiarPanel(facturacion);
                menu.PUFacturacion.setVisible(true);
                CtrlPVenta venta = new CtrlPVenta(facturacion);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(menu.PUCompra)) {
            CambiarPanel(compra);
            menu.PUFacturacion.setVisible(true);
            try {
                CtrlCompra ctrlcompra = new CtrlCompra(compra);
            } catch (SQLException ex) {
                Logger.getLogger(CtrlMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(menu.jBNuevo)) {
            int y = menu.jBNuevo.getHeight();
            menu.PUNuevo.show(menu.jBNuevo, 0, y);
        }
        if (e.getSource().equals(menu.jBFacturacion)) {
            int y = menu.jBFacturacion.getHeight();
            menu.PUFacturacion.show(menu.jBFacturacion, 0, y);
        }
    }

    //MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {
        VPosicionX = e.getXOnScreen() - MPosicionX;
        VPosicionY = e.getYOnScreen() - MPosicionY;
        menu.setLocation(VPosicionX, VPosicionY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
