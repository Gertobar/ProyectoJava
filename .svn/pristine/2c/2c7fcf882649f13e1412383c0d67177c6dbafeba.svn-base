/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Viajes2
 */
@Embeddable
public class BovedaDineroPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_BOVEDA")
    private long codigoBoveda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;

    public BovedaDineroPK() {
    }

    public BovedaDineroPK(long codigoBoveda, long codigoMoneda) {
        this.codigoBoveda = codigoBoveda;
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoBoveda() {
        return codigoBoveda;
    }

    public void setCodigoBoveda(long codigoBoveda) {
        this.codigoBoveda = codigoBoveda;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoBoveda;
        hash += (int) codigoMoneda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BovedaDineroPK)) {
            return false;
        }
        BovedaDineroPK other = (BovedaDineroPK) object;
        if (this.codigoBoveda != other.codigoBoveda) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.BovedaDineroPK[ codigoBoveda=" + codigoBoveda + ", codigoMoneda=" + codigoMoneda + " ]";
    }
    
}
