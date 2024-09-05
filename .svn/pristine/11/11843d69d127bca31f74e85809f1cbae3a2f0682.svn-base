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
@Table(name = "MKS_JUDICIALES.CABECERA_COSTO_JUDICIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CabeceraCostoJudicial.findAll", query = "SELECT c FROM CabeceraCostoJudicial c"),
    @NamedQuery(name = "CabeceraCostoJudicial.findByCodigo", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CabeceraCostoJudicial.findByCodigoPagareAbogado", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.codigoPagareAbogado = :codigoPagareAbogado"),
    @NamedQuery(name = "CabeceraCostoJudicial.findByCodigoProcesoJudicial", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.codigoProcesoJudicial = :codigoProcesoJudicial"),
    @NamedQuery(name = "CabeceraCostoJudicial.findByFechaIngreso", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "CabeceraCostoJudicial.findByValor", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.valor = :valor"),
    @NamedQuery(name = "CabeceraCostoJudicial.findByPagado", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.pagado = :pagado"),
    @NamedQuery(name = "CabeceraCostoJudicial.findBySaldo", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "CabeceraCostoJudicial.findByEliminado", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.eliminado = :eliminado"),
    @NamedQuery(name = "CabeceraCostoJudicial.findByRegistradoPor", query = "SELECT c FROM CabeceraCostoJudicial c WHERE c.registradoPor = :registradoPor")})
public class CabeceraCostoJudicial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PAGARE_ABOGADO")
    private long codigoPagareAbogado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROCESO_JUDICIAL")
    private long codigoProcesoJudicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAGADO")
    private BigDecimal pagado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Size(max = 1)
    @Column(name = "ELIMINADO")
    private String eliminado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRADO_POR")
    private long registradoPor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCabeceraCostoJudicial")
    private Collection<PagoCostoJudicial> pagoCostoJudicialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoCabeceraCostoJudicial")
    private Collection<DetalleCostoJudicial> detalleCostoJudicialCollection;

    public CabeceraCostoJudicial() {
    }

    public CabeceraCostoJudicial(Long codigo) {
        this.codigo = codigo;
    }

    public CabeceraCostoJudicial(Long codigo, long codigoPagareAbogado, long codigoProcesoJudicial, Date fechaIngreso, BigDecimal valor, BigDecimal pagado, BigDecimal saldo, long registradoPor) {
        this.codigo = codigo;
        this.codigoPagareAbogado = codigoPagareAbogado;
        this.codigoProcesoJudicial = codigoProcesoJudicial;
        this.fechaIngreso = fechaIngreso;
        this.valor = valor;
        this.pagado = pagado;
        this.saldo = saldo;
        this.registradoPor = registradoPor;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoPagareAbogado() {
        return codigoPagareAbogado;
    }

    public void setCodigoPagareAbogado(long codigoPagareAbogado) {
        this.codigoPagareAbogado = codigoPagareAbogado;
    }

    public long getCodigoProcesoJudicial() {
        return codigoProcesoJudicial;
    }

    public void setCodigoProcesoJudicial(long codigoProcesoJudicial) {
        this.codigoProcesoJudicial = codigoProcesoJudicial;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPagado() {
        return pagado;
    }

    public void setPagado(BigDecimal pagado) {
        this.pagado = pagado;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public long getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(long registradoPor) {
        this.registradoPor = registradoPor;
    }

    @XmlTransient
    public Collection<PagoCostoJudicial> getPagoCostoJudicialCollection() {
        return pagoCostoJudicialCollection;
    }

    public void setPagoCostoJudicialCollection(Collection<PagoCostoJudicial> pagoCostoJudicialCollection) {
        this.pagoCostoJudicialCollection = pagoCostoJudicialCollection;
    }

    @XmlTransient
    public Collection<DetalleCostoJudicial> getDetalleCostoJudicialCollection() {
        return detalleCostoJudicialCollection;
    }

    public void setDetalleCostoJudicialCollection(Collection<DetalleCostoJudicial> detalleCostoJudicialCollection) {
        this.detalleCostoJudicialCollection = detalleCostoJudicialCollection;
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
        if (!(object instanceof CabeceraCostoJudicial)) {
            return false;
        }
        CabeceraCostoJudicial other = (CabeceraCostoJudicial) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.judiciales.CabeceraCostoJudicial[ codigo=" + codigo + " ]";
    }
    
}
