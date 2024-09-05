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
public class PersonaInstitucionRepPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA_REP")
    private long codigoPersonaRep;

    public PersonaInstitucionRepPK() {
    }

    public PersonaInstitucionRepPK(long codigoPersona, long codigoPersonaRep) {
        this.codigoPersona = codigoPersona;
        this.codigoPersonaRep = codigoPersonaRep;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoPersonaRep() {
        return codigoPersonaRep;
    }

    public void setCodigoPersonaRep(long codigoPersonaRep) {
        this.codigoPersonaRep = codigoPersonaRep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPersona;
        hash += (int) codigoPersonaRep;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaInstitucionRepPK)) {
            return false;
        }
        PersonaInstitucionRepPK other = (PersonaInstitucionRepPK) object;
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        if (this.codigoPersonaRep != other.codigoPersonaRep) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaInstitucionRepPK[ codigoPersona=" + codigoPersona + ", codigoPersonaRep=" + codigoPersonaRep + " ]";
    }
    
}
