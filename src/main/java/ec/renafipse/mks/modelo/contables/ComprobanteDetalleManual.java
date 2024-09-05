/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Santiago
 */
public class ComprobanteDetalleManual {
    private Long codigoComprobante;
    private String cuentaContable;
    private Long codigoTipoPlan;
    private Long codigoIfip;
    private String tipo;
    private BigDecimal valor;
    private Long linea;
    private String numeroComprobante;
    private String estado;
    private String glosa;
    private Date fecha;
    public ComprobanteDetalleManual( Long codigoComprobante, String cuentaContable, Long codigoTipoPlan, Long codigoIfip, String tipo, BigDecimal valor, Long linea, String numeroComprobante, String estado, String glosa, Date fecha){
        this.codigoComprobante = codigoComprobante;
        this.cuentaContable = cuentaContable;
        this.codigoTipoPlan = codigoTipoPlan;
        this.codigoIfip = codigoIfip;
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
        this.numeroComprobante = numeroComprobante;
        this.estado = estado;
        this.glosa = glosa;
        this.fecha = fecha;
    }

    /**
     * @return the codigoComprobante
     */
    public Long getCodigoComprobante() {
        return codigoComprobante;
    }

    /**
     * @param codigoComprobante the codigoComprobante to set
     */
    public void setCodigoComprobante(Long codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    /**
     * @return the cuentaContable
     */
    public String getCuentaContable() {
        return cuentaContable;
    }

    /**
     * @param cuentaContable the cuentaContable to set
     */
    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    /**
     * @return the codigoTipoPlan
     */
    public Long getCodigoTipoPlan() {
        return codigoTipoPlan;
    }

    /**
     * @param codigoTipoPlan the codigoTipoPlan to set
     */
    public void setCodigoTipoPlan(Long codigoTipoPlan) {
        this.codigoTipoPlan = codigoTipoPlan;
    }

    /**
     * @return the codigoIfip
     */
    public Long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(Long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the linea
     */
    public Long getLinea() {
        return linea;
    }

    /**
     * @param linea the linea to set
     */
    public void setLinea(Long linea) {
        this.linea = linea;
    }

    /**
     * @return the numeroComprobante
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * @param numeroComprobante the numeroComprobante to set
     */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the glosa
     */
    public String getGlosa() {
        return glosa;
    }

    /**
     * @param glosa the glosa to set
     */
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
