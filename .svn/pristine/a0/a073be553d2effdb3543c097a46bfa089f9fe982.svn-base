/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.PAGO_CREDITO")
@XmlRootElement
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
    name = "PagoCredito.obtieneValorAPagar", 
    procedureName = "MKS_CREDITOS.PKM_PAGAR_CREDITO.P_OBTIENE_VALOR_A_PAGAR",
    parameters = { 
    @StoredProcedureParameter(name = "pt_numero_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_numero_cuotas", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_valor", type = BigDecimal.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_numero_cuota_a_pagar", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_capital", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_dias_interes", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_interes", type = BigDecimal.class, mode = ParameterMode.OUT), 
    @StoredProcedureParameter(name = "pt_dias_mora", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_mora", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_rubros", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_numero_notificaciones", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_notificaciones", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_costo_judicial", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_total", type = BigDecimal.class, mode = ParameterMode.OUT), 
    @StoredProcedureParameter(name = "pt_saldo_capital", type = BigDecimal.class, mode = ParameterMode.OUT), 
    @StoredProcedureParameter(name = "pv_cuotas_a_pagar", type = String.class, mode = ParameterMode.OUT), 
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
),
    @NamedStoredProcedureQuery(
    name = "PagoCredito.verificaReestructuraCapital", 
    procedureName = "MKS_CREDITOS.PKM_PAGAR_CREDITO.P_VERIFICAR_REE_CAP_CUO_VEN",
    parameters = { 
    @StoredProcedureParameter(name = "pt_numero_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_cuotas_a_pagar", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pn_reestructura", type = Integer.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_cuotas_vencidas", type = Integer.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_cuotas_castigadas", type = Integer.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
),
    @NamedStoredProcedureQuery(
    name = "PagoCredito.obtienePagoTotalCredito", 
    procedureName = "MKS_CREDITOS.PKM_PAGAR_CREDITO.P_OBTIENE_VALOR_PAGO_TOTAL",
    parameters = { 
    @StoredProcedureParameter(name = "pt_numero_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_numero_cuota_a_pagar", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_capital", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_dias_interes", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_interes", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_dias_mora", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_mora", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_rubros", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_numero_notificaciones", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_notificaciones", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_costo_judicial", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_total", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_saldo_capital", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_cuotas_a_pagar", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
),
    @NamedStoredProcedureQuery(
    name = "PagoCredito.pagoTotalCredito", 
    procedureName = "MKS_CREDITOS.PKM_PAGAR_CREDITO.P_PAGA_CREDITO_TOTAL",
    parameters = { 
    @StoredProcedureParameter(name = "pt_numero_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_usuario", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_canal_servicio", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_tipo_origen_pago", type = String.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip_agencia", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_observaciones", type = String.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_tipo_motivo", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_observaciones_mot", type = String.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_mensaje_formulario", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_codigo_formulario", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pn_genera_formulario", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_codigo_pago_credito", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_codigo_pago_avance_lin_cre", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_codigo_pago_linea_cre_sol", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
),
@NamedStoredProcedureQuery(
    name = "PagoCredito.pagaCredito", 
    procedureName = "MKS_CREDITOS.PKM_PAGAR_CREDITO.P_REALIZA_PAGO_CREDITO",
    parameters = { 
    @StoredProcedureParameter(name = "pt_codigo_avance_linea_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_numero_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_usuario", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_canal_servicio", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_tipo_origen_pago", type = String.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_valor", type = BigDecimal.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip_agencia", type = Long.class, mode = ParameterMode.IN), 
    @StoredProcedureParameter(name = "pt_observaciones", type = String.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_restructura_x_abono_capital", type = String.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_mensaje_formulario", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_codigo_formulario", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pn_genera_formulario", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_codigo_pago_credito", type = Long.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pt_codigo_pago_avance_lin_cre", type = Long.class, mode = ParameterMode.OUT), 
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
),
})
@NamedQueries({
    @NamedQuery(name = "PagoCredito.findAll", query = "SELECT p FROM PagoCredito p"),
    @NamedQuery(name = "PagoCredito.findByCodigo", query = "SELECT p FROM PagoCredito p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PagoCredito.findByCodigoIfipAgencia", query = "SELECT p FROM PagoCredito p WHERE p.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "PagoCredito.findByFechaCobro", query = "SELECT p FROM PagoCredito p WHERE p.fechaCobro = :fechaCobro"),
    @NamedQuery(name = "PagoCredito.findByFechaSistema", query = "SELECT p FROM PagoCredito p WHERE p.fechaSistema = :fechaSistema"),
    @NamedQuery(name = "PagoCredito.findByFormaPago", query = "SELECT p FROM PagoCredito p WHERE p.formaPago = :formaPago"),
    @NamedQuery(name = "PagoCredito.findByCapital", query = "SELECT p FROM PagoCredito p WHERE p.capital = :capital"),
    @NamedQuery(name = "PagoCredito.findByCuotas", query = "SELECT p FROM PagoCredito p WHERE p.cuotas = :cuotas"),
    @NamedQuery(name = "PagoCredito.findByDiasInteres", query = "SELECT p FROM PagoCredito p WHERE p.diasInteres = :diasInteres"),
    @NamedQuery(name = "PagoCredito.findByInteres", query = "SELECT p FROM PagoCredito p WHERE p.interes = :interes"),
    @NamedQuery(name = "PagoCredito.findByDiasMora", query = "SELECT p FROM PagoCredito p WHERE p.diasMora = :diasMora"),
    @NamedQuery(name = "PagoCredito.findByMora", query = "SELECT p FROM PagoCredito p WHERE p.mora = :mora"),
    @NamedQuery(name = "PagoCredito.findByNumeroNotificaciones", query = "SELECT p FROM PagoCredito p WHERE p.numeroNotificaciones = :numeroNotificaciones"),
    @NamedQuery(name = "PagoCredito.findByNotificaciones", query = "SELECT p FROM PagoCredito p WHERE p.notificaciones = :notificaciones"),
    @NamedQuery(name = "PagoCredito.findByRubros", query = "SELECT p FROM PagoCredito p WHERE p.rubros = :rubros"),
    @NamedQuery(name = "PagoCredito.findByCostosJudiciales", query = "SELECT p FROM PagoCredito p WHERE p.costosJudiciales = :costosJudiciales"),
    @NamedQuery(name = "PagoCredito.findByTotal", query = "SELECT p FROM PagoCredito p WHERE p.total = :total"),
    @NamedQuery(name = "PagoCredito.findBySaldoCapital", query = "SELECT p FROM PagoCredito p WHERE p.saldoCapital = :saldoCapital"),
    @NamedQuery(name = "PagoCredito.findByCuotaAPagar", query = "SELECT p FROM PagoCredito p WHERE p.cuotaAPagar = :cuotaAPagar"),
    @NamedQuery(name = "PagoCredito.findByFechaProximaPago", query = "SELECT p FROM PagoCredito p WHERE p.fechaProximaPago = :fechaProximaPago"),
//Personalizados
    @NamedQuery(name = "PagoCredito.findBySolicitudCreditoSocio", query = "SELECT s FROM PagoCredito p JOIN p.solicitud s JOIN s.socio so WHERE so.socioPK.codigo = :codigoSocio AND so.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "PagoCredito.findByPagosCreditoSolicitudSocioIfip", query = "SELECT p FROM PagoCredito p JOIN p.solicitud s JOIN s.socio so WHERE s.solicitudPK.numero = :numeroSolicitud AND so.socioPK.codigo = :codigoSocio AND so.socioPK.codigoIfip = :codigoIfip ORDER BY p.codigo, p.cuotas")
})
public class PagoCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findBySolicitudCreditoSocio = "PagoCredito.findBySolicitudCreditoSocio";
    public static final String findByPagosCreditoSolicitudSocioIfip = "PagoCredito.findByPagosCreditoSolicitudSocioIfip";
    public static final String ejecutaPagoCredito = "PagoCredito.pagaCredito";
    public static final String ejecutaObtieneValorAPagar = "PagoCredito.obtieneValorAPagar";
    public static final String ejecutaVerificaReestructuraCapital = "PagoCredito.verificaReestructuraCapital";
    public static final String ejecutaObtieneValorTotalAPagar = "PagoCredito.obtienePagoTotalCredito";
    public static final String ejecutaPagoTotalCredito = "PagoCredito.pagoTotalCredito";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_PAGO_CREDITO")
    @SequenceGenerator(name = "GSQ_PAGO_CREDITO", schema = "MKS_CREDITOS", allocationSize = 0, sequenceName = "SEQ_PAGO_CREDITO")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_COBRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCobro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SISTEMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMA_PAGO")
    private char formaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPITAL")
    private BigDecimal capital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUOTAS")
    private long cuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_INTERES")
    private long diasInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERES")
    private BigDecimal interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_MORA")
    private long diasMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MORA")
    private BigDecimal mora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_NOTIFICACIONES")
    private long numeroNotificaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFICACIONES")
    private BigDecimal notificaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUBROS")
    private BigDecimal rubros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTOS_JUDICIALES")
    private BigDecimal costosJudiciales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CAPITAL")
    private BigDecimal saldoCapital;
    @Column(name = "CUOTA_A_PAGAR")
    private Long cuotaAPagar;
    @Column(name = "FECHA_PROXIMA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProximaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAGO_TOTAL_CREDITO")
    private char pagoTotalCredito;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "codigoPagoCredito", fetch = FetchType.LAZY)
    private PagoCreditoIngEgrCaj pagoCreditoIngEgrCaj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagoCredito", fetch = FetchType.LAZY)
    private Collection<PagoCreditoDetalleCuota> pagoCreditoDetalleCuotaCollection;
    @JoinColumns({
        @JoinColumn(name = "NUMERO_CREDITO", referencedColumnName = "NUMERO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Solicitud solicitud;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "codigoPagoCredito", fetch = FetchType.LAZY)
    private PagoCreditoMovimientoCue pagoCreditoMovimientoCue;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;
    @JoinColumns({
        @JoinColumn(name = "COBRADO_POR", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Usuario cobradoPor;

    public PagoCredito() {
    }

    public PagoCredito(Long codigo) {
        this.codigo = codigo;
    }

    public PagoCredito(Long codigo, long codigoIfipAgencia, Date fechaCobro, Date fechaSistema, char formaPago, BigDecimal capital, long cuotas, long diasInteres, BigDecimal interes, long diasMora, BigDecimal mora, long numeroNotificaciones, BigDecimal notificaciones, BigDecimal rubros, BigDecimal costosJudiciales, BigDecimal total, BigDecimal saldoCapital) {
        this.codigo = codigo;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.fechaCobro = fechaCobro;
        this.fechaSistema = fechaSistema;
        this.formaPago = formaPago;
        this.capital = capital;
        this.cuotas = cuotas;
        this.diasInteres = diasInteres;
        this.interes = interes;
        this.diasMora = diasMora;
        this.mora = mora;
        this.numeroNotificaciones = numeroNotificaciones;
        this.notificaciones = notificaciones;
        this.rubros = rubros;
        this.costosJudiciales = costosJudiciales;
        this.total = total;
        this.saldoCapital = saldoCapital;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public char getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(char formaPago) {
        this.formaPago = formaPago;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public long getCuotas() {
        return cuotas;
    }

    public void setCuotas(long cuotas) {
        this.cuotas = cuotas;
    }

    public long getDiasInteres() {
        return diasInteres;
    }

    public void setDiasInteres(long diasInteres) {
        this.diasInteres = diasInteres;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public long getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(long diasMora) {
        this.diasMora = diasMora;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public long getNumeroNotificaciones() {
        return numeroNotificaciones;
    }

    public void setNumeroNotificaciones(long numeroNotificaciones) {
        this.numeroNotificaciones = numeroNotificaciones;
    }

    public BigDecimal getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(BigDecimal notificaciones) {
        this.notificaciones = notificaciones;
    }

    public BigDecimal getRubros() {
        return rubros;
    }

    public void setRubros(BigDecimal rubros) {
        this.rubros = rubros;
    }

    public BigDecimal getCostosJudiciales() {
        return costosJudiciales;
    }

    public void setCostosJudiciales(BigDecimal costosJudiciales) {
        this.costosJudiciales = costosJudiciales;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSaldoCapital() {
        return saldoCapital;
    }

    public void setSaldoCapital(BigDecimal saldoCapital) {
        this.saldoCapital = saldoCapital;
    }

    public Long getCuotaAPagar() {
        return cuotaAPagar;
    }

    public void setCuotaAPagar(Long cuotaAPagar) {
        this.cuotaAPagar = cuotaAPagar;
    }

    public Date getFechaProximaPago() {
        return fechaProximaPago;
    }

    public void setFechaProximaPago(Date fechaProximaPago) {
        this.fechaProximaPago = fechaProximaPago;
    }

    public PagoCreditoIngEgrCaj getPagoCreditoIngEgrCaj() {
        return pagoCreditoIngEgrCaj;
    }

    public void setPagoCreditoIngEgrCaj(PagoCreditoIngEgrCaj pagoCreditoIngEgrCaj) {
        this.pagoCreditoIngEgrCaj = pagoCreditoIngEgrCaj;
    }

    @XmlTransient
    public Collection<PagoCreditoDetalleCuota> getPagoCreditoDetalleCuotaCollection() {
        return pagoCreditoDetalleCuotaCollection;
    }

    public void setPagoCreditoDetalleCuotaCollection(Collection<PagoCreditoDetalleCuota> pagoCreditoDetalleCuotaCollection) {
        this.pagoCreditoDetalleCuotaCollection = pagoCreditoDetalleCuotaCollection;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public PagoCreditoMovimientoCue getPagoCreditoMovimientoCue() {
        return pagoCreditoMovimientoCue;
    }

    public void setPagoCreditoMovimientoCue(PagoCreditoMovimientoCue pagoCreditoMovimientoCue) {
        this.pagoCreditoMovimientoCue = pagoCreditoMovimientoCue;
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
        if (!(object instanceof PagoCredito)) {
            return false;
        }
        PagoCredito other = (PagoCredito) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.PagoCredito[ codigo=" + codigo + " ]";
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
     * @return the cobradoPor
     */
    public Usuario getCobradoPor() {
        return cobradoPor;
    }

    /**
     * @param cobradoPor the cobradoPor to set
     */
    public void setCobradoPor(Usuario cobradoPor) {
        this.cobradoPor = cobradoPor;
    }

    /**
     * @return the pagoTotalCredito
     */
    public char getPagoTotalCredito() {
        return pagoTotalCredito;
    }

    /**
     * @param pagoTotalCredito the pagoTotalCredito to set
     */
    public void setPagoTotalCredito(char pagoTotalCredito) {
        this.pagoTotalCredito = pagoTotalCredito;
    }

}
