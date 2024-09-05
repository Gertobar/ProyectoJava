/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PAGO_CREDITO_DETALLE_CUOTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCreditoDetalleCuota.findAll", query = "SELECT p FROM PagoCreditoDetalleCuota p"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByCodigoPagoCredito", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.pagoCreditoDetalleCuotaPK.codigoPagoCredito = :codigoPagoCredito"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByNumeroCredito", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.pagoCreditoDetalleCuotaPK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByCodigoIfip", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.pagoCreditoDetalleCuotaPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByCuota", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.pagoCreditoDetalleCuotaPK.cuota = :cuota"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByTipo", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByCapital", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.capital = :capital"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByDiasInteres", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.diasInteres = :diasInteres"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByInteres", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.interes = :interes"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByDiasMora", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.diasMora = :diasMora"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByMora", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.mora = :mora"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByRubros", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.rubros = :rubros"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByNumeroNotificaciones", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.numeroNotificaciones = :numeroNotificaciones"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByNotificaciones", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.notificaciones = :notificaciones"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByTotalPago", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.totalPago = :totalPago"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findBySaldoCapital", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.saldoCapital = :saldoCapital"),
    @NamedQuery(name = "PagoCreditoDetalleCuota.findByCapitalAPagar", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.capitalAPagar = :capitalAPagar"),
//Personalizadas

    @NamedQuery(name = "PagoCreditoDetalleCuota.findByDetalleCuotaPagoCredito", query = "SELECT p FROM PagoCreditoDetalleCuota p WHERE p.pagoCreditoDetalleCuotaPK.codigoPagoCredito = :codigoPagoCredito ORDER BY p.pagoCreditoDetalleCuotaPK.cuota")
})
public class PagoCreditoDetalleCuota implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByDetalleCuotaPagoCredito = "PagoCreditoDetalleCuota.findByDetalleCuotaPagoCredito";

    @EmbeddedId
    protected PagoCreditoDetalleCuotaPK pagoCreditoDetalleCuotaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @Column(name = "INTERES")
    private BigDecimal interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA")
    private long diasMora;
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
    @Column(name = "NUMERO_NOTIFICACIONES")
    private long numeroNotificaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFICACIONES")
    private BigDecimal notificaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PAGO")
    private BigDecimal totalPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL_A_PAGAR")
    private BigDecimal capitalAPagar;

    @JoinColumn(name = "CODIGO_PAGO_CREDITO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PagoCredito pagoCredito;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO_CREDITO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false),
        @JoinColumn(name = "CUOTA", referencedColumnName = "CUOTA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CalculoCuotaPagar calculoCuotaPagar;

    public PagoCreditoDetalleCuota() {
    }

    public PagoCreditoDetalleCuota(PagoCreditoDetalleCuotaPK pagoCreditoDetalleCuotaPK) {
        this.pagoCreditoDetalleCuotaPK = pagoCreditoDetalleCuotaPK;
    }

    public PagoCreditoDetalleCuota(PagoCreditoDetalleCuotaPK pagoCreditoDetalleCuotaPK, char tipo, BigDecimal capital, long diasInteres, BigDecimal interes, long diasMora, BigDecimal mora, BigDecimal rubros, long numeroNotificaciones, BigDecimal notificaciones, BigDecimal totalPago, BigDecimal saldoCapital, BigDecimal capitalAPagar) {
        this.pagoCreditoDetalleCuotaPK = pagoCreditoDetalleCuotaPK;
        this.tipo = tipo;
        this.capital = capital;
        this.diasInteres = diasInteres;
        this.interes = interes;
        this.diasMora = diasMora;
        this.mora = mora;
        this.rubros = rubros;
        this.numeroNotificaciones = numeroNotificaciones;
        this.notificaciones = notificaciones;
        this.totalPago = totalPago;
        this.saldoCapital = saldoCapital;
        this.capitalAPagar = capitalAPagar;
    }

    public PagoCreditoDetalleCuota(long codigoPagoCredito, long numeroCredito, long codigoIfip, long cuota) {
        this.pagoCreditoDetalleCuotaPK = new PagoCreditoDetalleCuotaPK(codigoPagoCredito, numeroCredito, codigoIfip, cuota);
    }

    public PagoCreditoDetalleCuotaPK getPagoCreditoDetalleCuotaPK() {
        return pagoCreditoDetalleCuotaPK;
    }

    public void setPagoCreditoDetalleCuotaPK(PagoCreditoDetalleCuotaPK pagoCreditoDetalleCuotaPK) {
        this.pagoCreditoDetalleCuotaPK = pagoCreditoDetalleCuotaPK;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
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

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public long getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(long diasMora) {
        this.diasMora = diasMora;
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

    public long getNumeroNotificaciones() {
        return numeroNotificaciones;
    }

    public void setNumeroNotificaciones(long numeroNotificaciones) {
        this.numeroNotificaciones = numeroNotificaciones;
    }

    public BigDecimal getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(BigDecimal notificaciones) {
        this.notificaciones = notificaciones;
    }

    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(BigDecimal totalPago) {
        this.totalPago = totalPago;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public BigDecimal getCapitalAPagar() {
        return capitalAPagar;
    }

    public void setCapitalAPagar(BigDecimal capitalAPagar) {
        this.capitalAPagar = capitalAPagar;
    }

    public PagoCredito getPagoCredito() {
        return pagoCredito;
    }

    public void setPagoCredito(PagoCredito pagoCredito) {
        this.pagoCredito = pagoCredito;
    }

    public CalculoCuotaPagar getCalculoCuotaPagar() {
        return calculoCuotaPagar;
    }

    public void setCalculoCuotaPagar(CalculoCuotaPagar calculoCuotaPagar) {
        this.calculoCuotaPagar = calculoCuotaPagar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoCreditoDetalleCuotaPK != null ? pagoCreditoDetalleCuotaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCreditoDetalleCuota)) {
            return false;
        }
        PagoCreditoDetalleCuota other = (PagoCreditoDetalleCuota) object;
        if ((this.pagoCreditoDetalleCuotaPK == null && other.pagoCreditoDetalleCuotaPK != null) || (this.pagoCreditoDetalleCuotaPK != null && !this.pagoCreditoDetalleCuotaPK.equals(other.pagoCreditoDetalleCuotaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleCuota[ pagoCreditoDetalleCuotaPK=" + pagoCreditoDetalleCuotaPK + " ]";
    }

}
