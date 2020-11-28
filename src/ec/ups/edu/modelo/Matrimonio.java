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
public class Matrimonio {
    
    private String codigo;
    private Date fecha;
    private String lugarDeCelebracion;
    private Persona contribuyente1;
    private Persona contribuyente2;
    private Persona testigo1;
    private Persona testigo2;
    private Persona juez;

    public Matrimonio(String codigo,Date fecha, String lugarDeCelebracion, 
            Persona contribuyente1, Persona contribuyente2, Persona testigo1,
            Persona testigo2, Persona juez) {
        
        this.codigo = codigo;
        this.fecha = fecha;
        this.lugarDeCelebracion = lugarDeCelebracion;
        this.contribuyente1 = contribuyente1;
        this.contribuyente2 = contribuyente2;
        this.testigo1 = testigo1;
        this.testigo2 = testigo2;
        this.juez = juez;
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
    
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        
        this.codigo = validarEspacios(codigo, 5);
        
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugarDeCelebracion() {
        return lugarDeCelebracion;
    }

    public void setLugarDeCelebracion(String lugarDeCelebracion) {
        
        this.lugarDeCelebracion = validarEspacios(lugarDeCelebracion, 5);
        
    }

    public Persona getContribuyente1() {
        return contribuyente1;
    }

    public void setContribuyente1(Persona contribuyente1) {
        this.contribuyente1 = contribuyente1;
    }

    public Persona getContribuyente2() {
        return contribuyente2;
    }

    public void setContribuyente2(Persona contribuyente2) {
        this.contribuyente2 = contribuyente2;
    }

    public Persona getTestigo1() {
        return testigo1;
    }

    public void setTestigo1(Persona testigo1) {
        this.testigo1 = testigo1;
    }

    public Persona getTestigo2() {
        return testigo2;
    }

    public void setTestigo2(Persona testigo2) {
        this.testigo2 = testigo2;
    }

    public Persona getJuez() {
        return juez;
    }

    public void setJuez(Persona juez) {
        this.juez = juez;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.codigo);
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
        final Matrimonio other = (Matrimonio) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Matrimonio{" + "codigo=" + codigo + ", fecha=" + fecha + ", lugarDeCelebracion=" + lugarDeCelebracion + ", contribuyente1=" + contribuyente1 + ", contribuyente2=" + contribuyente2 + ", testigo1=" + testigo1 + ", testigo2=" + testigo2 + ", juez=" + juez + '}';
    }

}
