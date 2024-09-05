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
public class PeriodicidadProMonIfiPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PRODUCTO")
    private long codigoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERIODICIDAD")
    private long codigoPeriodicidad;

    public PeriodicidadProMonIfiPK() {
    }

    public PeriodicidadProMonIfiPK(long codigoProducto, long codigoMoneda, long codigoIfip, long codigoPeriodicidad) {
        this.codigoProducto = codigoProducto;
        this.codigoMoneda = codigoMoneda;
        this.codigoIfip = codigoIfip;
        this.codigoPeriodicidad = codigoPeriodicidad;
    }

    public long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoPeriodicidad() {
        return codigoPeriodicidad;
    }

    public void setCodigoPeriodicidad(long codigoPeriodicidad) {
        this.codigoPeriodicidad = codigoPeriodicidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoProducto;
        hash += (int) codigoMoneda;
        hash += (int) codigoIfip;
        hash += (int) codigoPeriodicidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodicidadProMonIfiPK)) {
            return false;
        }
        PeriodicidadProMonIfiPK other = (PeriodicidadProMonIfiPK) object;
        if (this.codigoProducto != other.codigoProducto) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.codigoPeriodicidad != other.codigoPeriodicidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PeriodicidadProMonIfiPK[ codigoProducto=" + codigoProducto + ", codigoMoneda=" + codigoMoneda + ", codigoIfip=" + codigoIfip + ", codigoPeriodicidad=" + codigoPeriodicidad + " ]";
    }
    
}
