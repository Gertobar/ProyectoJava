/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.socios;

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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_SOCIOS.ESTADO_SOCIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSocio.findAll", query = "SELECT e FROM EstadoSocio e"),
    @NamedQuery(name = "EstadoSocio.findByCodigo", query = "SELECT e FROM EstadoSocio e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EstadoSocio.findByNombre", query = "SELECT e FROM EstadoSocio e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoSocio.findByIndicaActivo", query = "SELECT e FROM EstadoSocio e WHERE e.indicaActivo = :indicaActivo"),
    @NamedQuery(name = "EstadoSocio.findByEliminado", query = "SELECT e FROM EstadoSocio e WHERE e.eliminado = :eliminado ORDER BY e.nombre")})
public class EstadoSocio implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByEliminado = "EstadoSocio.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INDICA_ACTIVO")
    private char indicaActivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEstadoSocio")
    private Collection<Socio> socioCollection;

    public EstadoSocio() {
    }

    public EstadoSocio(Long codigo) {
        this.codigo = codigo;
    }

    public EstadoSocio(Long codigo, String nombre, char indicaActivo, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.indicaActivo = indicaActivo;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getIndicaActivo() {
        return indicaActivo;
    }

    public void setIndicaActivo(char indicaActivo) {
        this.indicaActivo = indicaActivo;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Socio> getSocioCollection() {
        return socioCollection;
    }

    public void setSocioCollection(Collection<Socio> socioCollection) {
        this.socioCollection = socioCollection;
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
        if (!(object instanceof EstadoSocio)) {
            return false;
        }
        EstadoSocio other = (EstadoSocio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.EstadoSocio[ codigo=" + codigo + " ]";
    }
    
}
