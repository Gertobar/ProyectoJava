/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.dpfs;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_DPFS.RENOVACION_CONTRATO_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RenovacionContratoDpf.findAll", query = "SELECT r FROM RenovacionContratoDpf r"),
    @NamedQuery(name = "RenovacionContratoDpf.findByCodigo", query = "SELECT r FROM RenovacionContratoDpf r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RenovacionContratoDpf.findByRenovadoPor", query = "SELECT r FROM RenovacionContratoDpf r WHERE r.renovadoPor = :renovadoPor"),
    @NamedQuery(name = "RenovacionContratoDpf.findByFechaRenovacion", query = "SELECT r FROM RenovacionContratoDpf r WHERE r.fechaRenovacion = :fechaRenovacion"),
    @NamedQuery(name = "RenovacionContratoDpf.findByFechaSistema", query = "SELECT r FROM RenovacionContratoDpf r WHERE r.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "RenovacionContratoDpf.findByFechaVencimientoAnterior", query = "SELECT r FROM RenovacionContratoDpf r WHERE r.fechaVencimientoAnterior = :fechaVencimientoAnterior"),
    @NamedQuery(name = "RenovacionContratoDpf.findByFechaVencimiento", query = "SELECT r FROM RenovacionContratoDpf r WHERE r.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "RenovacionContratoDpf.findByEstado", query = "SELECT r FROM RenovacionContratoDpf r WHERE r.estado = :estado"),
    //PERSONALIZADOS
    @NamedQuery(name = "RenovacionContratoDpf.findByCodigoPersonaCodigoIfipEstado", query = "SELECT r FROM RenovacionContratoDpf r JOIN r.contratoDpf c WHERE c.codigoPersona = :codigoPersona AND c.contratoDpfPK.codigoIfip = :codigoIfip AND r.estado = :estado ORDER BY r.codigo")

})
public class RenovacionContratoDpf implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByCodigoPersonaCodigoIfipEstado = "RenovacionContratoDpf.findByCodigoPersonaCodigoIfipEstado";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_RENOVACION_CONTRATO_DPF")
    @SequenceGenerator(name = "GSQ_RENOVACION_CONTRATO_DPF", schema = "MKS_DPFS", allocationSize = 0, sequenceName = "SEQ_RENOVACION_CONTRATO_DPF")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RENOVADO_POR")
    private long renovadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_RENOVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRenovacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VENCIMIENTO_ANTERIOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimientoAnterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA_INTERES")
    private BigDecimal tasaInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "CON_INTERES")
    private char conInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONTRATO")
    private Long codigoContrato;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private Long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMPUTADOR")
    private Long codigoComputador;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CONTRATO_RENOVADO")
    private Long codigoContratoRenovado;
    
    @JoinColumn(name = "CODIGO_TASA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TasaInteresProDpfMon codigoTasa;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CONTRATO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ContratoDpf contratoDpf;
    
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;
    
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CONTRATO_RENOVADO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ContratoDpf contratoRenovado;

    public RenovacionContratoDpf() {
    }

    public RenovacionContratoDpf(Long codigo) {
        this.codigo = codigo;
    }

    public RenovacionContratoDpf(Long codigo, long codigoPeriodicidad, long renovadoPor, Date fechaRenovacion, Date fechaSistema, Date fechaVencimientoAnterior, Date fechaVencimiento, BigDecimal tasaInteres, BigDecimal porcentajeRetencion, long plazo, long plazoMeses, long plazoDias) {
        this.codigo = codigo;
        this.renovadoPor = renovadoPor;
        this.fechaRenovacion = fechaRenovacion;
        this.fechaSistema = fechaSistema;
        this.fechaVencimientoAnterior = fechaVencimientoAnterior;
        this.fechaVencimiento = fechaVencimiento;
        this.tasaInteres = tasaInteres;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getRenovadoPor() {
        return renovadoPor;
    }

    public void setRenovadoPor(long renovadoPor) {
        this.renovadoPor = renovadoPor;
    }

    public Date getFechaRenovacion() {
        return fechaRenovacion;
    }

    public void setFechaRenovacion(Date fechaRenovacion) {
        this.fechaRenovacion = fechaRenovacion;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Date getFechaVencimientoAnterior() {
        return fechaVencimientoAnterior;
    }

    public void setFechaVencimientoAnterior(Date fechaVencimientoAnterior) {
        this.fechaVencimientoAnterior = fechaVencimientoAnterior;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public TasaInteresProDpfMon getCodigoTasa() {
        return codigoTasa;
    }

    public void setCodigoTasa(TasaInteresProDpfMon codigoTasa) {
        this.codigoTasa = codigoTasa;
    }

    public ContratoDpf getContratoDpf() {
        return contratoDpf;
    }

    public void setContratoDpf(ContratoDpf contratoDpf) {
        this.contratoDpf = contratoDpf;
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
        if (!(object instanceof RenovacionContratoDpf)) {
            return false;
        }
        RenovacionContratoDpf other = (RenovacionContratoDpf) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.RenovacionContratoDpf[ codigo=" + codigo + " ]";
    }

    /**
     * @return the codigoIfipAgencia
     */
    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    /**
     * @param codigoIfipAgencia the codigoIfipAgencia to set
     */
    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }
  
    /**
     * @return the ifipAgencia
     */
    public IfipAgencia getIfipAgencia() {
        return ifipAgencia;
    }

    /**
     * @param ifipAgencia the ifipAgencia to set
     */
    public void setIfipAgencia(IfipAgencia ifipAgencia) {
        this.ifipAgencia = ifipAgencia;
    }
    

    /**
     * @return the conInteres
     */
    public char getConInteres() {
        return conInteres;
    }

    /**
     * @param conInteres the conInteres to set
     */
    public void setConInteres(char conInteres) {
        this.conInteres = conInteres;
    }

    /**
     * @return the codigoContrato
     */
    public Long getCodigoContrato() {
        return codigoContrato;
    }

    /**
     * @param codigoContrato the codigoContrato to set
     */
    public void setCodigoContrato(Long codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    /**
     * @return the codigoIfip
     */
    public Long getCodigoIfip() {
        return codigoIfip;
    }

    /**
     * @param codigoIfip the codigoIfip to set
     */
    public void setCodigoIfip(Long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    /**
     * @return the estado
     */
    public char getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(char estado) {
        this.estado = estado;
    }

    /**
     * @return the codigoComputador
     */
    public Long getCodigoComputador() {
        return codigoComputador;
    }

    /**
     * @param codigoComputador the codigoComputador to set
     */
    public void setCodigoComputador(Long codigoComputador) {
        this.codigoComputador = codigoComputador;
    }

    /**
     * @return the contratoRenovado
     */
    public ContratoDpf getContratoRenovado() {
        return contratoRenovado;
    }

    /**
     * @param contratoRenovado the contratoRenovado to set
     */
    public void setContratoRenovado(ContratoDpf contratoRenovado) {
        this.contratoRenovado = contratoRenovado;
    }

    /**
     * @return the codigoContratoRenovado
     */
    public Long getCodigoContratoRenovado() {
        return codigoContratoRenovado;
    }

    /**
     * @param codigoContratoRenovado the codigoContratoRenovado to set
     */
    public void setCodigoContratoRenovado(Long codigoContratoRenovado) {
        this.codigoContratoRenovado = codigoContratoRenovado;
    }

}
