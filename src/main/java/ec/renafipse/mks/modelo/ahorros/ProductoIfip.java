/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.comunes.Periodicidad;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vicastoc
 */
    @Entity
@Table(name = "MKS_AHORROS.PRODUCTO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoIfip.findAll", query = "SELECT p FROM ProductoIfip p"),
    @NamedQuery(name = "ProductoIfip.findByCodigoTipoProducto", query = "SELECT p FROM ProductoIfip p WHERE p.productoIfipPK.codigoTipoProducto = :codigoTipoProducto"),
    @NamedQuery(name = "ProductoIfip.findByCodigoMoneda", query = "SELECT p FROM ProductoIfip p WHERE p.productoIfipPK.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "ProductoIfip.findByCodigoIfip", query = "SELECT p FROM ProductoIfip p WHERE p.productoIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ProductoIfip.findByCodigoPerCal", query = "SELECT p FROM ProductoIfip p WHERE p.codigoPerCal = :codigoPerCal"),
    @NamedQuery(name = "ProductoIfip.findByCodigoPerCap", query = "SELECT p FROM ProductoIfip p WHERE p.codigoPerCap = :codigoPerCap"),
    @NamedQuery(name = "ProductoIfip.findByRegistradoPor", query = "SELECT p FROM ProductoIfip p WHERE p.registradoPor = :registradoPor"),
    @NamedQuery(name = "ProductoIfip.findBySiglasLibreta", query = "SELECT p FROM ProductoIfip p WHERE p.siglasLibreta = :siglasLibreta"),
    @NamedQuery(name = "ProductoIfip.findByFechaRegistro", query = "SELECT p FROM ProductoIfip p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ProductoIfip.findByInteresCalculadoSobre", query = "SELECT p FROM ProductoIfip p WHERE p.interesCalculadoSobre = :interesCalculadoSobre"),
    @NamedQuery(name = "ProductoIfip.findByEliminado", query = "SELECT p FROM ProductoIfip p WHERE p.eliminado = :eliminado"),
    //Personalizados
    @NamedQuery(name = "ProductoIfip.findByMonedaProductoIfip", query = "SELECT p FROM ProductoIfip p JOIN p.producto r  JOIN r.tipoProducto t WHERE p.productoIfipPK.codigoIfip = :codigoIfip AND p.productoIfipPK.codigoMoneda = :codigoMoneda AND p.eliminado = :eliminado AND r.eliminado = :eliminado AND t.eliminado = :eliminado"),
    @NamedQuery(name = "ProductoIfip.findBySiglasLibretaProductoIfip", query = "SELECT p FROM ProductoIfip p WHERE p.siglasLibreta = :siglasLibreta"),
    @NamedQuery(name = "ProductoIfip.findByProductoIfipPK", query = "SELECT p FROM ProductoIfip p WHERE p.productoIfipPK.codigoIfip = :codigoIfip AND p.productoIfipPK.codigoMoneda = :codigoMoneda AND p.productoIfipPK.codigoTipoProducto = :codigoTipoProducto"),
      
    @NamedQuery(name = "ProductoIfip.findByPKSiglasLibreta", query = "SELECT p FROM ProductoIfip p WHERE p.productoIfipPK.codigoIfip = :codigoIfip AND p.productoIfipPK.codigoMoneda = :codigoMoneda AND p.productoIfipPK.codigoTipoProducto = :codigoTipoProducto AND p.siglasLibreta = :siglasLibreta")
})
public class ProductoIfip implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByMonedaProductoIfip = "ProductoIfip.findByMonedaProductoIfip";
    public static final String findByProductoIfipPK="ProductoIfip.findByProductoIfipPK";
    public static final String findBySiglasLibretaProductoIfip="ProductoIfip.findBySiglasLibretaProductoIfip";
    public static final String findByMonedaProductoIfipProducto =  "ProductoIfip.findByMonedaProductoIfipProducto";
    public static final String findByPKSiglasLibreta =  "ProductoIfip.findByPKSiglasLibreta";
    @EmbeddedId
    protected ProductoIfipPK productoIfipPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PER_CAL")
    private long codigoPerCal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PER_CAP")
    private long codigoPerCap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS_LIBRETA")
    private String siglasLibreta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES_CALCULADO_SOBRE")
    private char interesCalculadoSobre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;
  
    @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Producto producto;
    
     
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false) 
    private Usuario registradoPorUsuario;
  

  
    @JoinColumn(name = "CODIGO_PER_CAL", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false) 
    private Periodicidad periodicidadCal;
    
    @JoinColumn(name = "CODIGO_PER_CAP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false) 
    private Periodicidad perdiodicidadCap;
    
    public ProductoIfip() {
    }

    public ProductoIfip(ProductoIfipPK productoIfipPK) {
        this.productoIfipPK = productoIfipPK;
    }

    public ProductoIfip(ProductoIfipPK productoIfipPK, long codigoPerCal, long codigoPerCap, long registradoPor, String siglasLibreta, Date fechaRegistro, char interesCalculadoSobre, char eliminado) {
        this.productoIfipPK = productoIfipPK;
        this.codigoPerCal = codigoPerCal;
        this.codigoPerCap = codigoPerCap;
        this.registradoPor = registradoPor;
        this.siglasLibreta = siglasLibreta;
        this.fechaRegistro = fechaRegistro;
        this.interesCalculadoSobre = interesCalculadoSobre;
        this.eliminado = eliminado;
    }

    public ProductoIfip(long codigoTipoProducto, long codigoMoneda, long codigoIfip) {
        this.productoIfipPK = new ProductoIfipPK(codigoTipoProducto, codigoMoneda, codigoIfip);
    }

    public ProductoIfipPK getProductoIfipPK() {
        return productoIfipPK;
    }

    public void setProductoIfipPK(ProductoIfipPK productoIfipPK) {
        this.productoIfipPK = productoIfipPK;
    }

    public long getCodigoPerCal() {
        return codigoPerCal;
    }

    public void setCodigoPerCal(long codigoPerCal) {
        this.codigoPerCal = codigoPerCal;
    }

    public long getCodigoPerCap() {
        return codigoPerCap;
    }

    public void setCodigoPerCap(long codigoPerCap) {
        this.codigoPerCap = codigoPerCap;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    public String getSiglasLibreta() {
        return siglasLibreta;
    }

    public void setSiglasLibreta(String siglasLibreta) {
        this.siglasLibreta = siglasLibreta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public char getInteresCalculadoSobre() {
        return interesCalculadoSobre;
    }

    public void setInteresCalculadoSobre(char interesCalculadoSobre) {
        this.interesCalculadoSobre = interesCalculadoSobre;
    }

    public char getEliminado() {
        return eliminado;
    }

    public void setEliminado(char eliminado) {
        this.eliminado = eliminado;
    }

//    @XmlTransient
//    public Collection<TasaInteresProductoIfip> getTasaInteresProductoIfipCollection() {
//        return tasaInteresProductoIfipCollection;
//    }
//
//    public void setTasaInteresProductoIfipCollection(Collection<TasaInteresProductoIfip> tasaInteresProductoIfipCollection) {
//        this.tasaInteresProductoIfipCollection = tasaInteresProductoIfipCollection;
//    }

 

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoIfipPK != null ? productoIfipPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoIfip)) {
            return false;
        }
        ProductoIfip other = (ProductoIfip) object;
        if ((this.productoIfipPK == null && other.productoIfipPK != null) || (this.productoIfipPK != null && !this.productoIfipPK.equals(other.productoIfipPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.ProductoIfip[ productoIfipPK=" + productoIfipPK + " ]";
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

    /**
     * @return the periodicidadCal
     */
    public Periodicidad getPeriodicidadCal() {
        return periodicidadCal;
    }

    /**
     * @param periodicidadCal the periodicidadCal to set
     */
    public void setPeriodicidadCal(Periodicidad periodicidadCal) {
        this.periodicidadCal = periodicidadCal;
    }

    /**
     * @return the perdiodicidadCap
     */
    public Periodicidad getPerdiodicidadCap() {
        return perdiodicidadCap;
    }

    /**
     * @param perdiodicidadCap the perdiodicidadCap to set
     */
    public void setPerdiodicidadCap(Periodicidad perdiodicidadCap) {
        this.perdiodicidadCap = perdiodicidadCap;
    }
}
