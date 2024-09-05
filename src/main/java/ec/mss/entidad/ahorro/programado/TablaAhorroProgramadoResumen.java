/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.entidad.ahorro.programado;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author andres
 */
public class TablaAhorroProgramadoResumen {
    
    private Long cuota;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal valor;
    private BigDecimal interes;
    private Date fechaMaximaPago;

    public TablaAhorroProgramadoResumen() {
    }

    public TablaAhorroProgramadoResumen(Long cuota, Date fechaInicio, Date fechaFin, BigDecimal valor, BigDecimal interes, Date fechaMaximaPago) {
        this.cuota = cuota;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.valor = valor;
        this.interes = interes;
        this.fechaMaximaPago = fechaMaximaPago;
    }

    @Override
    public String toString() {
        return "TablaAhorroProgramadoResumen{" + "cuota=" + cuota + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", valor=" + valor + ", interes=" + interes + ", fechaMaximaPago=" + fechaMaximaPago + '}';
    }
    
    /**
     * @return the cuota
     */
    public Long getCuota() {
        return cuota;
    }

    /**
     * @param cuota the cuota to set
     */
    public void setCuota(Long cuota) {
        this.cuota = cuota;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the interes
     */
    public BigDecimal getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    /**
     * @return the fechaMaximaPago
     */
    public Date getFechaMaximaPago() {
        return fechaMaximaPago;
    }

    /**
     * @param fechaMaximaPago the fechaMaximaPago to set
     */
    public void setFechaMaximaPago(Date fechaMaximaPago) {
        this.fechaMaximaPago = fechaMaximaPago;
    }
    
   
    
}
