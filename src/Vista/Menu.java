package Vista;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;

public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PUNuevo = new javax.swing.JPopupMenu();
        PUUsuario = new javax.swing.JMenuItem();
        PUClientes = new javax.swing.JMenuItem();
        PUProveedores = new javax.swing.JMenuItem();
        PUArticulos = new javax.swing.JMenuItem();
        PUFacturacion = new javax.swing.JPopupMenu();
        PUCompra = new javax.swing.JMenuItem();
        PUVenta = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        PanelContenedor = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jBInicio = new javax.swing.JButton();
        jBReportes = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jBNuevo = new javax.swing.JButton();
        jBFacturacion = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Calendario = new javax.swing.JLabel();

        PUNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PUNuevo.setPreferredSize(new java.awt.Dimension(190, 132));

        PUUsuario.setText("USUARIO");
        PUNuevo.add(PUUsuario);

        PUClientes.setText("CLIENTES");
        PUNuevo.add(PUClientes);

        PUProveedores.setText("PROVEEDORES");
        PUNuevo.add(PUProveedores);

        PUArticulos.setText("ARTICULOS");
        PUNuevo.add(PUArticulos);

        PUFacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PUFacturacion.setPreferredSize(new java.awt.Dimension(190, 80));

        PUCompra.setText("COMPRA");
        PUFacturacion.add(PUCompra);

        PUVenta.setText("VENTA");
        PUFacturacion.add(PUVenta);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 500));

        PanelContenedor.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(25, 159, 177));
        jPanel2.setPreferredSize(new java.awt.Dimension(204, 224));

        jBInicio.setBackground(new java.awt.Color(165, 209, 225));
        jBInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBInicio.setForeground(new java.awt.Color(255, 255, 255));
        jBInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/iconos_menu/casa.png"))); // NOI18N
        jBInicio.setText("INICO");
        jBInicio.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(255, 0, 0)));
        jBInicio.setBorderPainted(false);
        jBInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBInicio.setIconTextGap(10);
        jBInicio.setOpaque(true);

        jBReportes.setBackground(new java.awt.Color(165, 209, 225));
        jBReportes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBReportes.setForeground(new java.awt.Color(255, 255, 255));
        jBReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/iconos_menu/registros.png"))); // NOI18N
        jBReportes.setText("REPORTES");
        jBReportes.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        jBReportes.setBorderPainted(false);
        jBReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBReportes.setIconTextGap(10);
        jBReportes.setOpaque(true);

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jBNuevo.setBackground(new java.awt.Color(165, 209, 225));
        jBNuevo.setForeground(new java.awt.Color(255, 255, 255));
        jBNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/iconos_menu/base-de-datos.png"))); // NOI18N
        jBNuevo.setText("NUEVO");
        jBNuevo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        jBNuevo.setBorderPainted(false);
        jBNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jBNuevo.setIconTextGap(10);

        jBFacturacion.setBackground(new java.awt.Color(165, 209, 225));
        jBFacturacion.setForeground(new java.awt.Color(255, 255, 255));
        jBFacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/iconos_menu/factura.png"))); // NOI18N
        jBFacturacion.setText("FACTURACION");
        jBFacturacion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        jBFacturacion.setBorderPainted(false);
        jBFacturacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBFacturacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBFacturacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jBFacturacion.setIconTextGap(10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jBInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jBFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jBReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jBNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jBFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jBReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(62, 125, 149));

        Calendario.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Calendario.setForeground(new java.awt.Color(255, 255, 255));
        Calendario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Calendario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Calendario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Calendario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(809, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(PanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(PanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        UIManager.put("Button.arc", 999);
        FlatMacLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Calendario;
    public javax.swing.JMenuItem PUArticulos;
    public javax.swing.JMenuItem PUClientes;
    public javax.swing.JMenuItem PUCompra;
    public javax.swing.JPopupMenu PUFacturacion;
    public javax.swing.JPopupMenu PUNuevo;
    public javax.swing.JMenuItem PUProveedores;
    public javax.swing.JMenuItem PUUsuario;
    public javax.swing.JMenuItem PUVenta;
    public javax.swing.JPanel PanelContenedor;
    public javax.swing.JButton jBFacturacion;
    public javax.swing.JButton jBInicio;
    public javax.swing.JButton jBNuevo;
    public javax.swing.JButton jBReportes;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
