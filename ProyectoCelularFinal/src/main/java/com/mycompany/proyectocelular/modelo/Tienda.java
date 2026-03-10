package com.mycompany.proyectocelular.modelo;

import java.io.File;

/**
 *
 * @author ASUS
 */

public class Tienda {
    private static final int TAM_NOMBRE = 20;
    private static final int TAM_REGISTRO = 26;
    
    
    private int nit;
    private String nombre;
    private String estado;

        
    public Tienda(int nit, String nombre, String estado) {
            this.nit = nit;
            this.nombre = nombre;
            this.estado = estado;
        }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
   
    }

    

    

