/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos.lineacredito;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "AVANCE_LINEA_CREDITO" , schema = "MKS_CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvanceLineaCredito.findAll", query = "SELECT a FROM AvanceLineaCredito a")
    , @NamedQuery(name = "AvanceLineaCredito.findByCodigo", query = "SELECT a FROM AvanceLineaCredito a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "AvanceLineaCredito.findByCodigoSolicitudPendientePago", query = "SELECT a FROM AvanceLineaCredito a WHERE a.codigoLineaCreditoSolicitud.codigo = :codigoLineaCreditoSolicitud AND a.estado IN ('V')")
})
public class AvanceLineaCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_LINEA_CREDITO_SOLICITUD", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private LineaCreditoSolicitud codigoLineaCreditoSolicitud;
    @Basic(optional = false)
    @Column(name = "NUMERO_CREDITO") 
    private Long numeroCredito;
    @Basic(optional = false)
    @Column(name = "CODIGO_IFIP")
    private Long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private Long codigoIfipAgencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA_INTERES")
    private BigDecimal tasaInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CUOTAS")
    private Long numeroCuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAZO_DIAS")
    private Long plazoDias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CONCECION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConcecion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL_A_PAGAR")
    private BigDecimal capitalAPagar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES_A_PAGAR")
    private BigDecimal interesAPagar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBROS_A_PAGAR")
    private BigDecimal rubrosAPagar;
    @Basic(optional = false)
    @Column(name = "SALDO_CAPITAL_MOMENTO_CREDITO")
    private BigDecimal saldoCapitalMomentoCredito;
    @Basic(optional = false)
    @Column(name = "INTERES_MOMENTO_CREDITO")
    private BigDecimal interesMomentoCredito;
    @Basic(optional = false)
    @Column(name = "RUBROS_MOMENTO_CREDITO")
    private BigDecimal rubrosMomentoCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_USUARIO")
    private Long codigoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BASE_CALCULO")
    private BigDecimal baseCalculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES_PENDIENTE")
    private BigDecimal interesPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBROS_PENDIENTE")
    private BigDecimal rubrosPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MORA_PENDIENTE")
    private BigDecimal moraPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA")
    private Short diasMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_ATRASO")
    private Short diasAtraso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBRO_CONCESION")
    private BigDecimal rubroConcesion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBRO_CONCESION_DEBITADO")
    private BigDecimal rubroConcesionDebitado;
    
    public AvanceLineaCredito() {
    }

    public AvanceLineaCredito(Long codigo) {
        this.codigo = codigo;
    }

    public AvanceLineaCredito(Long codigo, LineaCreditoSolicitud codigoLineaCreditoSolicitud, Long numeroCredito, Long codigoIfip, Long codigoIfipAgencia, BigDecimal monto, BigDecimal tasaInteres, Long numeroCuotas, Long plazoDias, Date fechaConcecion, Date fechaVencimiento, BigDecimal capitalAPagar, BigDecimal interesAPagar, BigDecimal rubrosAPagar, BigDecimal saldoCapitalMomentoCredito, BigDecimal interesMomentoCredito, BigDecimal rubrosMomentoCredito, Date fechaRegistro, Long codigoUsuario, String estado, BigDecimal saldoCapital, BigDecimal baseCalculo, BigDecimal interesPendiente, BigDecimal rubrosPendiente, BigDecimal moraPendiente, Short diasMora, Short diasAtraso, BigDecimal rubroConcesion, BigDecimal rubroConcesionDebitado) {
        this.codigo = codigo;
        this.codigoLineaCreditoSolicitud = codigoLineaCreditoSolicitud;
        this.numeroCredito = numeroCredito;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.numeroCuotas = numeroCuotas;
        this.plazoDias = plazoDias;
        this.fechaConcecion = fechaConcecion;
        this.fechaVencimiento = fechaVencimiento;
        this.capitalAPagar = capitalAPagar;
        this.interesAPagar = interesAPagar;
        this.rubrosAPagar = rubrosAPagar;
        this.saldoCapitalMomentoCredito = saldoCapitalMomentoCredito;
        this.interesMomentoCredito = interesMomentoCredito;
        this.rubrosMomentoCredito = rubrosMomentoCredito;
        this.fechaRegistro = fechaRegistro;
        this.codigoUsuario = codigoUsuario;
        this.estado = estado;
        this.saldoCapital = saldoCapital;
        this.baseCalculo = baseCalculo;
        this.interesPendiente = interesPendiente;
        this.rubrosPendiente = rubrosPendiente;
        this.moraPendiente = moraPendiente;
        this.diasMora = diasMora;
        this.diasAtraso = diasAtraso;
        this.rubroConcesion = rubroConcesion;
        this.rubroConcesionDebitado = rubroConcesionDebitado;
    }

    

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(Long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Long getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Long numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public Long getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(Long plazoDias) {
        this.plazoDias = plazoDias;
    }

    public Date getFechaConcecion() {
        return fechaConcecion;
    }

    public void setFechaConcecion(Date fechaConcecion) {
        this.fechaConcecion = fechaConcecion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getCapitalAPagar() {
        return capitalAPagar;
    }

    public void setCapitalAPagar(BigDecimal capitalAPagar) {
        this.capitalAPagar = capitalAPagar;
    }

    public BigDecimal getInteresAPagar() {
        return interesAPagar;
    }

    public void setInteresAPagar(BigDecimal interesAPagar) {
        this.interesAPagar = interesAPagar;
    }

    public BigDecimal getRubrosAPagar() {
        return rubrosAPagar;
    }

    public void setRubrosAPagar(BigDecimal rubrosAPagar) {
        this.rubrosAPagar = rubrosAPagar;
    }

    public BigDecimal getSaldoCapitalMomentoCredito() {
        return saldoCapitalMomentoCredito;
    }

    public void setSaldoCapitalMomentoCredito(BigDecimal saldoCapitalMomentoCredito) {
        this.saldoCapitalMomentoCredito = saldoCapitalMomentoCredito;
    }

    public BigDecimal getInteresMomentoCredito() {
        return interesMomentoCredito;
    }

    public void setInteresMomentoCredito(BigDecimal interesMomentoCredito) {
        this.interesMomentoCredito = interesMomentoCredito;
    }

    public BigDecimal getRubrosMomentoCredito() {
        return rubrosMomentoCredito;
    }

    public void setRubrosMomentoCredito(BigDecimal rubrosMomentoCredito) {
        this.rubrosMomentoCredito = rubrosMomentoCredito;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public BigDecimal getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(BigDecimal baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public BigDecimal getInteresPendiente() {
        return interesPendiente;
    }

    public void setInteresPendiente(BigDecimal interesPendiente) {
        this.interesPendiente = interesPendiente;
    }

    public BigDecimal getRubrosPendiente() {
        return rubrosPendiente;
    }

    public void setRubrosPendiente(BigDecimal rubrosPendiente) {
        this.rubrosPendiente = rubrosPendiente;
    }

    public LineaCreditoSolicitud getCodigoLineaCreditoSolicitud() {
        return codigoLineaCreditoSolicitud;
    }

    public void setCodigoLineaCreditoSolicitud(LineaCreditoSolicitud codigoLineaCreditoSolicitud) {
        this.codigoLineaCreditoSolicitud = codigoLineaCreditoSolicitud;
    }

    public Long getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(Long numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public Long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }
    
     /**
     * @return the moraPendiente
     */
    public BigDecimal getMoraPendiente() {
        return moraPendiente;
    }

    /**
     * @param moraPendiente the moraPendiente to set
     */
    public void setMoraPendiente(BigDecimal moraPendiente) {
        this.moraPendiente = moraPendiente;
    }

    /**
     * @return the diasMora
     */
    public Short getDiasMora() {
        return diasMora;
    }

    /**
     * @param diasMora the diasMora to set
     */
    public void setDiasMora(Short diasMora) {
        this.diasMora = diasMora;
    }

    /**
     * @return the diasAtraso
     */
    public Short getDiasAtraso() {
        return diasAtraso;
    }

    /**
     * @param diasAtraso the diasAtraso to set
     */
    public void setDiasAtraso(Short diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    /**
     * @return the rubroConcesion
     */
    public BigDecimal getRubroConcesion() {
        return rubroConcesion;
    }

    /**
     * @param rubroConcesion the rubroConcesion to set
     */
    public void setRubroConcesion(BigDecimal rubroConcesion) {
        this.rubroConcesion = rubroConcesion;
    }

    /**
     * @return the rubroConcesionDebitado
     */
    public BigDecimal getRubroConcesionDebitado() {
        return rubroConcesionDebitado;
    }

    /**
     * @param rubroConcesionDebitado the rubroConcesionDebitado to set
     */
    public void setRubroConcesionDebitado(BigDecimal rubroConcesionDebitado) {
        this.rubroConcesionDebitado = rubroConcesionDebitado;
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
        if (!(object instanceof AvanceLineaCredito)) {
            return false;
        }
        AvanceLineaCredito other = (AvanceLineaCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvanceLineaCredito{" + "codigo=" + codigo + ", codigoLineaCreditoSolicitud=" + codigoLineaCreditoSolicitud + ", numeroCredito=" + numeroCredito + ", codigoIfip=" + codigoIfip + ", codigoIfipAgencia=" + codigoIfipAgencia + ", monto=" + monto + ", tasaInteres=" + tasaInteres + ", numeroCuotas=" + numeroCuotas + ", plazoDias=" + plazoDias + ", fechaConcecion=" + fechaConcecion + ", fechaVencimiento=" + fechaVencimiento + ", capitalAPagar=" + capitalAPagar + ", interesAPagar=" + interesAPagar + ", rubrosAPagar=" + rubrosAPagar + ", saldoCapitalMomentoCredito=" + saldoCapitalMomentoCredito + ", interesMomentoCredito=" + interesMomentoCredito + ", rubrosMomentoCredito=" + rubrosMomentoCredito + ", fechaRegistro=" + fechaRegistro + ", codigoUsuario=" + codigoUsuario + ", estado=" + estado + ", saldoCapital=" + saldoCapital + ", baseCalculo=" + baseCalculo + ", interesPendiente=" + interesPendiente + ", rubrosPendiente=" + rubrosPendiente + ", moraPendiente=" + moraPendiente + ", diasMora=" + diasMora + ", diasAtraso=" + diasAtraso + ", rubroConcesion=" + rubroConcesion + ", rubroConcesionDebitado=" + rubroConcesionDebitado + '}';
    }
    
}
