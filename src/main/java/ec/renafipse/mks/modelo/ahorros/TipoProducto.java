/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

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
@Table(name = "MKS_AHORROS.TIPO_PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProducto.findAll", query = "SELECT t FROM TipoProducto t"),
    @NamedQuery(name = "TipoProducto.findByCodigo", query = "SELECT t FROM TipoProducto t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoProducto.findByNombre", query = "SELECT t FROM TipoProducto t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoProducto.findBySiglas", query = "SELECT t FROM TipoProducto t WHERE t.siglas = :siglas"),
    @NamedQuery(name = "TipoProducto.findByEsProgramado", query = "SELECT t FROM TipoProducto t WHERE t.esProgramado = :esProgramado"),
    @NamedQuery(name = "TipoProducto.findByEliminado", query = "SELECT t FROM TipoProducto t WHERE t.eliminado = :eliminado"),
    //Personalizadas
    @NamedQuery(name = "TipoProducto.findByMoneda", query = "SELECT t FROM Producto p JOIN p.tipoProducto  t WHERE p.productoPK.codigoMoneda = :codigoMoneda AND p.eliminado = :eliminado"),
    @NamedQuery(name = "TipoProducto.findByConceptoTransaccion", query = "SELECT DISTINCT t FROM ConceptoTransaccionPro c JOIN c.producto p JOIN p.tipoProducto t"),
@NamedQuery(name = "TipoProducto.findByMonedaIfip", query = "SELECT t FROM ProductoIfip f JOIN f.producto p JOIN p.tipoProducto t WHERE f.productoIfipPK.codigoIfip = :codigoIfip AND f.productoIfipPK.codigoMoneda = :codigoMoneda"),
@NamedQuery(name = "TipoProducto.findBytipoProductoIfip", query = "SELECT t FROM ProductoIfip f JOIN f.producto p JOIN p.tipoProducto t WHERE f.productoIfipPK.codigoIfip = :codigoIfip AND f.eliminado = :eliminado")})
public class TipoProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByEliminado = "TipoProducto.findByEliminado";
    public static final String findByMoneda = "TipoProducto.findByMoneda";
    public static final String findByCodigo = "TipoProducto.findByCodigo";
    public static final String findByConceptoTransaccion = "TipoProducto.findByConceptoTransaccion";
    public static final String findByMonedaIfip = "TipoProducto.findByMonedaIfip";
    public static final String findBytipoProductoIfip = "TipoProducto.findBytipoProductoIfip";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_PRODUCTO")
    @SequenceGenerator(name = "GSQ_TIPO_PRODUCTO", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_TIPO_PRODUCTO")
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
    @Size(min = 1, max = 2)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_PROGRAMADO")
    private char esProgramado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProducto")
    private Collection<Producto> productoCollection;

    public TipoProducto() {
    }

    public TipoProducto(Long codigo) {
        this.codigo = codigo;
    }

    public TipoProducto(Long codigo, String nombre, String siglas, char esProgramado, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.esProgramado = esProgramado;
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

    public char getEsProgramado() {
        return esProgramado;
    }

    public void setEsProgramado(char esProgramado) {
        this.esProgramado = esProgramado;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
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
        if (!(object instanceof TipoProducto)) {
            return false;
        }
        TipoProducto other = (TipoProducto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.TipoProducto[ codigo=" + codigo + " ]";
    }
    
}
