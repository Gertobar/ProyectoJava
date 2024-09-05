/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.adquisiciones;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_ADQUISICIONES.PAGO_COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoCompra.findAll", query = "SELECT p FROM PagoCompra p"),
    @NamedQuery(name = "PagoCompra.findByCodigo", query = "SELECT p FROM PagoCompra p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PagoCompra.findByFechaPago", query = "SELECT p FROM PagoCompra p WHERE p.fechaPago = :fechaPago"),
    @NamedQuery(name = "PagoCompra.findByFechaSistema", query = "SELECT p FROM PagoCompra p WHERE p.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "PagoCompra.findByPagadoPor", query = "SELECT p FROM PagoCompra p WHERE p.pagadoPor = :pagadoPor"),
    @NamedQuery(name = "PagoCompra.findByAbono", query = "SELECT p FROM PagoCompra p WHERE p.abono = :abono"),
    @NamedQuery(name = "PagoCompra.findBySaldo", query = "SELECT p FROM PagoCompra p WHERE p.saldo = :saldo"),
    @NamedQuery(name = "PagoCompra.findByCompraCancelada", query = "SELECT p FROM PagoCompra p WHERE p.compraCancelada = :compraCancelada"),
    @NamedQuery(name = "PagoCompra.findByCodigoCompra", query = "SELECT p FROM PagoCompra p JOIN p.compra c WHERE c.codigo = :codigoCompra")
})
public class PagoCompra implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoCompra = "PagoCompra.findByCodigoCompra";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PAGO_COMPRA")
    @SequenceGenerator(name = "GSQ_PAGO_COMPRA", schema = "MKS_ADQUISICIONES", allocationSize = 0, sequenceName = "SEQ_PAGO_COMPRA")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAGADO_POR")
    private long pagadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABONO")
    private BigDecimal abono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPRA_CANCELADA")
    private char compraCancelada;
    @JoinColumn(name = "CODIGO_COMPRA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra compra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagoCompra", fetch = FetchType.LAZY)
    private Collection<PagoCompraDetalle> pagoCompraDetalleCollection;

    public PagoCompra() {
    }

    public PagoCompra(Long codigo) {
        this.codigo = codigo;
    }

    public PagoCompra(Long codigo, Date fechaPago, Date fechaSistema, long pagadoPor, BigDecimal abono, BigDecimal saldo, char compraCancelada) {
        this.codigo = codigo;
        this.fechaPago = fechaPago;
        this.fechaSistema = fechaSistema;
        this.pagadoPor = pagadoPor;
        this.abono = abono;
        this.saldo = saldo;
        this.compraCancelada = compraCancelada;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public long getPagadoPor() {
        return pagadoPor;
    }

    public void setPagadoPor(long pagadoPor) {
        this.pagadoPor = pagadoPor;
    }

  

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public char getCompraCancelada() {
        return compraCancelada;
    }

    public void setCompraCancelada(char compraCancelada) {
        this.compraCancelada = compraCancelada;
    }

  

    @XmlTransient
    public Collection<PagoCompraDetalle> getPagoCompraDetalleCollection() {
        return pagoCompraDetalleCollection;
    }

    public void setPagoCompraDetalleCollection(Collection<PagoCompraDetalle> pagoCompraDetalleCollection) {
        this.pagoCompraDetalleCollection = pagoCompraDetalleCollection;
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
        if (!(object instanceof PagoCompra)) {
            return false;
        }
        PagoCompra other = (PagoCompra) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelos.aquisiciones.PagoCompra[ codigo=" + codigo + " ]";
    }

    /**
     * @return the compra
     */
    public Compra getCompra() {
        return compra;
    }

    /**
     * @param compra the compra to set
     */
    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    /**
     * @return the abono
     */
    public BigDecimal getAbono() {
        return abono;
    }

    /**
     * @param abono the abono to set
     */
    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }
    
}
