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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.COMPRA_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompraDetalle.findAll", query = "SELECT c FROM CompraDetalle c"),
    @NamedQuery(name = "CompraDetalle.findByCodigo", query = "SELECT c FROM CompraDetalle c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CompraDetalle.findByDetalleArticulo", query = "SELECT c FROM CompraDetalle c WHERE c.detalleArticulo = :detalleArticulo"),
    @NamedQuery(name = "CompraDetalle.findBySubtotal", query = "SELECT c FROM CompraDetalle c WHERE c.subtotal = :subtotal"),
    @NamedQuery(name = "CompraDetalle.findByGravaIva", query = "SELECT c FROM CompraDetalle c WHERE c.gravaIva = :gravaIva"),
    @NamedQuery(name = "CompraDetalle.findByPorcentajeIva", query = "SELECT c FROM CompraDetalle c WHERE c.porcentajeIva = :porcentajeIva"),
    @NamedQuery(name = "CompraDetalle.findByIva", query = "SELECT c FROM CompraDetalle c WHERE c.iva = :iva"),
    @NamedQuery(name = "CompraDetalle.findByCodigoIfipAllComprasDetalle", query = "SELECT cd FROM CompraDetalle cd JOIN cd.codigoCompra.ifipAgencia.codigoIfip cif  WHERE cif.codigo = :codigoIfip"),
    @NamedQuery(name = "CompraDetalle.findByCodigoCompra", query = "SELECT c FROM CompraDetalle c WHERE c.codigoCompra.codigo = :codCompra"),
    @NamedQuery(name = "CompraDetalle.findByTotal", query = "SELECT c FROM CompraDetalle c WHERE c.total = :total")})
public class CompraDetalle implements Serializable {

    public static final String findByCodigoIfipAllComprasDetalle = "CompraDetalle.findByCodigoIfipAllComprasDetalle";
    public static final String findByCodigoCompra = "CompraDetalle.findByCodigoCompra";
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_COMPRAS_DETALLE")
    @SequenceGenerator(name = "GSQ_COMPRAS_DETALLE", schema = "MKS_ADQUISICIONES", allocationSize = 0, sequenceName = "SEQ_COMPRAS_DETALLE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DETALLE_ARTICULO")
    private String detalleArticulo;

    // @Max(value=?)  
    @Min(value = 1)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRAVA_IVA")
    private char gravaIva;
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
    @Column(name = "TOTAL")
    private BigDecimal total;
    @JoinColumn(name = "CODIGO_GRUPO_ARTICULO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GrupoArticulo codigoArticulo;
    @JoinColumn(name = "CODIGO_COMPRA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra codigoCompra;

    public CompraDetalle() {
    }

    public CompraDetalle(Long codigo) {
        this.codigo = codigo;
    }

    public CompraDetalle(Long codigo, String detalleArticulo, BigDecimal subtotal, char gravaIva, BigDecimal porcentajeIva, BigDecimal iva, BigDecimal total) {
        this.codigo = codigo;
        this.detalleArticulo = detalleArticulo;
        this.subtotal = subtotal;
        this.gravaIva = gravaIva;
        this.porcentajeIva = porcentajeIva;
        this.iva = iva;
        this.total = total;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDetalleArticulo() {
        return detalleArticulo;
    }

    public void setDetalleArticulo(String detalleArticulo) {
        this.detalleArticulo = detalleArticulo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public char getGravaIva() {
        return gravaIva;
    }

    public void setGravaIva(char gravaIva) {
        this.gravaIva = gravaIva;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public GrupoArticulo getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(GrupoArticulo codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public Compra getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(Compra codigoCompra) {
        this.codigoCompra = codigoCompra;
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
        if (!(object instanceof CompraDetalle)) {
            return false;
        }
        CompraDetalle other = (CompraDetalle) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.CompraDetalle[ codigo=" + codigo + " ]";
    }
}
