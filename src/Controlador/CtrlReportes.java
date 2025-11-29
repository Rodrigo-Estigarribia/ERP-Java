/*package Controlador;

import Consultas.ConsulReportes;
import Vista.PReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CtrlReportes implements ActionListener{

    PReportes presportes = new PReportes();
    ConsulReportes consulta = new ConsulReportes();

    public CtrlReportes(PReportes presportes){
        this.presportes = presportes;
        this.presportes.consultar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(presportes.consultar)) {
            try {
                consulta.reporte();
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

}*/
