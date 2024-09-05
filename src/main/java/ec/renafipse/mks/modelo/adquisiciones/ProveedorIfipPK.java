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
public class ProveedorIfipPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROVEEDOR")
    private long codigoProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public ProveedorIfipPK() {
    }

    public ProveedorIfipPK(long codigoProveedor, long codigoIfip) {
        this.codigoProveedor = codigoProveedor;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(long codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoProveedor;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorIfipPK)) {
            return false;
        }
        ProveedorIfipPK other = (ProveedorIfipPK) object;
        if (this.codigoProveedor != other.codigoProveedor) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.ProveedorIfipPK[ codigoProveedor=" + codigoProveedor + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
