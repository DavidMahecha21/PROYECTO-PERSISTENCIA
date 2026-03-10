/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pocjavauno.model;

/**
 *
 * @author Unibague
 */
public class Empresa {
    private int nit;
    private String nombre;

    public Empresa(int nit, String nombre) {
        this.nit = nit;
        this.nombre = nombre;
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
    
    
}
