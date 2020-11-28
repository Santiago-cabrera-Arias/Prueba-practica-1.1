/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.controlador;

import ec.ups.edu.modelo.Matrimonio;
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
public class ControladorMatrimonio extends ControladorAbstracto<Matrimonio> {

    /*
      private String codigo; = 5
    private Date fecha;  =  30
    private String lugarDeCelebracion; =30
    private Persona contribuyente1; = 10
    private Persona contribuyente2; = 10
    private Persona testigo1; = 10
    private Persona testigo2; = 10
    private Persona juez; = 10
    
    total = 131
    
     */
    private static ControladorMatrimonio instancia;

    public static ControladorMatrimonio getInstance() {

        if (instancia == null) {

            instancia = new ControladorMatrimonio();

        }
        return instancia;
    }

    private RandomAccessFile archivo;
    private int salto;
    private int tamanioRegistro;

    public ControladorMatrimonio() {

        try {

            archivo = new RandomAccessFile("usaurio4.txt", "rw");

        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

    @Override
    public void create(Matrimonio objeto) {

        salto = 0;

        try {

            archivo.seek(salto);
            archivo.writeUTF(objeto.getCodigo());
            archivo.writeUTF(objeto.getFecha() + "");
            archivo.writeUTF(objeto.getLugarDeCelebracion());
            archivo.writeUTF(objeto.getContribuyente1().getCedula());
            archivo.writeUTF(objeto.getContribuyente2().getCedula());
            archivo.writeUTF(objeto.getTestigo1().getCedula());
            archivo.writeUTF(objeto.getTestigo2().getCedula());
            archivo.writeUTF(objeto.getJuez().getCedula());

        } catch (IOException ex) {

            System.out.println("Error de lectura y escritura");
            ex.printStackTrace();

        }

    }
    
    public Persona contribuyente(String codigo){
        
        ControladorPersona controladorPersona = ControladorPersona.getInstance();
        
        Persona persona = controladorPersona.read(codigo);
        
        return persona;
        
    }
    
    public Persona Testigo(String cedula){
        
        ControladorTestigos controladorTestigo = ControladorTestigos.getInstance();
        
        Persona testigo =  controladorTestigo.read(cedula);
        
        return testigo;
        
    }
    
    public Persona Juezz(String cedula){
        
        ControladorJuez controladorJuez = ControladorJuez.getInstance();
        
        Persona juez = controladorJuez.read(cedula);
        
        return juez;
        
    }
            
            
            

    @Override
    public Matrimonio read(String codigo) {

        salto = 0;
        tamanioRegistro = 131;

        try {

            if (salto < archivo.length()) {

                archivo.seek(salto);

                String codigoArchivo = archivo.readUTF();

                if (codigo.equals(codigoArchivo)) {

                    String fecha = archivo.readUTF();
                    String lugarDeCeremonia = archivo.readUTF();
                    String contribuyente1 = archivo.readUTF();
                    String contribuyente2 = archivo.readUTF();
                    String testigo1 = archivo.readUTF();
                    String testigo2 = archivo.readUTF();
                    String juez = archivo.readUTF();

                    int dia = Integer.parseInt(fecha.substring(7, 10));
                    int anio = Integer.parseInt(fecha.substring(24, 28));
                    int mes = Integer.parseInt(fecha.substring(3, 7));

                    Date date = new Date(anio - 1900, mes, dia);

                    Matrimonio matrimonio = new Matrimonio(codigo, date, lugarDeCeremonia, 
                            contribuyente(contribuyente1), contribuyente(contribuyente2), 
                            Testigo(testigo1),Testigo(testigo2) , Juezz(juez));

                    return matrimonio;

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
    public boolean update(Matrimonio objeto) {

        salto = 0;
        tamanioRegistro = 131;

        try {

            if (salto < archivo.length()) {

                archivo.seek(salto);
                archivo.writeUTF(objeto.getFecha() + "");
                archivo.writeUTF(objeto.getLugarDeCelebracion());
                archivo.writeUTF(objeto.getContribuyente1() + "");
                archivo.writeUTF(objeto.getContribuyente2() + "");
                archivo.writeUTF(objeto.getJuez() + "");
                archivo.writeUTF(objeto.getTestigo1() + "");
                archivo.writeUTF(objeto.getTestigo2() + "");

            }

            salto += tamanioRegistro;

        } catch (IOException ex) {

            ex.printStackTrace();

        }

        return false;

    }

    @Override
    public boolean delete(Matrimonio objeto) {

        try {

            salto = 0;
            tamanioRegistro = 131;
            String cadena = "";

            if (salto < archivo.length()) {

                archivo.seek(salto);
                archivo.writeUTF(String.format("%-" + 5 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 30 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 30 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 10 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 10 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 10 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 10 + "s", cadena));
                archivo.writeUTF(String.format("%-" + 10 + "s", cadena));

            }

            salto += tamanioRegistro;

        } catch (IOException ex) {

            System.out.println("Error de lectura y escritura.");
            ex.printStackTrace();

        }

        return false;

    }

    @Override
    public List<Matrimonio> lista() {

        List<Persona> lista = new ArrayList<Persona>();
        int salto = 0;
        int registro = 131;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);

                String cedula = archivo.readUTF().trim();
                if (!cedula.equals("")) {

                    String fecha = archivo.readUTF().trim();
                    String lugarCelebracion = archivo.readUTF().trim();
                    String contribuyente1 = archivo.readUTF().trim();
                    String contribuyente2 = archivo.readUTF().trim();
                    String testigo1 = archivo.readUTF().trim();
                    String testigo2 = archivo.readUTF().trim();
                    String juez = archivo.readUTF().trim();

                      int dia = Integer.parseInt(fecha.substring(7,10));
                    int anio = Integer.parseInt(fecha.substring(24,28));
                    int mes  = Integer.parseInt(fecha.substring(3,7));
                    
                    Date date = new Date(anio-1900,mes,dia);
                    
                    Matrimonio matrimonio = new Matrimonio(cedula, date, lugarCelebracion,
                            contribuyente(contribuyente1), contribuyente(contribuyente2), Testigo(testigo1),
                            Testigo(testigo2), Juezz(juez));

                    
                }

                salto += registro;

            }

        } catch (IOException ex) {
            System.out.println("Error lectrura escritura (UsuarioDao:Update)");
            ex.printStackTrace();
        }
        
        return null;
        
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
