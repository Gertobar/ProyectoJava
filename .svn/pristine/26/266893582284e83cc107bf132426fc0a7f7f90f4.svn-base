/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

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
public class SocioSituacionPatrimonialPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOCIO")
    private long codigoSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    public SocioSituacionPatrimonialPK() {
    }

    public SocioSituacionPatrimonialPK(long codigoSocio, long codigoIfip) {
        this.codigoSocio = codigoSocio;
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(long codigoSocio) {
        this.codigoSocio = codigoSocio;
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
        hash += (int) codigoSocio;
        hash += (int) codigoIfip;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioSituacionPatrimonialPK)) {
            return false;
        }
        SocioSituacionPatrimonialPK other = (SocioSituacionPatrimonialPK) object;
        if (this.codigoSocio != other.codigoSocio) {
            return false;
        }
        if (this.codigoIfip != other.codigoIfip) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioSituacionPatrimonialPK[ codigoSocio=" + codigoSocio + ", codigoIfip=" + codigoIfip + " ]";
    }
    
}
