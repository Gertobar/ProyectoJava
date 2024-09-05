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
@Table(name = "MKS_SOCIOS.MOTIVO_SOCIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoSocio.findAll", query = "SELECT m FROM MotivoSocio m"),
    @NamedQuery(name = "MotivoSocio.findByCodigo", query = "SELECT m FROM MotivoSocio m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MotivoSocio.findByNombre", query = "SELECT m FROM MotivoSocio m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MotivoSocio.findByEliminado", query = "SELECT m FROM MotivoSocio m WHERE m.eliminado = :eliminado")})
public class MotivoSocio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_MOTIVO_SOCIO")
    @SequenceGenerator(name = "GSQ_MOTIVO_SOCIO", schema = "MKS_SOCIOS", initialValue = 1, allocationSize = 0, sequenceName = "SEQ_MOTIVO_SOCIO")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMotivoSocio")
    private Collection<Socio> socioCollection;

    public MotivoSocio() {
    }

    public MotivoSocio(Long codigo) {
        this.codigo = codigo;
    }

    public MotivoSocio(Long codigo, String nombre, char eliminado) {
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
        if (!(object instanceof MotivoSocio)) {
            return false;
        }
        MotivoSocio other = (MotivoSocio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.socios.MotivoSocio[ codigo=" + codigo + " ]";
    }
    
}
