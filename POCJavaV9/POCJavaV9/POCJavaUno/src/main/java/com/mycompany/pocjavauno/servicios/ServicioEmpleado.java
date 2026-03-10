/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pocjavauno.servicios;

import com.mycompany.pocjavauno.gui.GUIPrincipal;
import com.mycompany.pocjavauno.model.Empleado;
import com.mycompany.pocjavauno.model.Empresa;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Unibague
 */
public class ServicioEmpleado {
 
    public static final int TAM_NOMBRE = 20;
    public static final int TAM_GENERO = 9;
    public static final int TAM_REGISTRO = 49;
    
    public static String adjustStringLength(String input, int n) {
        if (input == null) {
            return " ".repeat(n);
        }

        if (input.length() > n) {
            return input.substring(0, n);
        } else {
            return String.format("%-" + n + "s", input);
        }
    }
    
    public static Empleado buscarEmpleado(int pCodigo){
        String nombre;
        String genero;
        int codigo;
        double salario;
        int nit;
        Empleado emp = null;
        
        try {
            RandomAccessFile file = new RandomAccessFile("data//ejemplo.txt", "rw");
            //Se posiciona al inicio del archivo
            file.seek(0);
            while(file.getFilePointer() < file.length()){
                //lee los datos del archivo y los almacena en tres variables diferentes
                codigo = file.readInt();
                nombre = file.readUTF().trim();
                genero = file.readUTF().trim();
                salario = file.readDouble();
                nit = file.readInt();
                
                if (codigo == pCodigo) {
                    //Crea un objeto de tipo Empleado (emp) a partir de los valores leidos del archivo. 
                    emp = new Empleado(codigo, nombre, genero, salario, nit);
                 
                    file.close();
                    return emp;
                }
            }
            file.close();
        } catch (Exception ex) {
            System.out.println("Error! " + ex);
        }

        return null;
    }


    public static boolean actualizarEmpleadoParcial(int pCodigo){
        String nombre;
        String genero;
        int codigo;
        double salario;
        int nit;
        
        try {
            RandomAccessFile file = new RandomAccessFile("data//ejemplo.txt", "rw");
            //Se posiciona al inicio del archivo
            file.seek(0);
            while(file.getFilePointer() < file.length()){
                //lee los datos del archivo y los almacena en tres variables diferentes
                codigo = file.readInt();
                nombre = file.readUTF().trim();
                genero = file.readUTF().trim();
                salario = file.readDouble();
                nit = file.readInt();
                
                if (codigo == pCodigo) {
                    file.seek(file.getFilePointer() - TAM_REGISTRO);
                    file.writeInt(codigo);
                    file.writeUTF(adjustStringLength(nombre, TAM_NOMBRE));
                    file.writeUTF(adjustStringLength(genero, TAM_GENERO));
                    file.writeDouble(salario * 1.10);
                    file.writeInt(nit);
                    file.close();
                    return true;
                }
            }
            file.close();
        } catch (Exception ex) {
            System.out.println("Error! " + ex);
        }

        return false;
    }


    
    public static boolean actualizarEmpleado(Empleado emp){
        String nombre;
        String genero;
        int codigo;
        double salario;
        int nit;
        
        try {
            RandomAccessFile file = new RandomAccessFile("data//ejemplo.txt", "rw");
            //Se posiciona al inicio del archivo
            file.seek(0);
            while(file.getFilePointer() < file.length()){
                //lee los datos del archivo y los almacena en tres variables diferentes
                codigo = file.readInt();
                nombre = file.readUTF().trim();
                genero = file.readUTF().trim();
                salario = file.readDouble();
                nit = file.readInt();
                
                if (codigo == emp.getCodigo()) {
                    file.seek(file.getFilePointer() - TAM_REGISTRO);
                    file.writeInt(emp.getCodigo());
                    file.writeUTF(adjustStringLength(emp.getNombre(), TAM_NOMBRE));
                    file.writeUTF(adjustStringLength(emp.getGenero(), TAM_GENERO));
                    file.writeDouble(emp.getSalario());
                    file.writeInt(nit);
                     
                    file.close();
                    return true;
                }
            }
            file.close();
        } catch (Exception ex) {
            System.out.println("Error! " + ex);
        }

        return false;
    }
    
    public static boolean guardarEnArchivo(Empleado emp){
        
        Empleado buscado = buscarEmpleado(emp.getCodigo());

        if(buscado != null){
            return false;
        }
        
        ////////
        //Validar FK
        Empresa buscada = ServicioEmpresa.buscarEmpresa(emp.getNit());
        if (buscada == null){
            return false;
        }
        ///////
        
        try {
            RandomAccessFile file = new RandomAccessFile("data//ejemplo.txt", "rw");            
            file.seek(file.length());
            file.writeInt(emp.getCodigo());
            file.writeUTF(adjustStringLength(emp.getNombre(), TAM_NOMBRE));
            file.writeUTF(adjustStringLength(emp.getGenero(), TAM_GENERO));
            file.writeDouble(emp.getSalario());
            file.writeInt(emp.getNit());
            file.close();
        } catch (Exception ex) {
            Logger.getLogger(GUIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    public static List getEmpleados(){
        //Se declara una variable llamada empleados de tipo ArrayList que permite "almacenar" objetos de tipo empleado
        List <Empleado> empleados = new ArrayList();
        String nombre;
        String genero;
        int codigo;
        double salario;
        int nit;
        //Variable de tipo empleado - NO hay objeto aún
        Empleado emp = null;
        
        try {
            RandomAccessFile file = new RandomAccessFile("data//ejemplo.txt", "rw");
            //Se posiciona al inicio del archivo
            file.seek(0);
            while(file.getFilePointer() < file.length()){
                //lee los datos del archivo y los almacena en tres variables diferentes
                codigo = file.readInt();
                nombre = file.readUTF().trim();
                genero = file.readUTF().trim();
                salario = file.readDouble();
                nit = file.readInt();
                //Crea un objeto de tipo Empleado (emp) a partir de los valores leidos del archivo. 
                emp = new Empleado(codigo, nombre, genero, salario, nit);
                //Se adiciona al ArrayList empleados el empleado leido
                empleados.add(emp);
            }
            file.close();
        } catch (Exception ex) {
            System.out.println("Error! " + ex);
        }

        return empleados;
    }
}
