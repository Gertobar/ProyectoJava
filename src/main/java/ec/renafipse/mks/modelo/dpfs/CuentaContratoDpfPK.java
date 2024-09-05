/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vastudillo
 */
@Embeddable
public class CuentaContratoDpfPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONTRATO")
    private long codigoContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public CuentaContratoDpfPK() {
    }

    public CuentaContratoDpfPK(long codigoContrato, long codigoIfip) {
        this.codigoContrato = codigoContrato;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(long codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoContrato;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContratoDpfPK)) {
            return false;
        }
        CuentaContratoDpfPK other = (CuentaContratoDpfPK) object;
        if (this.codigoContrato != other.codigoContrato) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.CuentaContratoDpfPK[ codigoContrato=" + codigoContrato + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
