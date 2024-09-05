/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vastudillo
 */
@Embeddable
public class PagoCreditoDetalleNotPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PAGO_CREDITO")
    private long codigoPagoCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CREDITO")
    private long numeroCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUOTA")
    private long cuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_NOTIFICACION")
    private long codigoNotificacion;

    public PagoCreditoDetalleNotPK() {
    }

    public PagoCreditoDetalleNotPK(long codigoPagoCredito, long numeroCredito, long codigoIfip, long cuota, long codigoNotificacion) {
        this.codigoPagoCredito = codigoPagoCredito;
        this.numeroCredito = numeroCredito;
        this.codigoIfip = codigoIfip;
        this.cuota = cuota;
        this.codigoNotificacion = codigoNotificacion;
    }

    public long getCodigoPagoCredito() {
        return codigoPagoCredito;
    }

    public void setCodigoPagoCredito(long codigoPagoCredito) {
        this.codigoPagoCredito = codigoPagoCredito;
    }

    public long getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCuota() {
        return cuota;
    }

    public void setCuota(long cuota) {
        this.cuota = cuota;
    }

    public long getCodigoNotificacion() {
        return codigoNotificacion;
    }

    public void setCodigoNotificacion(long codigoNotificacion) {
        this.codigoNotificacion = codigoNotificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPagoCredito;
        hash += (int) numeroCredito;
        hash += (int) codigoIfip;
        hash += (int) cuota;
        hash += (int) codigoNotificacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCreditoDetalleNotPK)) {
            return false;
        }
        PagoCreditoDetalleNotPK other = (PagoCreditoDetalleNotPK) object;
        if (this.codigoPagoCredito != other.codigoPagoCredito) {
            return false;
        }
        if (this.numeroCredito != other.numeroCredito) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.cuota != other.cuota) {
            return false;
        }
        if (this.codigoNotificacion != other.codigoNotificacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleNotPK[ codigoPagoCredito=" + codigoPagoCredito + ", numeroCredito=" + numeroCredito + ", codigoIfip=" + codigoIfip + ", cuota=" + cuota + ", codigoNotificacion=" + codigoNotificacion + " ]";
    }
    
}
