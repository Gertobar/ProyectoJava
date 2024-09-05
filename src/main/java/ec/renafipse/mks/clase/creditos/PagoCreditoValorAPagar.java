/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.clase.creditos;

import java.math.BigDecimal;

/**
 *
 * @author Santiago Araujo
 */
public class PagoCreditoValorAPagar {

    private long numeroCuotaAPagar;
    private BigDecimal capital;
    private long diasInteres;
    private BigDecimal interes;
    private long diasMora;
    private BigDecimal mora;
    private BigDecimal rubros;
    private long numeroNotificaciones;
    private BigDecimal notificaciones;
    private BigDecimal costoJudicial;
    private BigDecimal total;
    private BigDecimal saldoCapital;
    private String cuotasAPagar;
    public PagoCreditoValorAPagar( long numeroCuotaAPagar, BigDecimal capital, long diasInteres, BigDecimal interes, long diasMora, BigDecimal mora, BigDecimal rubros, long numeroNotificaciones, BigDecimal notificaciones, BigDecimal costoJudicial, BigDecimal total, BigDecimal saldoCapital, String cuotasAPagar ){
        this.numeroCuotaAPagar = numeroCuotaAPagar;
        this.capital = capital;
        this.diasInteres = diasInteres;
        this.interes = interes;
        this.diasMora = diasMora;
        this.mora = mora;
        this.rubros = rubros;
        this.notificaciones = notificaciones;
        this.numeroNotificaciones = numeroNotificaciones;
        this.costoJudicial = costoJudicial;
        this.total = total;
        this.saldoCapital = saldoCapital;
        this.cuotasAPagar = cuotasAPagar;
    }
    /**
     * @return the numeroCuotaAPagar
     */
    public long getNumeroCuotaAPagar() {
        return numeroCuotaAPagar;
    }

    /**
     * @param numeroCuotaAPagar the numeroCuotaAPagar to set
     */
    public void setNumeroCuotaAPagar(long numeroCuotaAPagar) {
        this.numeroCuotaAPagar = numeroCuotaAPagar;
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
     * @return the diasInteres
     */
    public long getDiasInteres() {
        return diasInteres;
    }

    /**
     * @param diasInteres the diasInteres to set
     */
    public void setDiasInteres(long diasInteres) {
        this.diasInteres = diasInteres;
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
     * @return the diasMora
     */
    public long getDiasMora() {
        return diasMora;
    }

    /**
     * @param diasMora the diasMora to set
     */
    public void setDiasMora(long diasMora) {
        this.diasMora = diasMora;
    }

    /**
     * @return the mora
     */
    public BigDecimal getMora() {
        return mora;
    }

    /**
     * @param mora the mora to set
     */
    public void setMora(BigDecimal mora) {
        this.mora = mora;
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
     * @return the numeroNotificaciones
     */
    public long getNumeroNotificaciones() {
        return numeroNotificaciones;
    }

    /**
     * @param numeroNotificaciones the numeroNotificaciones to set
     */
    public void setNumeroNotificaciones(long numeroNotificaciones) {
        this.numeroNotificaciones = numeroNotificaciones;
    }

    /**
     * @return the notificaciones
     */
    public BigDecimal getNotificaciones() {
        return notificaciones;
    }

    /**
     * @param notificaciones the notificaciones to set
     */
    public void setNotificaciones(BigDecimal notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * @return the costoJudicial
     */
    public BigDecimal getCostoJudicial() {
        return costoJudicial;
    }

    /**
     * @param costoJudicial the costoJudicial to set
     */
    public void setCostoJudicial(BigDecimal costoJudicial) {
        this.costoJudicial = costoJudicial;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
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
     * @return the cuotasAPagar
     */
    public String getCuotasAPagar() {
        return cuotasAPagar;
    }

    /**
     * @param cuotasAPagar the cuotasAPagar to set
     */
    public void setCuotasAPagar(String cuotasAPagar) {
        this.cuotasAPagar = cuotasAPagar;
    }
}
