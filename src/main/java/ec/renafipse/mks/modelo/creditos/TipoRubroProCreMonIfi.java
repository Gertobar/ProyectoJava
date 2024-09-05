/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.TIPO_RUBRO_PRO_CRE_MON_IFI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRubroProCreMonIfi.findAll", query = "SELECT t FROM TipoRubroProCreMonIfi t"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByCodigo", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByTipo", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByCalculaSobre", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.calculaSobre = :calculaSobre"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByCobradoEn", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.cobradoEn = :cobradoEn"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByOrdenCobro", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.ordenCobro = :ordenCobro"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByValor", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.valor = :valor"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByRegistradoPor", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByFechaRegistro", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByEliminado", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.eliminado = :eliminado"),
    //PERSONALIZADA
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByTipoRubroProCreMonIfi", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.codigoTipoRubro.codio = :codigoTipoRubro AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByTipoRubroProCreMonIfiOrden", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.codigoTipoRubro.codio = :codigoTipoRubro AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND t.ordenCobro = :ordenCobro"),
    @NamedQuery(name = "TipoRubroProCreMonIfi.findByProCreMonIfi", query = "SELECT t FROM TipoRubroProCreMonIfi t WHERE t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND t.eliminado = :eliminado")
})
public class TipoRubroProCreMonIfi implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByTipoRubroProCreMonIfi = "TipoRubroProCreMonIfi.findByTipoRubroProCreMonIfi";
    public static final String findByTipoRubroProCreMonIfiOrden = "TipoRubroProCreMonIfi.findByTipoRubroProCreMonIfiOrden";
    public static final String findByProCreMonIfi = "TipoRubroProCreMonIfi.findByProCreMonIfi";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TIPO_RUBRO_PRO_CRE_MON_IFI")
    @SequenceGenerator(name = "GSQ_TIPO_RUBRO_PRO_CRE_MON_IFI", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_TIPO_RUBRO_PRO_CRE_MON_IFI")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALCULA_SOBRE")
    private char calculaSobre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COBRADO_EN")
    private char cobradoEn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN_COBRO")
    private short ordenCobro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
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
    @JoinColumn(name = "CODIGO_TIPO_RUBRO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoRubro codigoTipoRubro;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PRODUCTO")
    private long codigoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;

    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRegistradoPor;

    public TipoRubroProCreMonIfi() {
    }

    public TipoRubroProCreMonIfi(Long codigo) {
        this.codigo = codigo;
    }

    public TipoRubroProCreMonIfi(Long codigo, char tipo, char calculaSobre, char cobradoEn, short ordenCobro, BigDecimal valor, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.calculaSobre = calculaSobre;
        this.cobradoEn = cobradoEn;
        this.ordenCobro = ordenCobro;
        this.valor = valor;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro;
        this.eliminado = eliminado;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public char getCalculaSobre() {
        return calculaSobre;
    }

    public void setCalculaSobre(char calculaSobre) {
        this.calculaSobre = calculaSobre;
    }

    public char getCobradoEn() {
        return cobradoEn;
    }

    public void setCobradoEn(char cobradoEn) {
        this.cobradoEn = cobradoEn;
    }

    public short getOrdenCobro() {
        return ordenCobro;
    }

    public void setOrdenCobro(short ordenCobro) {
        this.ordenCobro = ordenCobro;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    public TipoRubro getCodigoTipoRubro() {
        return codigoTipoRubro;
    }

    public void setCodigoTipoRubro(TipoRubro codigoTipoRubro) {
        this.codigoTipoRubro = codigoTipoRubro;
    }

    public ProductoCreditoMonedaIfip getProductoCreditoMonedaIfip() {
        return productoCreditoMonedaIfip;
    }

    public void setProductoCreditoMonedaIfip(ProductoCreditoMonedaIfip productoCreditoMonedaIfip) {
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
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
        if (!(object instanceof TipoRubroProCreMonIfi)) {
            return false;
        }
        TipoRubroProCreMonIfi other = (TipoRubroProCreMonIfi) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TipoRubroProCreMonIfi[ codigo=" + codigo + " ]";
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
     * @return the codigoProducto
     */
    public long getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * @param codigoProducto the codigoProducto to set
     */
    public void setCodigoProducto(long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
     * @return the codigoMoneda
     */
    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * @param codigoMoneda the codigoMoneda to set
     */
    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
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

}
