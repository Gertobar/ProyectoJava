/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.CANAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canal.findAll", query = "SELECT c FROM Canal c"),
    @NamedQuery(name = "Canal.findByCodigo", query = "SELECT c FROM Canal c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Canal.findByNombre", query = "SELECT c FROM Canal c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Canal.findByDescripcion", query = "SELECT c FROM Canal c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Canal.findByEliminado", query = "SELECT c FROM Canal c WHERE c.eliminado = :eliminado")})
public class Canal implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCanal")
    private Collection<TransferenciaEntreCuentas> transferenciaEntreCuentasCollection;

    public Canal() {
    }

    public Canal(Long codigo) {
        this.codigo = codigo;
    }

    public Canal(Long codigo, String nombre, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<TransferenciaEntreCuentas> getTransferenciaEntreCuentasCollection() {
        return transferenciaEntreCuentasCollection;
    }

    public void setTransferenciaEntreCuentasCollection(Collection<TransferenciaEntreCuentas> transferenciaEntreCuentasCollection) {
        this.transferenciaEntreCuentasCollection = transferenciaEntreCuentasCollection;
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
        if (!(object instanceof Canal)) {
            return false;
        }
        Canal other = (Canal) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.Canal[ codigo=" + codigo + " ]";
    }
    
}
