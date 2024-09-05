/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.servicio.entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "SERVICIO_APLICACION", schema = "MSS_SERVICIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioAplicacion.findAll", query = "SELECT s FROM ServicioAplicacion s")
    , @NamedQuery(name = "ServicioAplicacion.findByCodigo", query = "SELECT s FROM ServicioAplicacion s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "ServicioAplicacion.findByEstado", query = "SELECT s FROM ServicioAplicacion s WHERE s.estado = :estado")})
public class ServicioAplicacion implements Serializable {

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
    @Size(min = 1, max = 80)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IP")
    private String ip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUERTO")
    private int puerto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private short estado;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "codigoServicioAplicacion")
    private Set<ServicioAplicacionRecurso> servicioAplicacionRecursoCollection = new HashSet<ServicioAplicacionRecurso>();
    @JoinColumn(name = "CODIGO_GRUPO_SERVICIO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private GrupoServicio codigoGrupoServicio;

    public ServicioAplicacion() {
    }

    public ServicioAplicacion(Long codigo) {
        this.codigo = codigo;
    }

    public ServicioAplicacion(Long codigo, long codigoIfip, String nombre, String ip, int puerto, String descripcion, short estado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.nombre = nombre;
        this.ip = ip;
        this.puerto = puerto;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Set<ServicioAplicacionRecurso> getServicioAplicacionRecursoCollection() {
        return servicioAplicacionRecursoCollection;
    }

    public void setServicioAplicacionRecursoCollection(Set<ServicioAplicacionRecurso> servicioAplicacionRecursoCollection) {
        this.servicioAplicacionRecursoCollection = servicioAplicacionRecursoCollection;
    }

    public GrupoServicio getCodigoGrupoServicio() {
        return codigoGrupoServicio;
    }

    public void setCodigoGrupoServicio(GrupoServicio codigoGrupoServicio) {
        this.codigoGrupoServicio = codigoGrupoServicio;
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
        if (!(object instanceof ServicioAplicacion)) {
            return false;
        }
        ServicioAplicacion other = (ServicioAplicacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.mss.web.servicio.entidad.ServicioAplicacion[ codigo=" + codigo + " ]";
    }
    
}
