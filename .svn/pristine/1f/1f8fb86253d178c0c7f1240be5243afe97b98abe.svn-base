/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_MADURACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMaduracion.findAll", query = "SELECT t FROM TipoMaduracion t"),
    @NamedQuery(name = "TipoMaduracion.findByCodigo", query = "SELECT t FROM TipoMaduracion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoMaduracion.findByNombre", query = "SELECT t FROM TipoMaduracion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoMaduracion.findByDescripcion", query = "SELECT t FROM TipoMaduracion t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoMaduracion.findByEliminado", query = "SELECT t FROM TipoMaduracion t WHERE t.eliminado = :eliminado")})
public class TipoMaduracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 30)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoMaduracion")
    private Collection<TablaAmortizacion> tablaAmortizacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoMaduracion")
    private Collection<TipoCarteraMaduracion> tipoCarteraMaduracionCollection;

    public TipoMaduracion() {
    }

    public TipoMaduracion(String codigo) {
        this.codigo = codigo;
    }

    public TipoMaduracion(String codigo, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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
    public Collection<TablaAmortizacion> getTablaAmortizacionCollection() {
        return tablaAmortizacionCollection;
    }

    public void setTablaAmortizacionCollection(Collection<TablaAmortizacion> tablaAmortizacionCollection) {
        this.tablaAmortizacionCollection = tablaAmortizacionCollection;
    }

    @XmlTransient
    public Collection<TipoCarteraMaduracion> getTipoCarteraMaduracionCollection() {
        return tipoCarteraMaduracionCollection;
    }

    public void setTipoCarteraMaduracionCollection(Collection<TipoCarteraMaduracion> tipoCarteraMaduracionCollection) {
        this.tipoCarteraMaduracionCollection = tipoCarteraMaduracionCollection;
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
        if (!(object instanceof TipoMaduracion)) {
            return false;
        }
        TipoMaduracion other = (TipoMaduracion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoMaduracion[ codigo=" + codigo + " ]";
    }
    
}
