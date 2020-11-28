/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author santi
 */
public class Persona {
    
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String genero;
    private Date fecha;

    public Persona(String cedula) {
        
        this.cedula = cedula;
        fecha = new Date();
        
        
    }

    
    public Persona(String cedula, String nombre, String apellido, String direccion,
            String genero,Date fecha) {
        
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.genero = genero;
        this.fecha = fecha;
        
    }

       /*
    private String cedula; = 10
    private String nombre; = 15
    private String apellido; = 15
    private String direccion; = 50
    private String genero; = 10
    private Date fecha; = 30
    
    total registro = 142.
     */
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
    
        this.cedula = validarEspacios(cedula, 10);
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        
        this.nombre = validarEspacios(nombre, 15);
        
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        
        this.apellido = validarEspacios(apellido, 15);
        
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        
        this.direccion = validarEspacios(direccion, 50);
        
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        
        this.genero = validarEspacios(genero, 10);
        
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

      public String validarEspacios(String cadena, int numero) {
        if (cadena.length() == numero) {
            return cadena;
        } else {
            if (cadena.length() > numero) {
                cadena = cadena.substring(0, numero);
                return cadena;
            } else {
                for (int i = cadena.length(); i < numero; i++) {
                    cadena += " ";
                }
                return cadena;
            }
        }
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.cedula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", genero=" + genero +'}';
    }
    
}
