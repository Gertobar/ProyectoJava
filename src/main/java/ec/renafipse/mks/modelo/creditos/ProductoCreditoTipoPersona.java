/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.socios.TipoPersona;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Santiago
 */
@Entity
@Table(name = "MKS_CREDITOS.PRODUCTO_CREDITO_TIPO_PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoCreditoTipoPersona.findAll", query = "SELECT p FROM ProductoCreditoTipoPersona p"),
    @NamedQuery(name = "ProductoCreditoTipoPersona.findProdcutoCreditoMonIfiipByTipoPersona", query = "SELECT p FROM ProductoCreditoTipoPersona p JOIN p.tipoPersona t WHERE p.tipoPersona.codigo = t.codigo AND p.productoCreditoMonedaIfip.productoCreditoMoneda.productoCredito.codigoTipoCartera.codigo = :codigoTipoCartera AND t.codigo = :codigoTipoPersona AND p.vigente = 'S' AND p.productoCreditoMonedaIfip.productoCreditoMoneda.productoCredito.eliminado = 'N'")
    })
public class ProductoCreditoTipoPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findProdcutoCreditoMonIfiipByTipoPersona = "ProductoCreditoTipoPersona.findProdcutoCreditoMonIfiipByTipoPersona";
    @EmbeddedId
    protected ProductoCreditoTipoPersonaPK productoCreditoTipoPersonaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "CODIGO_TIPO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoPersona tipoPersona;
    @Basic(optional = false)
    @NotNull
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;

    public ProductoCreditoTipoPersona() {
    }

    public ProductoCreditoTipoPersona(ProductoCreditoTipoPersonaPK productoCreditoTipoPersonaPK) {
        this.productoCreditoTipoPersonaPK = productoCreditoTipoPersonaPK;
    }

    public ProductoCreditoTipoPersona(ProductoCreditoTipoPersonaPK productoCreditoTipoPersonaPK, String vigente) {
        this.productoCreditoTipoPersonaPK = productoCreditoTipoPersonaPK;
        this.vigente = vigente;
    }

    public ProductoCreditoTipoPersona(long codigoProducto, long codigoMoneda, long codigoIfip, long codigoTipoPersona) {
        this.productoCreditoTipoPersonaPK = new ProductoCreditoTipoPersonaPK(codigoProducto, codigoMoneda, codigoIfip, codigoTipoPersona);
    }
    public ProductoCreditoTipoPersona(String vigente, TipoPersona tipoPersona, ProductoCreditoMonedaIfip productoCreditoMonedaIfip){
        this.vigente = vigente;
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
        this.tipoPersona = tipoPersona;
    }

    public ProductoCreditoTipoPersonaPK getProductoCreditoTipoPersonaPK() {
        return productoCreditoTipoPersonaPK;
    }

    public void setProductoCreditoTipoPersonaPK(ProductoCreditoTipoPersonaPK productoCreditoTipoPersonaPK) {
        this.productoCreditoTipoPersonaPK = productoCreditoTipoPersonaPK;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoCreditoTipoPersonaPK != null ? productoCreditoTipoPersonaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoCreditoTipoPersona)) {
            return false;
        }
        ProductoCreditoTipoPersona other = (ProductoCreditoTipoPersona) object;
        if ((this.productoCreditoTipoPersonaPK == null && other.productoCreditoTipoPersonaPK != null) || (this.productoCreditoTipoPersonaPK != null && !this.productoCreditoTipoPersonaPK.equals(other.productoCreditoTipoPersonaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ProductoCreditoTipoPersona[ productoCreditoTipoPersonaPK=" + productoCreditoTipoPersonaPK + " ]";
    }

    /**
     * @return the tipoPersona
     */
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * @return the productoCreditoMonedaIfip
     */
    public ProductoCreditoMonedaIfip getProductoCreditoMonedaIfip() {
        return productoCreditoMonedaIfip;
    }

    /**
     * @param productoCreditoMonedaIfip the productoCreditoMonedaIfip to set
     */
    public void setProductoCreditoMonedaIfip(ProductoCreditoMonedaIfip productoCreditoMonedaIfip) {
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
    }
    
}
