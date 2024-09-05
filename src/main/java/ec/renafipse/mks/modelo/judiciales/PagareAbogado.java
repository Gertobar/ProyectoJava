/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.judiciales;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_JUDICIALES.PAGARE_ABOGADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagareAbogado.findAll", query = "SELECT p FROM PagareAbogado p"),
    @NamedQuery(name = "PagareAbogado.findByCodigo", query = "SELECT p FROM PagareAbogado p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PagareAbogado.findByNumeroCredito", query = "SELECT p FROM PagareAbogado p WHERE p.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "PagareAbogado.findByCodigoIfip", query = "SELECT p FROM PagareAbogado p WHERE p.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "PagareAbogado.findByFechaEntregaPagare", query = "SELECT p FROM PagareAbogado p WHERE p.fechaEntregaPagare = :fechaEntregaPagare"),
    @NamedQuery(name = "PagareAbogado.findByUsuarioEntrega", query = "SELECT p FROM PagareAbogado p WHERE p.usuarioEntrega = :usuarioEntrega"),
    @NamedQuery(name = "PagareAbogado.findBySaldoCapitalEntrega", query = "SELECT p FROM PagareAbogado p WHERE p.saldoCapitalEntrega = :saldoCapitalEntrega"),
    @NamedQuery(name = "PagareAbogado.findByFechaInicioDemanda", query = "SELECT p FROM PagareAbogado p WHERE p.fechaInicioDemanda = :fechaInicioDemanda"),
    @NamedQuery(name = "PagareAbogado.findByUsuarioIniciaDemanda", query = "SELECT p FROM PagareAbogado p WHERE p.usuarioIniciaDemanda = :usuarioIniciaDemanda"),
    @NamedQuery(name = "PagareAbogado.findBySaldoCapitalIniciaDemanda", query = "SELECT p FROM PagareAbogado p WHERE p.saldoCapitalIniciaDemanda = :saldoCapitalIniciaDemanda"),
    @NamedQuery(name = "PagareAbogado.findByVigente", query = "SELECT p FROM PagareAbogado p WHERE p.vigente = :vigente")})
public class PagareAbogado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CREDITO")
    private long numeroCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ENTREGA_PAGARE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntregaPagare;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO_ENTREGA")
    private long usuarioEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL_ENTREGA")
    private BigDecimal saldoCapitalEntrega;
    @Column(name = "FECHA_INICIO_DEMANDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioDemanda;
    @Column(name = "USUARIO_INICIA_DEMANDA")
    private Long usuarioIniciaDemanda;
    @Column(name = "SALDO_CAPITAL_INICIA_DEMANDA")
    private BigDecimal saldoCapitalIniciaDemanda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VIGENTE")
    private String vigente;
    @JoinColumn(name = "CODIGO_ABOGADO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Abogado codigoAbogado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPagareAbogado")
    private Collection<DocumentoCreditoEntregado> documentoCreditoEntregadoCollection;

    public PagareAbogado() {
    }

    public PagareAbogado(Long codigo) {
        this.codigo = codigo;
    }

    public PagareAbogado(Long codigo, long numeroCredito, long codigoIfip, Date fechaEntregaPagare, long usuarioEntrega, BigDecimal saldoCapitalEntrega, String vigente) {
        this.codigo = codigo;
        this.numeroCredito = numeroCredito;
        this.codigoIfip = codigoIfip;
        this.fechaEntregaPagare = fechaEntregaPagare;
        this.usuarioEntrega = usuarioEntrega;
        this.saldoCapitalEntrega = saldoCapitalEntrega;
        this.vigente = vigente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public Date getFechaEntregaPagare() {
        return fechaEntregaPagare;
    }

    public void setFechaEntregaPagare(Date fechaEntregaPagare) {
        this.fechaEntregaPagare = fechaEntregaPagare;
    }

    public long getUsuarioEntrega() {
        return usuarioEntrega;
    }

    public void setUsuarioEntrega(long usuarioEntrega) {
        this.usuarioEntrega = usuarioEntrega;
    }

    public BigDecimal getSaldoCapitalEntrega() {
        return saldoCapitalEntrega;
    }

    public void setSaldoCapitalEntrega(BigDecimal saldoCapitalEntrega) {
        this.saldoCapitalEntrega = saldoCapitalEntrega;
    }

    public Date getFechaInicioDemanda() {
        return fechaInicioDemanda;
    }

    public void setFechaInicioDemanda(Date fechaInicioDemanda) {
        this.fechaInicioDemanda = fechaInicioDemanda;
    }

    public Long getUsuarioIniciaDemanda() {
        return usuarioIniciaDemanda;
    }

    public void setUsuarioIniciaDemanda(Long usuarioIniciaDemanda) {
        this.usuarioIniciaDemanda = usuarioIniciaDemanda;
    }

    public BigDecimal getSaldoCapitalIniciaDemanda() {
        return saldoCapitalIniciaDemanda;
    }

    public void setSaldoCapitalIniciaDemanda(BigDecimal saldoCapitalIniciaDemanda) {
        this.saldoCapitalIniciaDemanda = saldoCapitalIniciaDemanda;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }

    public Abogado getCodigoAbogado() {
        return codigoAbogado;
    }

    public void setCodigoAbogado(Abogado codigoAbogado) {
        this.codigoAbogado = codigoAbogado;
    }

    @XmlTransient
    public Collection<DocumentoCreditoEntregado> getDocumentoCreditoEntregadoCollection() {
        return documentoCreditoEntregadoCollection;
    }

    public void setDocumentoCreditoEntregadoCollection(Collection<DocumentoCreditoEntregado> documentoCreditoEntregadoCollection) {
        this.documentoCreditoEntregadoCollection = documentoCreditoEntregadoCollection;
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
        if (!(object instanceof PagareAbogado)) {
            return false;
        }
        PagareAbogado other = (PagareAbogado) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.PagareAbogado[ codigo=" + codigo + " ]";
    }
    
}
