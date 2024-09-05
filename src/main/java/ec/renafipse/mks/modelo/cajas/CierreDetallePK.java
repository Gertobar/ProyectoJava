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
public class CierreDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_APERTURA")
    private long codigoApertura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;

    public CierreDetallePK() {
    }

    public CierreDetallePK(long codigoApertura, long codigoMoneda) {
        this.codigoApertura = codigoApertura;
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoApertura() {
        return codigoApertura;
    }

    public void setCodigoApertura(long codigoApertura) {
        this.codigoApertura = codigoApertura;
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
        hash += (int) codigoApertura;
        hash += (int) codigoMoneda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CierreDetallePK)) {
            return false;
        }
        CierreDetallePK other = (CierreDetallePK) object;
        if (this.codigoApertura != other.codigoApertura) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.CierreDetallePK[ codigoApertura=" + codigoApertura + ", codigoMoneda=" + codigoMoneda + " ]";
    }
    
}
