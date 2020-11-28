/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.controlador;

import ec.ups.edu.modelo.Persona;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author santi
 */
public class ControladorJuez extends ControladorAbstracto<Persona> {

    /*
    private String cedula; = 10
    private String nombre; = 15
    private String apellido; = 15
    private String direccion; = 50
    private String genero; = 10
    private Date fecha; = 30
    
    total registro = 142.
     */
    private RandomAccessFile archivo;
    private int salto;
    private int tamanioRegistro;

    private static ControladorJuez instancia;

    public static ControladorJuez getInstance() {

        if (instancia == null) {

            instancia = new ControladorJuez();

        }
        return instancia;
    }

    public ControladorJuez() {

        try {

            archivo = new RandomAccessFile("usaurio5.txt", "rw");

        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

    @Override
    public void create(Persona objeto) {

        salto = 0;
        
        try {

            archivo.seek(salto);
            archivo.writeUTF(objeto.getCedula());
            archivo.writeUTF(objeto.getNombre());
            archivo.writeUTF(objeto.getApellido());
            archivo.writeUTF(objeto.getDireccion());
            archivo.writeUTF(objeto.getGenero());
            archivo.writeUTF(objeto.getFecha() + "");

        } catch (IOException ex) {

            System.out.println("Error de lectura y escritura");
            ex.printStackTrace();

        }

    }

     @Override
    public Persona read(String cedula) {

        salto = 0;
        tamanioRegistro = 142;

        try {

            if (salto < archivo.length()) {

                archivo.seek(salto);
                String cedulaArchivo = archivo.readUTF();

                if (cedula.equals(cedulaArchivo)) {

                    String nombre = archivo.readUTF().trim();
                    String apellido = archivo.readUTF().trim();
                    String direccion = archivo.readUTF().trim();
                    String genero = archivo.readUTF().trim();
                    String fecha = archivo.readUTF().trim();

                      int dia = Integer.parseInt(fecha.substring(7,10));
                    int anio = Integer.parseInt(fecha.substring(24,28));
                    int mes  = Integer.parseInt(fecha.substring(3,7));
                    
                    Date date = new Date(anio-1900,mes,dia);
                    
                    Persona persona = new Persona(cedula, nombre, apellido, direccion, genero, date);

                    return persona;

                }

                salto += tamanioRegistro;

            }

        } catch (IOException ex) {

            System.out.println("Error de lectura y escritura");
            ex.printStackTrace();

        }

        return null;

    }

    @Override
    public boolean update(Persona objeto) {

        salto = 0;
        tamanioRegistro = 142;

        try {

            if (salto < archivo.length()) {

                archivo.seek(salto);
                archivo.writeUTF(objeto.getCedula());
                archivo.writeUTF(objeto.getNombre());
                archivo.writeUTF(objeto.getApellido());
                archivo.writeUTF(objeto.getDireccion());
                archivo.writeUTF(objeto.getGenero());
                archivo.writeUTF(objeto.getFecha() + "");

            }

            salto += tamanioRegistro;

        } catch (IOException ex) {

            ex.printStackTrace();

        }

        return false;

    }

    @Override
    public boolean delete(Persona objeto) {

        try {

            salto = 0;
            tamanioRegistro = 142;
            String cadena = "";

            if (salto < archivo.length()) {

                archivo.seek(salto);
                archivo.writeUTF(String.format("%-" + 10 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 15 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 15 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 50 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 10 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 30 + "s", cadena));

            }

            salto += tamanioRegistro;

        } catch (IOException ex) {

            System.out.println("Error de lectura y escritura.");
            ex.printStackTrace();

        }

        return false;

    }

    @Override
    public List<Persona> lista() {

        List<Persona> lista = new ArrayList<Persona>();
        int salto = 0;
        int registro = 142;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);

                String cedula = archivo.readUTF().trim();
                if (!cedula.equals("")) {

                    String nombre = archivo.readUTF().trim();
                    String apellido = archivo.readUTF().trim();
                    String direccion = archivo.readUTF().trim();
                    String genero = archivo.readUTF().trim();
                    String fecha = archivo.readUTF().trim();

                    System.out.println(fecha);
                    
                      int dia = Integer.parseInt(fecha.substring(8,10));
                    int anio = Integer.parseInt(fecha.substring(24,28));
                    int mes  = recuperarMes(fecha.substring(4,7));
                    
                    Date date = new Date(anio-1900,mes,dia);
                    
                    Persona persona = new Persona(cedula, nombre, apellido, direccion, genero,date);
                    lista.add(persona);
                    
                }

                salto += registro;

            }

        } catch (IOException ex) {
            System.out.println("Error lectrura escritura (UsuarioDao:Update)");
            ex.printStackTrace();
        }
        return lista;

    }
    
      public int recuperarMes(String mes){
        
        String meses[] = {"Jan","Feb","Mar","April","May","June","Jul","Aug","Sep","Oct","Nov","Dec"};
        
        for (int i = 0; i < 10; i++) {
            
            if (meses[i].equals(mes)) {
                
                return i;
                
            }
            
        }
        
        return 0;
        
    }
    
}


