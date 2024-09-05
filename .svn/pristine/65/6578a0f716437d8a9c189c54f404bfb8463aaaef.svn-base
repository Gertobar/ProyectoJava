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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CONTABLES.REGISTRO_CONTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroContable.findAll", query = "SELECT r FROM RegistroContable r"),
    @NamedQuery(name = "RegistroContable.findByCodigo", query = "SELECT r FROM RegistroContable r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RegistroContable.findByCodigoIfip", query = "SELECT r FROM RegistroContable r WHERE r.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "RegistroContable.findByCodigoAgenciaIfip", query = "SELECT r FROM RegistroContable r WHERE r.codigoAgenciaIfip = :codigoAgenciaIfip"),
    @NamedQuery(name = "RegistroContable.findByFecha", query = "SELECT r FROM RegistroContable r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RegistroContable.findByFechaSistema", query = "SELECT r FROM RegistroContable r WHERE r.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "RegistroContable.findByGlosa", query = "SELECT r FROM RegistroContable r WHERE r.glosa = :glosa"),
    @NamedQuery(name = "RegistroContable.findByTotal", query = "SELECT r FROM RegistroContable r WHERE r.total = :total"),
    @NamedQuery(name = "RegistroContable.findByProcesado", query = "SELECT r FROM RegistroContable r WHERE r.procesado = :procesado")})
public class RegistroContable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_REGISTRO_CONTABLE")
    @SequenceGenerator(name = "GSQ_REGISTRO_CONTABLE", schema = "MKS_CONTABLES",  allocationSize = 0, sequenceName = "SEQ_REGISTRO_CONTABLE")        
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
    @Column(name = "CODIGO_AGENCIA_IFIP")
    private long codigoAgenciaIfip;
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
    @Size(min = 1, max = 100)
    @Column(name = "GLOSA")
    private String glosa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROCESADO")
    private char procesado;
    @JoinColumn(name = "CODIGO_PROCESO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProcesoContable codigoProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroContable", fetch = FetchType.LAZY)
    private Collection<RegistroContableDetalle> registroContableDetalleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRegistro", fetch = FetchType.LAZY)
    private Collection<RegistroContableIngEgrCaj> registroContableIngEgrCajCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRegistro", fetch = FetchType.LAZY)
    private Collection<RegistroContableMovCue> registroContableMovCueCollection;

    public RegistroContable() {
    }

    public RegistroContable(Long codigo) {
        this.codigo = codigo;
    }

    public RegistroContable(Long codigo, long codigoIfip, long codigoAgenciaIfip, Date fecha, Date fechaSistema, String glosa, BigDecimal total, char procesado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoAgenciaIfip = codigoAgenciaIfip;
        this.fecha = fecha;
        this.fechaSistema = fechaSistema;
        this.glosa = glosa;
        this.total = total;
        this.procesado = procesado;
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

    public long getCodigoAgenciaIfip() {
        return codigoAgenciaIfip;
    }

    public void setCodigoAgenciaIfip(long codigoAgenciaIfip) {
        this.codigoAgenciaIfip = codigoAgenciaIfip;
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

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public char getProcesado() {
        return procesado;
    }

    public void setProcesado(char procesado) {
        this.procesado = procesado;
    }

    public ProcesoContable getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(ProcesoContable codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    @XmlTransient
    public Collection<RegistroContableDetalle> getRegistroContableDetalleCollection() {
        return registroContableDetalleCollection;
    }

    public void setRegistroContableDetalleCollection(Collection<RegistroContableDetalle> registroContableDetalleCollection) {
        this.registroContableDetalleCollection = registroContableDetalleCollection;
    }

    @XmlTransient
    public Collection<RegistroContableIngEgrCaj> getRegistroContableIngEgrCajCollection() {
        return registroContableIngEgrCajCollection;
    }

    public void setRegistroContableIngEgrCajCollection(Collection<RegistroContableIngEgrCaj> registroContableIngEgrCajCollection) {
        this.registroContableIngEgrCajCollection = registroContableIngEgrCajCollection;
    }

    @XmlTransient
    public Collection<RegistroContableMovCue> getRegistroContableMovCueCollection() {
        return registroContableMovCueCollection;
    }

    public void setRegistroContableMovCueCollection(Collection<RegistroContableMovCue> registroContableMovCueCollection) {
        this.registroContableMovCueCollection = registroContableMovCueCollection;
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
        if (!(object instanceof RegistroContable)) {
            return false;
        }
        RegistroContable other = (RegistroContable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.contables.RegistroContable[ codigo=" + codigo + " ]";
    }
    
}
