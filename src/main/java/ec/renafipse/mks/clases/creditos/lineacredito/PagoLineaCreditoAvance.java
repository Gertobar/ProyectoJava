/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.clases.creditos.lineacredito;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author andres
 */
public class PagoLineaCreditoAvance {
    
    private int cuota;
    private Date fechaPago;
    private BigDecimal saldoCapital;
    private BigDecimal capital;
    private BigDecimal totalAPagar;
    private BigDecimal pagoMinimo;
    
    public PagoLineaCreditoAvance(){}
    
    public PagoLineaCreditoAvance(int cuota, Date fechaPago, BigDecimal saldoCapital, BigDecimal capital, BigDecimal totalAPagar, BigDecimal pagoMinimo) {
        this.cuota = cuota;
        this.fechaPago = fechaPago;
        this.saldoCapital = saldoCapital;
        this.capital = capital;
        this.totalAPagar = totalAPagar;
        this.pagoMinimo = pagoMinimo;
    }
    
    /**
     * @return the cuota
     */
    public int getCuota() {
        return cuota;
    }

    /**
     * @param cuota the cuota to set
     */
    public void setCuota(int cuota) {
        this.cuota = cuota;
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
     * @return the totalAPagar
     */
    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    /**
     * @param totalAPagar the totalAPagar to set
     */
    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    /**
     * @return the pagoMinimo
     */
    public BigDecimal getPagoMinimo() {
        return pagoMinimo;
    }

    /**
     * @param pagoMinimo the pagoMinimo to set
     */
    public void setPagoMinimo(BigDecimal pagoMinimo) {
        this.pagoMinimo = pagoMinimo;
    }
    
}
