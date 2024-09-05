/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.comunes;

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
@Table(name = "MKS_COMUNES.JERARQUIA_UBICACION_GEOGRAFICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JerarquiaUbicacionGeografica.findAll", query = "SELECT j FROM JerarquiaUbicacionGeografica j"),
    @NamedQuery(name = "JerarquiaUbicacionGeografica.findByCodigo", query = "SELECT j FROM JerarquiaUbicacionGeografica j WHERE j.codigo = :codigo"),
    @NamedQuery(name = "JerarquiaUbicacionGeografica.findByNombre", query = "SELECT j FROM JerarquiaUbicacionGeografica j WHERE j.nombre = :nombre"),
    @NamedQuery(name = "JerarquiaUbicacionGeografica.findBySiglas", query = "SELECT j FROM JerarquiaUbicacionGeografica j WHERE j.siglas = :siglas"),
    @NamedQuery(name = "JerarquiaUbicacionGeografica.findByOrden", query = "SELECT j FROM JerarquiaUbicacionGeografica j WHERE j.orden = :orden"),
    @NamedQuery(name = "JerarquiaUbicacionGeografica.findByEliminado", query = "SELECT j FROM JerarquiaUbicacionGeografica j WHERE j.eliminado = :eliminado")})
public class JerarquiaUbicacionGeografica implements Serializable {
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
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private short orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoJerarquia")
    private Collection<UbicacionGeografica> ubicacionGeograficaCollection;

    public JerarquiaUbicacionGeografica() {
    }

    public JerarquiaUbicacionGeografica(Long codigo) {
        this.codigo = codigo;
    }

    public JerarquiaUbicacionGeografica(Long codigo, String nombre, String siglas, short orden, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.orden = orden;
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<UbicacionGeografica> getUbicacionGeograficaCollection() {
        return ubicacionGeograficaCollection;
    }

    public void setUbicacionGeograficaCollection(Collection<UbicacionGeografica> ubicacionGeograficaCollection) {
        this.ubicacionGeograficaCollection = ubicacionGeograficaCollection;
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
        if (!(object instanceof JerarquiaUbicacionGeografica)) {
            return false;
        }
        JerarquiaUbicacionGeografica other = (JerarquiaUbicacionGeografica) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.comunes.JerarquiaUbicacionGeografica[ codigo=" + codigo + " ]";
    }
    
}
