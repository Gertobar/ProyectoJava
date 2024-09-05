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
 * @author andres
 */
@Embeddable
public class SocioSituacionPatActPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ITEM_SIT_PAT")
    private long codigoItemSitPat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;

    public SocioSituacionPatActPK() {
    }

    public SocioSituacionPatActPK(long codigoItemSitPat, long codigoPersona) {
        this.codigoItemSitPat = codigoItemSitPat;
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoItemSitPat() {
        return codigoItemSitPat;
    }

    public void setCodigoItemSitPat(long codigoItemSitPat) {
        this.codigoItemSitPat = codigoItemSitPat;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoItemSitPat;
        hash += (int) codigoPersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioSituacionPatActPK)) {
            return false;
        }
        SocioSituacionPatActPK other = (SocioSituacionPatActPK) object;
        if (this.codigoItemSitPat != other.codigoItemSitPat) {
            return false;
        }
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.SocioSituacionPatActPK[ codigoItemSitPat=" + codigoItemSitPat + ", codigoPersona=" + codigoPersona + " ]";
    }
    
}