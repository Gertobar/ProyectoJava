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
public class EfectivizacionChequeDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_EFE_CHE")
    private long codigoEfeChe;

    public EfectivizacionChequeDetallePK() {
    }

    public EfectivizacionChequeDetallePK(long codigoMoneda, long codigoEfeChe) {
        this.codigoMoneda = codigoMoneda;
        this.codigoEfeChe = codigoEfeChe;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoEfeChe() {
        return codigoEfeChe;
    }

    public void setCodigoEfeChe(long codigoEfeChe) {
        this.codigoEfeChe = codigoEfeChe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoMoneda;
        hash += (int) codigoEfeChe;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EfectivizacionChequeDetallePK)) {
            return false;
        }
        EfectivizacionChequeDetallePK other = (EfectivizacionChequeDetallePK) object;
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoEfeChe != other.codigoEfeChe) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.EfectivizacionChequeDetallePK[ codigoMoneda=" + codigoMoneda + ", codigoEfeChe=" + codigoEfeChe + " ]";
    }
    
}
