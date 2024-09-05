/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.contables;

import ec.renafipse.mks.modelo.seguridades.Usuario;
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
import javax.persistence.OneToOne;
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
@Table(name = "MKS_CONTABLES.COMPROBANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprobante.findAll", query = "SELECT c FROM Comprobante c"),
    @NamedQuery(name = "Comprobante.findByCodigo", query = "SELECT c FROM Comprobante c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Comprobante.findByCodigoIfip", query = "SELECT c FROM Comprobante c WHERE c.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Comprobante.findByCodigoIfipAgencia", query = "SELECT c FROM Comprobante c WHERE c.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "Comprobante.findByCodigoIfipAgenciaOrigina", query = "SELECT c FROM Comprobante c WHERE c.codigoIfipAgenciaOrigina = :codigoIfipAgenciaOrigina"),
    @NamedQuery(name = "Comprobante.findByNumeroComprobante", query = "SELECT c FROM Comprobante c WHERE c.numeroComprobante = :numeroComprobante"),
    @NamedQuery(name = "Comprobante.findByGlosa", query = "SELECT c FROM Comprobante c WHERE c.glosa = :glosa"),
    @NamedQuery(name = "Comprobante.findByFecha", query = "SELECT c FROM Comprobante c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Comprobante.findByFechaSistema", query = "SELECT c FROM Comprobante c WHERE c.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "Comprobante.findByTotal", query = "SELECT c FROM Comprobante c WHERE c.total = :total"),
    @NamedQuery(name = "Comprobante.findByEstadoColocadoPor", query = "SELECT c FROM Comprobante c WHERE c.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "Comprobante.findByFechaEstado", query = "SELECT c FROM Comprobante c WHERE c.fechaEstado = :fechaEstado"),
///personalizadas
    @NamedQuery(name = "Comprobante.findByFechaAgenciaIFIP", query = "SELECT c FROM Comprobante c WHERE c.fecha = :fecha AND c.codigoIfip = :codigoIfip AND c.codigoIfipAgencia = :codigoIfipAgencia ORDER BY c.codigo"),
    @NamedQuery(name = "Comprobante.findByNumeroComprobanteAgenciaIFIP", query = "SELECT c FROM Comprobante c WHERE c.numeroComprobante = :numeroComprobante AND c.codigoIfip = :codigoIfip AND c.codigoIfipAgencia = :codigoIfipAgencia ORDER BY c.codigo"),
    @NamedQuery(name = "Comprobante.findPeriodo", query = "SELECT DISTINCT c FROM Comprobante c"),
    @NamedQuery(name = "Comprobante.findByIfipEntreFecha", query = "SELECT c FROM Comprobante c WHERE c.codigoIfip = :codigoIfip AND c.fecha >= :fechaInicio AND c.fecha <= :fechaFin AND c.codigoEstado.codigo IN (1,3)" )

})

public class Comprobante implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String findByFechaAgenciaIFIP="Comprobante.findByFechaAgenciaIFIP";
    public static final String findByNumeroComprobanteAgenciaIFIP="Comprobante.findByNumeroComprobanteAgenciaIFIP";
    public static final String findPeriodo="Comprobante.findPeriodo";
    public static final String findByIfipEntreFecha="Comprobante.findByIfipEntreFecha";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_COMPROBANTE")
    @SequenceGenerator(name = "GSQ_COMPROBANTE", schema = "MKS_CONTABLES",  allocationSize = 0, sequenceName = "SEQ_COMPROBANTE")    
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
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA_ORIGINA")
    private long codigoIfipAgenciaOrigina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NUMERO_COMPROBANTE")
    private String numeroComprobante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GLOSA")
    private String glosa;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
  
    @JoinColumn(name = "CODIGO_TIPO_COMPROBANTE", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TipoComprobante codigoTipoComprobante;
    @JoinColumn(name = "CODIGO_PERIODO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private PeriodoContable codigoPeriodo;
    @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private EstadoComprobante codigoEstado;
  
    @JoinColumn(name = "ESTADO_COLOCADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioEstadoColocadoPor;
    public Comprobante() {
    }

    public Comprobante(Long codigo) {
        this.codigo = codigo;
    }

    public Comprobante(Long codigo, long codigoIfip, long codigoIfipAgencia, long codigoIfipAgenciaOrigina, String numeroComprobante, String glosa, Date fecha, Date fechaSistema, BigDecimal total, long estadoColocadoPor, Date fechaEstado) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoIfipAgenciaOrigina = codigoIfipAgenciaOrigina;
        this.numeroComprobante = numeroComprobante;
        this.glosa = glosa;
        this.fecha = fecha;
        this.fechaSistema = fechaSistema;
        this.total = total;
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

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoIfipAgenciaOrigina() {
        return codigoIfipAgenciaOrigina;
    }

    public void setCodigoIfipAgenciaOrigina(long codigoIfipAgenciaOrigina) {
        this.codigoIfipAgenciaOrigina = codigoIfipAgenciaOrigina;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

  

    public TipoComprobante getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(TipoComprobante codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
    }

    public PeriodoContable getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(PeriodoContable codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public EstadoComprobante getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(EstadoComprobante codigoEstado) {
        this.codigoEstado = codigoEstado;
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
        if (!(object instanceof Comprobante)) {
            return false;
        }
        Comprobante other = (Comprobante) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo+"";
    }

    /**
     * @return the usuarioEstadoColocadoPor
     */
    public Usuario getUsuarioEstadoColocadoPor() {
        return usuarioEstadoColocadoPor;
    }

    /**
     * @param usuarioEstadoColocadoPor the usuarioEstadoColocadoPor to set
     */
    public void setUsuarioEstadoColocadoPor(Usuario usuarioEstadoColocadoPor) {
        this.usuarioEstadoColocadoPor = usuarioEstadoColocadoPor;
    }
    
}
