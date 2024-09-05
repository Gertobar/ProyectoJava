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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.RANGO_MADURACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RangoMaduracion.findAll", query = "SELECT r FROM RangoMaduracion r"),
    @NamedQuery(name = "RangoMaduracion.findByCodigo", query = "SELECT r FROM RangoMaduracion r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RangoMaduracion.findByRangoInicial", query = "SELECT r FROM RangoMaduracion r WHERE r.rangoInicial = :rangoInicial"),
    @NamedQuery(name = "RangoMaduracion.findByRangoFinal", query = "SELECT r FROM RangoMaduracion r WHERE r.rangoFinal = :rangoFinal"),
    @NamedQuery(name = "RangoMaduracion.findByEliminado", query = "SELECT r FROM RangoMaduracion r WHERE r.eliminado = :eliminado")})
public class RangoMaduracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGO_INICIAL")
    private long rangoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGO_FINAL")
    private long rangoFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRangoMaduracion")
    private Collection<TipoCarteraMaduracion> tipoCarteraMaduracionCollection;

    public RangoMaduracion() {
    }

    public RangoMaduracion(Long codigo) {
        this.codigo = codigo;
    }

    public RangoMaduracion(Long codigo, long rangoInicial, long rangoFinal, char eliminado) {
        this.codigo = codigo;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(long rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public long getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(long rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
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
        if (!(object instanceof RangoMaduracion)) {
            return false;
        }
        RangoMaduracion other = (RangoMaduracion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.RangoMaduracion[ codigo=" + codigo + " ]";
    }
    
}
