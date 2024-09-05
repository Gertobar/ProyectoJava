/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author v.astudillo
 */
@Embeddable
public class TalonarioChequeDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CHEQUE")
    private long numeroCheque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_ENT_FIN")
    private long codigoCuentaEntFin;

    public TalonarioChequeDetallePK() {
    }

    public TalonarioChequeDetallePK(long numeroCheque, long codigoCuentaEntFin) {
        this.numeroCheque = numeroCheque;
        this.codigoCuentaEntFin = codigoCuentaEntFin;
    }

    public long getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(long numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public long getCodigoCuentaEntFin() {
        return codigoCuentaEntFin;
    }

    public void setCodigoCuentaEntFin(long codigoCuentaEntFin) {
        this.codigoCuentaEntFin = codigoCuentaEntFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroCheque;
        hash += (int) codigoCuentaEntFin;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TalonarioChequeDetallePK)) {
            return false;
        }
        TalonarioChequeDetallePK other = (TalonarioChequeDetallePK) object;
        if (this.numeroCheque != other.numeroCheque) {
            return false;
        }
        if (this.codigoCuentaEntFin != other.codigoCuentaEntFin) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.modelo.ahorros.TalonarioChequeDetallePK[ numeroCheque=" + numeroCheque + ", codigoCuentaEntFin=" + codigoCuentaEntFin + " ]";
    }
    
}
