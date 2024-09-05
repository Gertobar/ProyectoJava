/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.adquisiciones;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByCodigo", query = "SELECT c FROM Compra c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Compra.findByCodigoIfip", query = "SELECT c FROM Compra c WHERE c.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Compra.findByCodigoIfipAgencia", query = "SELECT c FROM Compra c WHERE c.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "Compra.findByNumeroComprobante", query = "SELECT c FROM Compra c WHERE c.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "Compra.findByFechaIngreso", query = "SELECT c FROM Compra c WHERE c.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Compra.findByEstado", query = "SELECT c FROM Compra c WHERE c.estado = :estado"),
    @NamedQuery(name = "Compra.findByFechaEstado", query = "SELECT c FROM Compra c WHERE c.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "Compra.findByEstadoColocadoPor", query = "SELECT c FROM Compra c WHERE c.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "Compra.findByFechaEmisionComprobante", query = "SELECT c FROM Compra c WHERE c.fechaEmisionComprobante = :fechaEmisionComprobante"),
    @NamedQuery(name = "Compra.findByEmitidaRetencion", query = "SELECT c FROM Compra c WHERE c.emitidaRetencion = :emitidaRetencion"),
    @NamedQuery(name = "Compra.findBySubtotal", query = "SELECT c FROM Compra c WHERE c.subtotal = :subtotal"),
    @NamedQuery(name = "Compra.findBySubtotalConIva", query = "SELECT c FROM Compra c WHERE c.subtotalConIva = :subtotalConIva"),
    @NamedQuery(name = "Compra.findBySubtotalIvaCero", query = "SELECT c FROM Compra c WHERE c.subtotalIvaCero = :subtotalIvaCero"),
    @NamedQuery(name = "Compra.findByDescuento", query = "SELECT c FROM Compra c WHERE c.descuento = :descuento"),
    @NamedQuery(name = "Compra.findByTotalRetencion", query = "SELECT c FROM Compra c WHERE c.totalRetencion = :totalRetencion"),
    @NamedQuery(name = "Compra.findByTotal", query = "SELECT c FROM Compra c WHERE c.total = :total"),
    @NamedQuery(name = "Compra.findByAbono", query = "SELECT c FROM Compra c WHERE c.abono = :abono"),
    @NamedQuery(name = "Compra.findBySaldo", query = "SELECT c FROM Compra c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Compra.findByObservaciones", query = "SELECT c FROM Compra c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "Compra.findByCodigoIfipFechaIngresoEstado", query = "SELECT c FROM Compra c WHERE c.codigoIfip = :codigoIfip AND  c.fechaIngreso >= :fechaInicio AND c.fechaIngreso <= :fechaFin AND c.estado = :estadoCompra "),
    @NamedQuery(name = "Compra.findByCodigoIfipFechaIngreso", query = "SELECT c FROM Compra c WHERE c.codigoIfip = :codigoIfip AND  c.fechaIngreso >= :fechaInicio AND c.fechaIngreso <= :fechaFin"),
    @NamedQuery(name = "Compra.findByCodigoProveedor", query = "SELECT c FROM Compra c JOIN c.codigoProveedor p  WHERE c.codigoIfip = :codigoIfip AND  p.persona.identificacion = :identificacion"),

    //personalizada
    @NamedQuery(name = "Compra.findByCodigoProveedorTipoComprobanteNumeroFactura", query = "SELECT c FROM Compra c JOIN c.codigoProveedor p  WHERE c.codigoIfipAgencia = :codigoIfip AND  c.codigoProveedor.codigo = :codigoProvedor AND  c.codigoTipoComprobante.codigo = :codigoTipoComprobante AND  c.numeroComprobante LIKE :numeroComprobante ORDER BY c.codigo"),
    @NamedQuery(name = "Compra.findByComprasCodigoProveedor", query = "SELECT c FROM Compra c JOIN c.codigoProveedor p  WHERE c.codigoIfip = :codigoIfip AND  c.codigoProveedor.codigo = :codigoprovedor ORDER BY c.codigo"),
    @NamedQuery(name = "Compra.findByComprasCodigoProveedorIngresadaRetenida", query = "SELECT c FROM Compra c JOIN c.codigoProveedor p  WHERE c.codigoIfip = :codigoIfip AND  c.codigoProveedor.codigo = :codigoprovedor AND c.estado IN (:estadoUno, :estadoDos) ORDER BY c.codigo"),
    @NamedQuery(name = "Compra.findByComprasCodProvEstado", query = "SELECT c FROM Compra c JOIN c.codigoProveedor p  WHERE c.codigoIfip = :codigoIfip AND  c.codigoProveedor.codigo = :codigoprovedor AND c.estado = :estadoCompra ORDER BY c.codigo"),
    @NamedQuery(name = "Compra.findByCodigoIfipFechaIngresoIngresadaRetenida", query = "SELECT c FROM Compra c WHERE c.codigoIfip = :codigoIfip AND  c.fechaIngreso >= :fechaInicio AND c.fechaIngreso <= :fechaFin  AND c.estado IN (:estadoUno, :estadoDos) ORDER BY c.codigo"),
    @NamedQuery(name = "Compra.findByComprobanteTipoIfipNumero", query = "SELECT c FROM Compra c  WHERE c.codigoTipoComprobante.codigo = :codigoTipoComprobante AND c.codigoIfip = :codigoIfip AND c.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "Compra.findByComprobanteTipoIfipNumeroProv", query = "SELECT c FROM Compra c  WHERE c.codigoTipoComprobante.codigo = :codigoTipoComprobante AND c.codigoIfip = :codigoIfip AND c.numeroComprobante = :numeroComprobante AND c.codigoProveedor.codigo = :codigoProveedor")
})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    // lista todas las compras segun el codigo de la ifip y tambien un rango de fechas especuificos
    public static final String findByCodigoIfipFechaIngreso = "Compra.findByCodigoIfipFechaIngreso";
    public static final String findByCodigoIfipIdentificacionProveedor = "Compra.findByCodigoIfipIdentificacionProveedor";
    public static final String findByComprasCodigoProveedorIngresadaRetenida = "Compra.findByComprasCodigoProveedorIngresadaRetenida";
    public static final String findByCodigoIfipFechaIngresoIngresadaRetenida = "Compra.findByCodigoIfipFechaIngresoIngresadaRetenida";
    public static final String findByComprobanteTipoIfipNumero = "Compra.findByComprobanteTipoIfipNumero";
    public static final String findByComprobanteTipoIfipNumeroProv = "Compra.findByComprobanteTipoIfipNumeroProv";
    
    //personalida
    public static final String findByCodigoProveedor = "Compra.findByComprasCodigoProveedor";
    //listar todas las compras segun el codigo ifip
    public static final String findByCodigoIfipAllCompras = "Compra.findByCodigoIfip";
    // listar por el codigo de proveedor, tipo de comprobante, numerdo factura
    public static final String findByCodigoProveedorTipoComprobanteNumeroFactura = "Compra.findByCodigoProveedorTipoComprobanteNumeroFactura";
    /// listar todas las compras por la ifip, codigo proveedor y segun el estado
    public static final String findByComprasCodProvEstado = "Compra.findByComprasCodProvEstado";
    // lista todas las compras segun el codigo de la ifip y tambien un rango de fechas especuificos
    public static final String findByCodigoIfipFechaIngresoEstado = "Compra.findByCodigoIfipFechaIngresoEstado";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_COMPRAS")
    @SequenceGenerator(name = "GSQ_COMPRAS", schema = "MKS_ADQUISICIONES", allocationSize = 0, sequenceName = "SEQ_COMPRAS")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_COMPROBANTE")
    private String numeroComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_EMISION_COMPROBANTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmisionComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMITIDA_RETENCION")
    private char emitidaRetencion;
    @Min(value = 1)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @Column(name = "DESCUENTO")
    private BigDecimal descuento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_RETENCION")
    private BigDecimal totalRetencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA")
    private BigDecimal iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABONO")
    private BigDecimal abono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AUTORIZACION_ELECTRONICA")
    private String autorizacionElectronica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_FACTURA_ELECTRONICA")
    private char esFacruraElectronica;
    @JoinColumn(name = "CODIGO_TIPO_COMPROBANTE", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoComprobanteCompra codigoTipoComprobante;
    @JoinColumn(name = "CODIGO_SUSTENTO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SustentoTributario codigoSustento;
    @JoinColumn(name = "CODIGO_PROVEEDOR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor codigoProveedor;
    @JoinColumn(name = "ESTADO_COLOCADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario ususarioEstadoColocadoPor;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra", fetch = FetchType.LAZY)
    private Collection<PagoCompra> pagoCompraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCompra", fetch = FetchType.LAZY)
    private Collection<CompraDetalle> compraDetalleCollection;

    public Compra() {
    }

    public Compra(Long codigo) {
        this.codigo = codigo;
    }

    public Compra(Long codigo, long codigoIfip, long codigoIfipAgencia, String numeroComprobante, Date fechaIngreso, char estado, Date fechaEstado, long estadoColocadoPor, Date fechaEmisionComprobante, char emitidaRetencion, BigDecimal subtotal, BigDecimal subtotalConIva, BigDecimal subtotalIvaCero, BigDecimal descuento, BigDecimal totalRetencion, BigDecimal total, BigDecimal abono, BigDecimal saldo, String observaciones) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.numeroComprobante = numeroComprobante;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.fechaEstado = fechaEstado;
        this.estadoColocadoPor = estadoColocadoPor;
        this.fechaEmisionComprobante = fechaEmisionComprobante;
        this.emitidaRetencion = emitidaRetencion;
        this.subtotal = subtotal;
        this.subtotalConIva = subtotalConIva;
        this.subtotalIvaCero = subtotalIvaCero;
        this.descuento = descuento;
        this.totalRetencion = totalRetencion;
        this.total = total;
        this.abono = abono;
        this.saldo = saldo;
        this.observaciones = observaciones;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
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

    public Date getFechaEmisionComprobante() {
        return fechaEmisionComprobante;
    }

    public void setFechaEmisionComprobante(Date fechaEmisionComprobante) {
        this.fechaEmisionComprobante = fechaEmisionComprobante;
    }

    public char getEmitidaRetencion() {
        return emitidaRetencion;
    }

    public void setEmitidaRetencion(char emitidaRetencion) {
        this.emitidaRetencion = emitidaRetencion;
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

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getTotalRetencion() {
        return totalRetencion;
    }

    public void setTotalRetencion(BigDecimal totalRetencion) {
        this.totalRetencion = totalRetencion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoComprobanteCompra getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(TipoComprobanteCompra codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
    }

    public SustentoTributario getCodigoSustento() {
        return codigoSustento;
    }

    public void setCodigoSustento(SustentoTributario codigoSustento) {
        this.codigoSustento = codigoSustento;
    }

    public Proveedor getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(Proveedor codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    @XmlTransient
    public Collection<PagoCompra> getPagoCompraCollection() {
        return pagoCompraCollection;
    }

    public void setPagoCompraCollection(Collection<PagoCompra> pagoCompraCollection) {
        this.pagoCompraCollection = pagoCompraCollection;
    }

    @XmlTransient
    public Collection<CompraDetalle> getCompraDetalleCollection() {
        return compraDetalleCollection;
    }

    public void setCompraDetalleCollection(Collection<CompraDetalle> compraDetalleCollection) {
        this.compraDetalleCollection = compraDetalleCollection;
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
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.Compra[ codigo=" + codigo + " ]";
    }

    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }

    /**
     * @return the ususarioEstadoColocadoPor
     */
    public Usuario getUsusarioEstadoColocadoPor() {
        return ususarioEstadoColocadoPor;
    }

    /**
     * @param ususarioEstadoColocadoPor the ususarioEstadoColocadoPor to set
     */
    public void setUsusarioEstadoColocadoPor(Usuario ususarioEstadoColocadoPor) {
        this.ususarioEstadoColocadoPor = ususarioEstadoColocadoPor;
    }

    /**
     * @return the fechaSistema
     */
    public Date getFechaSistema() {
        return fechaSistema;
    }

    /**
     * @param fechaSistema the fechaSistema to set
     */
    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    /**
     * @return the iva
     */
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the autorizacionElectronica
     */
    public String getAutorizacionElectronica() {
        return autorizacionElectronica;
    }

    /**
     * @param autorizacionElectronica the autorizacionElectronica to set
     */
    public void setAutorizacionElectronica(String autorizacionElectronica) {
        this.autorizacionElectronica = autorizacionElectronica;
    }

    /**
     * @return the esFacruraElectronica
     */
    public char getEsFacruraElectronica() {
        return esFacruraElectronica;
    }

    /**
     * @param esFacruraElectronica the esFacruraElectronica to set
     */
    public void setEsFacruraElectronica(char esFacruraElectronica) {
        this.esFacruraElectronica = esFacruraElectronica;
    }

}
