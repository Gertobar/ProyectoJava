/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.contables;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MKS_CONTABLES.RETENCION_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RetencionDpf.findAll", query = "SELECT r FROM RetencionDpf r"),
    @NamedQuery(name = "RetencionDpf.findByCodigo", query = "SELECT r FROM RetencionDpf r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RetencionDpf.findByCodigoIfip", query = "SELECT r FROM RetencionDpf r WHERE r.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RetencionDpf.findByCodigoContrato", query = "SELECT r FROM RetencionDpf r WHERE r.codigoContrato = :codigoContrato"),
    @NamedQuery(name = "RetencionDpf.findByCodigoIfipAgencia", query = "SELECT r FROM RetencionDpf r WHERE r.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "RetencionDpf.findByFechaRetencionDpf", query = "SELECT r FROM RetencionDpf r WHERE r.fechaRetencionDpf = :fechaRetencionDpf"),
    @NamedQuery(name = "RetencionDpf.findByTotalRetenido", query = "SELECT r FROM RetencionDpf r WHERE r.totalRetenido = :totalRetenido"),
    @NamedQuery(name = "RetencionDpf.findByEstado", query = "SELECT r FROM RetencionDpf r WHERE r.estado = :estado"),
    @NamedQuery(name = "RetencionDpf.findByEstadoColocadoPor", query = "SELECT r FROM RetencionDpf r WHERE r.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "RetencionDpf.findByFechaEstado", query = "SELECT r FROM RetencionDpf r WHERE r.fechaEstado = :fechaEstado")})
public class RetencionDpf implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONTRATO")
    private long codigoContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_RETENCION_DPF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetencionDpf;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "retencionDpf")
    private Collection<DocumentoRetencionDpf> documentoRetencionDpfCollection;
    @JoinColumn(name = "CODIGO_PERIODO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private PeriodoContable codigoPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMA_PAGO_INTERES")
    private char formaPagoInteres;
    @Column(name = "NUMERO_PAGO")
    private long numeroPago;

    public RetencionDpf() {
    }

    public RetencionDpf(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public RetencionDpf(BigDecimal codigo, long codigoIfip, long codigoContrato, long codigoIfipAgencia, Date fechaRetencionDpf, BigDecimal totalRetenido, char estado, long estadoColocadoPor, Date fechaEstado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoContrato = codigoContrato;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.fechaRetencionDpf = fechaRetencionDpf;
        this.totalRetenido = totalRetenido;
        this.estado = estado;
        this.estadoColocadoPor = estadoColocadoPor;
        this.fechaEstado = fechaEstado;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public long getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(long codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFechaRetencionDpf() {
        return fechaRetencionDpf;
    }

    public void setFechaRetencionDpf(Date fechaRetencionDpf) {
        this.fechaRetencionDpf = fechaRetencionDpf;
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
    public Collection<DocumentoRetencionDpf> getDocumentoRetencionDpfCollection() {
        return documentoRetencionDpfCollection;
    }

    public void setDocumentoRetencionDpfCollection(Collection<DocumentoRetencionDpf> documentoRetencionDpfCollection) {
        this.documentoRetencionDpfCollection = documentoRetencionDpfCollection;
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
        if (!(object instanceof RetencionDpf)) {
            return false;
        }
        RetencionDpf other = (RetencionDpf) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.RetencionDpf[ codigo=" + codigo + " ]";
    }

    /**
     * @return the formaPagoInteres
     */
    public char getFormaPagoInteres() {
        return formaPagoInteres;
    }

    /**
     * @param formaPagoInteres the formaPagoInteres to set
     */
    public void setFormaPagoInteres(char formaPagoInteres) {
        this.formaPagoInteres = formaPagoInteres;
    }

    /**
     * @return the numeroPago
     */
    public long getNumeroPago() {
        return numeroPago;
    }

    /**
     * @param numeroPago the numeroPago to set
     */
    public void setNumeroPago(long numeroPago) {
        this.numeroPago = numeroPago;
    }

}
