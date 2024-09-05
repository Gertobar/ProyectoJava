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
public class TransferenciaChequeDepDetPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TRANSFERENCIA")
    private long codigoTransferencia;

    public TransferenciaChequeDepDetPK() {
    }

    public TransferenciaChequeDepDetPK(long codigoMoneda, long codigoTransferencia) {
        this.codigoMoneda = codigoMoneda;
        this.codigoTransferencia = codigoTransferencia;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoTransferencia() {
        return codigoTransferencia;
    }

    public void setCodigoTransferencia(long codigoTransferencia) {
        this.codigoTransferencia = codigoTransferencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoMoneda;
        hash += (int) codigoTransferencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransferenciaChequeDepDetPK)) {
            return false;
        }
        TransferenciaChequeDepDetPK other = (TransferenciaChequeDepDetPK) object;
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoTransferencia != other.codigoTransferencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.TransferenciaChequeDepDetPK[ codigoMoneda=" + codigoMoneda + ", codigoTransferencia=" + codigoTransferencia + " ]";
    }
    
}
