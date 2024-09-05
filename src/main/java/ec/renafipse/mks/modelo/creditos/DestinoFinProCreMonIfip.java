/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MKS_CREDITOS.DESTINO_FIN_PRO_CRE_MON_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DestinoFinProCreMonIfip.findAll", query = "SELECT d FROM DestinoFinProCreMonIfip d"),
    @NamedQuery(name = "DestinoFinProCreMonIfip.findByProductoCredito", query = "SELECT d FROM DestinoFinProCreMonIfip d JOIN d.destinoFinanciero f WHERE d.destinoFinanciero.codigo = f.codigo AND d.productoCreditoMonedaIfip.productoCreditoMoneda.productoCredito.codigo = :codigoProducto AND d.productoCreditoMonedaIfip.productoCreditoMoneda.moneda.codigo = :codigoMoneda AND d.productoCreditoMonedaIfip.ifip.codigo = :codigoIfip AND d.vigente = 'S' AND f.eliminado = 'N'")
    })
public class DestinoFinProCreMonIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByProductoCredito = "DestinoFinProCreMonIfip.findByProductoCredito";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "CODIGO_DESTINO_FINANCIERO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DestinoFinanciero destinoFinanciero;
    @Basic(optional = false)
    @NotNull
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;

    public DestinoFinProCreMonIfip() {
    }

    public DestinoFinProCreMonIfip(Long codigo) {
        this.codigo = codigo;
    }

    public DestinoFinProCreMonIfip(Long codigo, ProductoCreditoMonedaIfip productoCreditoMonedaIfip, DestinoFinanciero destinoFinanciero, String vigente) {
        this.codigo = codigo;
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
        this.destinoFinanciero = destinoFinanciero;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DestinoFinProCreMonIfip)) {
            return false;
        }
        DestinoFinProCreMonIfip other = (DestinoFinProCreMonIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.DestinoFinProCreMonIfiip[ codigo=" + codigo + " ]";
    }

    /**
     * @return the destinoFinanciero
     */
    public DestinoFinanciero getDestinoFinanciero() {
        return destinoFinanciero;
    }

    /**
     * @param destinoFinanciero the destinoFinanciero to set
     */
    public void setDestinoFinanciero(DestinoFinanciero destinoFinanciero) {
        this.destinoFinanciero = destinoFinanciero;
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
