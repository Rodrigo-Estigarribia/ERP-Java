package Main;

import Controlador.CtrlLogin;
import Controlador.CtrlMenu;
import Modelo.Usuarios;
import Vista.Login;
import Vista.Menu;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;

public class main {

    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        UIManager.put("Button.arc", 999);

        //Usuarios usuario = new Usuarios();
        Login login = new Login();

        CtrlLogin contl = new CtrlLogin(login);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
}
