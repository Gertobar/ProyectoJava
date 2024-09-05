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
public class CuentaAhorroProgramado {
    
    private Long codigo;
    private String numero;

    public CuentaAhorroProgramado() {
    }

    public CuentaAhorroProgramado(Long codigo, String numero) {
        this.codigo = codigo;
        this.numero = numero;
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

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
