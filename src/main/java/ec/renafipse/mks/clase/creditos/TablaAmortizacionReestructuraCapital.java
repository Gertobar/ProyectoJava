/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.clase.creditos;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Mascoop
 */
public class TablaAmortizacionReestructuraCapital {
    private long cuota;
    private Date fechaInicio;
    private Date fechaPago;
    private BigDecimal saldoCapital;
    private BigDecimal capital;
    private BigDecimal interes;
    private BigDecimal rubros;
    private BigDecimal totalCuota;

    public TablaAmortizacionReestructuraCapital() {
    }

    public TablaAmortizacionReestructuraCapital(long cuota, Date fechaInicio, Date fechaPago, BigDecimal saldoCapital, BigDecimal capital, BigDecimal interes, BigDecimal rubros, BigDecimal totalCuota) {
        this.cuota = cuota;
        this.fechaInicio = fechaInicio;
        this.fechaPago = fechaPago;
        this.saldoCapital = saldoCapital;
        this.capital = capital;
        this.interes = interes;
        this.rubros = rubros;
        this.totalCuota = totalCuota;
    }

    /**
     * @return the cuota
     */
    public long getCuota() {
        return cuota;
    }

    /**
     * @param cuota the cuota to set
     */
    public void setCuota(long cuota) {
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
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the saldoCapital
     */
    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    /**
     * @param saldoCapital the saldoCapital to set
     */
    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    /**
     * @return the capital
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
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
     * @return the rubros
     */
    public BigDecimal getRubros() {
        return rubros;
    }

    /**
     * @param rubros the rubros to set
     */
    public void setRubros(BigDecimal rubros) {
        this.rubros = rubros;
    }

    /**
     * @return the totalCuota
     */
    public BigDecimal getTotalCuota() {
        return totalCuota;
    }

    /**
     * @param totalCuota the totalCuota to set
     */
    public void setTotalCuota(BigDecimal totalCuota) {
        this.totalCuota = totalCuota;
    }

    @Override
    public String toString() {
        return "TablaAmortizacionReestructuraCapital{" + "cuota=" + cuota + ", fechaInicio=" + fechaInicio + ", fechaPago=" + fechaPago + ", saldoCapital=" + saldoCapital + ", capital=" + capital + ", interes=" + interes + ", rubros=" + rubros + ", totalCuota=" + totalCuota + '}';
    }
    
}
