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
public class MotivoPagoCreditoTotalPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PAGO")
    private long codigoPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_MOTIVO")
    private long codigoTipoMotivo;

    public MotivoPagoCreditoTotalPK() {
    }

    public MotivoPagoCreditoTotalPK(long codigoPago, long codigoTipoMotivo) {
        this.codigoPago = codigoPago;
        this.codigoTipoMotivo = codigoTipoMotivo;
    }

    public long getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(long codigoPago) {
        this.codigoPago = codigoPago;
    }

    public long getCodigoTipoMotivo() {
        return codigoTipoMotivo;
    }

    public void setCodigoTipoMotivo(long codigoTipoMotivo) {
        this.codigoTipoMotivo = codigoTipoMotivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPago;
        hash += (int) codigoTipoMotivo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoPagoCreditoTotalPK)) {
            return false;
        }
        MotivoPagoCreditoTotalPK other = (MotivoPagoCreditoTotalPK) object;
        if (this.codigoPago != other.codigoPago) {
            return false;
        }
        if (this.codigoTipoMotivo != other.codigoTipoMotivo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.FINA.MotivoPagoCreditoTotalPK[ codigoPago=" + codigoPago + ", codigoTipoMotivo=" + codigoTipoMotivo + " ]";
    }
    
}
