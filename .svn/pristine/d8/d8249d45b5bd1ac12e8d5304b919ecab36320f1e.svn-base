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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_SOCIOS.GRUPO_INSTITUCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoInstitucion.findAll", query = "SELECT g FROM GrupoInstitucion g"),
    @NamedQuery(name = "GrupoInstitucion.findByCodigo", query = "SELECT g FROM GrupoInstitucion g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GrupoInstitucion.findByCodigoIfip", query = "SELECT g FROM GrupoInstitucion g WHERE g.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "GrupoInstitucion.findByRegistradoPor", query = "SELECT g FROM GrupoInstitucion g WHERE g.registradoPor = :registradoPor"),
    @NamedQuery(name = "GrupoInstitucion.findByFechaRegistro", query = "SELECT g FROM GrupoInstitucion g WHERE g.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "GrupoInstitucion.findByObservaciones", query = "SELECT g FROM GrupoInstitucion g WHERE g.observaciones = :observaciones"),
    @NamedQuery(name = "GrupoInstitucion.findByEliminado", query = "SELECT g FROM GrupoInstitucion g WHERE g.eliminado = :eliminado")})
public class GrupoInstitucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoInstitucion")
    private Collection<GrupoInstitucionIfipAgencia> grupoInstitucionIfipAgenciaCollection;
    @JoinColumn(name = "CODIGO_PERSONA_INSTITUCION", referencedColumnName = "CODIGO_PERSONA")
    @ManyToOne(optional = false)
    private PersonaInstitucion codigoPersonaInstitucion;

    public GrupoInstitucion() {
    }

    public GrupoInstitucion(Long codigo) {
        this.codigo = codigo;
    }

    public GrupoInstitucion(Long codigo, long codigoIfip, long registradoPor, Date fechaRegistro, String observaciones, char eliminado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.observaciones = observaciones;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
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

    @XmlTransient
    public Collection<GrupoInstitucionIfipAgencia> getGrupoInstitucionIfipAgenciaCollection() {
        return grupoInstitucionIfipAgenciaCollection;
    }

    public void setGrupoInstitucionIfipAgenciaCollection(Collection<GrupoInstitucionIfipAgencia> grupoInstitucionIfipAgenciaCollection) {
        this.grupoInstitucionIfipAgenciaCollection = grupoInstitucionIfipAgenciaCollection;
    }

    public PersonaInstitucion getCodigoPersonaInstitucion() {
        return codigoPersonaInstitucion;
    }

    public void setCodigoPersonaInstitucion(PersonaInstitucion codigoPersonaInstitucion) {
        this.codigoPersonaInstitucion = codigoPersonaInstitucion;
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
        if (!(object instanceof GrupoInstitucion)) {
            return false;
        }
        GrupoInstitucion other = (GrupoInstitucion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.GrupoInstitucion[ codigo=" + codigo + " ]";
    }
    
}
