/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.GRUPO_ARTICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoArticulo.findAll", query = "SELECT g FROM GrupoArticulo g"),
    @NamedQuery(name = "GrupoArticulo.findByCodigo", query = "SELECT g FROM GrupoArticulo g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GrupoArticulo.findByNombre", query = "SELECT g FROM GrupoArticulo g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GrupoArticulo.findByDescripcion", query = "SELECT g FROM GrupoArticulo g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GrupoArticulo.findByTipo", query = "SELECT g FROM GrupoArticulo g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GrupoArticulo.findByEliminado", query = "SELECT g FROM GrupoArticulo g WHERE g.eliminado = :eliminado ORDER BY g.nombre")})
public class GrupoArticulo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByAllGrupoArticulo = "GrupoArticulo.findAll";
    public static final String findByEliminado = "GrupoArticulo.findByEliminado";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoArticulo", fetch = FetchType.LAZY)
    private Collection<CompraDetalle> compraDetalleCollection;

    public GrupoArticulo() {
    }

    public GrupoArticulo(Long codigo) {
        this.codigo = codigo;
    }

    public GrupoArticulo(Long codigo, String nombre, String descripcion, char tipo, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<CompraDetalle> getCompraDetalleCollection() {
        return compraDetalleCollection;
    }

    public void setCompraDetalleCollection(Collection<CompraDetalle> compraDetalleCollection) {
        this.compraDetalleCollection = compraDetalleCollection;
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
        if (!(object instanceof GrupoArticulo)) {
            return false;
        }
        GrupoArticulo other = (GrupoArticulo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.GrupoArticulo[ codigo=" + codigo + " ]";
    }
    
}
