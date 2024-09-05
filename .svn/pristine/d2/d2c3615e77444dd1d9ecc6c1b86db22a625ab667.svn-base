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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MKS_SOCIOS.CONOCIMIENTO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConocimientoIfip.findAll", query = "SELECT c FROM ConocimientoIfip c"),
    @NamedQuery(name = "ConocimientoIfip.findByCodigo", query = "SELECT c FROM ConocimientoIfip c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ConocimientoIfip.findByNombre", query = "SELECT c FROM ConocimientoIfip c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ConocimientoIfip.findByEliminado", query = "SELECT c FROM ConocimientoIfip c WHERE c.eliminado = :eliminado ORDER BY c.nombre")})
public class ConocimientoIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByEliminado = "ConocimientoIfip.findByEliminado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_CONOCIMIENTO_IFIP")
    @SequenceGenerator(name = "GSQ_CONOCIMIENTO_IFIP", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_CONOCIMIENTO_IFIP")
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoConocimientoIfip")
    private Collection<Socio> socioCollection;

    public ConocimientoIfip() {
    }

    public ConocimientoIfip(Long codigo) {
        this.codigo = codigo;
    }

    public ConocimientoIfip(Long codigo, String nombre, char eliminado) {
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
        if (!(object instanceof ConocimientoIfip)) {
            return false;
        }
        ConocimientoIfip other = (ConocimientoIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.ConocimientoIfip[ codigo=" + codigo + " ]";
    }
    
}
