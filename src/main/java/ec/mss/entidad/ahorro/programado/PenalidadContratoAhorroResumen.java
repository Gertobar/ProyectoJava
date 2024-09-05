/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.entidad.ahorro.programado;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author andres
 */
public class PenalidadContratoAhorroResumen {
    
    private Long codigoContratoAhorroPro;
    private Double porcentajePenalizacion;
    private BigDecimal interesAPercibir;
    private BigDecimal interesPenalizado;
    private Double porcentajeTranscurrido;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date fechaFinalizacion;

    /**
     *
     */
    public PenalidadContratoAhorroResumen() {
    }
    
    /**
     * 
     * @param codigoContratoAhorroPro
     * @param porcentajePenalizacion
     * @param interesAPercibir
     * @param interesPenalizado
     * @param porcentajeTranscurrido
     * @param fechaFinalizacion 
     */
    public PenalidadContratoAhorroResumen(Long codigoContratoAhorroPro, Double porcentajePenalizacion, BigDecimal interesAPercibir, BigDecimal interesPenalizado, Double porcentajeTranscurrido, Date fechaFinalizacion) {
        this.codigoContratoAhorroPro = codigoContratoAhorroPro;
        this.porcentajePenalizacion = porcentajePenalizacion;
        this.interesAPercibir = interesAPercibir;
        this.interesPenalizado = interesPenalizado;
        this.porcentajeTranscurrido = porcentajeTranscurrido;
        this.fechaFinalizacion = fechaFinalizacion;
    }   
    
    /**
     *
     * @return
     */
    public Long getCodigoContratoAhorroPro() {
        return codigoContratoAhorroPro;
    }

    /**
     *
     * @param codigoContratoAhorroPro
     */
    public void setCodigoContratoAhorroPro(Long codigoContratoAhorroPro) {
        this.codigoContratoAhorroPro = codigoContratoAhorroPro;
    }

    /**
     *
     * @return
     */
    public Double getPorcentajePenalizacion() {
        return porcentajePenalizacion;
    }

    /**
     *
     * @param porcentajePenalizacion
     */
    public void setPorcentajePenalizacion(Double porcentajePenalizacion) {
        this.porcentajePenalizacion = porcentajePenalizacion;
    }

    /**
     *
     * @return
     */
    public BigDecimal getInteresAPercibir() {
        return interesAPercibir;
    }

    /**
     *
     * @param interesAPercibir
     */
    public void setInteresAPercibir(BigDecimal interesAPercibir) {
        this.interesAPercibir = interesAPercibir;
    }

    /**
     *
     * @return
     */
    public BigDecimal getInteresPenalizado() {
        return interesPenalizado;
    }

    /**
     *
     * @param interesPenalizado
     */
    public void setInteresPenalizado(BigDecimal interesPenalizado) {
        this.interesPenalizado = interesPenalizado;
    }

    /**
     *
     * @return
     */
    public Double getPorcentajeTranscurrido() {
        return porcentajeTranscurrido;
    }

    /**
     *
     * @param porcentajeTranscurrido
     */
    public void setPorcentajeTranscurrido(Double porcentajeTranscurrido) {
        this.porcentajeTranscurrido = porcentajeTranscurrido;
    }
    
    /**
     * 
     * @return 
     */
    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }
     
    /**
     * 
     * @param fechaFinalizacion 
     */
    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    @Override
    public String toString() {
        return "PenalidadContratoAhorroResumen{" + "codigoContratoAhorroPro=" + codigoContratoAhorroPro + ", porcentajePenalizacion=" + porcentajePenalizacion + ", interesAPercibir=" + interesAPercibir + ", interesPenalizado=" + interesPenalizado + ", porcentajeTranscurrido=" + porcentajeTranscurrido + ", fechaFinalizacion=" + fechaFinalizacion + '}';
    }
        
}
