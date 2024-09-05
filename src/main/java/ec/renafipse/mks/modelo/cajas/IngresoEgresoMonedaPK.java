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
public class IngresoEgresoMonedaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_INGRESO_EGRESO")
    private long codigoIngresoEgreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;

    public IngresoEgresoMonedaPK() {
    }

    public IngresoEgresoMonedaPK(long codigoIngresoEgreso, long codigoMoneda) {
        this.codigoIngresoEgreso = codigoIngresoEgreso;
        this.codigoMoneda = codigoMoneda;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoIngresoEgreso;
        hash += (int) codigoMoneda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoEgresoMonedaPK)) {
            return false;
        }
        IngresoEgresoMonedaPK other = (IngresoEgresoMonedaPK) object;
        if (this.codigoIngresoEgreso != other.codigoIngresoEgreso) {
            return false;
        }
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.IngresoEgresoMonedaPK[ codigoIngresoEgreso=" + codigoIngresoEgreso + ", codigoMoneda=" + codigoMoneda + " ]";
    }
    
}
