/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.seguridades.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MKS_CREDITOS.TASA_INTERES_PRO_CRE_MON_IFI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TasaInteresProCreMonIfi.findAll", query = "SELECT t FROM TasaInteresProCreMonIfi t"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByCodigo", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByMontoInicial", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.montoInicial = :montoInicial"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByMontoFinal", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.montoFinal = :montoFinal"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByRegistradoPor", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.registradoPor = :registradoPor"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByFechaRegistro", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByEliminado", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.eliminado = :eliminado"),
    //PERSONALIZADAS
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByProductoCreditoMonedaIfip", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoProducto = :codigoProducto AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoMoneda = :codigoMoneda AND t.productoCreditoMonedaIfip.productoCreditoMonedaIfipPK.codigoIfip = :codigoIfip AND t.codigoTasa.codigo = :codigoTasa AND t.montoInicial = :montoInicial"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByEliminadoIfip", query = "SELECT t FROM TasaInteresProCreMonIfi t  WHERE t.codigoIfip = :codigoIfip AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByProductoCredito", query = "SELECT p FROM TasaInteresProCreMonIfi t  JOIN t.productoCreditoMonedaIfip i JOIN i.productoCreditoMoneda m JOIN m.productoCredito p WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByMoneda", query = "SELECT m FROM TasaInteresProCreMonIfi t  JOIN t.productoCreditoMonedaIfip i JOIN i.productoCreditoMoneda p JOIN p.moneda m WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByCodigoTasa", query = "SELECT c FROM TasaInteresProCreMonIfi t JOIN t.codigoTasa c WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByTasaProCreMonIfi", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.codigoProducto = :codigoProducto AND t.codigoMoneda = :codigoMoneda AND t.codigoIfip = :codigoIfip AND t.codigoTasa.codigo = :codigoTasa AND t.eliminado = :eliminado"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByTasaProCreMonIfiMonto", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.codigoProducto = :codigoProducto AND t.codigoMoneda = :codigoMoneda AND t.codigoIfip = :codigoIfip AND t.codigoTasa.codigo = :codigoTasa AND t.eliminado = :eliminado AND t.montoFinal = :montoFinal"),
    @NamedQuery(name = "TasaInteresProCreMonIfi.findByProCreMonIfi", query = "SELECT t FROM TasaInteresProCreMonIfi t WHERE t.codigoProducto = :codigoProducto AND t.codigoMoneda = :codigoMoneda AND t.codigoIfip = :codigoIfip AND t.eliminado = :eliminado")

})
public class TasaInteresProCreMonIfi implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByProductoCreditoMonedaIfip = "TasaInteresProCreMonIfi.findByProductoCreditoMonedaIfip";
    public static final String findByEliminadoIfip = "TasaInteresProCreMonIfi.findByEliminadoIfip";
    public static final String findByProductoCredito = "TasaInteresProCreMonIfi.findByProductoCredito";
    public static final String findByMoneda = "TasaInteresProCreMonIfi.findByMoneda";
    public static final String findByCodigoTasa = "TasaInteresProCreMonIfi.findByCodigoTasa";
    public static final String findByTasaProCreMonIfi = "TasaInteresProCreMonIfi.findByTasaProCreMonIfi";
    public static final String findByTasaProCreMonIfiMonto = "TasaInteresProCreMonIfi.findByTasaProCreMonIfiMonto";
    public static final String findByProCreMonIfi = "TasaInteresProCreMonIfi.findByProCreMonIfi";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_TASA_INT_PRO_CRE_MON_IFI")
    @SequenceGenerator(name = "GSQ_TASA_INT_PRO_CRE_MON_IFI", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_TASA_INT_PRO_CRE_MON_IFI")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_INICIAL")
    private BigDecimal montoInicial;
    @Column(name = "MONTO_FINAL")
    private BigDecimal montoFinal;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIENE_GARANTIAS")
    private char tieneGarantias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTasa", fetch = FetchType.LAZY)
    private Collection<TipoGarantiaProCreMonIfi> tipoGarantiaProCreMonIfiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTasa", fetch = FetchType.LAZY)
    private Collection<Solicitud> solicitudCollection;
    @JoinColumn(name = "CODIGO_TASA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TasaInteresCreditoIfip codigoTasa;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;
    @JoinColumn(name = "REGISTRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRegistradoPor;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_TASA")
    private long codigoTasaCode;
    public TasaInteresProCreMonIfi() {
    }

    public TasaInteresProCreMonIfi(Long codigo) {
        this.codigo = codigo;
    }

    public TasaInteresProCreMonIfi(Long codigo, BigDecimal montoInicial, long registradoPor, Date fechaRegistro, char eliminado) {
        this.codigo = codigo;
        this.montoInicial = montoInicial;
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

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public BigDecimal getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(BigDecimal montoFinal) {
        this.montoFinal = montoFinal;
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

    @XmlTransient
    public Collection<TipoGarantiaProCreMonIfi> getTipoGarantiaProCreMonIfiCollection() {
        return tipoGarantiaProCreMonIfiCollection;
    }

    public void setTipoGarantiaProCreMonIfiCollection(Collection<TipoGarantiaProCreMonIfi> tipoGarantiaProCreMonIfiCollection) {
        this.tipoGarantiaProCreMonIfiCollection = tipoGarantiaProCreMonIfiCollection;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    public TasaInteresCreditoIfip getCodigoTasa() {
        return codigoTasa;
    }

    public void setCodigoTasa(TasaInteresCreditoIfip codigoTasa) {
        this.codigoTasa = codigoTasa;
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
        if (!(object instanceof TasaInteresProCreMonIfi)) {
            return false;
        }
        TasaInteresProCreMonIfi other = (TasaInteresProCreMonIfi) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.TasaInteresProCreMonIfi[ codigo=" + codigo + " ]";
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

    /**
     * @return the codigoTasaCode
     */
    public long getCodigoTasaCode() {
        return codigoTasaCode;
    }

    /**
     * @param codigoTasaCode the codigoTasaCode to set
     */
    public void setCodigoTasaCode(long codigoTasaCode) {
        this.codigoTasaCode = codigoTasaCode;
    }

    /**
     * @return the tieneGarantias
     */
    public char getTieneGarantias() {
        return tieneGarantias;
    }

    /**
     * @param tieneGarantias the tieneGarantias to set
     */
    public void setTieneGarantias(char tieneGarantias) {
        this.tieneGarantias = tieneGarantias;
    }

}
