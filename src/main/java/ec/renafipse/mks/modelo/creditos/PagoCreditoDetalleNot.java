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
@Table(name = "MKS_CREDITOS.PAGO_CREDITO_DETALLE_NOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCreditoDetalleNot.findAll", query = "SELECT p FROM PagoCreditoDetalleNot p"),
    @NamedQuery(name = "PagoCreditoDetalleNot.findByCodigoPagoCredito", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.pagoCreditoDetalleNotPK.codigoPagoCredito = :codigoPagoCredito"),
    @NamedQuery(name = "PagoCreditoDetalleNot.findByNumeroCredito", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.pagoCreditoDetalleNotPK.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "PagoCreditoDetalleNot.findByCodigoIfip", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.pagoCreditoDetalleNotPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "PagoCreditoDetalleNot.findByCuota", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.pagoCreditoDetalleNotPK.cuota = :cuota"),
    @NamedQuery(name = "PagoCreditoDetalleNot.findByCodigoNotificacion", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.pagoCreditoDetalleNotPK.codigoNotificacion = :codigoNotificacion"),
    @NamedQuery(name = "PagoCreditoDetalleNot.findByTipo", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PagoCreditoDetalleNot.findByValor", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.valor = :valor"),
    @NamedQuery(name = "PagoCreditoDetalleNot.findBySaldo", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.saldo = :saldo"),
//Personalizadas
    @NamedQuery(name = "PagoCreditoDetalleNot.findByCodigoPagoCreditoDetalleCuota", query = "SELECT p FROM PagoCreditoDetalleNot p WHERE p.pagoCreditoDetalleNotPK.codigoPagoCredito = :codigoPagoCredito AND p.pagoCreditoDetalleNotPK.cuota = :cuota ORDER BY p.pagoCreditoDetalleNotPK.codigoNotificacion")
})
public class PagoCreditoDetalleNot implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPagoCreditoDetalleCuota = "PagoCreditoDetalleNot.findByCodigoPagoCreditoDetalleCuota";

    @EmbeddedId
    protected PagoCreditoDetalleNotPK pagoCreditoDetalleNotPK;
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
    @JoinColumn(name = "CODIGO_NOTIFICACION", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NotificacionCredito notificacionCredito;

    public PagoCreditoDetalleNot() {
    }

    public PagoCreditoDetalleNot(PagoCreditoDetalleNotPK pagoCreditoDetalleNotPK) {
        this.pagoCreditoDetalleNotPK = pagoCreditoDetalleNotPK;
    }

    public PagoCreditoDetalleNot(PagoCreditoDetalleNotPK pagoCreditoDetalleNotPK, char tipo, BigDecimal valor, BigDecimal saldo) {
        this.pagoCreditoDetalleNotPK = pagoCreditoDetalleNotPK;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    public PagoCreditoDetalleNot(long codigoPagoCredito, long numeroCredito, long codigoIfip, long cuota, long codigoNotificacion) {
        this.pagoCreditoDetalleNotPK = new PagoCreditoDetalleNotPK(codigoPagoCredito, numeroCredito, codigoIfip, cuota, codigoNotificacion);
    }

    public PagoCreditoDetalleNotPK getPagoCreditoDetalleNotPK() {
        return pagoCreditoDetalleNotPK;
    }

    public void setPagoCreditoDetalleNotPK(PagoCreditoDetalleNotPK pagoCreditoDetalleNotPK) {
        this.pagoCreditoDetalleNotPK = pagoCreditoDetalleNotPK;
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

    public NotificacionCredito getNotificacionCredito() {
        return notificacionCredito;
    }

    public void setNotificacionCredito(NotificacionCredito notificacionCredito) {
        this.notificacionCredito = notificacionCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoCreditoDetalleNotPK != null ? pagoCreditoDetalleNotPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCreditoDetalleNot)) {
            return false;
        }
        PagoCreditoDetalleNot other = (PagoCreditoDetalleNot) object;
        if ((this.pagoCreditoDetalleNotPK == null && other.pagoCreditoDetalleNotPK != null) || (this.pagoCreditoDetalleNotPK != null && !this.pagoCreditoDetalleNotPK.equals(other.pagoCreditoDetalleNotPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleNot[ pagoCreditoDetalleNotPK=" + pagoCreditoDetalleNotPK + " ]";
    }

}
