/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.PERSONA_INSTITUCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaInstitucion.findAll", query = "SELECT p FROM PersonaInstitucion p"),
    @NamedQuery(name = "PersonaInstitucion.findByCodigoPersona", query = "SELECT p FROM PersonaInstitucion p WHERE p.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "PersonaInstitucion.findByRazonSocial", query = "SELECT p FROM PersonaInstitucion p WHERE p.razonSocial = :razonSocial"),
    @NamedQuery(name = "PersonaInstitucion.findByNombreComercial", query = "SELECT p FROM PersonaInstitucion p WHERE p.nombreComercial = :nombreComercial"),
    @NamedQuery(name = "PersonaInstitucion.findByObjetoSocial", query = "SELECT p FROM PersonaInstitucion p WHERE p.objetoSocial = :objetoSocial"),
    @NamedQuery(name = "PersonaInstitucion.findByFechaConstitucion", query = "SELECT p FROM PersonaInstitucion p WHERE p.fechaConstitucion = :fechaConstitucion"),
    @NamedQuery(name = "PersonaInstitucion.findByEsSujetoSri", query = "SELECT p FROM PersonaInstitucion p WHERE p.esSujetoSri = :esSujetoSri"),
    @NamedQuery(name = "PersonaInstitucion.findByObservaciones", query = "SELECT p FROM PersonaInstitucion p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "PersonaInstitucion.findByFechaIngreso", query = "SELECT p FROM PersonaInstitucion p WHERE p.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "PersonaInstitucion.findByFechaActualizacion", query = "SELECT p FROM PersonaInstitucion p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PersonaInstitucion implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPersona = "PersonaInstitucion.findByCodigoPersona";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private Long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 150)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 150)
    @Column(name = "NOMBRE_COMERCIAL")
    private String nombreComercial;
    @Size(max = 150)
    @Column(name = "OBJETO_SOCIAL")
    private String objetoSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONSTITUCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConstitucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_SUJETO_SRI")
    private char esSujetoSri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
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
    @Column(name = "CONTROLADA_OC")
    private char controladaOc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaInstitucion",fetch = FetchType.EAGER)
    private Collection<PersonaInstitucionRep> personaInstitucionRepCollection;
    @JoinColumn(name = "CODIGO_TIPO_INSTITUCION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoInstitucion codigoTipoInstitucion;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;

    public PersonaInstitucion() {
    }

    public PersonaInstitucion(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public PersonaInstitucion(Long codigoPersona, String razonSocial, String nombreComercial, Date fechaConstitucion, char esSujetoSri, String observaciones, Date fechaIngreso, Date fechaActualizacion) {
        this.codigoPersona = codigoPersona;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
        this.fechaConstitucion = fechaConstitucion;
        this.esSujetoSri = esSujetoSri;
        this.observaciones = observaciones;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getObjetoSocial() {
        return objetoSocial;
    }

    public void setObjetoSocial(String objetoSocial) {
        this.objetoSocial = objetoSocial;
    }

    public Date getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(Date fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    public char getEsSujetoSri() {
        return esSujetoSri;
    }

    public void setEsSujetoSri(char esSujetoSri) {
        this.esSujetoSri = esSujetoSri;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    @XmlTransient
    public Collection<PersonaInstitucionRep> getPersonaInstitucionRepCollection() {
        return personaInstitucionRepCollection;
    }

    public void setPersonaInstitucionRepCollection(Collection<PersonaInstitucionRep> personaInstitucionRepCollection) {
        this.personaInstitucionRepCollection = personaInstitucionRepCollection;
    }

    public TipoInstitucion getCodigoTipoInstitucion() {
        return codigoTipoInstitucion;
    }

    public void setCodigoTipoInstitucion(TipoInstitucion codigoTipoInstitucion) {
        this.codigoTipoInstitucion = codigoTipoInstitucion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        if (!(object instanceof PersonaInstitucion)) {
            return false;
        }
        PersonaInstitucion other = (PersonaInstitucion) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.PersonaInstitucion[ codigoPersona=" + codigoPersona + " ]";
    }

    /**
     * @return the controladaOc
     */
    public char getControladaOc() {
        return controladaOc;
    }

    /**
     * @param controladaOc the controladaOc to set
     */
    public void setControladaOc(char controladaOc) {
        this.controladaOc = controladaOc;
    }
    
}
