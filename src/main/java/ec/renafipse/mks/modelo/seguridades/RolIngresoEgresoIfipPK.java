/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vastudillo
 */
@Embeddable
public class RolIngresoEgresoIfipPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_INGRESO_EGRESO")
    private long codigoIngresoEgreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO_ROL")
    private String codigoRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public RolIngresoEgresoIfipPK() {
    }

    public RolIngresoEgresoIfipPK(long codigoIngresoEgreso, long codigoMoneda, String codigoRol, long codigoIfip) {
        this.codigoIngresoEgreso = codigoIngresoEgreso;
        this.codigoMoneda = codigoMoneda;
        this.codigoRol = codigoRol;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoIngresoEgreso() {
        return codigoIngresoEgreso;
    }

    public void setCodigoIngresoEgreso(long codigoIngresoEgreso) {
        this.codigoIngresoEgreso = codigoIngresoEgreso;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
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
        hash += (int) codigoIngresoEgreso;
        hash += (int) codigoMoneda;
        hash += (codigoRol != null ? codigoRol.hashCode() : 0);
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolIngresoEgresoIfipPK)) {
            return false;
        }
        RolIngresoEgresoIfipPK other = (RolIngresoEgresoIfipPK) object;
        if (this.codigoIngresoEgreso != other.codigoIngresoEgreso) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if ((this.codigoRol == null && other.codigoRol != null) || (this.codigoRol != null && !this.codigoRol.equals(other.codigoRol))) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolIngresoEgresoIfipPK[ codigoIngresoEgreso=" + codigoIngresoEgreso + ", codigoMoneda=" + codigoMoneda + ", codigoRol=" + codigoRol + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
