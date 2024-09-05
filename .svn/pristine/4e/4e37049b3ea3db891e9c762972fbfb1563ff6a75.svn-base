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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_TEL_TRA_ACT_ECO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaTelTraActEco.findAll", query = "SELECT p FROM PersonaTelTraActEco p"),
    @NamedQuery(name = "PersonaTelTraActEco.findByCodigoPerTraActEco", query = "SELECT p FROM PersonaTelTraActEco p WHERE p.personaTelTraActEcoPK.codigoPerTraActEco = :codigoPerTraActEco"),
    @NamedQuery(name = "PersonaTelTraActEco.findByCodigoTipoTelefono", query = "SELECT p FROM PersonaTelTraActEco p WHERE p.codigoTipoTelefono = :codigoTipoTelefono"),
    @NamedQuery(name = "PersonaTelTraActEco.findByCodigoOperadoraTelefono", query = "SELECT p FROM PersonaTelTraActEco p WHERE p.codigoOperadoraTelefono = :codigoOperadoraTelefono"),
    @NamedQuery(name = "PersonaTelTraActEco.findByNumero", query = "SELECT p FROM PersonaTelTraActEco p WHERE p.personaTelTraActEcoPK.numero = :numero"),
    @NamedQuery(name = "PersonaTelTraActEco.findByObservaciones", query = "SELECT p FROM PersonaTelTraActEco p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "PersonaTelTraActEco.findByEliminado", query = "SELECT p FROM PersonaTelTraActEco p WHERE p.eliminado = :eliminado")})
public class PersonaTelTraActEco implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaTelTraActEcoPK personaTelTraActEcoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_TELEFONO")
    private long codigoTipoTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_OPERADORA_TELEFONO")
    private long codigoOperadoraTelefono;
    @Size(max = 50)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_PER_TRA_ACT_ECO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PersonaTrabajoActEco personaTrabajoActEco;

    public PersonaTelTraActEco() {
    }

    public PersonaTelTraActEco(PersonaTelTraActEcoPK personaTelTraActEcoPK) {
        this.personaTelTraActEcoPK = personaTelTraActEcoPK;
    }

    public PersonaTelTraActEco(PersonaTelTraActEcoPK personaTelTraActEcoPK, long codigoTipoTelefono, long codigoOperadoraTelefono, char eliminado) {
        this.personaTelTraActEcoPK = personaTelTraActEcoPK;
        this.codigoTipoTelefono = codigoTipoTelefono;
        this.codigoOperadoraTelefono = codigoOperadoraTelefono;
        this.eliminado = eliminado;
    }

    public PersonaTelTraActEco(long codigoPerTraActEco, String numero) {
        this.personaTelTraActEcoPK = new PersonaTelTraActEcoPK(codigoPerTraActEco, numero);
    }

    public PersonaTelTraActEcoPK getPersonaTelTraActEcoPK() {
        return personaTelTraActEcoPK;
    }

    public void setPersonaTelTraActEcoPK(PersonaTelTraActEcoPK personaTelTraActEcoPK) {
        this.personaTelTraActEcoPK = personaTelTraActEcoPK;
    }

    public long getCodigoTipoTelefono() {
        return codigoTipoTelefono;
    }

    public void setCodigoTipoTelefono(long codigoTipoTelefono) {
        this.codigoTipoTelefono = codigoTipoTelefono;
    }

    public long getCodigoOperadoraTelefono() {
        return codigoOperadoraTelefono;
    }

    public void setCodigoOperadoraTelefono(long codigoOperadoraTelefono) {
        this.codigoOperadoraTelefono = codigoOperadoraTelefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public PersonaTrabajoActEco getPersonaTrabajoActEco() {
        return personaTrabajoActEco;
    }

    public void setPersonaTrabajoActEco(PersonaTrabajoActEco personaTrabajoActEco) {
        this.personaTrabajoActEco = personaTrabajoActEco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaTelTraActEcoPK != null ? personaTelTraActEcoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaTelTraActEco)) {
            return false;
        }
        PersonaTelTraActEco other = (PersonaTelTraActEco) object;
        if ((this.personaTelTraActEcoPK == null && other.personaTelTraActEcoPK != null) || (this.personaTelTraActEcoPK != null && !this.personaTelTraActEcoPK.equals(other.personaTelTraActEcoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaTelTraActEco[ personaTelTraActEcoPK=" + personaTelTraActEcoPK + " ]";
    }
    
}
