/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author v.astudillo
 */
@Embeddable
public class CuentaContableItemVentaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ITEM_VENTA")
    private long codigoItemVenta;

    public CuentaContableItemVentaPK() {
    }

    public CuentaContableItemVentaPK(long codigoIfip, long codigoItemVenta) {
        this.codigoIfip = codigoIfip;
        this.codigoItemVenta = codigoItemVenta;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoItemVenta() {
        return codigoItemVenta;
    }

    public void setCodigoItemVenta(long codigoItemVenta) {
        this.codigoItemVenta = codigoItemVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoIfip;
        hash += (int) codigoItemVenta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContableItemVentaPK)) {
            return false;
        }
        CuentaContableItemVentaPK other = (CuentaContableItemVentaPK) object;
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.codigoItemVenta != other.codigoItemVenta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.CuentaContableItemVentaPK[ codigoIfip=" + codigoIfip + ", codigoItemVenta=" + codigoItemVenta + " ]";
    }
    
}
