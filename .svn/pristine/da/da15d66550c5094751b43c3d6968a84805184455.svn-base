/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.dpfs;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.comunes.Periodicidad;
import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.seguridades.Usuario;
import ec.renafipse.mks.modelo.socios.Persona;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_DPFS.CONTRATO_DPF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoDpf.findAll", query = "SELECT c FROM ContratoDpf c"),
    @NamedQuery(name = "ContratoDpf.findByCodigo", query = "SELECT c FROM ContratoDpf c WHERE c.contratoDpfPK.codigo = :codigo"),
    @NamedQuery(name = "ContratoDpf.findByCodigoIfip", query = "SELECT c FROM ContratoDpf c WHERE c.contratoDpfPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "ContratoDpf.findByCodigoMoneda", query = "SELECT c FROM ContratoDpf c WHERE c.codigoMoneda = :codigoMoneda"),
    @NamedQuery(name = "ContratoDpf.findByCodigoPersona", query = "SELECT c FROM ContratoDpf c WHERE c.codigoPersona = :codigoPersona"),
    @NamedQuery(name = "ContratoDpf.findByCodigoIfipAgencia", query = "SELECT c FROM ContratoDpf c WHERE c.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "ContratoDpf.findByCodigoPeriodicidad", query = "SELECT c FROM ContratoDpf c WHERE c.codigoPeriodicidad = :codigoPeriodicidad"),
    @NamedQuery(name = "ContratoDpf.findByRealizadoPor", query = "SELECT c FROM ContratoDpf c WHERE c.realizadoPor = :realizadoPor"),
    @NamedQuery(name = "ContratoDpf.findByFechaContrato", query = "SELECT c FROM ContratoDpf c WHERE c.fechaContrato = :fechaContrato"),
    @NamedQuery(name = "ContratoDpf.findByFechaVencimiento", query = "SELECT c FROM ContratoDpf c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "ContratoDpf.findByFechaSistema", query = "SELECT c FROM ContratoDpf c WHERE c.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "ContratoDpf.findByPlazo", query = "SELECT c FROM ContratoDpf c WHERE c.plazo = :plazo"),
    @NamedQuery(name = "ContratoDpf.findByPlazoEnMeses", query = "SELECT c FROM ContratoDpf c WHERE c.plazoEnMeses = :plazoEnMeses"),
    @NamedQuery(name = "ContratoDpf.findByPlazoDias", query = "SELECT c FROM ContratoDpf c WHERE c.plazoDias = :plazoDias"),
    @NamedQuery(name = "ContratoDpf.findByCapital", query = "SELECT c FROM ContratoDpf c WHERE c.capital = :capital"),
    @NamedQuery(name = "ContratoDpf.findByTasaInteres", query = "SELECT c FROM ContratoDpf c WHERE c.tasaInteres = :tasaInteres"),
    @NamedQuery(name = "ContratoDpf.findByInteres", query = "SELECT c FROM ContratoDpf c WHERE c.interes = :interes"),
    @NamedQuery(name = "ContratoDpf.findByPorcentajeRetencion", query = "SELECT c FROM ContratoDpf c WHERE c.porcentajeRetencion = :porcentajeRetencion"),
    @NamedQuery(name = "ContratoDpf.findByRetencion", query = "SELECT c FROM ContratoDpf c WHERE c.retencion = :retencion"),
    @NamedQuery(name = "ContratoDpf.findByTotal", query = "SELECT c FROM ContratoDpf c WHERE c.total = :total"),
    @NamedQuery(name = "ContratoDpf.findByEsDeSocio", query = "SELECT c FROM ContratoDpf c WHERE c.esDeSocio = :esDeSocio"),
    @NamedQuery(name = "ContratoDpf.findByEsRenovacion", query = "SELECT c FROM ContratoDpf c WHERE c.esRenovacion = :esRenovacion"),
    @NamedQuery(name = "ContratoDpf.findByEstado", query = "SELECT c FROM ContratoDpf c WHERE c.estado = :estado"),
    //PERSONALIZADOS
    @NamedQuery(name = "ContratoDpf.findByCodigoIfipCodigoPersona", query = "SELECT c FROM ContratoDpf c WHERE c.contratoDpfPK.codigoIfip = :codigoIfip AND c.codigoPersona = :codigoPersona ORDER BY c.contratoDpfPK.codigo DESC"),
    @NamedQuery(name = "ContratoDpf.findByRenovacionContratoDpf", query = " SELECT  c FROM ContratoDpf c WHERE c.contratoDpfPK.codigoIfip = :codigoIfip AND c.codigoPersona = :codigoPersona AND c.estado = :estado  AND c.renovacionAutomatica = :renovacionAutomatica AND c.contratoDpfPK.codigo NOT IN (SELECT r.codigoContrato  FROM RenovacionContratoDpf r WHERE r.contratoDpf.contratoDpfPK.codigoIfip = :codigoIfip AND r.contratoDpf.codigoPersona = :codigoPersona  AND r.estado = :estadoRenovacion) ORDER by c.contratoDpfPK.codigo"),
    @NamedQuery(name = "ContratoDpf.findByAnularContrato", query = "SELECT c FROM ContratoDpf c WHERE c.contratoDpfPK.codigoIfip = :codigoIfip AND c.codigoIfipAgencia = :codigoIfipAgencia AND c.estado = :estado AND   c.fechaContrato = :fechaContrato"),
    @NamedQuery(name = "ContratoDpf.findByIfipPersonaEstado", query = "SELECT c FROM ContratoDpf c WHERE c.contratoDpfPK.codigoIfip = :codigoIfip AND c.codigoPersona = :codigoPersona AND c.estado = :estado ORDER BY c.contratoDpfPK.codigo"),
    @NamedQuery(name = "ContratoDpf.findByIfipPersonaAcreditaMensual", query = "SELECT c FROM ContratoDpf c WHERE c.contratoDpfPK.codigoIfip = :codigoIfip AND c.codigoPersona = :codigoPersona AND c.acreditacionMensual = :acreditaMensual ORDER BY c.contratoDpfPK.codigo"),
    @NamedQuery(name = "ContratoDpf.findByCodigoIfipCodigoPersonaConRetencion", query = "SELECT c FROM ContratoDpf c WHERE c.contratoDpfPK.codigoIfip = :codigoIfip AND c.codigoPersona = :codigoPersona AND c.retencion > :retencion ORDER BY c.contratoDpfPK.codigo DESC")
    

})
public class ContratoDpf implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static  final String findByCodigoIfipCodigoPersona = "ContratoDpf.findByCodigoIfipCodigoPersona";
    public static  final String findByRenovacionContratoDpf = "ContratoDpf.findByRenovacionContratoDpf";
    public static  final String findByAnularContrato = "ContratoDpf.findByAnularContrato";
    public static  final String findByIfipPersonaEstado = "ContratoDpf.findByIfipPersonaEstado";
    public static  final String findByIfipPersonaAcreditaMensual = "ContratoDpf.findByIfipPersonaAcreditaMensual";
    public static  final String findByCodigoIfipCodigoPersonaConRetencion = "ContratoDpf.findByCodigoIfipCodigoPersonaConRetencion";
    
    @EmbeddedId
    protected ContratoDpfPK contratoDpfPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERSONA")
    private long codigoPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERIODICIDAD")
    private long codigoPeriodicidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REALIZADO_POR")
    private long realizadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAZO")
    private long plazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAZO_EN_MESES")
    private long plazoEnMeses;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAZO_DIAS")
    private long plazoDias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL")
    private BigDecimal capital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA_INTERES")
    private BigDecimal tasaInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES")
    private BigDecimal interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE_RETENCION")
    private BigDecimal porcentajeRetencion;
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
    @Column(name = "ES_DE_SOCIO")
    private char esDeSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_RENOVACION")
    private char esRenovacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACREDITA_MENSUAL")    
    private char acreditacionMensual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RENOVACION_AUTOMATICA")    
    private char renovacionAutomatica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RENOVACION_CON_INTERES")    
    private char renovacionConInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RETENCION_IMPRESA")
    private char retencionImpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_SOCIO_BENEFICIARIO")
    private char esSocioBeneficiario;
    @Column(name = "CODIGO_PERSONA_BENEFICIARIO")
    private Long codigoPersonaBeneficiario;
    
    @JoinColumn(name = "CODIGO_TASA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TasaInteresProDpfMon codigoTasa;
    @JoinColumn(name = "CODIGO_PERSONA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "REALIZADO_POR", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioRealizadoPor;
    @JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Periodicidad periodicidad;
    @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda;
    @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IfipAgencia  ifipAgencia;
    @JoinColumn(name = "CODIGO_PERSONA_BENEFICIARIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona personaBeneficiario;
    
    
    public ContratoDpf() {
    }

    public ContratoDpf(ContratoDpfPK contratoDpfPK) {
        this.contratoDpfPK = contratoDpfPK;
    }

    public ContratoDpf(ContratoDpfPK contratoDpfPK, long codigoMoneda, long codigoPersona, long codigoIfipAgencia, long codigoPeriodicidad, long realizadoPor, Date fechaContrato, Date fechaVencimiento, Date fechaSistema, long plazo, long plazoEnMeses, long plazoDias, BigDecimal capital, BigDecimal tasaInteres, BigDecimal interes, BigDecimal porcentajeRetencion, BigDecimal retencion, BigDecimal total, char esDeSocio, char esRenovacion, char estado) {
        this.contratoDpfPK = contratoDpfPK;
        this.codigoMoneda = codigoMoneda;
        this.codigoPersona = codigoPersona;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoPeriodicidad = codigoPeriodicidad;
        this.realizadoPor = realizadoPor;
        this.fechaContrato = fechaContrato;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaSistema = fechaSistema;
        this.plazo = plazo;
        this.plazoEnMeses = plazoEnMeses;
        this.plazoDias = plazoDias;
        this.capital = capital;
        this.tasaInteres = tasaInteres;
        this.interes = interes;
        this.porcentajeRetencion = porcentajeRetencion;
        this.retencion = retencion;
        this.total = total;
        this.esDeSocio = esDeSocio;
        this.esRenovacion = esRenovacion;
        this.estado = estado;
    }

    public ContratoDpf(long codigo, long codigoIfip) {
        this.contratoDpfPK = new ContratoDpfPK(codigo, codigoIfip);
    }

    public ContratoDpfPK getContratoDpfPK() {
        return contratoDpfPK;
    }

    public void setContratoDpfPK(ContratoDpfPK contratoDpfPK) {
        this.contratoDpfPK = contratoDpfPK;
    }

    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoPeriodicidad() {
        return codigoPeriodicidad;
    }

    public void setCodigoPeriodicidad(long codigoPeriodicidad) {
        this.codigoPeriodicidad = codigoPeriodicidad;
    }

    public long getRealizadoPor() {
        return realizadoPor;
    }

    public void setRealizadoPor(long realizadoPor) {
        this.realizadoPor = realizadoPor;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public long getPlazo() {
        return plazo;
    }

    public void setPlazo(long plazo) {
        this.plazo = plazo;
    }

    public long getPlazoEnMeses() {
        return plazoEnMeses;
    }

    public void setPlazoEnMeses(long plazoEnMeses) {
        this.plazoEnMeses = plazoEnMeses;
    }

    public long getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(long plazoDias) {
        this.plazoDias = plazoDias;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getPorcentajeRetencion() {
        return porcentajeRetencion;
    }

    public void setPorcentajeRetencion(BigDecimal porcentajeRetencion) {
        this.porcentajeRetencion = porcentajeRetencion;
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

    public char getEsDeSocio() {
        return esDeSocio;
    }

    public void setEsDeSocio(char esDeSocio) {
        this.esDeSocio = esDeSocio;
    }

    public char getEsRenovacion() {
        return esRenovacion;
    }

    public void setEsRenovacion(char esRenovacion) {
        this.esRenovacion = esRenovacion;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

   

    public TasaInteresProDpfMon getCodigoTasa() {
        return codigoTasa;
    }

    public void setCodigoTasa(TasaInteresProDpfMon codigoTasa) {
        this.codigoTasa = codigoTasa;
    }


   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratoDpfPK != null ? contratoDpfPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoDpf)) {
            return false;
        }
        ContratoDpf other = (ContratoDpf) object;
        if ((this.contratoDpfPK == null && other.contratoDpfPK != null) || (this.contratoDpfPK != null && !this.contratoDpfPK.equals(other.contratoDpfPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.dpfs.ContratoDpf[ contratoDpfPK=" + contratoDpfPK + " ]";
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the usuarioRealizadoPor
     */
    public Usuario getUsuarioRealizadoPor() {
        return usuarioRealizadoPor;
    }

    /**
     * @param usuarioRealizadoPor the usuarioRealizadoPor to set
     */
    public void setUsuarioRealizadoPor(Usuario usuarioRealizadoPor) {
        this.usuarioRealizadoPor = usuarioRealizadoPor;
    }

    /**
     * @return the periodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
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
     * @return the acreditacionMensual
     */
    public char getAcreditacionMensual() {
        return acreditacionMensual;
    }

    /**
     * @param acreditacionMensual the acreditacionMensual to set
     */
    public void setAcreditacionMensual(char acreditacionMensual) {
        this.acreditacionMensual = acreditacionMensual;
    }

    /**
     * @return the renovacionAutomatica
     */
    public char getRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    /**
     * @param renovacionAutomatica the renovacionAutomatica to set
     */
    public void setRenovacionAutomatica(char renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    /**
     * @return the renovacionConInteres
     */
    public char getRenovacionConInteres() {
        return renovacionConInteres;
    }

    /**
     * @param renovacionConInteres the renovacionConInteres to set
     */
    public void setRenovacionConInteres(char renovacionConInteres) {
        this.renovacionConInteres = renovacionConInteres;
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

    /**
     * @return the esSocioBeneficiario
     */
    public char getEsSocioBeneficiario() {
        return esSocioBeneficiario;
    }

    /**
     * @param esSocioBeneficiario the esSocioBeneficiario to set
     */
    public void setEsSocioBeneficiario(char esSocioBeneficiario) {
        this.esSocioBeneficiario = esSocioBeneficiario;
    }

    /**
     * @return the codigoPersonaBeneficiario
     */
    public Long getCodigoPersonaBeneficiario() {
        return codigoPersonaBeneficiario;
    }

    /**
     * @param codigoPersonaBeneficiario the codigoPersonaBeneficiario to set
     */
    public void setCodigoPersonaBeneficiario(Long codigoPersonaBeneficiario) {
        this.codigoPersonaBeneficiario = codigoPersonaBeneficiario;
    }
    
}
