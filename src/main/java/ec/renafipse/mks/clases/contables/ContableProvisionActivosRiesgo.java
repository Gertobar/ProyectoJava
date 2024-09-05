/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.clases.contables;

import java.math.BigDecimal;

/**
 *
 * @author Andres
 */
public class ContableProvisionActivosRiesgo {
   private String agencia;
   private String tipoCartera;
   private String calificacion;
   private String cuentaDebe;
   private String cuentaHaber;
   private BigDecimal valor;
   
    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    
    /**
     * @return the tipoCartera
     */
    public String getTipoCartera() {
        return tipoCartera;
    }

    /**
     * @param tipoCartera the tipoCartera to set
     */
    public void setTipoCartera(String tipoCartera) {
        this.tipoCartera = tipoCartera;
    }

    /**
     * @return the calificacion
     */
    public String getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the cuentaDebe
     */
    public String getCuentaDebe() {
        return cuentaDebe;
    }

    /**
     * @param cuentaDebe the cuentaDebe to set
     */
    public void setCuentaDebe(String cuentaDebe) {
        this.cuentaDebe = cuentaDebe;
    }

    /**
     * @return the cuentaHaber
     */
    public String getCuentaHaber() {
        return cuentaHaber;
    }

    /**
     * @param cuentaHaber the cuentaHaber to set
     */
    public void setCuentaHaber(String cuentaHaber) {
        this.cuentaHaber = cuentaHaber;
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
    
    public ContableProvisionActivosRiesgo(){}
    
    public ContableProvisionActivosRiesgo(String agencia, String tipoCartera, String calificacion, String cuentaDebe, String cuentaHaber, BigDecimal valor){
        this.agencia = agencia;
        this.tipoCartera = tipoCartera;
        this.calificacion = calificacion;
        this.cuentaDebe = cuentaDebe;
        this.cuentaHaber = cuentaHaber;
        this.valor = valor;
    }
    
    public ContableProvisionActivosRiesgo(String tipoCartera, String cuentaDebe, String cuentaHaber, BigDecimal valor){
        this.agencia = agencia;
        this.tipoCartera = tipoCartera;
        this.calificacion = calificacion;
        this.cuentaDebe = cuentaDebe;
        this.cuentaHaber = cuentaHaber;
        this.valor = valor;
    }
}
