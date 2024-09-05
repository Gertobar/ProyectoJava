/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByCodigoTipoProducto", query = "SELECT p FROM Producto p WHERE p.productoPK.codigoTipoProducto = :codigoTipoProducto"),
    @NamedQuery(name = "Producto.findByCodigoMoneda", query = "SELECT p FROM Producto p WHERE p.productoPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "Producto.findByRegistradoPor", query = "SELECT p FROM Producto p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "Producto.findByFechaRegistro", query = "SELECT p FROM Producto p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Producto.findByEliminado", query = "SELECT p FROM Producto p WHERE p.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "Producto.findByMonedaProducto", query = "SELECT r FROM ProductoIfip p   JOIN  p.producto r JOIN r.tipoProducto t WHERE p.productoIfipPK.codigoIfip = :codigoIfip AND p.productoIfipPK.codigoMoneda = :codigoMoneda AND p.eliminado = :eliminado AND r.eliminado = :eliminado AND t.eliminado = :eliminado"),
    @NamedQuery(name = "Producto.findByProductoPK", query = "SELECT p FROM Producto p  WHERE p.productoPK.codigoMoneda = :codigoMoneda AND p.productoPK.codigoTipoProducto = :codigoTipoProducto "),
    @NamedQuery(name = "Producto.findByProductoIfip", query = "SELECT p FROM ProductoIfip i  JOIN  i.producto p WHERE i.eliminado = :ifipEliminado AND p.eliminado = :productoEliminado"),
    @NamedQuery(name = "Producto.findByUnique", query = "SELECT p FROM ProductoIfip i  JOIN  i.producto p WHERE p.productoPK.codigoTipoProducto = :codigoTipoProducto AND p.productoPK.codigoMoneda = :codigoMoneda")
})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByMonedaProducto = "Producto.findByMonedaProducto";
    public static final String findByCodigoTipoProducto ="Producto.findByCodigoTipoProducto";
    public static final String findByProductoPK ="Producto.findByProductoPK";
    public static final String findByUnique ="Producto.findByUnique";
    @EmbeddedId
    protected ProductoPK productoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoProducto tipoProducto;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private Collection<ProductoIfip> productoIfipCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private Collection<ConceptoTransaccionPro> conceptoTransaccionProCollection;
    
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false) 
    private Usuario registradoPorUsuario;
    
    public Producto() {
    }

    public Producto(ProductoPK productoPK) {
        this.productoPK = productoPK;
    }

    public Producto(ProductoPK productoPK, long registradoPor, Date fechaRegistro, char eliminado) {
        this.productoPK = productoPK;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Producto(long codigoTipoProducto, long codigoMoneda) {
        this.productoPK = new ProductoPK(codigoTipoProducto, codigoMoneda);
    }

    public ProductoPK getProductoPK() {
        return productoPK;
    }

    public void setProductoPK(ProductoPK productoPK) {
        this.productoPK = productoPK;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @XmlTransient
    public Collection<ProductoIfip> getProductoIfipCollection() {
        return productoIfipCollection;
    }

    public void setProductoIfipCollection(Collection<ProductoIfip> productoIfipCollection) {
        this.productoIfipCollection = productoIfipCollection;
    }

    @XmlTransient
    public Collection<ConceptoTransaccionPro> getConceptoTransaccionProCollection() {
        return conceptoTransaccionProCollection;
    }

    public void setConceptoTransaccionProCollection(Collection<ConceptoTransaccionPro> conceptoTransaccionProCollection) {
        this.conceptoTransaccionProCollection = conceptoTransaccionProCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoPK != null ? productoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.productoPK == null && other.productoPK != null) || (this.productoPK != null && !this.productoPK.equals(other.productoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.Producto[ productoPK=" + productoPK + " ]";
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the registradoPorUsuario
     */
    public Usuario getRegistradoPorUsuario() {
        return registradoPorUsuario;
    }

    /**
     * @param registradoPorUsuario the registradoPorUsuario to set
     */
    public void setRegistradoPorUsuario(Usuario registradoPorUsuario) {
        this.registradoPorUsuario = registradoPorUsuario;
    }

}
