/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.cajas;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vicastoc
 */
@Embeddable
public class FondeoCajaDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_FONDEO")
    private long codigoFondeo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;

    public FondeoCajaDetallePK() {
    }

    public FondeoCajaDetallePK(long codigoFondeo, long codigoMoneda) {
        this.codigoFondeo = codigoFondeo;
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoFondeo() {
        return codigoFondeo;
    }

    public void setCodigoFondeo(long codigoFondeo) {
        this.codigoFondeo = codigoFondeo;
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
        hash += (int) codigoFondeo;
        hash += (int) codigoMoneda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FondeoCajaDetallePK)) {
            return false;
        }
        FondeoCajaDetallePK other = (FondeoCajaDetallePK) object;
        if (this.codigoFondeo != other.codigoFondeo) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.FondeoCajaDetallePK[ codigoFondeo=" + codigoFondeo + ", codigoMoneda=" + codigoMoneda + " ]";
    }
    
}
