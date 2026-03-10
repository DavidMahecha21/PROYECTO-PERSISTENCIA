/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectocelular.servicio;

import com.mycompany.proyectocelular.gui.GUIPrincipal;
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
public class ServicioTienda 
{
    private static final String NOM_ARCHIVO = "data//registroTiendas.txt";
    
    public static void escribirNombre(RandomAccessFile file, String nombre) throws Exception 
    {
        int i;

        for(i = 0; i < nombre.length(); i++) 
        {

            file.writeChar(nombre.charAt(i));

        }

        for(i = nombre.length(); i < 20; i++) 
        {

            file.writeChar(' ');

        }

    }

    public static String leerNombre(RandomAccessFile file) throws Exception 
    {

        String nombre = "";

        for(int i = 0; i < 20; i++) 
        {

            nombre += file.readChar();

        }

        return nombre.trim();

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
    
    public static boolean guardarEnArchivo(Tienda tie)
    {
        try 
        {
            RandomAccessFile file = new RandomAccessFile(NOM_ARCHIVO, "rw");
            file.seek(file.length());
            file.writeInt(tie.getNit());
            escribirNombre(file, tie.getNombre());
            escribirEstado(file, tie.getEstado());         
            file.close();
        }catch (Exception ex) 
        {
            Logger.getLogger(GUIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
         return true;
    }
       public static List getTiendas()
       {
        List <Tienda> tiendas = new ArrayList();
        int nit;
        String nombre;
        String estado;
        Tienda tie = null;
        
        try {
            RandomAccessFile file = new RandomAccessFile(NOM_ARCHIVO, "rw");
        
            file.seek(0);
            while(file.getFilePointer() < file.length())
            {
                nit = file.readInt();
                nombre = leerNombre(file);
                estado = leerEstado(file);
                 
                tie = new Tienda(nit, nombre, estado);
                tiendas.add(tie);
            }
            file.close();
        } catch (Exception ex) 
        {
            System.out.println("Error! " + ex);
        }

        return tiendas;
    }
       
    public static boolean eliminarTienda(int nitEliminar)
    {
        try 
        {
            List<Tienda> lista = getTiendas();
            RandomAccessFile file = new RandomAccessFile(NOM_ARCHIVO, "rw");
            
            file.setLength(0);
            
            for(Tienda t : lista) {
                if (t.getNit() != nitEliminar) 
                {
                    file.writeInt(t.getNit());
                    escribirNombre(file, t.getNombre());
                    escribirEstado(file, t.getEstado());
                }
            }
            file.close();
            return true;
        }catch (Exception e) 
        {
            return false;
        }
    }
    
    public static boolean actualizarTienda(Tienda tieActualizado) 
    {

    try {
        List<Tienda> lista = getTiendas();
        RandomAccessFile file = new RandomAccessFile(NOM_ARCHIVO, "rw");

        file.setLength(0); 
        for (Tienda t : lista) {

            if (t.getNit() == tieActualizado.getNit()) {
                
                t.setNombre(tieActualizado.getNombre());
                t.setEstado(tieActualizado.getEstado());
            }

            file.writeInt(t.getNit());
            escribirNombre(file, t.getNombre());
            escribirEstado(file, t.getEstado());
        }

        file.close();
        return true;

    } catch (Exception e) {
        return false;
    }
    }
    public static Tienda buscarTienda(int nitBuscar)
    {
        try {
            RandomAccessFile file = new RandomAccessFile(NOM_ARCHIVO, "rw");
            
            while(file.getFilePointer() < file.length())
            {
                int nit = file.readInt();
                String nombre = leerNombre(file);
                String estado = leerEstado(file);

                if(nit == nitBuscar)
                {
                    file.close();
                    return new Tienda(nit, nombre, estado);
                }
            }

            file.close();

        } catch(Exception e) 
        {
        return null;
        }

        return null;
    }
}
