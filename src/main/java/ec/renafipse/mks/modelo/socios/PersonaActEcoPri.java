/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_ACT_ECO_PRI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaActEcoPri.findAll", query = "SELECT p FROM PersonaActEcoPri p"),
    @NamedQuery(name = "PersonaActEcoPri.findByCodigoPersona", query = "SELECT p FROM PersonaActEcoPri p WHERE p.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "PersonaActEcoPri.findByFechaIngreso", query = "SELECT p FROM PersonaActEcoPri p WHERE p.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "PersonaActEcoPri.findByFechaActualizacion", query = "SELECT p FROM PersonaActEcoPri p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PersonaActEcoPri implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ACTIVIDAD_ECONOMICA")
    private Long codigoActividadEconomica;
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
    @JoinColumns({
        @JoinColumn(name = "CODIGO_ACTIVIDAD_ECONOMICA", referencedColumnName = "CODIGO_ACTIVIDAD_ECONOMICA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO_PERSONA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PersonaActividadEconomica personaActividadEconomica;

    public PersonaActEcoPri() {
    }

    public PersonaActEcoPri(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public PersonaActEcoPri(Long codigoPersona, Date fechaIngreso, Date fechaActualizacion) {
        this.codigoPersona = codigoPersona;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
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

    public PersonaActividadEconomica getPersonaActividadEconomica() {
        return personaActividadEconomica;
    }

    public void setPersonaActividadEconomica(PersonaActividadEconomica personaActividadEconomica) {
        this.personaActividadEconomica = personaActividadEconomica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPersona != null ? codigoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaActEcoPri)) {
            return false;
        }
        PersonaActEcoPri other = (PersonaActEcoPri) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaActEcoPri[ codigoPersona=" + codigoPersona + " ]";
    }

    /**
     * @return the codigoActividadEconomica
     */
    public Long getCodigoActividadEconomica() {
        return codigoActividadEconomica;
    }

    /**
     * @param codigoActividadEconomica the codigoActividadEconomica to set
     */
    public void setCodigoActividadEconomica(Long codigoActividadEconomica) {
        this.codigoActividadEconomica = codigoActividadEconomica;
    }
    
}
