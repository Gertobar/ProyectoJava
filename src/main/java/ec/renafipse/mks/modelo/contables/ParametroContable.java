/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

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
@Table(name = "MKS_CONTABLES.PARAMETRO_CONTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroContable.findAll", query = "SELECT p FROM ParametroContable p"),
    @NamedQuery(name = "ParametroContable.findByCodigo", query = "SELECT p FROM ParametroContable p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ParametroContable.findByNombre", query = "SELECT p FROM ParametroContable p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ParametroContable.findByDescripcion", query = "SELECT p FROM ParametroContable p WHERE p.descripcion = :descripcion")})
public class ParametroContable implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroContable")
    private Collection<ParametroContableIfip> parametroContableIfipCollection;

    public ParametroContable() {
    }

    public ParametroContable(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroContable(Long codigo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    @XmlTransient
    public Collection<ParametroContableIfip> getParametroContableIfipCollection() {
        return parametroContableIfipCollection;
    }

    public void setParametroContableIfipCollection(Collection<ParametroContableIfip> parametroContableIfipCollection) {
        this.parametroContableIfipCollection = parametroContableIfipCollection;
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
        if (!(object instanceof ParametroContable)) {
            return false;
        }
        ParametroContable other = (ParametroContable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.ParametroContable[ codigo=" + codigo + " ]";
    }
    
}
