/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MKS_SOCIOS.PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByCodigo", query = "SELECT p FROM Persona p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Persona.findByIdentificacion", query = "SELECT p FROM Persona p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Persona.findByNombreCompleto", query = "SELECT p FROM Persona p WHERE p.nombreCompleto LIKE :nombreCompleto"),
    @NamedQuery(name = "Persona.findByFechaIngreso", query = "SELECT p FROM Persona p WHERE p.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Persona.findByFechaActualizacion", query = "SELECT p FROM Persona p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Persona.findByNombreCompletoEsParaInstitucion", query = "SELECT p FROM Persona p WHERE p.nombreCompleto LIKE :nombreCompleto AND p.codigoTipoPersona.esParaInstitucion = :esParaInstitucion")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Persona.findByIdentificacionPersonaNatural", query = "SELECT p.codigo, p.codigo_tipo_identificacion,p.codigo_tipo_persona,p.identificacion,p.nombre_completo,p.fecha_ingreso,p.fecha_actualizacion,p.correo_electronico,p.fecha_caducidad_identificacion FROM mks_socios.persona p, mks_socios.persona_natural pn WHERE p.identificacion = :identificacion AND p.codigo = pn.codigo_persona", resultClass = Persona.class),
    @NamedNativeQuery(name = "Persona.findByNombreCompletoPersonaNatural", query = "SELECT p.codigo, p.codigo_tipo_identificacion,p.codigo_tipo_persona,p.identificacion,p.nombre_completo,p.fecha_ingreso,p.fecha_actualizacion,p.correo_electronico,p.fecha_caducidad_identificacion FROM mks_socios.persona p, mks_socios.persona_natural pn WHERE p.nombre_completo LIKE UPPER(:nombreCompleto) AND p.codigo = pn.codigo_persona", resultClass = Persona.class)
})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigo = "Persona.findByCodigo";
    public static final String findByIdentificacion = "Persona.findByIdentificacion";
    public static final String findByNombreCompleto = "Persona.findByNombreCompleto";
    public static final String findByNombreCompletoEsParaInstitucion = "Persona.findByNombreCompletoEsParaInstitucion";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PERSONA")
    @SequenceGenerator(name = "GSQ_PERSONA", schema = "MKS_SOCIOS",  allocationSize = 0, sequenceName = "SEQ_PERSONA")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(max = 30)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 150)
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @Basic(optional = true)    
    @Size(max = 100)
    @Column(name = "CORREO_ELECTRONICO")
    private String correoEletronico;
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
    @Column(name = "FECHA_CADUCIDAD_IDENTIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidadIdentificacion;
    @JoinColumn(name = "CODIGO_TIPO_PERSONA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoPersona codigoTipoPersona;
    @JoinColumn(name = "CODIGO_TIPO_IDENTIFICACION", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoIdentificacion codigoTipoIdentificacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPersona", fetch = FetchType.LAZY)
    private Collection<PersonaTelefono> personaTelefonoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPersona", fetch = FetchType.LAZY)
    private Collection<ReferenciaEntidadFinanciera> referenciaEntidadFinancieraCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona",  fetch = FetchType.LAZY)
    private PersonaInstitucion personaInstitucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPersona",  fetch = FetchType.LAZY)
    private Collection<Socio> socioCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona",  fetch = FetchType.LAZY)
    private PersonaNatural personaNatural;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPersona", fetch = FetchType.LAZY)
    private Collection<ReferenciaPersonal> referenciaPersonalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona" , fetch = FetchType.LAZY)
    private Collection<PersonaActividadEconomica> personaActividadEconomicaCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona" , fetch = FetchType.LAZY)
    private PersonaResidencia personaResidencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<PersonaVinculado> personaVinculadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona1", fetch = FetchType.LAZY)
    private List<PersonaVinculado> itemsPersonaVinculado;

    public Persona() {
    }

    public Persona(Long codigo) {
        this.codigo = codigo;
    }

    public Persona(Long codigo, String identificacion, String nombreCompleto, Date fechaIngreso, Date fechaActualizacion) {
        this.codigo = codigo;
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        //System.out.println("setnombrecompleto 1:  " + this.nombreCompleto);
        this.nombreCompleto = nombreCompleto;
        //System.out.println("setnombrecompleto 2:  " + this.nombreCompleto);
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

    public TipoPersona getCodigoTipoPersona() {
        return codigoTipoPersona;
    }

    public void setCodigoTipoPersona(TipoPersona codigoTipoPersona) {
        this.codigoTipoPersona = codigoTipoPersona;
    }

    public TipoIdentificacion getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

    public void setCodigoTipoIdentificacion(TipoIdentificacion codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    @XmlTransient
    public Collection<PersonaTelefono> getPersonaTelefonoCollection() {
        return personaTelefonoCollection;
    }

    public void setPersonaTelefonoCollection(Collection<PersonaTelefono> personaTelefonoCollection) {
        this.personaTelefonoCollection = personaTelefonoCollection;
    }

    @XmlTransient
    public Collection<ReferenciaEntidadFinanciera> getReferenciaEntidadFinancieraCollection() {
        return referenciaEntidadFinancieraCollection;
    }

    public void setReferenciaEntidadFinancieraCollection(Collection<ReferenciaEntidadFinanciera> referenciaEntidadFinancieraCollection) {
        this.referenciaEntidadFinancieraCollection = referenciaEntidadFinancieraCollection;
    }

    public PersonaInstitucion getPersonaInstitucion() {
        return personaInstitucion;
    }

    public void setPersonaInstitucion(PersonaInstitucion personaInstitucion) {
        this.personaInstitucion = personaInstitucion;
    }

    @XmlTransient
    public Collection<Socio> getSocioCollection() {
        return socioCollection;
    }

    public void setSocioCollection(Collection<Socio> socioCollection) {
        this.socioCollection = socioCollection;
    }

    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    @XmlTransient
    public Collection<ReferenciaPersonal> getReferenciaPersonalCollection() {
        return referenciaPersonalCollection;
    }

    public void setReferenciaPersonalCollection(Collection<ReferenciaPersonal> referenciaPersonalCollection) {
        this.referenciaPersonalCollection = referenciaPersonalCollection;
    }

    @XmlTransient
    public Collection<PersonaActividadEconomica> getPersonaActividadEconomicaCollection() {
        return personaActividadEconomicaCollection;
    }

    public void setPersonaActividadEconomicaCollection(Collection<PersonaActividadEconomica> personaActividadEconomicaCollection) {
        this.personaActividadEconomicaCollection = personaActividadEconomicaCollection;
    }

    public PersonaResidencia getPersonaResidencia() {
        return personaResidencia;
    }

    public void setPersonaResidencia(PersonaResidencia personaResidencia) {
        this.personaResidencia = personaResidencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.Persona[ codigo=" + codigo + " ]";
    }

    /**
     * @return the correoEletronico
     */
    public String getCorreoEletronico() {
        return correoEletronico;
    }

    /**
     * @param correoEletronico the correoEletronico to set
     */
    public void setCorreoEletronico(String correoEletronico) {
        this.correoEletronico = correoEletronico;
    }

    /**
     * @return the fechaCaducidadIdentificacion
     */
    public Date getFechaCaducidadIdentificacion() {
        return fechaCaducidadIdentificacion;
    }

    /**
     * @param fechaCaducidadIdentificacion the fechaCaducidadIdentificacion to set
     */
    public void setFechaCaducidadIdentificacion(Date fechaCaducidadIdentificacion) {
        this.fechaCaducidadIdentificacion = fechaCaducidadIdentificacion;
    }

    /**
     * @return the itemsPersonaVinculado
     */
    public List<PersonaVinculado> getItemsPersonaVinculado() {
        return itemsPersonaVinculado;
    }

    /**
     * @param itemsPersonaVinculado the itemsPersonaVinculado to set
     */
    public void setItemsPersonaVinculado(List<PersonaVinculado> itemsPersonaVinculado) {
        this.itemsPersonaVinculado = itemsPersonaVinculado;
    }

    /**
     * @return the personaVinculadoList
     */
    public List<PersonaVinculado> getPersonaVinculadoList() {
        return personaVinculadoList;
    }

    /**
     * @param personaVinculadoList the personaVinculadoList to set
     */
    public void setPersonaVinculadoList(List<PersonaVinculado> personaVinculadoList) {
        this.personaVinculadoList = personaVinculadoList;
    }
    
}
