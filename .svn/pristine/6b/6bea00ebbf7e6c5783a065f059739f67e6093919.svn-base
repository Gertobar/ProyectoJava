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
 * @author mvks
 */
@Embeddable
public class PersonaVinculadoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA", nullable = false)
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA_VINCULADO", nullable = false)
    private long codigoPersonaVinculado;

    public PersonaVinculadoPK() {
    }

    public PersonaVinculadoPK(long codigoPersona, long codigoPersonaVinculado) {
        this.codigoPersona = codigoPersona;
        this.codigoPersonaVinculado = codigoPersonaVinculado;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoPersonaVinculado() {
        return codigoPersonaVinculado;
    }

    public void setCodigoPersonaVinculado(long codigoPersonaVinculado) {
        this.codigoPersonaVinculado = codigoPersonaVinculado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPersona;
        hash += (int) codigoPersonaVinculado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaVinculadoPK)) {
            return false;
        }
        PersonaVinculadoPK other = (PersonaVinculadoPK) object;
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        if (this.codigoPersonaVinculado != other.codigoPersonaVinculado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaVinculadoPK[ codigoPersona=" + codigoPersona + ", codigoPersonaVinculado=" + codigoPersonaVinculado + " ]";
    }
    
}
