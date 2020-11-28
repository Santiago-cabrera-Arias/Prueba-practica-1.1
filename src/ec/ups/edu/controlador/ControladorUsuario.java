/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.ups.edu.controlador;

import ec.ups.edu.modelo.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santi
 */
public class ControladorUsuario extends ControladorAbstracto<Usuario> {

    //Aplicando Singleton.
    private static ControladorUsuario instancia;

    public static ControladorUsuario getInstance() {

        if (instancia == null) {

            instancia = new ControladorUsuario();

        }
        return instancia;
    }

    private RandomAccessFile archivo;
    int salto;

    public ControladorUsuario() {

        try {
            archivo = new RandomAccessFile("usuario1.txt", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void create(Usuario objeto) {

        salto = 0;

        try {

            archivo.seek(salto);
            archivo.writeUTF(objeto.getUsuario());
            archivo.writeUTF(objeto.getContrasena());

        } catch (IOException ex) {

            System.out.println("Error de lectura y ecritura");
            ex.printStackTrace();

        }
    }

    @Override
    public Usuario read(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> lista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario login(String correo, String contraseña) {

        int salto = 0;
        int registro = 20;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String correoArchivo = archivo.readUTF();
                String contraseñaArchivo = archivo.readUTF();

                if (correo.equals(correoArchivo.trim()) && contraseña.equals(contraseñaArchivo.trim())) {
                    archivo.seek(salto);
                    Usuario usuario = new Usuario(correoArchivo, contraseñaArchivo);
                    return usuario;
                }
                salto += registro;
            }
        } catch (IOException ex) {
            System.out.println("Error lectura escritura (UsuarioDao:login)");
            ex.printStackTrace();
        }
        return null;

    }

}
