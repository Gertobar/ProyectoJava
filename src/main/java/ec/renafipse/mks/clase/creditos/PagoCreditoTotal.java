/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.clase.creditos;

/**
 *
 * @author Santiago Araujo
 */
public class PagoCreditoTotal {
    private String mensajeFormulario;
    private Long codigoFormulario;
    private Long generaFormulario;
    private Long codigoPagoCredito;
    private Long codigoPagoAvanceLineaCredito;
    private Long codigoPagoLineaSolicitudCredito;
    public PagoCreditoTotal(String mensajeFormulario, Long codigoFormulario, Long generaFormulario, Long codigoPagoCredito, Long codigoPagoAvanceLineaCredito, Long codigoPagoLineaSolicitudCredito){
        this.mensajeFormulario = mensajeFormulario;
        this.codigoFormulario = codigoFormulario;
        this.generaFormulario = generaFormulario;
        this.codigoPagoCredito = codigoPagoCredito;
        this.codigoPagoAvanceLineaCredito = codigoPagoAvanceLineaCredito;
        this.codigoPagoLineaSolicitudCredito = codigoPagoLineaSolicitudCredito;
    }

    /**
     * @return the mensajeFormulario
     */
    public String getMensajeFormulario() {
        return mensajeFormulario;
    }

    /**
     * @param mensajeFormulario the mensajeFormulario to set
     */
    public void setMensajeFormulario(String mensajeFormulario) {
        this.mensajeFormulario = mensajeFormulario;
    }

    /**
     * @return the codigoFormulario
     */
    public Long getCodigoFormulario() {
        return codigoFormulario;
    }

    /**
     * @param codigoFormulario the codigoFormulario to set
     */
    public void setCodigoFormulario(Long codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
    }

    /**
     * @return the generaFormulario
     */
    public Long getGeneraFormulario() {
        return generaFormulario;
    }

    /**
     * @param generaFormulario the generaFormulario to set
     */
    public void setGeneraFormulario(Long generaFormulario) {
        this.generaFormulario = generaFormulario;
    }

    /**
     * @return the codigPagoCredito
     */
    public Long getCodigoPagoCredito() {
        return codigoPagoCredito;
    }

    /**
     * @param codigPagoCredito the codigPagoCredito to set
     */
    public void setCodigoPagoCredito(Long codigoPagoCredito) {
        this.codigoPagoCredito = codigoPagoCredito;
    }

    /**
     * @return the codigoPagoAvanceLineaCredito
     */
    public Long getCodigoPagoAvanceLineaCredito() {
        return codigoPagoAvanceLineaCredito;
    }

    /**
     * @param codigoPagoAvanceLineaCredito the codigoPagoAvanceLineaCredito to set
     */
    public void setCodigoPagoAvanceLineaCredito(Long codigoPagoAvanceLineaCredito) {
        this.codigoPagoAvanceLineaCredito = codigoPagoAvanceLineaCredito;
    }
}
