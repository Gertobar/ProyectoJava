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
public class PersonaConyugePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA_CONYUGE")
    private long codigoPersonaConyuge;

    public PersonaConyugePK() {
    }

    public PersonaConyugePK(long codigoPersona, long codigoPersonaConyuge) {
        this.codigoPersona = codigoPersona;
        this.codigoPersonaConyuge = codigoPersonaConyuge;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoPersonaConyuge() {
        return codigoPersonaConyuge;
    }

    public void setCodigoPersonaConyuge(long codigoPersonaConyuge) {
        this.codigoPersonaConyuge = codigoPersonaConyuge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPersona;
        hash += (int) codigoPersonaConyuge;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaConyugePK)) {
            return false;
        }
        PersonaConyugePK other = (PersonaConyugePK) object;
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        if (this.codigoPersonaConyuge != other.codigoPersonaConyuge) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaConyugePK[ codigoPersona=" + codigoPersona + ", codigoPersonaConyuge=" + codigoPersonaConyuge + " ]";
    }
    
}
