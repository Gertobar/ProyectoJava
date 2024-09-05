/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos.lineacredito;

import ec.renafipse.mks.clase.ErrorEjecucionPaquete;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "PAGO_AVANCE_LINEA_CREDITO", schema = "MKS_CREDITOS")
@NamedStoredProcedureQueries({
         @NamedStoredProcedureQuery(
			    name = "pagaLineaCreditoAvanceParcial",
			    procedureName = "PKM_LINEA_CREDITO.P_PAGA_AVANCE_LIN_CRE_SIN_CRE",
			    parameters = { 
                                        @StoredProcedureParameter(name = "pt_codigo_avance_linea_credito", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_valor", type = BigDecimal.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pv_tipo_origen", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_usuario", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_ifip_agencia", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_observaciones", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_pago_avance_lin_cre", type = Long.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
	),
        @NamedStoredProcedureQuery(
			    name = "pagaLineaCreditoAvanceTotal",
			    procedureName = "PKM_LINEA_CREDITO.P_PAGA_AVANCE_LIN_CRE_SIN_CRE",
			    parameters = { 
                                        @StoredProcedureParameter(name = "pt_codigo_avance_linea_credito", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_valor", type = BigDecimal.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pv_tipo_origen", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_usuario", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_ifip_agencia", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_observaciones", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_pago_avance_lin_cre", type = Long.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
	)
	})
public class PagoAvanceLineaCredito extends ErrorEjecucionPaquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL")
    private BigDecimal capital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES")
    private BigDecimal interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MORA")
    private BigDecimal mora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBRO")
    private BigDecimal rubro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFICACION")
    private BigDecimal notificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_JUDICIAL")
    private BigDecimal costoJudicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "FORMA_PAGO")
    private String formaPago;
    @JoinColumn(name = "CODIGO_AVANCE_LINEA_CREDITO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private AvanceLineaCredito avanceLineaCredito;

    public PagoAvanceLineaCredito() {
    }

    public PagoAvanceLineaCredito(Long codigo) {
        this.codigo = codigo;
    }

    public PagoAvanceLineaCredito(Long codigo, BigDecimal capital, BigDecimal interes, BigDecimal mora, BigDecimal rubro, BigDecimal notificacion, BigDecimal costoJudicial, BigDecimal total, String formaPago, AvanceLineaCredito avanceLineaCredito) {
        this.codigo = codigo;
        this.capital = capital;
        this.interes = interes;
        this.mora = mora;
        this.rubro = rubro;
        this.notificacion = notificacion;
        this.costoJudicial = costoJudicial;
        this.total = total;
        this.formaPago = formaPago;
        this.avanceLineaCredito = avanceLineaCredito;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public BigDecimal getRubro() {
        return rubro;
    }

    public void setRubro(BigDecimal rubro) {
        this.rubro = rubro;
    }

    public BigDecimal getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(BigDecimal notificacion) {
        this.notificacion = notificacion;
    }

    public BigDecimal getCostoJudicial() {
        return costoJudicial;
    }

    public void setCostoJudicial(BigDecimal costoJudicial) {
        this.costoJudicial = costoJudicial;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public AvanceLineaCredito getAvanceLineaCredito() {
        return avanceLineaCredito;
    }

    public void setAvanceLineaCredito(AvanceLineaCredito avanceLineaCredito) {
        this.avanceLineaCredito = avanceLineaCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoAvanceLineaCredito)) {
            return false;
        }
        PagoAvanceLineaCredito other = (PagoAvanceLineaCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PagoAvanceLineaCredito{" + "codigo=" + codigo + ", capital=" + capital + ", interes=" + interes + ", mora=" + mora + ", rubro=" + rubro + ", notificacion=" + notificacion + ", costoJudicial=" + costoJudicial + ", total=" + total + ", formaPago=" + formaPago + ", avanceLineaCredito=" + avanceLineaCredito + '}';
    }
    
}
