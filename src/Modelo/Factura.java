package Modelo;

import java.sql.Date;

public class Factura {

    private int ID_FACTURA;
    private Date FECHA;
    private int ID_CLIENTE;
    private int ID_TIPO_FACTURA;
    private int ID_USUARIO;
    private int NUMERO_FACTURA;

    public int getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(int ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }

    public Date getFECHA() {
        return FECHA;
    }

    public void setFECHA(Date FECHA) {
        this.FECHA = FECHA;
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public int getID_TIPO_FACTURA() {
        return ID_TIPO_FACTURA;
    }

    public void setID_TIPO_FACTURA(int ID_TIPO_FACTURA) {
        this.ID_TIPO_FACTURA = ID_TIPO_FACTURA;
    }

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public int getNUMERO_FACTURA() {
        return NUMERO_FACTURA;
    }

    public void setNUMERO_FACTURA(int NUMERO_FACTURA) {
        this.NUMERO_FACTURA = NUMERO_FACTURA;
    }

    
}
