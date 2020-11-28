/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santi
 * @param <T>
 */
public abstract class ControladorAbstracto<T> {

    public abstract void create(T objeto);
    public abstract T read(String cedula);
    public abstract boolean update(T objeto);
    public abstract boolean delete(T objeto);  
    public abstract List<T> lista();
    

}
