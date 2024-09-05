/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.servicio.entidad;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "GRUPO_SERVICIO", schema = "MSS_SERVICIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoServicio.findAll", query = "SELECT g FROM GrupoServicio g")
    , @NamedQuery(name = "GrupoServicio.findByCodigo", query = "SELECT g FROM GrupoServicio g WHERE g.codigo = :codigo")
    , @NamedQuery(name = "GrupoServicio.findByCodigoIfip", query = "SELECT g FROM GrupoServicio g WHERE g.codigoIfip = :codigoIfip")
    , @NamedQuery(name = "GrupoServicio.findByNombre", query = "SELECT g FROM GrupoServicio g WHERE g.nombre = :nombre")
    , @NamedQuery(name = "GrupoServicio.findByDescripcion", query = "SELECT g FROM GrupoServicio g WHERE g.descripcion = :descripcion")})
public class GrupoServicio implements Serializable {

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
    @Size(min = 1, max = 15)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoGrupoServicio")
    private Collection<ServicioAplicacion> servicioAplicacionCollection;

    public GrupoServicio() {
    }

    public GrupoServicio(Long codigo) {
        this.codigo = codigo;
    }

    public GrupoServicio(Long codigo, long codigoIfip, String nombre, String descripcion) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<ServicioAplicacion> getServicioAplicacionCollection() {
        return servicioAplicacionCollection;
    }

    public void setServicioAplicacionCollection(Collection<ServicioAplicacion> servicioAplicacionCollection) {
        this.servicioAplicacionCollection = servicioAplicacionCollection;
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
        if (!(object instanceof GrupoServicio)) {
            return false;
        }
        GrupoServicio other = (GrupoServicio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.mss.web.servicio.entidad.GrupoServicio[ codigo=" + codigo + " ]";
    }
    
}
