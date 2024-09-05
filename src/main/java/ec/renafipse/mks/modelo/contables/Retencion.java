/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;


import ec.renafipse.mks.modelo.adquisiciones.Compra;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.RETENCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retencion.findAll", query = "SELECT r FROM Retencion r"),
    @NamedQuery(name = "Retencion.findByCodigo", query = "SELECT r FROM Retencion r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Retencion.findByCodigoIfip", query = "SELECT r FROM Retencion r WHERE r.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Retencion.findByCodigoCompra", query = "SELECT r FROM Retencion r WHERE r.codigoCompra = :codigoCompra"),
    @NamedQuery(name = "Retencion.findByCodigoIfipAgencia", query = "SELECT r FROM Retencion r WHERE r.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "Retencion.findByFechaRetencion", query = "SELECT r FROM Retencion r WHERE r.fechaRetencion = :fechaRetencion"),
    @NamedQuery(name = "Retencion.findByTotalRetenido", query = "SELECT r FROM Retencion r WHERE r.totalRetenido = :totalRetenido"),
    @NamedQuery(name = "Retencion.findByEstado", query = "SELECT r FROM Retencion r WHERE r.estado = :estado"),
    @NamedQuery(name = "Retencion.findByEstadoCodigoCompra", query = "SELECT r FROM Retencion r WHERE r.estado = :estado AND r.codigoCompra = :codigoCompra"),
    @NamedQuery(name = "Retencion.findByEstadoColocadoPor", query = "SELECT r FROM Retencion r WHERE r.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "Retencion.findByFechaEstado", query = "SELECT r FROM Retencion r WHERE r.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "Retencion.findByIfipIAgenciaFechaIngreso", query = "SELECT r FROM Retencion r WHERE r.codigoIfip = :codigoIfip AND r.codigoIfipAgencia = :codigoIfipAgencia AND  r.fechaRetencion between :fechaInicio AND :fechaFin"),
    @NamedQuery(name = "Retencion.findByIfipIAgenciaPro", query = "SELECT r FROM Retencion r JOIN r.compra c WHERE r.codigoIfip = :codigoIfip AND r.codigoIfipAgencia = :codigoIfipAgencia AND  c.codigoProveedor.codigo =:codigoProveedor")
})
public class Retencion implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByCodigoCompra = "Retencion.findByCodigoCompra";
    public static final String findByEstado = "Retencion.findByEstado";
    public static final String findByEstadoCodigoCompra="Retencion.findByEstadoCodigoCompra";
    public static final String findByIfipIAgenciaFechaIngreso="Retencion.findByIfipIAgenciaFechaIngreso";
    public static final String findByIfipIAgenciaPro="Retencion.findByIfipIAgenciaPro";
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
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
    @Column(name = "CODIGO_COMPRA")
    private long codigoCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_RETENCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_RETENIDO")
    private BigDecimal totalRetenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "retencion", fetch = FetchType.LAZY)
    private Collection<RetencionDetalle> retencionDetalleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "retencion", fetch = FetchType.LAZY)
    private Collection<DocumentoRetencion> documentoRetencionCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "codigoRetencion", fetch = FetchType.LAZY)
    private ComprabanteRetencion comprabanteRetencion;

    @JoinColumn(name = "CODIGO_PERIODO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PeriodoContable codigoPeriodo;

    @JoinColumn(name = "CODIGO_COMPRA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compra compra;


    public Retencion() {
    }

    public Retencion(Long codigo) {
        this.codigo = codigo;
    }

    public Retencion(Long codigo, long codigoIfip, long codigoCompra, long codigoIfipAgencia, Date fechaRetencion, BigDecimal totalRetenido, char estado, long estadoColocadoPor, Date fechaEstado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoCompra = codigoCompra;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.fechaRetencion = fechaRetencion;
        this.totalRetenido = totalRetenido;
        this.estado = estado;
        this.estadoColocadoPor = estadoColocadoPor;
        this.fechaEstado = fechaEstado;
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

    public long getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(long codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFechaRetencion() {
        return fechaRetencion;
    }

    public void setFechaRetencion(Date fechaRetencion) {
        this.fechaRetencion = fechaRetencion;
    }

    public BigDecimal getTotalRetenido() {
        return totalRetenido;
    }

    public void setTotalRetenido(BigDecimal totalRetenido) {
        this.totalRetenido = totalRetenido;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public long getEstadoColocadoPor() {
        return estadoColocadoPor;
    }

    public void setEstadoColocadoPor(long estadoColocadoPor) {
        this.estadoColocadoPor = estadoColocadoPor;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    @XmlTransient
    public Collection<RetencionDetalle> getRetencionDetalleCollection() {
        return retencionDetalleCollection;
    }

    public void setRetencionDetalleCollection(Collection<RetencionDetalle> retencionDetalleCollection) {
        this.retencionDetalleCollection = retencionDetalleCollection;
    }

    @XmlTransient
    public Collection<DocumentoRetencion> getDocumentoRetencionCollection() {
        return documentoRetencionCollection;
    }

    public void setDocumentoRetencionCollection(Collection<DocumentoRetencion> documentoRetencionCollection) {
        this.documentoRetencionCollection = documentoRetencionCollection;
    }

    public ComprabanteRetencion getComprabanteRetencion() {
        return comprabanteRetencion;
    }

    public void setComprabanteRetencion(ComprabanteRetencion comprabanteRetencion) {
        this.comprabanteRetencion = comprabanteRetencion;
    }

    public PeriodoContable getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(PeriodoContable codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
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
        if (!(object instanceof Retencion)) {
            return false;
        }
        Retencion other = (Retencion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.Retencion[ codigo=" + codigo + " ]";
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

    
}
