/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.VENTA_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaDetalle.findAll", query = "SELECT v FROM VentaDetalle v"),
    @NamedQuery(name = "VentaDetalle.findByCodigo", query = "SELECT v FROM VentaDetalle v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "VentaDetalle.findByCodigoVenta", query = "SELECT v FROM VentaDetalle v WHERE v.codigoVenta = :codigoVenta"),
    @NamedQuery(name = "VentaDetalle.findByCodigoItem", query = "SELECT v FROM VentaDetalle v WHERE v.codigoItem = :codigoItem"),
    @NamedQuery(name = "VentaDetalle.findBySubtotal", query = "SELECT v FROM VentaDetalle v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "VentaDetalle.findBySubtotalConIva", query = "SELECT v FROM VentaDetalle v WHERE v.subtotalConIva = :subtotalConIva"),
    @NamedQuery(name = "VentaDetalle.findBySubtotalIvaCero", query = "SELECT v FROM VentaDetalle v WHERE v.subtotalIvaCero = :subtotalIvaCero"),
    @NamedQuery(name = "VentaDetalle.findByPorcentajeIva", query = "SELECT v FROM VentaDetalle v WHERE v.porcentajeIva = :porcentajeIva"),
    @NamedQuery(name = "VentaDetalle.findByIva", query = "SELECT v FROM VentaDetalle v WHERE v.iva = :iva"),
    @NamedQuery(name = "VentaDetalle.findByDescuento", query = "SELECT v FROM VentaDetalle v WHERE v.descuento = :descuento"),
    @NamedQuery(name = "VentaDetalle.findByTotal", query = "SELECT v FROM VentaDetalle v WHERE v.total = :total"),
    @NamedQuery(name = "VentaDetalle.findByDescripcion", query = "SELECT v FROM VentaDetalle v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "VentaDetalle.findByGravaIva", query = "SELECT v FROM VentaDetalle v WHERE v.gravaIva = :gravaIva")})
public class VentaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoVenta = "VentaDetalle.findByCodigoVenta";
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_VENTA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Venta codigoVenta;
    @JoinColumn(name = "CODIGO_ITEM", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ItemVenta codigoItem;
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
    @Column(name = "PORCENTAJE_IVA")
    private BigDecimal porcentajeIva;
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
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRAVA_IVA")
    private char gravaIva;

    public VentaDetalle() {
    }

    public VentaDetalle(Long codigo) {
        this.codigo = codigo;
    }

    public VentaDetalle(Long codigo, Venta codigoVenta, ItemVenta codigoItem, BigDecimal subtotal, BigDecimal subtotalConIva, BigDecimal subtotalIvaCero, BigDecimal porcentajeIva, BigDecimal iva, BigDecimal descuento, BigDecimal total, String descripcion, char gravaIva) {
        this.codigo = codigo;
        this.codigoVenta = codigoVenta;
        this.codigoItem = codigoItem;
        this.subtotal = subtotal;
        this.subtotalConIva = subtotalConIva;
        this.subtotalIvaCero = subtotalIvaCero;
        this.porcentajeIva = porcentajeIva;
        this.iva = iva;
        this.descuento = descuento;
        this.total = total;
        this.descripcion = descripcion;
        this.gravaIva = gravaIva;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Venta getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Venta codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public ItemVenta getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(ItemVenta codigoItem) {
        this.codigoItem = codigoItem;
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

    public BigDecimal getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(BigDecimal porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getGravaIva() {
        return gravaIva;
    }

    public void setGravaIva(char gravaIva) {
        this.gravaIva = gravaIva;
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
        if (!(object instanceof VentaDetalle)) {
            return false;
        }
        VentaDetalle other = (VentaDetalle) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.adquisiciones.VentaDetalle[ codigo=" + codigo + " ]";
    }
    
}