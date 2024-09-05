/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

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
public class PagoCompraDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PAGO")
    private long codigoPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_FORMA_PAGO")
    private long codigoFormaPago;

    public PagoCompraDetallePK() {
    }

    public PagoCompraDetallePK(long codigoPago, long codigoFormaPago) {
        this.codigoPago = codigoPago;
        this.codigoFormaPago = codigoFormaPago;
    }

    public long getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(long codigoPago) {
        this.codigoPago = codigoPago;
    }

    public long getCodigoFormaPago() {
        return codigoFormaPago;
    }

    public void setCodigoFormaPago(long codigoFormaPago) {
        this.codigoFormaPago = codigoFormaPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPago;
        hash += (int) codigoFormaPago;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoCompraDetallePK)) {
            return false;
        }
        PagoCompraDetallePK other = (PagoCompraDetallePK) object;
        if (this.codigoPago != other.codigoPago) {
            return false;
        }
        if (this.codigoFormaPago != other.codigoFormaPago) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.PagoCompraDetallePK[ codigoPago=" + codigoPago + ", codigoFormaPago=" + codigoFormaPago + " ]";
    }
    
}
