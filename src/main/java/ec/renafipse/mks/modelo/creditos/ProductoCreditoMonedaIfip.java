/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PRODUCTO_CREDITO_MONEDA_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findAll", query = "SELECT p FROM ProductoCreditoMonedaIfip p"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findByCodigoProducto", query = "SELECT p FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findByCodigoMoneda", query = "SELECT p FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findByCodigoIfip", query = "SELECT p FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findByRegistradorPor", query = "SELECT p FROM ProductoCreditoMonedaIfip p WHERE p.registradorPor = :registradorPor"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findByFechaRegistro", query = "SELECT p FROM ProductoCreditoMonedaIfip p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findByEliminado", query = "SELECT p FROM ProductoCreditoMonedaIfip p WHERE p.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findByProductoCreditoMonedaIfipPK", query = "SELECT p FROM ProductoCreditoMonedaIfip p WHERE P.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND p.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND p.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findMonedaByCodigoIfip", query = "SELECT p.productoCreditoMoneda.moneda FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND p.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND p.eliminado = :eliminado"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findMonedaByIfipEliminado", query = "SELECT p.productoCreditoMoneda.moneda FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND p.eliminado = :eliminado"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findMonedaByCodigoIfipMoneda", query = "SELECT p.productoCreditoMoneda.productoCredito FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND p.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND p.productoCreditoMoneda.productoCredito.codigoTipoCartera.codigo = :codigoTipoCartera AND p.eliminado = :eliminado"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findByIfipMonedaBy", query = "SELECT p FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND p.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND p.eliminado = :eliminado ORDER BY p.productoCreditoMonedaIfipPK.codigoProducto"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findProductoCreditoByCodigoIfipMonedaTipoCartera", query = "SELECT p.productoCreditoMoneda.productoCredito FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMoneda.productoCredito.codigoTipoCartera.codigo = :codigoTipoCartera AND p.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND p.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND p.eliminado = :eliminado"),
    @NamedQuery(name = "ProductoCreditoMonedaIfip.findProductoCreditoByCodigoIfipMoneda", query = "SELECT p.productoCreditoMoneda.productoCredito FROM ProductoCreditoMonedaIfip p WHERE p.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND p.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND p.eliminado = :eliminado"), })
public class ProductoCreditoMonedaIfip implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByProductoCreditoMonedaIfipPK = "ProductoCreditoMonedaIfip.findByProductoCreditoMonedaIfipPK";
    public static final String findMonedaByCodigoIfip = "ProductoCreditoMonedaIfip.findMonedaByCodigoIfip";
    public static final String findMonedaByIfipEliminado = "ProductoCreditoMonedaIfip.findMonedaByIfipEliminado";    
    public static final String findMonedaByCodigoIfipMoneda = "ProductoCreditoMonedaIfip.findMonedaByCodigoIfipMoneda";
    public static final String findByIfipMonedaBy = "ProductoCreditoMonedaIfip.findByIfipMonedaBy";
    @EmbeddedId
    protected ProductoCreditoMonedaIfipPK productoCreditoMonedaIfipPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADOR_POR")
    private long registradorPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoCreditoMonedaIfip", fetch = FetchType.LAZY)
    private Collection<PeriodicidadProMonIfi> periodicidadProMonIfiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoCreditoMonedaIfip", fetch = FetchType.LAZY)
    private Collection<Solicitud> solicitudCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoCreditoMonedaIfip", fetch = FetchType.LAZY)
    private Collection<TasaInteresProCreMonIfi> tasaInteresProCreMonIfiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoCreditoMonedaIfip", fetch = FetchType.LAZY)
    private Collection<TipoRubroProCreMonIfi> tipoRubroProCreMonIfiCollection;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMoneda productoCreditoMoneda;
    @JoinColumn(name = "REGISTRADOR_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRegistradoPor;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;
    
    public ProductoCreditoMonedaIfip() {
    }

    public ProductoCreditoMonedaIfip(ProductoCreditoMonedaIfipPK productoCreditoMonedaIfipPK) {
        this.productoCreditoMonedaIfipPK = productoCreditoMonedaIfipPK;
    }

    public ProductoCreditoMonedaIfip(ProductoCreditoMonedaIfipPK productoCreditoMonedaIfipPK, long registradorPor, Date fechaRegistro, char eliminado) {
        this.productoCreditoMonedaIfipPK = productoCreditoMonedaIfipPK;
        this.registradorPor = registradorPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public ProductoCreditoMonedaIfip(long codigoProducto, long codigoMoneda, long codigoIfip) {
        this.productoCreditoMonedaIfipPK = new ProductoCreditoMonedaIfipPK(codigoProducto, codigoMoneda, codigoIfip);
    }

    public ProductoCreditoMonedaIfipPK getProductoCreditoMonedaIfipPK() {
        return productoCreditoMonedaIfipPK;
    }

    public void setProductoCreditoMonedaIfipPK(ProductoCreditoMonedaIfipPK productoCreditoMonedaIfipPK) {
        this.productoCreditoMonedaIfipPK = productoCreditoMonedaIfipPK;
    }

    public long getRegistradorPor() {
        return registradorPor;
    }

    public void setRegistradorPor(long registradorPor) {
        this.registradorPor = registradorPor;
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

    @XmlTransient
    public Collection<PeriodicidadProMonIfi> getPeriodicidadProMonIfiCollection() {
        return periodicidadProMonIfiCollection;
    }

    public void setPeriodicidadProMonIfiCollection(Collection<PeriodicidadProMonIfi> periodicidadProMonIfiCollection) {
        this.periodicidadProMonIfiCollection = periodicidadProMonIfiCollection;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @XmlTransient
    public Collection<TasaInteresProCreMonIfi> getTasaInteresProCreMonIfiCollection() {
        return tasaInteresProCreMonIfiCollection;
    }

    public void setTasaInteresProCreMonIfiCollection(Collection<TasaInteresProCreMonIfi> tasaInteresProCreMonIfiCollection) {
        this.tasaInteresProCreMonIfiCollection = tasaInteresProCreMonIfiCollection;
    }

    @XmlTransient
    public Collection<TipoRubroProCreMonIfi> getTipoRubroProCreMonIfiCollection() {
        return tipoRubroProCreMonIfiCollection;
    }

    public void setTipoRubroProCreMonIfiCollection(Collection<TipoRubroProCreMonIfi> tipoRubroProCreMonIfiCollection) {
        this.tipoRubroProCreMonIfiCollection = tipoRubroProCreMonIfiCollection;
    }

    public ProductoCreditoMoneda getProductoCreditoMoneda() {
        return productoCreditoMoneda;
    }

    public void setProductoCreditoMoneda(ProductoCreditoMoneda productoCreditoMoneda) {
        this.productoCreditoMoneda = productoCreditoMoneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoCreditoMonedaIfipPK != null ? productoCreditoMonedaIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoCreditoMonedaIfip)) {
            return false;
        }
        ProductoCreditoMonedaIfip other = (ProductoCreditoMonedaIfip) object;
        if ((this.productoCreditoMonedaIfipPK == null && other.productoCreditoMonedaIfipPK != null) || (this.productoCreditoMonedaIfipPK != null && !this.productoCreditoMonedaIfipPK.equals(other.productoCreditoMonedaIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip[ productoCreditoMonedaIfipPK=" + productoCreditoMonedaIfipPK + " ]";
    }
  
    /**
     * @return the usuarioRegistradoPor
     */
    public Usuario getUsuarioRegistradoPor() {
        return usuarioRegistradoPor;
    }

    /**
     * @param usuarioRegistradoPor the usuarioRegistradoPor to set
     */
    public void setUsuarioRegistradoPor(Usuario usuarioRegistradoPor) {
        this.usuarioRegistradoPor = usuarioRegistradoPor;
    }

    /**
     * @return the ifip
     */
    public Ifip getIfip() {
        return ifip;
    }

    /**
     * @param ifip the ifip to set
     */
    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }
    
}
