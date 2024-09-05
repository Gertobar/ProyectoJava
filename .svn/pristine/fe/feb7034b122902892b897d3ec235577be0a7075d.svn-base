/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.ahorros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author v.astudillo
 */
@Entity
@Table(name = "MKS_AHORROS.EJECUCION_TRANSACCION_LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjecucionTransaccionLote.findAll", query = "SELECT e FROM EjecucionTransaccionLote e"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByCodigo", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByCodigoIfip", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByFecha", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByFechaSistema", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByNumeroTransacciones", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.numeroTransacciones = :numeroTransacciones"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByTotalTransaccionado", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.totalTransaccionado = :totalTransaccionado"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByObservaciones", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.observaciones = :observaciones"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByRealizadoPor", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.realizadoPor = :realizadoPor"),
    @NamedQuery(name = "EjecucionTransaccionLote.findByCodigoIfipAgencia", query = "SELECT e FROM EjecucionTransaccionLote e WHERE e.codigoIfipAgencia = :codigoIfipAgencia")})
public class EjecucionTransaccionLote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_EJECUCION_TRANSACCION_LOTE")
    @SequenceGenerator(name = "GSQ_EJECUCION_TRANSACCION_LOTE", schema = "MKS_AHORROS",  allocationSize = 0, sequenceName = "SEQ_EJECUCION_TRANSACCION_LOTE")
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
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_TRANSACCIONES")
    private long numeroTransacciones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_TRANSACCIONADO")
    private BigDecimal totalTransaccionado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REALIZADO_POR")
    private long realizadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoEjecucion")
    private Collection<EjecucionTransaccionLotDet> ejecucionTransaccionLotDetCollection;
    @JoinColumn(name = "CODIGO_TRANSACCION_LOTE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private ConceptoTransaccionLote codigoTransaccionLote;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejecucionTransaccionLote")
    private List<TmpEjecucionTraLotDet> tmpEjecucionTraLotDetLista;
    
    public EjecucionTransaccionLote() {
    }

    public EjecucionTransaccionLote(Long codigo) {
        this.codigo = codigo;
    }

    public EjecucionTransaccionLote(Long codigo, long codigoIfip, Date fecha, Date fechaSistema, long numeroTransacciones, BigDecimal totalTransaccionado, String observaciones, long realizadoPor, long codigoIfipAgencia) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.fecha = fecha;
        this.fechaSistema = fechaSistema;
        this.numeroTransacciones = numeroTransacciones;
        this.totalTransaccionado = totalTransaccionado;
        this.observaciones = observaciones;
        this.realizadoPor = realizadoPor;
        this.codigoIfipAgencia = codigoIfipAgencia;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public long getNumeroTransacciones() {
        return numeroTransacciones;
    }

    public void setNumeroTransacciones(long numeroTransacciones) {
        this.numeroTransacciones = numeroTransacciones;
    }

    public BigDecimal getTotalTransaccionado() {
        return totalTransaccionado;
    }

    public void setTotalTransaccionado(BigDecimal totalTransaccionado) {
        this.totalTransaccionado = totalTransaccionado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public long getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(long realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    @XmlTransient
    public Collection<EjecucionTransaccionLotDet> getEjecucionTransaccionLotDetCollection() {
        return ejecucionTransaccionLotDetCollection;
    }

    public void setEjecucionTransaccionLotDetCollection(Collection<EjecucionTransaccionLotDet> ejecucionTransaccionLotDetCollection) {
        this.ejecucionTransaccionLotDetCollection = ejecucionTransaccionLotDetCollection;
    }

    public ConceptoTransaccionLote getCodigoTransaccionLote() {
        return codigoTransaccionLote;
    }

    public void setCodigoTransaccionLote(ConceptoTransaccionLote codigoTransaccionLote) {
        this.codigoTransaccionLote = codigoTransaccionLote;
    }
    
    public List<TmpEjecucionTraLotDet> getTmpEjecucionTraLotDetLista() {
        return tmpEjecucionTraLotDetLista;
    }

    public void setTmpEjecucionTraLotDetLista(List<TmpEjecucionTraLotDet> tmpEjecucionTraLotDetLista) {
        this.tmpEjecucionTraLotDetLista = tmpEjecucionTraLotDetLista;
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
        if (!(object instanceof EjecucionTransaccionLote)) {
            return false;
        }
        EjecucionTransaccionLote other = (EjecucionTransaccionLote) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.ahorros.EjecucionTransaccionLote[ codigo=" + codigo + " ]";
    }

}
