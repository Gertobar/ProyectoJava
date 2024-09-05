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
public class ProtestoChequeDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PRO_CHE")
    private long codigoProChe;

    public ProtestoChequeDetallePK() {
    }

    public ProtestoChequeDetallePK(long codigoMoneda, long codigoProChe) {
        this.codigoMoneda = codigoMoneda;
        this.codigoProChe = codigoProChe;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoProChe() {
        return codigoProChe;
    }

    public void setCodigoProChe(long codigoProChe) {
        this.codigoProChe = codigoProChe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoMoneda;
        hash += (int) codigoProChe;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProtestoChequeDetallePK)) {
            return false;
        }
        ProtestoChequeDetallePK other = (ProtestoChequeDetallePK) object;
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoProChe != other.codigoProChe) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.ProtestoChequeDetallePK[ codigoMoneda=" + codigoMoneda + ", codigoProChe=" + codigoProChe + " ]";
    }
    
}
