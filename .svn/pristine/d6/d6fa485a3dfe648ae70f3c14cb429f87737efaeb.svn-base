/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PAGO_CREDITO_DETALLE_RUBRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCreditoDetalleRubro.findAll", query = "SELECT p FROM PagoCreditoDetalleRubro p"),
    @NamedQuery(name = "PagoCreditoDetalleRubro.findByCodigoPagoCredito", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.pagoCreditoDetalleRubroPK.codigoPagoCredito = :codigoPagoCredito"),
    @NamedQuery(name = "PagoCreditoDetalleRubro.findByNumeroCredito", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.pagoCreditoDetalleRubroPK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "PagoCreditoDetalleRubro.findByCodigoIfip", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.pagoCreditoDetalleRubroPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "PagoCreditoDetalleRubro.findByCuota", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.pagoCreditoDetalleRubroPK.cuota = :cuota"),
    @NamedQuery(name = "PagoCreditoDetalleRubro.findByCodigoRubro", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.pagoCreditoDetalleRubroPK.codigoRubro = :codigoRubro"),
    @NamedQuery(name = "PagoCreditoDetalleRubro.findByTipo", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PagoCreditoDetalleRubro.findByValor", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.valor = :valor"),
    @NamedQuery(name = "PagoCreditoDetalleRubro.findBySaldo", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.saldo = :saldo"),
    //Personalizadas
    @NamedQuery(name = "PagoCreditoDetalleRubro.findByCodigoPagoCreditoDetalleCuota", query = "SELECT p FROM PagoCreditoDetalleRubro p WHERE p.pagoCreditoDetalleRubroPK.codigoPagoCredito = :codigoPagoCredito AND p.pagoCreditoDetalleRubroPK.cuota = :cuota ORDER BY p.pagoCreditoDetalleRubroPK.codigoRubro")
})
public class PagoCreditoDetalleRubro implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPagoCreditoDetalleCuota = "PagoCreditoDetalleRubro.findByCodigoPagoCreditoDetalleCuota";
    @EmbeddedId
    protected PagoCreditoDetalleRubroPK pagoCreditoDetalleRubroPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO_CREDITO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false),
        @JoinColumn(name = "CUOTA", referencedColumnName = "CUOTA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_PAGO_CREDITO", referencedColumnName = "CODIGO_PAGO_CREDITO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PagoCreditoDetalleCuota pagoCreditoDetalleCuota;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO_CREDITO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false),
        @JoinColumn(name = "CUOTA", referencedColumnName = "CUOTA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_RUBRO", referencedColumnName = "CODIGO_RUBRO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private RubroTablaAmortizacion rubroTablaAmortizacion;

    public PagoCreditoDetalleRubro() {
    }

    public PagoCreditoDetalleRubro(PagoCreditoDetalleRubroPK pagoCreditoDetalleRubroPK) {
        this.pagoCreditoDetalleRubroPK = pagoCreditoDetalleRubroPK;
    }

    public PagoCreditoDetalleRubro(PagoCreditoDetalleRubroPK pagoCreditoDetalleRubroPK, char tipo, BigDecimal valor, BigDecimal saldo) {
        this.pagoCreditoDetalleRubroPK = pagoCreditoDetalleRubroPK;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    public PagoCreditoDetalleRubro(long codigoPagoCredito, long numeroCredito, long codigoIfip, long cuota, long codigoRubro) {
        this.pagoCreditoDetalleRubroPK = new PagoCreditoDetalleRubroPK(codigoPagoCredito, numeroCredito, codigoIfip, cuota, codigoRubro);
    }

    public PagoCreditoDetalleRubroPK getPagoCreditoDetalleRubroPK() {
        return pagoCreditoDetalleRubroPK;
    }

    public void setPagoCreditoDetalleRubroPK(PagoCreditoDetalleRubroPK pagoCreditoDetalleRubroPK) {
        this.pagoCreditoDetalleRubroPK = pagoCreditoDetalleRubroPK;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public PagoCreditoDetalleCuota getPagoCreditoDetalleCuota() {
        return pagoCreditoDetalleCuota;
    }

    public void setPagoCreditoDetalleCuota(PagoCreditoDetalleCuota pagoCreditoDetalleCuota) {
        this.pagoCreditoDetalleCuota = pagoCreditoDetalleCuota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoCreditoDetalleRubroPK != null ? pagoCreditoDetalleRubroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCreditoDetalleRubro)) {
            return false;
        }
        PagoCreditoDetalleRubro other = (PagoCreditoDetalleRubro) object;
        if ((this.pagoCreditoDetalleRubroPK == null && other.pagoCreditoDetalleRubroPK != null) || (this.pagoCreditoDetalleRubroPK != null && !this.pagoCreditoDetalleRubroPK.equals(other.pagoCreditoDetalleRubroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubro[ pagoCreditoDetalleRubroPK=" + pagoCreditoDetalleRubroPK + " ]";
    }

    /**
     * @return the rubroTablaAmortizacion
     */
    public RubroTablaAmortizacion getRubroTablaAmortizacion() {
        return rubroTablaAmortizacion;
    }

    /**
     * @param rubroTablaAmortizacion the rubroTablaAmortizacion to set
     */
    public void setRubroTablaAmortizacion(RubroTablaAmortizacion rubroTablaAmortizacion) {
        this.rubroTablaAmortizacion = rubroTablaAmortizacion;
    }

}
