/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PRODUCTO_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoCredito.findAll", query = "SELECT p FROM ProductoCredito p"),
    @NamedQuery(name = "ProductoCredito.findByCodigo", query = "SELECT p FROM ProductoCredito p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProductoCredito.findByNombre", query = "SELECT p FROM ProductoCredito p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ProductoCredito.findBySiglas", query = "SELECT p FROM ProductoCredito p WHERE p.siglas = :siglas"),
    @NamedQuery(name = "ProductoCredito.findByDescripcion", query = "SELECT p FROM ProductoCredito p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProductoCredito.findByEliminado", query = "SELECT p FROM ProductoCredito p WHERE p.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "ProductoCredito.findByProductoCreditoMonedaIfip", query = "SELECT c FROM ProductoCreditoMonedaIfip i JOIN i.productoCreditoMoneda m JOIN m.productoCredito c WHERE i.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND i.eliminado = :eliminado"),
    @NamedQuery(name = "ProductoCredito.findByProductoCreditoMonedaI", query = "SELECT c FROM ProductoCreditoMonedaIfip i JOIN i.productoCreditoMoneda m JOIN m.productoCredito c WHERE i.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ProductoCredito.findByTasaIntProCreMonIfi", query = "SELECT DISTINCT p FROM TasaInteresProCreMonIfi i JOIN i.productoCreditoMonedaIfip t JOIN t.productoCreditoMoneda c JOIN c.productoCredito p WHERE t.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND i.eliminado = :eliminado")

})
public class ProductoCredito implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByProductoCreditoMonedaIfip = "ProductoCredito.findByProductoCreditoMonedaIfip";
    public static final String findByProductoCreditoMonedaI = "ProductoCredito.findByProductoCreditoMonedaI";
    public static final String findByTasaIntProCreMonIfi = "ProductoCredito.findByTasaIntProCreMonIfi";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PRODUCTO_CREDITO")
    @SequenceGenerator(name = "GSQ_PRODUCTO_CREDITO", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_PRODUCTO_CREDITO")
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
    @Size(min = 1, max = 4)
    @Column(name = "SIGLAS")
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoCredito", fetch = FetchType.LAZY)
    private Collection<ProductoCreditoMoneda> productoCreditoMonedaCollection;
    @JoinColumn(name = "CODIGO_TIPO_CARTERA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoCartera codigoTipoCartera;

    public ProductoCredito() {
    }

    public ProductoCredito(Long codigo) {
        this.codigo = codigo;
    }

    public ProductoCredito(Long codigo, String nombre, String siglas, String descripcion, char eliminado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public Collection<ProductoCreditoMoneda> getProductoCreditoMonedaCollection() {
        return productoCreditoMonedaCollection;
    }

    public void setProductoCreditoMonedaCollection(Collection<ProductoCreditoMoneda> productoCreditoMonedaCollection) {
        this.productoCreditoMonedaCollection = productoCreditoMonedaCollection;
    }

    public TipoCartera getCodigoTipoCartera() {
        return codigoTipoCartera;
    }

    public void setCodigoTipoCartera(TipoCartera codigoTipoCartera) {
        this.codigoTipoCartera = codigoTipoCartera;
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
        if (!(object instanceof ProductoCredito)) {
            return false;
        }
        ProductoCredito other = (ProductoCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.ProductoCredito[ codigo=" + codigo + " ]";
    }
    
}
