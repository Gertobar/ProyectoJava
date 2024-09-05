/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import ec.renafipse.mks.modelo.comunes.Periodicidad;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_ACTIVIDAD_ECONOMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaActividadEconomica.findAll", query = "SELECT p FROM PersonaActividadEconomica p"),
    @NamedQuery(name = "PersonaActividadEconomica.findByCodigoPersona", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.personaActividadEconomicaPK.codigoPersona = :codigoPersona ORDER BY p.actividadEconomica.nombre"),
    @NamedQuery(name = "PersonaActividadEconomica.findByCodigoActividadEconomica", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.personaActividadEconomicaPK.codigoActividadEconomica = :codigoActividadEconomica"),
    @NamedQuery(name = "PersonaActividadEconomica.findByCodigoPeriodicidad", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.codigoPeriodicidad = :codigoPeriodicidad"),
    @NamedQuery(name = "PersonaActividadEconomica.findByTiempo", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.tiempo = :tiempo"),
    @NamedQuery(name = "PersonaActividadEconomica.findByFechaIngreso", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "PersonaActividadEconomica.findByFechaActualizacion", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PersonaActividadEconomica.findByEliminado", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.eliminado = :eliminado"),
    //Personalizadas
    @NamedQuery(name = "PersonaActividadEconomica.findByMaximoNivelEliminado", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.actividadEconomica.codigoNivel.codigo = (SELECT MAX(b.codigoNivel.codigo) FROM  ActividadEconomica b) AND p.persona.codigo = :codigoPersona AND  p.eliminado = :eliminado"),
    //@NamedQuery(name = "PersonaActividadEconomica.findBySectorSubsectorNivelEliminado", query = "SELECT p.actividadEconomica FROM PersonaActividadEconomica p WHERE p.actividadEconomica.codigoSubsector.codigo = :codigoSubsector AND p.actividadEconomica.codigoNivel.codigo = :codigoNivel AND p.eliminado = :eliminado"),
    @NamedQuery(name = "PersonaActividadEconomica.findByCodigoPersonaEliminado", query = "SELECT p FROM PersonaActividadEconomica p WHERE p.personaActividadEconomicaPK.codigoPersona = :codigoPersona AND p.eliminado = :eliminado ORDER BY p.actividadEconomica.nombre")
})
public class PersonaActividadEconomica implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPersona = "PersonaActividadEconomica.findByCodigoPersona";
    public static final String findByCodigoPersonaEliminado = "PersonaActividadEconomica.findByCodigoPersonaEliminado";
    public static final String findByMaximoNivelEliminado = "PersonaActividadEconomica.findByMaximoNivelEliminado";
    //public static final String findBySectorSubsectorNivelEliminado = "PersonaActividadEconomica.findBySectorSubsectorNivelEliminado";
    @EmbeddedId
    protected PersonaActividadEconomicaPK personaActividadEconomicaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERIODICIDAD")
    //@JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    //@ManyToOne(optional = false)
    private Long codigoPeriodicidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO")
    private int tiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PRINCIPAL")
    private char esPrincipal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaActividadEconomica", fetch = FetchType.LAZY)
    private Collection<PersonaActEcoPri> personaActEcoPriCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaActividadEconomica", fetch = FetchType.LAZY )
    private Collection<PersonaTrabajoActEco> personaTrabajoActEcoCollection;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "CODIGO_ACTIVIDAD_ECONOMICA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ActividadEconomica actividadEconomica;
    @JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodicidad periodicidad;

    public PersonaActividadEconomica() {
    }

    public PersonaActividadEconomica(PersonaActividadEconomicaPK personaActividadEconomicaPK) {
        this.personaActividadEconomicaPK = personaActividadEconomicaPK;
    }

    public PersonaActividadEconomica(PersonaActividadEconomicaPK personaActividadEconomicaPK, Long codigoPeriodicidad, int tiempo, Date fechaIngreso, Date fechaActualizacion, char eliminado) {
        this.personaActividadEconomicaPK = personaActividadEconomicaPK;
        this.codigoPeriodicidad = codigoPeriodicidad;
        this.tiempo = tiempo;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
        this.eliminado = eliminado;
    }

    public PersonaActividadEconomica(long codigoPersona, long codigoActividadEconomica) {
        this.personaActividadEconomicaPK = new PersonaActividadEconomicaPK(codigoPersona, codigoActividadEconomica);
    }

    public PersonaActividadEconomicaPK getPersonaActividadEconomicaPK() {
        return personaActividadEconomicaPK;
    }

    public void setPersonaActividadEconomicaPK(PersonaActividadEconomicaPK personaActividadEconomicaPK) {
        this.personaActividadEconomicaPK = personaActividadEconomicaPK;
    }

    public Long getCodigoPeriodicidad() {
        return codigoPeriodicidad;
    }

    public void setCodigoPeriodicidad(Long codigoPeriodicidad) {
        this.codigoPeriodicidad = codigoPeriodicidad;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<PersonaActEcoPri> getPersonaActEcoPriCollection() {
        return personaActEcoPriCollection;
    }

    public void setPersonaActEcoPriCollection(Collection<PersonaActEcoPri> personaActEcoPriCollection) {
        this.personaActEcoPriCollection = personaActEcoPriCollection;
    }

    @XmlTransient
    public Collection<PersonaTrabajoActEco> getPersonaTrabajoActEcoCollection() {
        return personaTrabajoActEcoCollection;
    }

    public void setPersonaTrabajoActEcoCollection(Collection<PersonaTrabajoActEco> personaTrabajoActEcoCollection) {
        this.personaTrabajoActEcoCollection = personaTrabajoActEcoCollection;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ActividadEconomica getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(ActividadEconomica actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaActividadEconomicaPK != null ? personaActividadEconomicaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaActividadEconomica)) {
            return false;
        }
        PersonaActividadEconomica other = (PersonaActividadEconomica) object;
        if ((this.personaActividadEconomicaPK == null && other.personaActividadEconomicaPK != null) || (this.personaActividadEconomicaPK != null && !this.personaActividadEconomicaPK.equals(other.personaActividadEconomicaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaActividadEconomica[ personaActividadEconomicaPK=" + personaActividadEconomicaPK + " ]";
    }

    /**
     * @return the esPrincipal
     */
    public char getEsPrincipal() {
        return esPrincipal;
    }

    /**
     * @param esPrincipal the esPrincipal to set
     */
    public void setEsPrincipal(char esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    /**
     * @return the periodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }
    
}
