/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ifips;

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
@Table(name = "MKS_IFIPS.SERVICIO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioIfip.findByCodigo", query = "SELECT s FROM ServicioIfip s WHERE s.codigo = :codigo")
})
public class ServicioIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoServicioIfip")
    private Collection<EnvioCanalServicioIfip> envioCanalServicioIfipCollection;

    public ServicioIfip() {
    }

    public ServicioIfip(Long codigo) {
        this.codigo = codigo;
    }

    public ServicioIfip(Long codigo, String siglas, String nombre, String descripcion, char eliminado, long codigoIfip) {
        this.codigo = codigo;
        this.siglas = siglas;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
        this.codigoIfip = codigoIfip;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
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

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    @XmlTransient
    public Collection<EnvioCanalServicioIfip> getEnvioCanalServicioIfipCollection() {
        return envioCanalServicioIfipCollection;
    }

    public void setEnvioCanalServicioIfipCollection(Collection<EnvioCanalServicioIfip> envioCanalServicioIfipCollection) {
        this.envioCanalServicioIfipCollection = envioCanalServicioIfipCollection;
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
        if (!(object instanceof ServicioIfip)) {
            return false;
        }
        ServicioIfip other = (ServicioIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.ServicioIfip[ codigo=" + codigo + " ]";
    }
    
}
