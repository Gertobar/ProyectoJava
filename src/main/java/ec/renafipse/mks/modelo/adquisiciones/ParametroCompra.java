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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.PARAMETRO_COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroCompra.findAll", query = "SELECT p FROM ParametroCompra p"),
    @NamedQuery(name = "ParametroCompra.findByCodigo", query = "SELECT p FROM ParametroCompra p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ParametroCompra.findByNombre", query = "SELECT p FROM ParametroCompra p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ParametroCompra.findByDescripcion", query = "SELECT p FROM ParametroCompra p WHERE p.descripcion = :descripcion")})
public class ParametroCompra implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroCompra", fetch = FetchType.LAZY)
    private Collection<ParametroCompraIfip> parametroCompraIfipCollection;

    public ParametroCompra() {
    }

    public ParametroCompra(Long codigo) {
        this.codigo = codigo;
    }

    public ParametroCompra(Long codigo, String nombre, String descripcion) {
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
    public Collection<ParametroCompraIfip> getParametroCompraIfipCollection() {
        return parametroCompraIfipCollection;
    }

    public void setParametroCompraIfipCollection(Collection<ParametroCompraIfip> parametroCompraIfipCollection) {
        this.parametroCompraIfipCollection = parametroCompraIfipCollection;
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
        if (!(object instanceof ParametroCompra)) {
            return false;
        }
        ParametroCompra other = (ParametroCompra) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.ParametroCompra[ codigo=" + codigo + " ]";
    }
    
}
