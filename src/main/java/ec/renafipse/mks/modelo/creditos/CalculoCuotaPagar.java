/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.CALCULO_CUOTA_PAGAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalculoCuotaPagar.findByNumeroCreditoAndCodigoIfip", query = "SELECT c FROM CalculoCuotaPagar c JOIN c.tablaAmortizacion t WHERE c.calculoCuotaPagarPK.numeroCredito = t.tablaAmortizacionPK.numeroCredito AND c.calculoCuotaPagarPK.codigoIfip = t.tablaAmortizacionPK.codigoIfip AND c.calculoCuotaPagarPK.cuota = t.tablaAmortizacionPK.cuota AND t.tablaAmortizacionPK.numeroCredito = :numeroCredito AND t.tablaAmortizacionPK.codigoIfip = :codigoIfip AND t.estado = 'P' ORDER BY c.calculoCuotaPagarPK.cuota"),
    @NamedQuery(name = "CalculoCuotaPagar.findAll", query = "SELECT c FROM CalculoCuotaPagar c"),
    @NamedQuery(name = "CalculoCuotaPagar.findByNumeroCredito", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.calculoCuotaPagarPK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "CalculoCuotaPagar.findByCodigoIfip", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.calculoCuotaPagarPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "CalculoCuotaPagar.findByCuota", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.calculoCuotaPagarPK.cuota = :cuota"),
    @NamedQuery(name = "CalculoCuotaPagar.findBySaldoCapital", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.saldoCapital = :saldoCapital"),
    @NamedQuery(name = "CalculoCuotaPagar.findByCapital", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.capital = :capital"),
    @NamedQuery(name = "CalculoCuotaPagar.findByDiasInteres", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.diasInteres = :diasInteres"),
    @NamedQuery(name = "CalculoCuotaPagar.findByFechaCalculoInteres", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.fechaCalculoInteres = :fechaCalculoInteres"),
    @NamedQuery(name = "CalculoCuotaPagar.findByInteresCausado", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.interesCausado = :interesCausado"),
    @NamedQuery(name = "CalculoCuotaPagar.findByDiasMora", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.diasMora = :diasMora"),
    @NamedQuery(name = "CalculoCuotaPagar.findByFechaCalculoMora", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.fechaCalculoMora = :fechaCalculoMora"),
    @NamedQuery(name = "CalculoCuotaPagar.findByMoraCausada", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.moraCausada = :moraCausada"),
    @NamedQuery(name = "CalculoCuotaPagar.findByRubroActual", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.rubroActual = :rubroActual"),
    @NamedQuery(name = "CalculoCuotaPagar.findByNumeroNotificaciones", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.numeroNotificaciones = :numeroNotificaciones"),
    @NamedQuery(name = "CalculoCuotaPagar.findByFechaNotificacion", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "CalculoCuotaPagar.findByNotificacionCausada", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.notificacionCausada = :notificacionCausada"),
    @NamedQuery(name = "CalculoCuotaPagar.findByTotalPago", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.totalPago = :totalPago"),
//Personalizadas
    @NamedQuery(name = "CalculoCuotaPagar.findByCalculoCuotaNumeroIfip", query = "SELECT c FROM CalculoCuotaPagar c WHERE c.calculoCuotaPagarPK.numeroCredito = :numeroCredito AND c.calculoCuotaPagarPK.codigoIfip = :codigoIfip AND c.calculoCuotaPagarPK.cuota = :cuota"),
    @NamedQuery(name = "CalculoCuotaPagar.findByCalculoCuotaPendientes", query = "SELECT c FROM CalculoCuotaPagar c JOIN c.tablaAmortizacion t WHERE c.calculoCuotaPagarPK.numeroCredito = :numeroCredito AND c.calculoCuotaPagarPK.codigoIfip = :codigoIfip AND t.estado = :estado ORDER BY t.tablaAmortizacionPK.cuota"),
    @NamedQuery(name = "CalculoCuotaPagar.findByCalculoNumeroIfip", query = "SELECT c FROM CalculoCuotaPagar c JOIN c.tablaAmortizacion t WHERE c.calculoCuotaPagarPK.numeroCredito = :numeroCredito AND c.calculoCuotaPagarPK.codigoIfip = :codigoIfip ORDER BY t.tablaAmortizacionPK.cuota"),
    @NamedQuery(name = "CalculoCuotaPagar.findDiasMoraNimeroIfip", query = "SELECT MAX(c.diasMora) FROM CalculoCuotaPagar c WHERE c.calculoCuotaPagarPK.numeroCredito = :numeroCredito AND c.calculoCuotaPagarPK.codigoIfip = :codigoIfip and c.tablaAmortizacion.estado = :estado"),
    @NamedQuery(name = "CalculoCuotaPagar.findByCuotasVencidasCreditoIfip", query = "SELECT c FROM CalculoCuotaPagar c JOIN c.tablaAmortizacion t WHERE t.tablaAmortizacionPK.numeroCredito = :numeroCredito AND t.tablaAmortizacionPK.codigoIfip = :codigoIfip AND t.fechaPago <= :fechaVence  AND t.estado = :estado ORDER BY t.tablaAmortizacionPK.cuota"),})
public class CalculoCuotaPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCalculoCuotaNumeroIfip = "CalculoCuotaPagar.findByCalculoCuotaNumeroIfip";
    public static final String findByCalculoCuotaPendientes = "CalculoCuotaPagar.findByCalculoCuotaPendientes";
    public static final String findByCalculoNumeroIfip = "CalculoCuotaPagar.findByCalculoNumeroIfip";
     public static final String findDiasMoraNimeroIfip = "CalculoCuotaPagar.findDiasMoraNimeroIfip";
    public static final String findByCuotasVencidasCreditoIfip = "CalculoCuotaPagar.findByCuotasVencidasCreditoIfip";
    @EmbeddedId
    protected CalculoCuotaPagarPK calculoCuotaPagarPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL")
    private BigDecimal capital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_INTERES")
    private long diasInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CALCULO_INTERES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCalculoInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES_CAUSADO")
    private BigDecimal interesCausado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA")
    private long diasMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CALCULO_MORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCalculoMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MORA_CAUSADA")
    private BigDecimal moraCausada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBRO_ACTUAL")
    private BigDecimal rubroActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_NOTIFICACIONES")
    private long numeroNotificaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NOTIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFICACION_CAUSADA")
    private BigDecimal notificacionCausada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PAGO")
    private BigDecimal totalPago;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO_CREDITO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false),
        @JoinColumn(name = "CUOTA", referencedColumnName = "CUOTA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TablaAmortizacion tablaAmortizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calculoCuotaPagar", fetch = FetchType.LAZY)
    private Collection<PagoCreditoDetalleCuota> pagoCreditoDetalleCuotaCollection;

    public CalculoCuotaPagar() {
    }

    public CalculoCuotaPagar(CalculoCuotaPagarPK calculoCuotaPagarPK) {
        this.calculoCuotaPagarPK = calculoCuotaPagarPK;
    }

    public CalculoCuotaPagar(CalculoCuotaPagarPK calculoCuotaPagarPK, BigDecimal saldoCapital, BigDecimal capital, long diasInteres, Date fechaCalculoInteres, BigDecimal interesCausado, long diasMora, Date fechaCalculoMora, BigDecimal moraCausada, BigDecimal rubroActual, long numeroNotificaciones, Date fechaNotificacion, BigDecimal notificacionCausada, BigDecimal totalPago) {
        this.calculoCuotaPagarPK = calculoCuotaPagarPK;
        this.saldoCapital = saldoCapital;
        this.capital = capital;
        this.diasInteres = diasInteres;
        this.fechaCalculoInteres = fechaCalculoInteres;
        this.interesCausado = interesCausado;
        this.diasMora = diasMora;
        this.fechaCalculoMora = fechaCalculoMora;
        this.moraCausada = moraCausada;
        this.rubroActual = rubroActual;
        this.numeroNotificaciones = numeroNotificaciones;
        this.fechaNotificacion = fechaNotificacion;
        this.notificacionCausada = notificacionCausada;
        this.totalPago = totalPago;
    }

    public CalculoCuotaPagar(long numeroCredito, long codigoIfip, long cuota) {
        this.calculoCuotaPagarPK = new CalculoCuotaPagarPK(numeroCredito, codigoIfip, cuota);
    }

    public CalculoCuotaPagarPK getCalculoCuotaPagarPK() {
        return calculoCuotaPagarPK;
    }

    public void setCalculoCuotaPagarPK(CalculoCuotaPagarPK calculoCuotaPagarPK) {
        this.calculoCuotaPagarPK = calculoCuotaPagarPK;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public long getDiasInteres() {
        return diasInteres;
    }

    public void setDiasInteres(long diasInteres) {
        this.diasInteres = diasInteres;
    }

    public Date getFechaCalculoInteres() {
        return fechaCalculoInteres;
    }

    public void setFechaCalculoInteres(Date fechaCalculoInteres) {
        this.fechaCalculoInteres = fechaCalculoInteres;
    }

    public BigDecimal getInteresCausado() {
        return interesCausado;
    }

    public void setInteresCausado(BigDecimal interesCausado) {
        this.interesCausado = interesCausado;
    }

    public long getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(long diasMora) {
        this.diasMora = diasMora;
    }

    public Date getFechaCalculoMora() {
        return fechaCalculoMora;
    }

    public void setFechaCalculoMora(Date fechaCalculoMora) {
        this.fechaCalculoMora = fechaCalculoMora;
    }

    public BigDecimal getMoraCausada() {
        return moraCausada;
    }

    public void setMoraCausada(BigDecimal moraCausada) {
        this.moraCausada = moraCausada;
    }

    public BigDecimal getRubroActual() {
        return rubroActual;
    }

    public void setRubroActual(BigDecimal rubroActual) {
        this.rubroActual = rubroActual;
    }

    public long getNumeroNotificaciones() {
        return numeroNotificaciones;
    }

    public void setNumeroNotificaciones(long numeroNotificaciones) {
        this.numeroNotificaciones = numeroNotificaciones;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(BigDecimal totalPago) {
        this.totalPago = totalPago;
    }

//    public TablaAmortizacion getTablaAmortizacion() {
//        return tablaAmortizacion;
//    }
//
//    public void setTablaAmortizacion(TablaAmortizacion tablaAmortizacion) {
//        this.tablaAmortizacion = tablaAmortizacion;
//    }
    @XmlTransient
    public Collection<PagoCreditoDetalleCuota> getPagoCreditoDetalleCuotaCollection() {
        return pagoCreditoDetalleCuotaCollection;
    }

    public void setPagoCreditoDetalleCuotaCollection(Collection<PagoCreditoDetalleCuota> pagoCreditoDetalleCuotaCollection) {
        this.pagoCreditoDetalleCuotaCollection = pagoCreditoDetalleCuotaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calculoCuotaPagarPK != null ? calculoCuotaPagarPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalculoCuotaPagar)) {
            return false;
        }
        CalculoCuotaPagar other = (CalculoCuotaPagar) object;
        if ((this.calculoCuotaPagarPK == null && other.calculoCuotaPagarPK != null) || (this.calculoCuotaPagarPK != null && !this.calculoCuotaPagarPK.equals(other.calculoCuotaPagarPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar[ calculoCuotaPagarPK=" + calculoCuotaPagarPK + " ]";
    }

    /**
     * @return the notificacionCausada
     */
    public BigDecimal getNotificacionCausada() {
        return notificacionCausada;
    }

    /**
     * @param notificacionCausada the notificacionCausada to set
     */
    public void setNotificacionCausada(BigDecimal notificacionCausada) {
        this.notificacionCausada = notificacionCausada;
    }

    /**
     * @return the tablaAmortizacion
     */
    public TablaAmortizacion getTablaAmortizacion() {
        return tablaAmortizacion;
    }

    /**
     * @param tablaAmortizacion the tablaAmortizacion to set
     */
    public void setTablaAmortizacion(TablaAmortizacion tablaAmortizacion) {
        this.tablaAmortizacion = tablaAmortizacion;
    }

    public String moraCausadaFormato() {
        return new DecimalFormat("###,###,###0.00").format(moraCausada.doubleValue());
    }
}
