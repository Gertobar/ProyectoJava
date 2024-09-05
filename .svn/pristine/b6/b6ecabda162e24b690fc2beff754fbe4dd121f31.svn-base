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
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_IFIPS.TIPO_SISTEMA_OPERATIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSistemaOperativo.findAll", query = "SELECT t FROM TipoSistemaOperativo t"),
    @NamedQuery(name = "TipoSistemaOperativo.findByCodigo", query = "SELECT t FROM TipoSistemaOperativo t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoSistemaOperativo.findByNombre", query = "SELECT t FROM TipoSistemaOperativo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoSistemaOperativo.findByEliminado", query = "SELECT t FROM TipoSistemaOperativo t WHERE t.eliminado = :eliminado"),
//PERSONALIZADO

 @NamedQuery(name = "TipoSistemaOperativo.findByIfipSistemaOpEliminado", query = "SELECT t FROM TipoSistemaOperativo t WHERE t.eliminado = :eliminado")})
public class TipoSistemaOperativo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByIfipSistemaOpEliminado="TipoSistemaOperativo.findByIfipSistemaOpEliminado";
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
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoSistemaOperativo")
    private Collection<Computador> computadorCollection;

    public TipoSistemaOperativo() {
    }

    public TipoSistemaOperativo(Long codigo) {
        this.codigo = codigo;
    }

    public TipoSistemaOperativo(Long codigo, String nombre, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Computador> getComputadorCollection() {
        return computadorCollection;
    }

    public void setComputadorCollection(Collection<Computador> computadorCollection) {
        this.computadorCollection = computadorCollection;
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
        if (!(object instanceof TipoSistemaOperativo)) {
            return false;
        }
        TipoSistemaOperativo other = (TipoSistemaOperativo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ifips.TipoSistemaOperativo[ codigo=" + codigo + " ]";
    }
    
}
