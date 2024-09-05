/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.creditos.PagoCredito;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.MOTIVO_PAGO_CREDITO_TOTAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoPagoCreditoTotal.findAll", query = "SELECT m FROM MotivoPagoCreditoTotal m"),
    @NamedQuery(name = "MotivoPagoCreditoTotal.findByCodigoPago", query = "SELECT m FROM MotivoPagoCreditoTotal m WHERE m.motivoPagoCreditoTotalPK.codigoPago = :codigoPago"),
    @NamedQuery(name = "MotivoPagoCreditoTotal.findByCodigoTipoMotivo", query = "SELECT m FROM MotivoPagoCreditoTotal m WHERE m.motivoPagoCreditoTotalPK.codigoTipoMotivo = :codigoTipoMotivo"),
    @NamedQuery(name = "MotivoPagoCreditoTotal.findByObservaciones", query = "SELECT m FROM MotivoPagoCreditoTotal m WHERE m.observaciones = :observaciones")})
public class MotivoPagoCreditoTotal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MotivoPagoCreditoTotalPK motivoPagoCreditoTotalPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "CODIGO_TIPO_MOTIVO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoMotivoPagoCredito tipoMotivoPagoCredito;
    @JoinColumn(name = "CODIGO_PAGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PagoCredito pagoCredito;

    public MotivoPagoCreditoTotal() {
    }

    public MotivoPagoCreditoTotal(MotivoPagoCreditoTotalPK motivoPagoCreditoTotalPK) {
        this.motivoPagoCreditoTotalPK = motivoPagoCreditoTotalPK;
    }

    public MotivoPagoCreditoTotal(MotivoPagoCreditoTotalPK motivoPagoCreditoTotalPK, String observaciones) {
        this.motivoPagoCreditoTotalPK = motivoPagoCreditoTotalPK;
        this.observaciones = observaciones;
    }

    public MotivoPagoCreditoTotal(long codigoPago, long codigoTipoMotivo) {
        this.motivoPagoCreditoTotalPK = new MotivoPagoCreditoTotalPK(codigoPago, codigoTipoMotivo);
    }

    public MotivoPagoCreditoTotalPK getMotivoPagoCreditoTotalPK() {
        return motivoPagoCreditoTotalPK;
    }

    public void setMotivoPagoCreditoTotalPK(MotivoPagoCreditoTotalPK motivoPagoCreditoTotalPK) {
        this.motivoPagoCreditoTotalPK = motivoPagoCreditoTotalPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoMotivoPagoCredito getTipoMotivoPagoCredito() {
        return tipoMotivoPagoCredito;
    }

    public void setTipoMotivoPagoCredito(TipoMotivoPagoCredito tipoMotivoPagoCredito) {
        this.tipoMotivoPagoCredito = tipoMotivoPagoCredito;
    }

    public PagoCredito getPagoCredito() {
        return pagoCredito;
    }

    public void setPagoCredito(PagoCredito pagoCredito) {
        this.pagoCredito = pagoCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (motivoPagoCreditoTotalPK != null ? motivoPagoCreditoTotalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoPagoCreditoTotal)) {
            return false;
        }
        MotivoPagoCreditoTotal other = (MotivoPagoCreditoTotal) object;
        if ((this.motivoPagoCreditoTotalPK == null && other.motivoPagoCreditoTotalPK != null) || (this.motivoPagoCreditoTotalPK != null && !this.motivoPagoCreditoTotalPK.equals(other.motivoPagoCreditoTotalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.FINA.MotivoPagoCreditoTotal[ motivoPagoCreditoTotalPK=" + motivoPagoCreditoTotalPK + " ]";
    }

}
