/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.socios.Persona;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel Saca
 */
@Entity
@Table(name = "PERSONA_VINCULADO", catalog = "", schema = "MKS_SOCIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaVinculado.findAll", query = "SELECT p FROM PersonaVinculado p")
    , @NamedQuery(name = "PersonaVinculado.findByCodigoPersona", query = "SELECT p FROM PersonaVinculado p WHERE p.personaVinculadoPK.codigoPersona = :codigoPersona")
    , @NamedQuery(name = "PersonaVinculado.findByCodigoPersonaVinculado", query = "SELECT p FROM PersonaVinculado p WHERE p.personaVinculadoPK.codigoPersonaVinculado = :codigoPersonaVinculado")
    , @NamedQuery(name = "PersonaVinculado.findByEliminado", query = "SELECT p FROM PersonaVinculado p WHERE p.eliminado = :eliminado")
    , @NamedQuery(name = "PersonaVinculado.findByRegistradoPor", query = "SELECT p FROM PersonaVinculado p WHERE p.registradoPor = :registradoPor")
    , @NamedQuery(name = "PersonaVinculado.findByFechaRegistro", query = "SELECT p FROM PersonaVinculado p WHERE p.fechaRegistro = :fechaRegistro")
        
    , @NamedQuery(name = "PersonaVinculado.findByVinculadoYpersona", query = "SELECT p FROM PersonaVinculado p WHERE p.persona.codigo = :codigoPersona OR p.persona1.codigo = :codigoPersona")
    , @NamedQuery(name = "PersonaVinculado.findByVinculadoNoEliminado", query = "SELECT p FROM PersonaVinculado p WHERE p.persona.codigo = :codigoPersona OR p.persona1.codigo = :codigoPersona and p.eliminado = :eliminado")})
public class PersonaVinculado implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByVinculadoYpersona = "PersonaVinculado.findByVinculadoYpersona";
    public static final String findByVinculadoNoEliminado = "PersonaVinculado.findByVinculadoNoEliminado";
    @EmbeddedId
    protected PersonaVinculadoPK personaVinculadoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO", nullable = false)
    private Character eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR", nullable = false)
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona persona;
    @JoinColumn(name = "CODIGO_PERSONA_VINCULADO", referencedColumnName = "CODIGO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona persona1;
    @JoinColumn(name = "CODIGO_TIPO_VINCULADO", referencedColumnName = "CODIGO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoVinculado codigoTipoVinculado;


    public PersonaVinculado() {
    }

    public PersonaVinculado(PersonaVinculadoPK personaVinculadoPK) {
        this.personaVinculadoPK = personaVinculadoPK;
    }

    public PersonaVinculado(PersonaVinculadoPK personaVinculadoPK, Character eliminado, long registradoPor, Date fechaRegistro) {
        this.personaVinculadoPK = personaVinculadoPK;
        this.eliminado = eliminado;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
    }

    public PersonaVinculado(long codigoPersona, long codigoPersonaVinculado) {
        this.personaVinculadoPK = new PersonaVinculadoPK(codigoPersona, codigoPersonaVinculado);
    }

    public PersonaVinculadoPK getPersonaVinculadoPK() {
        return personaVinculadoPK;
    }

    public void setPersonaVinculadoPK(PersonaVinculadoPK personaVinculadoPK) {
        this.personaVinculadoPK = personaVinculadoPK;
    }

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona1() {
        return persona1;
    }

    public void setPersona1(Persona persona1) {
        this.persona1 = persona1;
    }

    public TipoVinculado getCodigoTipoVinculado() {
        return codigoTipoVinculado;
    }

    public void setCodigoTipoVinculado(TipoVinculado codigoTipoVinculado) {
        this.codigoTipoVinculado = codigoTipoVinculado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaVinculadoPK != null ? personaVinculadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaVinculado)) {
            return false;
        }
        PersonaVinculado other = (PersonaVinculado) object;
        if ((this.personaVinculadoPK == null && other.personaVinculadoPK != null) || (this.personaVinculadoPK != null && !this.personaVinculadoPK.equals(other.personaVinculadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mkp.modelo.socios.PersonaVinculado[ personaVinculadoPK=" + personaVinculadoPK + " ]";
    }
    
}
