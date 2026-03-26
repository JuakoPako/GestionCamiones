/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author bevod
 */
public class Sesion {
    
    private static Usuario usuarioActual;
    
    public static void setUsuario(Usuario usuario) {
        usuarioActual = usuario;
    }
    
    public static Usuario getUsuario(){
        return usuarioActual;
    }
    
    public static void cerrarSesion() {
        usuarioActual = null;
    }
    
    public static boolean haySesion() {
        return usuarioActual  != null;
    }
    
}
