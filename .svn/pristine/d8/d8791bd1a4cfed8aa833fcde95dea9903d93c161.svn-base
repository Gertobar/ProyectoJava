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
public class GuiaDepositoChequeDetPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_GUIA_DEPOSITO")
    private long codigoGuiaDeposito;

    public GuiaDepositoChequeDetPK() {
    }

    public GuiaDepositoChequeDetPK(long codigoMoneda, long codigoGuiaDeposito) {
        this.codigoMoneda = codigoMoneda;
        this.codigoGuiaDeposito = codigoGuiaDeposito;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoGuiaDeposito() {
        return codigoGuiaDeposito;
    }

    public void setCodigoGuiaDeposito(long codigoGuiaDeposito) {
        this.codigoGuiaDeposito = codigoGuiaDeposito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoMoneda;
        hash += (int) codigoGuiaDeposito;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GuiaDepositoChequeDetPK)) {
            return false;
        }
        GuiaDepositoChequeDetPK other = (GuiaDepositoChequeDetPK) object;
        if (this.codigoMoneda != other.codigoMoneda) {
            return false;
        }
        if (this.codigoGuiaDeposito != other.codigoGuiaDeposito) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.cajas.GuiaDepositoChequeDetPK[ codigoMoneda=" + codigoMoneda + ", codigoGuiaDeposito=" + codigoGuiaDeposito + " ]";
    }
    
}
