/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pocjavauno.model;

/**
 *
 * @author ASUS
 */
public class Empleado {

    private int codigo;
    private String nombre;
    private String genero;
    private double salario;
    private int nit;

    public Empleado(int codigo, String nombre, String genero, double salario, int nit) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.salario = salario;
        this.nit = nit;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
