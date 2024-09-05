/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "MKS_DPFS.PAGO_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoDpf.findPagoDpfContratoIfip", query = "SELECT p FROM PagoDpf p WHERE p.pagoDpfPK.codigoContrato = :codigoContrato AND p.pagoDpfPK.codigoIfip = :codigoIfip  AND p.retencionImpresa = :retencionImpresa ORDER BY p.pagoDpfPK.numeroPago" ),
    @NamedQuery(name = "PagoDpf.findPagoDpfConIfip", query = "SELECT p FROM PagoDpf p WHERE p.pagoDpfPK.codigoContrato = :codigoContrato AND p.pagoDpfPK.codigoIfip = :codigoIfip  ORDER BY p.pagoDpfPK.numeroPago" )
    })
public class PagoDpf implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findPagoDpfContratoIfip = "PagoDpf.findPagoDpfContratoIfip";
    public static final String findPagoDpfConIfip = "PagoDpf.findPagoDpfConIfip";
    @EmbeddedId
    protected PagoDpfPK pagoDpfPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES_PAGADO")
    private BigDecimal interesPagado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL")
    private BigDecimal capital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RETENCION")
    private BigDecimal retencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Column(name = "COD_MOV_INT")
    private Long codMovInt;
    @Column(name = "COD_MOV_RET")
    private Long codMovRet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RETENCION_IMPRESA")
    private char retencionImpresa;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CONTRATO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ContratoDpf contratoDpf;
     

    public PagoDpf() {
    }

    public PagoDpf(PagoDpfPK pagoDpfPK) {
        this.pagoDpfPK = pagoDpfPK;
    }

    public PagoDpf(PagoDpfPK pagoDpfPK, BigDecimal interesPagado, BigDecimal capital, BigDecimal retencion, BigDecimal total, Date fechaPago, long codigoIfipAgencia) {
        this.pagoDpfPK = pagoDpfPK;
        this.interesPagado = interesPagado;
        this.capital = capital;
        this.retencion = retencion;
        this.total = total;
        this.fechaPago = fechaPago;
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public PagoDpf(long numeroPago, long codigoContrato, long codigoIfip) {
        this.pagoDpfPK = new PagoDpfPK(numeroPago, codigoContrato, codigoIfip);
    }

    public PagoDpfPK getPagoDpfPK() {
        return pagoDpfPK;
    }

    public void setPagoDpfPK(PagoDpfPK pagoDpfPK) {
        this.pagoDpfPK = pagoDpfPK;
    }

    public BigDecimal getInteresPagado() {
        return interesPagado;
    }

    public void setInteresPagado(BigDecimal interesPagado) {
        this.interesPagado = interesPagado;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getRetencion() {
        return retencion;
    }

    public void setRetencion(BigDecimal retencion) {
        this.retencion = retencion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Long getCodMovInt() {
        return codMovInt;
    }

    public void setCodMovInt(Long codMovInt) {
        this.codMovInt = codMovInt;
    }

    public Long getCodMovRet() {
        return codMovRet;
    }

    public void setCodMovRet(Long codMovRet) {
        this.codMovRet = codMovRet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoDpfPK != null ? pagoDpfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoDpf)) {
            return false;
        }
        PagoDpf other = (PagoDpf) object;
        if ((this.pagoDpfPK == null && other.pagoDpfPK != null) || (this.pagoDpfPK != null && !this.pagoDpfPK.equals(other.pagoDpfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.negocio.dpfs.PagoDpf[ pagoDpfPK=" + pagoDpfPK + " ]";
    }

    /**
     * @return the contratoDpf
     */
    public ContratoDpf getContratoDpf() {
        return contratoDpf;
    }

    /**
     * @param contratoDpf the contratoDpf to set
     */
    public void setContratoDpf(ContratoDpf contratoDpf) {
        this.contratoDpf = contratoDpf;
    }

    /**
     * @return the retencionImpresa
     */
    public char getRetencionImpresa() {
        return retencionImpresa;
    }

    /**
     * @param retencionImpresa the retencionImpresa to set
     */
    public void setRetencionImpresa(char retencionImpresa) {
        this.retencionImpresa = retencionImpresa;
    }
    
}
