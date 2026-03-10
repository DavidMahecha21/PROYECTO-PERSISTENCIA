/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectocelular.servicio;

import com.mycompany.proyectocelular.gui.GUIPrincipal;
import com.mycompany.proyectocelular.modelo.ClaseA;
import com.mycompany.proyectocelular.modelo.Tienda;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Mahecha
 */
public class ServicioClaseA 
{
    public static void escribirModelo(RandomAccessFile file, String modelo) throws Exception 
    {
        int i;

        for(i = 0; i < modelo.length(); i++) 
        {

            file.writeChar(modelo.charAt(i));

        }

        for(i = modelo.length(); i < 20; i++) 
        {

            file.writeChar(' ');

        }

    }

    public static String leerModelo(RandomAccessFile file) throws Exception 
    {

        String modelo = "";

        for(int i = 0; i < 20; i++) 
        {

            modelo += file.readChar();

        }

        return modelo.trim();

    }

    public static void escribirEstado(RandomAccessFile file, String estado) throws Exception 
    {

        int i;

        for(i = 0; i < estado.length(); i++) 
        {

            file.writeChar(estado.charAt(i));

        }

        for(i = estado.length(); i < 10; i++) 
        {

            file.writeChar(' ');

        }

    }

    public static String leerEstado(RandomAccessFile file) throws Exception 
    {
        String estado = "";

        for(int i = 0; i < 10; i++) 
        {

         estado += file.readChar();

        }
        return estado.trim();

    }
    
    public static boolean guardarEnArchivo(ClaseA cel)
    {
        
        ClaseA buscado = buscarCelular(cel.getImei());

        if(buscado != null){
            return false;
        }
        

        //Validar FK
        Tienda buscada = ServicioTienda.buscarTienda(cel.getNit());
        if (buscada == null){
            return false;
        }
       
        
        try 
        {
            RandomAccessFile file = new RandomAccessFile("data//registroCelulares.txt", "rw");
            file.seek(file.length());
            file.writeInt(cel.getImei());
            escribirModelo(file, cel.getModelo());
            file.writeDouble(cel.getPrecio());
            file.writeBoolean(cel.getTiene5G());
            escribirEstado(file, cel.getEstado());
            file.writeInt(cel.getNit());
            
            file.close();
        }catch (Exception ex) 
        {
            Logger.getLogger(GUIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
         return true;
    }
       public static List getCelulares()
       {
        List <ClaseA> celulares = new ArrayList();
        int imei;
        String modelo;
        double precio;
        boolean tiene5G;
        String estado;
        int nit;
        ClaseA cel = null;
        
        try {
            RandomAccessFile file = new RandomAccessFile("data//registroCelulares.txt", "rw");
        
            file.seek(0);
            while(file.getFilePointer() < file.length())
            {
                imei = file.readInt();
                modelo = leerModelo(file);
                precio = file.readDouble();
                tiene5G = file.readBoolean();
                estado = leerEstado(file);
                nit = file.readInt();
                 
                cel = new ClaseA(imei, modelo, precio, tiene5G, estado, nit);
           
                celulares.add(cel);
            }
            file.close();
        } catch (Exception ex) 
        {
            System.out.println("Error! " + ex);
        }

        return celulares;
    }
       
    public static boolean eliminarCelular(int imeiEliminar)
    {
        try 
        {
            List<ClaseA> lista = getCelulares();
            RandomAccessFile file = new RandomAccessFile("data//registroCelulares.txt", "rw");
            
            file.setLength(0);
            
            for(ClaseA c : lista) {
                if (c.getImei() != imeiEliminar) 
                {
                    file.writeInt(c.getImei());
                    escribirModelo(file, c.getModelo());
                    file.writeDouble(c.getPrecio());
                    file.writeBoolean(c.getTiene5G());
                    escribirEstado(file, c.getEstado());
                    file.writeInt(c.getNit());
                }
            }
            file.close();
            return true;
        }catch (Exception e) 
        {
            return false;
        }
    }
    
    public static boolean actualizarCelular(ClaseA celActualizado) 
    {

    try {
        List<ClaseA> lista = getCelulares();
        RandomAccessFile file = new RandomAccessFile("data//registroCelulares.txt", "rw");

        file.setLength(0); 
        for (ClaseA c : lista) {

            if (c.getImei() == celActualizado.getImei()) {
                
                c.setModelo(celActualizado.getModelo());
                c.setPrecio(celActualizado.getPrecio());
                c.setTiene5G(celActualizado.getTiene5G());
                c.setEstado(celActualizado.getEstado());
                c.setNit(celActualizado.getNit());
            }

            file.writeInt(c.getImei());
            escribirModelo(file, c.getModelo());
            file.writeDouble(c.getPrecio());
            file.writeBoolean(c.getTiene5G());
            escribirEstado(file, c.getEstado());
            file.writeInt(c.getNit());
        }

        file.close();
        return true;

    } catch (Exception e) {
        return false;
    }
    }
    public static ClaseA buscarCelular(int imeiBuscar)
    {
        try {
            RandomAccessFile file = new RandomAccessFile("data//registroCelulares.txt", "rw");
            
            while(file.getFilePointer() < file.length())
            {
                int imei = file.readInt();
                String modelo = leerModelo(file);
                double precio = file.readDouble();
                boolean tiene5G = file.readBoolean();
                String estado = leerEstado(file);
                int nit = file.readInt();

                if(imei == imeiBuscar)
                {
                    file.close();
                    return new ClaseA(imei, modelo, precio, tiene5G, estado, nit);
                }
            }

            file.close();

        } catch(Exception e) 
        {
        return null;
        }

        return null;
    }
    
    public static double sumaPrecios()
    {
        double suma = 0;

        List<ClaseA> lista = getCelulares();

        for(ClaseA c : lista)
        {
            suma = suma + c.getPrecio();
        }

        return suma;
    }
}
