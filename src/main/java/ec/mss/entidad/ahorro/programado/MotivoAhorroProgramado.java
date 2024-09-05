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
public class MotivoAhorroProgramado {
    private Long codigo;
    private String nombre;
    
    public MotivoAhorroProgramado(){}
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param codigo
     * @param nombre the nombre to set
     */
    public void setNombre(Long codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
}
