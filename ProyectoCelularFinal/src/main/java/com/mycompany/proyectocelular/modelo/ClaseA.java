package com.mycompany.proyectocelular.modelo;

/**
 *
 * @author ASUS
 */
public class ClaseA {
    
    private static final int TAM_MODELO = 20;
    private static final int TAM_ESTADO = 10;
    private static final int TAM_REGISTRO = 73;
    
    
    private int imei;
    private String modelo;
    private double precio;
    private boolean tiene5G;
    private String estado;
    private int nit;

    public ClaseA (int imei, String modelo, double precio, boolean tiene5G, String estado, int nit) {
        this.imei = imei;
        this.modelo = modelo;
        this.precio = precio;
        this.tiene5G = tiene5G;
        this.estado = estado;
        this.nit = nit;
    }

    public int getImei() {
        return imei;
    }

    public void setImei(int emai) {
        this.imei = emai;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean getTiene5G() {
        return tiene5G;
    }

    public void setTiene5G(boolean tiene5G) {
        this.tiene5G = tiene5G;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }
    
    
    
}

