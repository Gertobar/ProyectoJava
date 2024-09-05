/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.comunes.Moneda;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PRODUCTO_CREDITO_MONEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoCreditoMoneda.findAll", query = "SELECT p FROM ProductoCreditoMoneda p"),
    @NamedQuery(name = "ProductoCreditoMoneda.findByCodigoProducto", query = "SELECT p FROM ProductoCreditoMoneda p WHERE p.productoCreditoMonedaPK.codigoProducto = :codigoProducto"),
    @NamedQuery(name = "ProductoCreditoMoneda.findByCodigoMoneda", query = "SELECT p FROM ProductoCreditoMoneda p WHERE p.productoCreditoMonedaPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "ProductoCreditoMoneda.findByEliminado", query = "SELECT p FROM ProductoCreditoMoneda p WHERE p.eliminado = :eliminado")})
public class ProductoCreditoMoneda implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductoCreditoMonedaPK productoCreditoMonedaPK;
    @Column(name = "ELIMINADO")
    private Character eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoCreditoMoneda", fetch = FetchType.LAZY)
    private Collection<ProductoCreditoMonedaIfip> productoCreditoMonedaIfipCollection;
    @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProductoCredito productoCredito;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;

    public ProductoCreditoMoneda() {
    }

    public ProductoCreditoMoneda(ProductoCreditoMonedaPK productoCreditoMonedaPK) {
        this.productoCreditoMonedaPK = productoCreditoMonedaPK;
    }

    public ProductoCreditoMoneda(long codigoProducto, long codigoMoneda) {
        this.productoCreditoMonedaPK = new ProductoCreditoMonedaPK(codigoProducto, codigoMoneda);
    }

    public ProductoCreditoMonedaPK getProductoCreditoMonedaPK() {
        return productoCreditoMonedaPK;
    }

    public void setProductoCreditoMonedaPK(ProductoCreditoMonedaPK productoCreditoMonedaPK) {
        this.productoCreditoMonedaPK = productoCreditoMonedaPK;
    }

    public Character getEliminado() {
        return eliminado;
    }

    public void setEliminado(Character eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<ProductoCreditoMonedaIfip> getProductoCreditoMonedaIfipCollection() {
        return productoCreditoMonedaIfipCollection;
    }

    public void setProductoCreditoMonedaIfipCollection(Collection<ProductoCreditoMonedaIfip> productoCreditoMonedaIfipCollection) {
        this.productoCreditoMonedaIfipCollection = productoCreditoMonedaIfipCollection;
    }

    public ProductoCredito getProductoCredito() {
        return productoCredito;
    }

    public void setProductoCredito(ProductoCredito productoCredito) {
        this.productoCredito = productoCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoCreditoMonedaPK != null ? productoCreditoMonedaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoCreditoMoneda)) {
            return false;
        }
        ProductoCreditoMoneda other = (ProductoCreditoMoneda) object;
        if ((this.productoCreditoMonedaPK == null && other.productoCreditoMonedaPK != null) || (this.productoCreditoMonedaPK != null && !this.productoCreditoMonedaPK.equals(other.productoCreditoMonedaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ProductoCreditoMoneda[ productoCreditoMonedaPK=" + productoCreditoMonedaPK + " ]";
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
    
}
