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
 * @author vicastoc
 */
@Embeddable
public class RolOpcionOperacionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_OPERACION")
    private long codigoOperacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO_ROL")
    private String codigoRol;

    public RolOpcionOperacionPK() {
    }

    public RolOpcionOperacionPK(long codigoOperacion, long codigoIfip, String codigoRol) {
        this.codigoOperacion = codigoOperacion;
        this.codigoIfip = codigoIfip;
        this.codigoRol = codigoRol;
    }

    public long getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(long codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoOperacion;
        hash += (int) codigoIfip;
        hash += (codigoRol != null ? codigoRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolOpcionOperacionPK)) {
            return false;
        }
        RolOpcionOperacionPK other = (RolOpcionOperacionPK) object;
        if (this.codigoOperacion != other.codigoOperacion) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if ((this.codigoRol == null && other.codigoRol != null) || (this.codigoRol != null && !this.codigoRol.equals(other.codigoRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.RolOpcionOperacionPK[ codigoOperacion=" + codigoOperacion + ", codigoIfip=" + codigoIfip + ", codigoRol=" + codigoRol + " ]";
    }
    
}
