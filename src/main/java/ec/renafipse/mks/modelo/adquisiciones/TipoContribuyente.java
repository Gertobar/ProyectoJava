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
import javax.persistence.OneToOne;
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
@Table(name = "MKS_ADQUISICIONES.TIPO_CONTRIBUYENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContribuyente.findAll", query = "SELECT t FROM TipoContribuyente t"),
    @NamedQuery(name = "TipoContribuyente.findByCodigo", query = "SELECT t FROM TipoContribuyente t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoContribuyente.findByNombre", query = "SELECT t FROM TipoContribuyente t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoContribuyente.findBySiglas", query = "SELECT t FROM TipoContribuyente t WHERE t.siglas = :siglas"),    
    @NamedQuery(name = "TipoContribuyente.findByCodigoEliminado", query = "SELECT t FROM TipoContribuyente t WHERE t.codigo = :codigo AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TipoContribuyente.findByEliminado", query = "SELECT t FROM TipoContribuyente t WHERE t.eliminado = :eliminado")})
public class TipoContribuyente implements Serializable {
    public static final String findByEliminado = "TipoContribuyente.findByEliminado";
    public static final String findByCodigoEliminado = "TipoContribuyente.findByCodigoEliminado";
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
    @Column(name = "ELIMINADO")
    private char eliminado;
  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoContribuyente", fetch = FetchType.LAZY)
    private Collection<Proveedor> proveedorCollection;

    public TipoContribuyente() {
    }

    public TipoContribuyente(Long codigo) {
        this.codigo = codigo;
    }

    public TipoContribuyente(Long codigo, String nombre, String siglas, char llevaContabilidad, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
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

  

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
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
        if (!(object instanceof TipoContribuyente)) {
            return false;
        }
        TipoContribuyente other = (TipoContribuyente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.TipoContribuyente[ codigo=" + codigo + " ]";
    }

   @XmlTransient
    public Collection<Proveedor> getProveedorCollection() {
        return proveedorCollection;
    }

    /**
     * @param proveedorCollection the proveedorCollection to set
     */
    public void setProveedorCollection(Collection<Proveedor> proveedorCollection) {
        this.proveedorCollection = proveedorCollection;
    }

    /**
      
    @XmlTransient
    public Collection<Proveedor> getProveedorCollection() {
        return proveedorCollection;
    }
 
    public void setProveedorCollection(Collection<Proveedor> proveedorCollection) {
        this.proveedorCollection = proveedorCollection;
    }
    
     */
}
