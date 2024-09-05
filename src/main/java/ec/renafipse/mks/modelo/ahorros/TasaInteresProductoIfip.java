package ec.renafipse.mks.modelo.ahorros;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vicastoc
 */
@Entity
@Table(name = "MKS_AHORROS.TASA_INTERES_PRODUCTO_IFIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TasaInteresProductoIfip.findAll", query = "SELECT t FROM TasaInteresProductoIfip t"),
    @NamedQuery(name = "TasaInteresProductoIfip.findByCodigo", query = "SELECT t FROM TasaInteresProductoIfip t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TasaInteresProductoIfip.findByRangoInicial", query = "SELECT t FROM TasaInteresProductoIfip t WHERE t.rangoInicial = :rangoInicial"),
    @NamedQuery(name = "TasaInteresProductoIfip.findByRangoFinal", query = "SELECT t FROM TasaInteresProductoIfip t WHERE t.rangoFinal = :rangoFinal"),
    @NamedQuery(name = "TasaInteresProductoIfip.findByRegistradoPor", query = "SELECT t FROM TasaInteresProductoIfip t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TasaInteresProductoIfip.findByFechaRegistro", query = "SELECT t FROM TasaInteresProductoIfip t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TasaInteresProductoIfip.findByEliminado", query = "SELECT t FROM TasaInteresProductoIfip t WHERE t.eliminado = :eliminado"),
    @NamedQuery(name = "TasaInteresProductoIfip.findByRangos", query = "SELECT t FROM TasaInteresProductoIfip t"
            + "                                                         WHERE t.eliminado = :eliminado"
            + "                                                         and :rInicial BETWEEN t.rangoInicial and t.rangoFinal"
            + "                                                         and t.tipoProducto.codigo=:codigoTipoProducto "
            + "                                                         and t.tasaInteres=:codigoTasaInteres"
            + "                                                         or :rFinal BETWEEN t.rangoInicial and t.rangoFinal"
            + "                                                         and t.tipoProducto.codigo=:codigoTipoProducto"
            + "                                                         and t.tasaInteres=:codigoTasaInteres"),  
    @NamedQuery(name = "TasaInteresProductoIfip.findByUniqui", query = "SELECT t FROM TasaInteresProductoIfip t where t.CodigoMoneda=:codigoMoneda and t.codigoIfip=:codigoIfip and t.CodigoTipoProducto=:codigoTipoProducto and t.rangoInicial=:rangoInicial")
})

public class TasaInteresProductoIfip implements Serializable {

    private static final long serialVersionUID = 1L;
        public static final String findByRangos="TasaInteresProductoIfip.findByRangos";
          public static final String findByUniqui="TasaInteresProductoIfip.findByUniqui";
        
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TASA_INTERES_PRO_IFI")
    @SequenceGenerator(name = "GSQ_TASA_INTERES_PRO_IFI", schema = "MKS_AHORROS", allocationSize = 0, sequenceName = "SEQ_TASA_INTERES_PRO_IFIP")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGO_INICIAL")
    private long rangoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGO_FINAL")
    private long rangoFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ELIMINADO")
    private char eliminado;


        @JoinColumns({
        @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO_TIPO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
  @ManyToOne(cascade =CascadeType.ALL)
    private ProductoIfip productoIfip;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TIPO_PRODUCTO")
    private long CodigoTipoProducto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long CodigoMoneda;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    
 
    private long codigoIfip;

    @JoinColumn(name = "CODIGO_TASA_INTERES", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AhorroTasaInteresIfip codigoTasaInteres;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TASA_INTERES")
    private long tasaInteres;

    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario registradoUsuarioPor;

    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;

    @JoinColumn(name = "CODIGO_TIPO_PRODUCTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoProducto tipoProducto;

    public TasaInteresProductoIfip() {
    }

    public TasaInteresProductoIfip(long codigo) {
        this.codigo = codigo;
    }

    public TasaInteresProductoIfip(long codigo,
            long rangoInicial,
            long rangoFinal,
            Usuario registradoPor,
            Date fechaRegistro,
            char eliminado) {
        this.codigo = codigo;
        this.rangoInicial = rangoInicial;
        this.rangoFinal = rangoFinal;
        this.registradoUsuarioPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
//        this.CodigoTipoProducto=codigoTipoProducto;
//        this.CodigoMoneda=codigoMoneda;
//        this.codigoIfip=codigoIfip;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public long getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(long rangoInicial) {
        this.rangoInicial = rangoInicial;
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

    public ProductoIfip getProductoIfip() {
        return productoIfip;
    }

    public void setProductoIfip(ProductoIfip productoIfip) {
        this.productoIfip = productoIfip;
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
        if (!(object instanceof TasaInteresProductoIfip)) {
            return false;
        }
        TasaInteresProductoIfip other = (TasaInteresProductoIfip) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.TasaInteresProductoIfip[ codigo=" + codigo + " ]";
    }

    /**
     * @return the tipoProducto
     */
    public void setProductoIfip(ProductoIfipPK productoIfipPK) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the rangoFinal
     */
    public long getRangoFinal() {
        return rangoFinal;
    }

    /**
     * @param rangoFinal the rangoFinal to set
     */
    public void setRangoFinal(long rangoFinal) {
        this.rangoFinal = rangoFinal;
    }

    /**
     * @return the registradoUsuarioPor
     */
    public Usuario getRegistradoUsuarioPor() {
        return registradoUsuarioPor;
    }

    /**
     * @param registradoUsuarioPor the registradoUsuarioPor to set
     */
    public void setRegistradoUsuarioPor(Usuario registradoUsuarioPor) {
        this.registradoUsuarioPor = registradoUsuarioPor;
    }

    /**
     * @return the registradoPor
     */
    public long getRegistradoPor() {
        return registradoPor;
    }

    /**
     * @param registradoPor the registradoPor to set
     */
    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    /**
     * @return the tipoProducto
     */
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto the tipoProducto to set
     */
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * @return the CodigoTipoProducto
     */
    public long getCodigoTipoProducto() {
        return CodigoTipoProducto;
    }

    /**
     * @param CodigoTipoProducto the CodigoTipoProducto to set
     */
    public void setCodigoTipoProducto(long CodigoTipoProducto) {
        this.CodigoTipoProducto = CodigoTipoProducto;
    }

    /**
     * @return the CodigoMoneda
     */
    public long getCodigoMoneda() {
        return CodigoMoneda;
    }

    /**
     * @param CodigoMoneda the CodigoMoneda to set
     */
    public void setCodigoMoneda(long CodigoMoneda) {
        this.CodigoMoneda = CodigoMoneda;
    }

    /**
     * @return the codigoIfip
     */
    public long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the codigoTasaInteres
     */
    public AhorroTasaInteresIfip getCodigoTasaInteres() {
        return codigoTasaInteres;
    }

    /**
     * @param codigoTasaInteres the codigoTasaInteres to set
     */
    public void setCodigoTasaInteres(AhorroTasaInteresIfip codigoTasaInteres) {
        this.codigoTasaInteres = codigoTasaInteres;
    }

    /**
     * @return the tasaInteres
     */
    public long getTasaInteres() {
        return tasaInteres;
    }

    /**
     * @param tasaInteres the tasaInteres to set
     */
    public void setTasaInteres(long tasaInteres) {
        this.tasaInteres = tasaInteres;
    }


}
