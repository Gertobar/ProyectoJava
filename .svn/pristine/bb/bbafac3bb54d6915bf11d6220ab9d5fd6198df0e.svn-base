/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import ec.renafipse.mks.modelo.ifips.Ifip;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.VENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByCodigo", query = "SELECT v FROM Venta v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "Venta.findByCodigoDocumentoIfpAgencia", query = "SELECT v FROM Venta v WHERE v.codigoDocumentoIfpAgencia = :codigoDocumentoIfpAgencia"),
    @NamedQuery(name = "Venta.findByNumeroAutorizacion", query = "SELECT v FROM Venta v WHERE v.numeroAutorizacion = :numeroAutorizacion"),
    @NamedQuery(name = "Venta.findByNumeroComprobante", query = "SELECT v FROM Venta v WHERE v.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "Venta.findByFechaEmisionComprobante", query = "SELECT v FROM Venta v WHERE v.fechaEmisionComprobante = :fechaEmisionComprobante"),
    @NamedQuery(name = "Venta.findByFechaRegistro", query = "SELECT v FROM Venta v WHERE v.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Venta.findByFechaSistema", query = "SELECT v FROM Venta v WHERE v.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "Venta.findBySubtotal", query = "SELECT v FROM Venta v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "Venta.findBySubtotalConIva", query = "SELECT v FROM Venta v WHERE v.subtotalConIva = :subtotalConIva"),
    @NamedQuery(name = "Venta.findBySubtotalIvaCero", query = "SELECT v FROM Venta v WHERE v.subtotalIvaCero = :subtotalIvaCero"),
    @NamedQuery(name = "Venta.findByIva", query = "SELECT v FROM Venta v WHERE v.iva = :iva"),
    @NamedQuery(name = "Venta.findByDescuento", query = "SELECT v FROM Venta v WHERE v.descuento = :descuento"),
    @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total"),
    @NamedQuery(name = "Venta.findByFechaEstado", query = "SELECT v FROM Venta v WHERE v.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "Venta.findByEstadoColocadoPor", query = "SELECT v FROM Venta v WHERE v.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "Venta.findByObservaciones", query = "SELECT v FROM Venta v WHERE v.observaciones = :observaciones"),
    @NamedQuery(name = "Venta.findByRetencionIva", query = "SELECT v FROM Venta v WHERE v.retencionIva = :retencionIva"),
    @NamedQuery(name = "Venta.findByRetencionRenta", query = "SELECT v FROM Venta v WHERE v.retencionRenta = :retencionRenta"),
    @NamedQuery(name = "Venta.findByCodigoEstado", query = "SELECT v FROM Venta v WHERE v.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "Venta.findByCodigoSustento", query = "SELECT v FROM Venta v WHERE v.codigoSustento = :codigoSustento"),
    @NamedQuery(name = "Venta.findByCodigoDocIfipAgeDet", query = "SELECT v FROM Venta v WHERE v.codigoDocIfipAgeDet = :codigoDocIfipAgeDet"),
    @NamedQuery(name = "Venta.findByCodigoIfip", query = "SELECT v FROM Venta v WHERE v.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Venta.findByNumeroComprobanteClienteIfip", query = "SELECT v FROM Venta v WHERE v.numeroComprobante = :numeroComprobante AND v.codigoDocumentoIfpAgencia.codigoTipoComprobante = :codigoTipoComprobante AND v.clienteIfip.clienteIfipPK.codigoCliente = :codigoCliente"),
    @NamedQuery(name = "Venta.findByClienteIfip", query = "SELECT v FROM Venta v WHERE v.clienteIfip.clienteIfipPK.codigoCliente = :codigoCliente AND v.clienteIfip.clienteIfipPK.codigoIfip = :codigoIfip ORDER BY v.codigo DESC")
})
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByNumeroComprobanteClienteIfip = "Venta.findByNumeroComprobanteClienteIfip";
    public static final String findByClienteIfip = "Venta.findByClienteIfip";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "CODIGO_DOCUMENTO_IFP_AGENCIA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private DocumentosIfipAgencia codigoDocumentoIfpAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NUMERO_AUTORIZACION")
    private String numeroAutorizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NUMERO_COMPROBANTE")
    private String numeroComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EMISION_COMPROBANTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmisionComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBTOTAL_CON_IVA")
    private BigDecimal subtotalConIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBTOTAL_IVA_CERO")
    private BigDecimal subtotalIvaCero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA")
    private BigDecimal iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCUENTO")
    private BigDecimal descuento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Size(max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RETENCION_IVA")
    private BigDecimal retencionIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RETENCION_RENTA")
    private BigDecimal retencionRenta;
    @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VentaEstadoIfip codigoEstado;
    @JoinColumn(name = "CODIGO_SUSTENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SustentoTributario codigoSustento;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "CODIGO_DOC_IFIP_AGE_DET", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private DocumentosIfipAgenciaDet codigoDocIfipAgeDet;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CLIENTE", referencedColumnName = "CODIGO_CLIENTE"),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP")})
    @ManyToOne(optional = false)
    private ClienteIfip clienteIfip;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip codigoIfip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoVenta", fetch = FetchType.LAZY)
    private Collection<VentaDetalle> ventaDetalleCollection;

    public Venta() {
    }

    public Venta(Long codigo) {
        this.codigo = codigo;
    }

    public Venta(Long codigo, DocumentosIfipAgencia codigoDocumentoIfpAgencia, String numeroAutorizacion, String numeroComprobante, Date fechaEmisionComprobante, Date fechaRegistro, Date fechaSistema, BigDecimal subtotal, BigDecimal subtotalConIva, BigDecimal subtotalIvaCero, BigDecimal iva, BigDecimal descuento, BigDecimal total, Date fechaEstado, long estadoColocadoPor, BigDecimal retencionIva, BigDecimal retencionRenta, VentaEstadoIfip codigoEstado, DocumentosIfipAgenciaDet codigoDocIfipAgeDet, ClienteIfip clienteIfip, Ifip codigoIfip) {
        this.codigo = codigo;
        this.codigoDocumentoIfpAgencia = codigoDocumentoIfpAgencia;
        this.numeroAutorizacion = numeroAutorizacion;
        this.numeroComprobante = numeroComprobante;
        this.fechaEmisionComprobante = fechaEmisionComprobante;
        this.fechaRegistro = fechaRegistro;
        this.fechaSistema = fechaSistema;
        this.subtotal = subtotal;
        this.subtotalConIva = subtotalConIva;
        this.subtotalIvaCero = subtotalIvaCero;
        this.iva = iva;
        this.descuento = descuento;
        this.total = total;
        this.fechaEstado = fechaEstado;
        this.estadoColocadoPor = estadoColocadoPor;
        this.retencionIva = retencionIva;
        this.retencionRenta = retencionRenta;
        this.codigoEstado = codigoEstado;
        this.codigoDocIfipAgeDet = codigoDocIfipAgeDet;
        this.clienteIfip = clienteIfip;
        this.codigoIfip = codigoIfip;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public DocumentosIfipAgencia getCodigoDocumentoIfpAgencia() {
        return codigoDocumentoIfpAgencia;
    }

    public void setCodigoDocumentoIfpAgencia(DocumentosIfipAgencia codigoDocumentoIfpAgencia) {
        this.codigoDocumentoIfpAgencia = codigoDocumentoIfpAgencia;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaEmisionComprobante() {
        return fechaEmisionComprobante;
    }

    public void setFechaEmisionComprobante(Date fechaEmisionComprobante) {
        this.fechaEmisionComprobante = fechaEmisionComprobante;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getSubtotalConIva() {
        return subtotalConIva;
    }

    public void setSubtotalConIva(BigDecimal subtotalConIva) {
        this.subtotalConIva = subtotalConIva;
    }

    public BigDecimal getSubtotalIvaCero() {
        return subtotalIvaCero;
    }

    public void setSubtotalIvaCero(BigDecimal subtotalIvaCero) {
        this.subtotalIvaCero = subtotalIvaCero;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public long getEstadoColocadoPor() {
        return estadoColocadoPor;
    }

    public void setEstadoColocadoPor(long estadoColocadoPor) {
        this.estadoColocadoPor = estadoColocadoPor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getRetencionIva() {
        return retencionIva;
    }

    public void setRetencionIva(BigDecimal retencionIva) {
        this.retencionIva = retencionIva;
    }

    public BigDecimal getRetencionRenta() {
        return retencionRenta;
    }

    public void setRetencionRenta(BigDecimal retencionRenta) {
        this.retencionRenta = retencionRenta;
    }

    public VentaEstadoIfip getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(VentaEstadoIfip codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public SustentoTributario getCodigoSustento() {
        return codigoSustento;
    }

    public void setCodigoSustento(SustentoTributario codigoSustento) {
        this.codigoSustento = codigoSustento;
    }

    public DocumentosIfipAgenciaDet getCodigoDocIfipAgeDet() {
        return codigoDocIfipAgeDet;
    }

    public void setCodigoDocIfipAgeDet(DocumentosIfipAgenciaDet codigoDocIfipAgeDet) {
        this.codigoDocIfipAgeDet = codigoDocIfipAgeDet;
    }

    public ClienteIfip getClienteIfip() {
        return clienteIfip;
    }

    public void setClienteIfip(ClienteIfip clienteIfip) {
        this.clienteIfip = clienteIfip;
    }

    public Ifip getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Ifip codigoIfip) {
        this.codigoIfip = codigoIfip;
    }
    
    public Collection<VentaDetalle> getVentaDetalleCollection() {
        return ventaDetalleCollection;
    }

    public void setVentaDetalleCollection(Collection<VentaDetalle> ventaDetalleCollection) {
        this.ventaDetalleCollection = ventaDetalleCollection;
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
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.Venta[ codigo=" + codigo + " ]";
    }    
}