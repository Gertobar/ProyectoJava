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
public class PersonaActividadEconomicaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ACTIVIDAD_ECONOMICA")
    private long codigoActividadEconomica;

    public PersonaActividadEconomicaPK() {
    }

    public PersonaActividadEconomicaPK(long codigoPersona, long codigoActividadEconomica) {
        this.codigoPersona = codigoPersona;
        this.codigoActividadEconomica = codigoActividadEconomica;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoActividadEconomica() {
        return codigoActividadEconomica;
    }

    public void setCodigoActividadEconomica(long codigoActividadEconomica) {
        this.codigoActividadEconomica = codigoActividadEconomica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPersona;
        hash += (int) codigoActividadEconomica;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaActividadEconomicaPK)) {
            return false;
        }
        PersonaActividadEconomicaPK other = (PersonaActividadEconomicaPK) object;
        if (this.codigoPersona != other.codigoPersona) {
            return false;
        }
        if (this.codigoActividadEconomica != other.codigoActividadEconomica) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaActividadEconomicaPK[ codigoPersona=" + codigoPersona + ", codigoActividadEconomica=" + codigoActividadEconomica + " ]";
    }
    
}
