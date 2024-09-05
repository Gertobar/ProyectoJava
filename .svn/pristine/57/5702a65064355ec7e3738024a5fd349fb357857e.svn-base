/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos.lineacredito;

import ec.renafipse.mks.clase.ErrorEjecucionPaquete;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "PAGO_LINEA_CREDITO_SOLICITUD", schema = "MKS_CREDITOS")
@NamedStoredProcedureQueries({                  
         @NamedStoredProcedureQuery(
			    name = "calculaLineaCreditoPagoParcial",
			    procedureName = "PKM_PAGAR_CREDITO.P_OBTIENE_VALOR_PAGAR_LIN_CRE",
			    parameters = { 
			    		@StoredProcedureParameter(name = "pt_codigo_linea_credito_sol", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_cuotas", type = Short.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_valor", type = BigDecimal.class, mode = ParameterMode.IN),
			    		@StoredProcedureParameter(name = "pt_capital", type = BigDecimal.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pt_interes", type = BigDecimal.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pt_mora", type = BigDecimal.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pt_rubro", type = BigDecimal.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pt_notificaciones", type = BigDecimal.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pt_costo_judicial", type = BigDecimal.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pt_total", type = BigDecimal.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_cuotas_a_pagar", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
	 ),
         @NamedStoredProcedureQuery(
			    name = "pagaLineaCreditoParcial",
			    procedureName = "PKM_PAGAR_CREDITO.P_PAGA_LINEA_CREDITO",
			    parameters = { 
                                        @StoredProcedureParameter(name = "pt_codigo_linea_credito_sol", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_avance_linea_credito", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_usuario", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_canal_servicio", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pv_tipo_origen_pago", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_valor", type = BigDecimal.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_ifip_agencia", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_observaciones", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pv_restructura_x_abono_capital", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pv_mensaje_formulario", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pt_codigo_formulario", type = Long.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pn_genera_formulario", type = Long.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pt_codigo_pago_linea_cre_sol", type = Long.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
	),
        @NamedStoredProcedureQuery(
                          name = "calculaLineaCreditoPagoTotal",
                          procedureName = "PKM_PAGAR_CREDITO.P_OBTIENE_VAL_TOT_PAG_LIN_CRE",
                          parameters = { 
                                        @StoredProcedureParameter(name = "pt_codigo_linea_credito_sol", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_capital", type = BigDecimal.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pt_interes", type = BigDecimal.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pt_mora", type = BigDecimal.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pt_rubro", type = BigDecimal.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pt_notificaciones", type = BigDecimal.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pt_costo_judicial", type = BigDecimal.class, mode = ParameterMode.OUT),
			    		@StoredProcedureParameter(name = "pt_total", type = BigDecimal.class, mode = ParameterMode.OUT), 
                                        @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT)}
	),
        @NamedStoredProcedureQuery(
			    name = "pagaLineaCreditoTotal",
			    procedureName = "PKM_PAGAR_CREDITO.P_PAGA_LINEA_CREDITO",
			    parameters = { 
                                        @StoredProcedureParameter(name = "pt_codigo_linea_credito_sol", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_avance_linea_credito", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_usuario", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_canal_servicio", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pv_tipo_origen_pago", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_valor", type = BigDecimal.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_codigo_ifip_agencia", type = Long.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pt_observaciones", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pv_restructura_x_abono_capital", type = String.class, mode = ParameterMode.IN),
                                        @StoredProcedureParameter(name = "pv_mensaje_formulario", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pt_codigo_formulario", type = Long.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pn_genera_formulario", type = Long.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pt_codigo_pago_linea_cre_sol", type = Long.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
                                        @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
	)
	})
public class PagoLineaCreditoSolicitud extends ErrorEjecucionPaquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
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
    @Column(name = "RUBROS")
    private BigDecimal rubros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFICACIONES")
    private BigDecimal notificaciones;
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
    @Column(name = "CODIGO_LINEA_CREDITO_SOLICITUD")
    private long codigoLineaCreditoSolicitud;
    
    private String numeroCuotasApagar;
    
    public PagoLineaCreditoSolicitud() {
    }

    public PagoLineaCreditoSolicitud(Long codigo) {
        this.codigo = codigo;
    }

    public PagoLineaCreditoSolicitud(Long codigo, long codigoUsuario, long codigoIfipAgencia, Date fecha, Date fechaSistema, BigDecimal capital, BigDecimal interes, BigDecimal mora, BigDecimal rubros, BigDecimal notificaciones, BigDecimal costoJudicial, BigDecimal total, long codigoLineaCreditoSolicitud) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.fecha = fecha;
        this.fechaSistema = fechaSistema;
        this.capital = capital;
        this.interes = interes;
        this.mora = mora;
        this.rubros = rubros;
        this.notificaciones = notificaciones;
        this.costoJudicial = costoJudicial;
        this.total = total;
        this.codigoLineaCreditoSolicitud = codigoLineaCreditoSolicitud;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
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

    public BigDecimal getRubros() {
        return rubros;
    }

    public void setRubros(BigDecimal rubros) {
        this.rubros = rubros;
    }

    public BigDecimal getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(BigDecimal notificaciones) {
        this.notificaciones = notificaciones;
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

    public long getCodigoLineaCreditoSolicitud() {
        return codigoLineaCreditoSolicitud;
    }

    public void setCodigoLineaCreditoSolicitud(long codigoLineaCreditoSolicitud) {
        this.codigoLineaCreditoSolicitud = codigoLineaCreditoSolicitud;
    }

    public String getNumeroCuotasApagar() {
        return numeroCuotasApagar;
    }

    public void setNumeroCuotasApagar(String numeroCuotasApagar) {
        this.numeroCuotasApagar = numeroCuotasApagar;
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
        if (!(object instanceof PagoLineaCreditoSolicitud)) {
            return false;
        }
        PagoLineaCreditoSolicitud other = (PagoLineaCreditoSolicitud) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PagoLineaCreditoSolicitud{" + "codigo=" + codigo + ", codigoUsuario=" + codigoUsuario + ", codigoIfipAgencia=" + codigoIfipAgencia + ", fecha=" + fecha + ", fechaSistema=" + fechaSistema + ", capital=" + capital + ", interes=" + interes + ", mora=" + mora + ", rubros=" + rubros + ", notificaciones=" + notificaciones + ", costoJudicial=" + costoJudicial + ", total=" + total + ", codigoLineaCreditoSolicitud=" + codigoLineaCreditoSolicitud + ", numeroCuotasApagar=" + numeroCuotasApagar + '}';
    }

}
