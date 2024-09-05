/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_INSTITUCION_REP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaInstitucionRep.findAll", query = "SELECT p FROM PersonaInstitucionRep p"),
    @NamedQuery(name = "PersonaInstitucionRep.findByCodigoPersona", query = "SELECT p FROM PersonaInstitucionRep p WHERE p.personaInstitucionRepPK.codigoPersona = :codigoPersona"),
        @NamedQuery(name = "PersonaInstitucionRep.findByCodigoPersonaRep", query = "SELECT p FROM PersonaInstitucionRep p WHERE p.personaInstitucionRepPK.codigoPersonaRep = :codigoPersonaRep"),
    @NamedQuery(name = "PersonaInstitucionRep.findByEliminado", query = "SELECT p FROM PersonaInstitucionRep p WHERE p.eliminado = :eliminado")})
public class PersonaInstitucionRep implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPersonaRep = "PersonaInstitucionRep.findByCodigoPersonaRep";
    @EmbeddedId
    protected PersonaInstitucionRepPK personaInstitucionRepPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PERSONA_REP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO_PERSONA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PersonaInstitucion personaInstitucion;
    @JoinColumn(name = "CODIGO_NIVEL", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Nivel codigoNivel;
    @JoinColumn(name = "CODIGO_CARGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Cargo codigoCargo;

        public PersonaInstitucionRep() {
    }

    public PersonaInstitucionRep(PersonaInstitucionRepPK personaInstitucionRepPK) {
        this.personaInstitucionRepPK = personaInstitucionRepPK;
    }

    public PersonaInstitucionRep(PersonaInstitucionRepPK personaInstitucionRepPK, char eliminado) {
        this.personaInstitucionRepPK = personaInstitucionRepPK;
        this.eliminado = eliminado;
    }

    public PersonaInstitucionRep(long codigoPersona, long codigoPersonaRep) {
        this.personaInstitucionRepPK = new PersonaInstitucionRepPK(codigoPersona, codigoPersonaRep);
    }

    public PersonaInstitucionRepPK getPersonaInstitucionRepPK() {
        return personaInstitucionRepPK;
    }

    public void setPersonaInstitucionRepPK(PersonaInstitucionRepPK personaInstitucionRepPK) {
        this.personaInstitucionRepPK = personaInstitucionRepPK;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PersonaInstitucion getPersonaInstitucion() {
        return personaInstitucion;
    }

    public void setPersonaInstitucion(PersonaInstitucion personaInstitucion) {
        this.personaInstitucion = personaInstitucion;
    }

    public Nivel getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Nivel codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public Cargo getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Cargo codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaInstitucionRepPK != null ? personaInstitucionRepPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaInstitucionRep)) {
            return false;
        }
        PersonaInstitucionRep other = (PersonaInstitucionRep) object;
        if ((this.personaInstitucionRepPK == null && other.personaInstitucionRepPK != null) || (this.personaInstitucionRepPK != null && !this.personaInstitucionRepPK.equals(other.personaInstitucionRepPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaInstitucionRep[ personaInstitucionRepPK=" + personaInstitucionRepPK + " ]";
    }
    
}
