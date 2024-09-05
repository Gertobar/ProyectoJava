/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.entidad.ahorro.programado;

/**
 *
 * @author andres
 */
public class IfipAgencia {
    private String nombre;
    
    public IfipAgencia(){}

    public IfipAgencia(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
