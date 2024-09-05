/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.seguridades;

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
public class IfipSistemaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SISTEMA")
    private long codigoSistema;

    public IfipSistemaPK() {
    }

    public IfipSistemaPK(long codigoIfip, long codigoSistema) {
        this.codigoIfip = codigoIfip;
        this.codigoSistema = codigoSistema;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(long codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoIfip;
        hash += (int) codigoSistema;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfipSistemaPK)) {
            return false;
        }
        IfipSistemaPK other = (IfipSistemaPK) object;
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        if (this.codigoSistema != other.codigoSistema) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.seguridades.IfipSistemaPK[ codigoIfip=" + codigoIfip + ", codigoSistema=" + codigoSistema + " ]";
    }
    
}
