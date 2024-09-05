/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.modelo.creditos.lineacredito;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.comunes.UbicacionGeografica;
import ec.renafipse.mks.modelo.creditos.DestinoEspecifico;
import ec.renafipse.mks.modelo.creditos.DestinoFinanciero;
import ec.renafipse.mks.modelo.ifips.EnvioCanalServicioIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.modelo.socios.Socio;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MKS_CREDITOS.LINEA_CREDITO_SOLICITUD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigo", query = "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoYCodigoEstado", query = "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigo = :codigo AND l.codigoLineaCreditoEstadoSol = :codigoEstado"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoSocio", query = "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigoSocio = :codigoSocio ORDER BY l.codigo DESC"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoSocioEstadoSolicitud", query = "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigoSocio = :codigoSocio AND l.codigoLineaCreditoEstadoSol = :codigoEstado"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoSocioParaCambioEstado", query = "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigoSocio = :codigoSocio AND l.codigoLineaCreditoEstadoSol IN ( 8,7 )"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoListaCodigoEstado", query= "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigo = :codigo AND l.codigoLineaCreditoEstadoSol IN (:codigosEstado)"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoListaCodigoEstadoCodigoAgencia", query= "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigo = :codigo AND l.codigoIfipAgencia = :codigoAgencia AND l.codigoLineaCreditoEstadoSol IN (:codigosEstado)"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoSocioListaCodigoEstado", query= "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigoSocio = :codigoSocio AND l.codigoLineaCreditoEstadoSol IN (:codigosEstado)"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoAgenciaListaCodigoEstado", query= "SELECT l FROM LineaCreditoSolicitud l WHERE l.codigoIfipAgencia = :codigoAgencia AND l.codigoLineaCreditoEstadoSol IN (:codigosEstado) ORDER BY l.codigo"),
    @NamedQuery(name = "LineaCreditoSolicitud.findByCodigoEstado", query= "SELECT l FROM LineaCreditoSolicitud l WHERE l.socio.socioPK.codigo = :codigoSocio AND l.codigoLineaCreditoEstadoSol IN ( 1,2,3,4,8 )")
})
@NamedNativeQueries({
    @NamedNativeQuery(name="LineaCreditoSolicitud.findByIdentificacionSocio", query= "SELECT l.codigo,\n" +
"       l.codigo_ifip,\n" +
"       l.codigo_ifip_agencia,\n" +
"       l.codigo_linea_credito,\n" +
"       l.codigo_socio,\n" +
"       l.codigo_act_eco,\n" +
"       l.codigo_estado,\n" +
"       l.codigo_ubi_geo,\n" +
"       l.codigo_destino_financiero,\n" +
"       l.codigo_envio_can_ser_ifi,\n" +
"       l.monto_linea_credito,\n" +
"       l.dia_pago,\n" +
"       l.dia_estado_cuenta,\n" +
"       l.fecha_actualizacion,\n" +
"       l.porcentaje_pago_minimo,\n" +
"       l.codigo_cuenta_acreditada,\n" +
"       l.codigo_cuenta_debito,\n" +
"       l.observaciones,\n" +
"       l.fecha_solicitud,\n" +
"       l.codigo_destino_especifico FROM mks_creditos.linea_credito_solicitud l, mks_socios.socio s, mks_socios.persona p WHERE l.codigo_socio = s.codigo AND p.codigo = s.codigo_persona AND p.identificacion = :identificacion" , resultClass=LineaCreditoSolicitud.class),
    @NamedNativeQuery(name="LineaCreditoSolicitud.findAprobadasSinAvanceVigente", query= "SELECT l.codigo,\n" +
"       l.codigo_ifip,\n" +
"       l.codigo_ifip_agencia,\n" +
"       l.codigo_linea_credito,\n" +
"       l.codigo_socio,\n" +
"       l.codigo_act_eco,\n" +
"       l.codigo_estado,\n" +
"       l.codigo_ubi_geo,\n" +
"       l.codigo_destino_financiero,\n" +
"       l.codigo_envio_can_ser_ifi,\n" +
"       l.monto_linea_credito,\n" +
"       l.dia_pago,\n" +
"       l.dia_estado_cuenta,\n" +
"       l.fecha_actualizacion,\n" +
"       l.porcentaje_pago_minimo,\n" +
"       l.codigo_cuenta_acreditada,\n" +
"       l.codigo_cuenta_debito,\n" +
"       l.observaciones,\n" +
"       l.fecha_solicitud,\n" +
"       l.codigo_destino_especifico FROM mks_creditos.linea_credito_solicitud l WHERE l.codigo_socio = :codigoSocio AND l.codigo_estado = 8 AND (SELECT NVL(COUNT(alc.codigo), 0) FROM mks_creditos.avance_linea_credito alc WHERE alc.estado = 'V') = 0" , resultClass=LineaCreditoSolicitud.class),
    @NamedNativeQuery(name="LineaCreditoSolicitud.findPendientePagoByCodigoSocioListaCodigoEstado", query= "SELECT DISTINCT lcs.codigo,\n" +
"       lcs.codigo_ifip,\n" +
"       lcs.codigo_ifip_agencia,\n" +
"       lcs.codigo_linea_credito,\n" +
"       lcs.codigo_socio,\n" +
"       lcs.codigo_act_eco,\n" +
"       lcs.codigo_estado,\n" +
"       lcs.codigo_ubi_geo,\n" +
"       lcs.codigo_destino_financiero,\n" +
"       lcs.codigo_envio_can_ser_ifi,\n" +
"       lcs.monto_linea_credito,\n" +
"       lcs.dia_pago,\n" +
"       lcs.dia_estado_cuenta,\n" +
"       lcs.fecha_actualizacion,\n" +
"       lcs.porcentaje_pago_minimo,\n" +
"       lcs.codigo_cuenta_acreditada,\n" +
"       lcs.codigo_cuenta_debito,\n" +
"       lcs.observaciones,\n" +
"       lcs.fecha_solicitud,\n" +
"       lcs.codigo_destino_especifico FROM mks_creditos.linea_credito_solicitud lcs, mks_creditos.avance_linea_credito alc WHERE lcs.codigo = alc.codigo_linea_credito_solicitud AND lcs.codigo_socio = :codigoSocio AND alc.estado IN ('G')", resultClass=LineaCreditoSolicitud.class),
    @NamedNativeQuery(name="LineaCreditoSolicitud.findPendientePagoAvanceByCodigoSocioListaCodigoEstado", query= "SELECT DISTINCT lcs.codigo,\n" +
"       lcs.codigo_ifip,\n" +
"       lcs.codigo_ifip_agencia,\n" +
"       lcs.codigo_linea_credito,\n" +
"       lcs.codigo_socio,\n" +
"       lcs.codigo_act_eco,\n" +
"       lcs.codigo_estado,\n" +
"       lcs.codigo_ubi_geo,\n" +
"       lcs.codigo_destino_financiero,\n" +
"       lcs.codigo_envio_can_ser_ifi,\n" +
"       lcs.monto_linea_credito,\n" +
"       lcs.dia_pago,\n" +
"       lcs.dia_estado_cuenta,\n" +
"       lcs.fecha_actualizacion,\n" +
"       lcs.porcentaje_pago_minimo,\n" +
"       lcs.codigo_cuenta_acreditada,\n" +
"       lcs.codigo_cuenta_debito,\n" +
"       lcs.observaciones,\n" +
"       lcs.fecha_solicitud,\n" +
"       lcs.codigo_destino_especifico FROM mks_creditos.linea_credito_solicitud lcs, mks_creditos.avance_linea_credito alc WHERE lcs.codigo = alc.codigo_linea_credito_solicitud AND lcs.codigo_socio = :codigoSocio AND alc.estado IN ('V')", resultClass=LineaCreditoSolicitud.class)
})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
    name = "LineaCreditoSolicitud.guardaFirmante", 
    procedureName = "MKS_CREDITOS.PKM_LINEA_CREDITO.P_GUARDA_FIRMANTE",
    parameters = { 
    @StoredProcedureParameter(name = "pt_codigo_linea_credito_sol", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
),
    @NamedStoredProcedureQuery(
    name = "LineaCreditoSolicitud.obtieneMontoFinalAvance", 
    procedureName = "MKS_CREDITOS.PKM_LINEA_CREDITO.P_OBTIENE_MONTO_FINAL_AVANCE",
    parameters = { 
    @StoredProcedureParameter(name = "pt_codigo_linea_credito_sol", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_monto_solicitado", type = BigDecimal.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_monto_final", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
    ),            
    
    @NamedStoredProcedureQuery(
    name = "LineaCreditoSolicitud.obtieneSaldoOcupadoLinea", 
    procedureName = "MKS_CREDITOS.PKM_LINEA_CREDITO.P_OBTIENE_SALDO_CAPITAL_LINEA",
    parameters = { 
    @StoredProcedureParameter(name = "pt_codigo_linea_credito_sol", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_saldo_capital", type = BigDecimal.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) }
)
})
public class LineaCreditoSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByCodigoEstado = "LineaCreditoSolicitud.findByCodigoEstado";
    public static String guardaFirmante = "LineaCreditoSolicitud.guardaFirmante";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GSQ_LINEA_CREDITO_SOLICITUD")
    @SequenceGenerator(name = "GSQ_LINEA_CREDITO_SOLICITUD", schema = "MKS_CREDITOS",  allocationSize = 0, sequenceName = "SEQ_LINEA_CREDITO_SOLICITUD")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ifip ifip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP")
    private Long codigoIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private Long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOCIO")
    private Long codigoSocio;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Socio socio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ACT_ECO")
    private Long codigoActEco;
    @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LineaCreditoEstadoSol lineaCreditoEstadoSol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO")
    private Long codigoLineaCreditoEstadoSol;
    @JoinColumn(name = "CODIGO_UBI_GEO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UbicacionGeografica ubicacionGeografica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_UBI_GEO")
    private Long codigoUbicacionGeografica;
    @JoinColumn(name = "CODIGO_ENVIO_CAN_SER_IFI", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EnvioCanalServicioIfip envioCanalServicioIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENVIO_CAN_SER_IFI")
    private Long codigoEnvioCanalServicioIfip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_LINEA_CREDITO")
    private BigDecimal montoLineaCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_PAGO")
    private short diaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_ESTADO_CUENTA")
    private short diaEstadoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @JoinColumn(name = "CODIGO_LINEA_CREDITO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LineaCredito lineaCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_LINEA_CREDITO")
    private Long codigoLineaCredito;
    @JoinColumn(name = "CODIGO_DESTINO_FINANCIERO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DestinoFinanciero destinoFinanciero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_DESTINO_FINANCIERO")
    private Long codigoDestinoFinanciero;
    @JoinColumn(name = "CODIGO_DESTINO_ESPECIFICO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private DestinoEspecifico destinoEspecifico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_DESTINO_ESPECIFICO")
    private Long codigoDestinoEspecifico;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lineaCreditoSolicitud")
    private LineaCreditoSolEnvCom lineaCreditoSolEnvCom;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lineaCreditoSolicitud")
    private LineaCreditoSolAprNeg lineaCreditoSolAprNeg;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lineaCreditoSolicitud")
    private LineaCreditoSolicitudInfTec lineaCreditoSolicitudInfTec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE_PAGO_MINIMO")
    private BigDecimal porcentajePagoMinimo;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CUENTA_ACREDITADA", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Cuenta cuentaAcreditada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_ACREDITADA")        
    private Long codigoCuentaAcreditada;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_CUENTA_ACREDITADA", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Cuenta cuentaDebito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_CUENTA_DEBITO")
    private Long codigoCuentaDebito;
    @Basic(optional = false)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineaCreditoSolicitud")
    private List<LineaCreditoSolTipGar> listaLineaCreditoSolicitudTipoGarantia;
    @Transient
    private String nombreSocio;
    @Transient
    private String agencia;
    @Transient
    private String usuarioAprueba;
    @Transient
    private String observacionesEnvioComision;
    @Transient
    private String nombreLineaCredito;
    
    public LineaCreditoSolicitud() {
    }

    public LineaCreditoSolicitud(Long codigo) {
        this.codigo = codigo;
    }

    public LineaCreditoSolicitud(Long codigo, Long codigoIfip, Long codigoIfipAgencia, Long codigoSocio, Long codigoActEco, Long codigoLineaCreditoEstadoSol, Long codigoUbicacionGeografica, Long codigoEnvioCanalServicioIfip, BigDecimal montoLineaCredito, short diaPago, short diaEstadoCuenta, Date fechaActualizacion, Long codigoLineaCredito, Long codigoDestinoFinanciero, BigDecimal porcentajePagoMinimo, Long codigoCuentaAcreditada, Long codigoCuentaDebito, String observaciones, Date fechaSolicitud) {
        this.codigo = codigo;
        this.codigoIfip = codigoIfip;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoSocio = codigoSocio;
        this.codigoActEco = codigoActEco;
        this.codigoLineaCreditoEstadoSol = codigoLineaCreditoEstadoSol;
        this.codigoUbicacionGeografica = codigoUbicacionGeografica;
        this.codigoEnvioCanalServicioIfip = codigoEnvioCanalServicioIfip;
        this.montoLineaCredito = montoLineaCredito;
        this.diaPago = diaPago;
        this.diaEstadoCuenta = diaEstadoCuenta;
        this.fechaActualizacion = fechaActualizacion;
        this.codigoLineaCredito = codigoLineaCredito;
        this.codigoDestinoFinanciero = codigoDestinoFinanciero;
        this.porcentajePagoMinimo = porcentajePagoMinimo;
        this.codigoCuentaAcreditada = codigoCuentaAcreditada; 
        this.codigoCuentaDebito = codigoCuentaDebito;
        this.observaciones = observaciones;
        this.fechaSolicitud = fechaSolicitud;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoIfip() {
        return codigoIfip;
    }

    public void setCodigoIfip(Long codigoIfip) {
        this.codigoIfip = codigoIfip;
    }

    public Long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(Long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public Long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(Long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }
    
    public Socio getSocio() {
        return socio;
    }
    
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    
    public Long getCodigoActEco() {
        return codigoActEco;
    }

    public void setCodigoActEco(Long codigoActEco) {
        this.codigoActEco = codigoActEco;
    }

    public LineaCreditoEstadoSol getLineaCreditoEstadoSol() {
        return lineaCreditoEstadoSol;
    }

    public void setLineaCreditoEstadoSol(LineaCreditoEstadoSol lineaCreditoEstadoSol) {
        this.lineaCreditoEstadoSol = lineaCreditoEstadoSol;
    }

    public Long getCodigoUbicacionGeografica() {
        return codigoUbicacionGeografica;
    }

    public void setCodigoUbicacionGeografica(Long codigoUbicacionGeografica) {
        this.codigoUbicacionGeografica = codigoUbicacionGeografica;
    }

    public EnvioCanalServicioIfip getEnvioCanalServicioIfip() {
        return envioCanalServicioIfip;
    }

    public void setEnvioCanalServicioIfip(EnvioCanalServicioIfip envioCanalServicioIfip) {
        this.envioCanalServicioIfip = envioCanalServicioIfip;
    }

    public BigDecimal getMontoLineaCredito() {
        return montoLineaCredito;
    }

    public void setMontoLineaCredito(BigDecimal montoLineaCredito) {
        this.montoLineaCredito = montoLineaCredito;
    }

    public short getDiaPago() {
        return diaPago;
    }

    public void setDiaPago(short diaPago) {
        this.diaPago = diaPago;
    }

    public short getDiaEstadoCuenta() {
        return diaEstadoCuenta;
    }

    public void setDiaEstadoCuenta(short diaEstadoCuenta) {
        this.diaEstadoCuenta = diaEstadoCuenta;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LineaCredito getLineaCredito() {
        return lineaCredito;
    }

    public void setLineaCredito(LineaCredito lineaCredito) {
        this.lineaCredito = lineaCredito;
    }

    public Long getCodigoDestinoFinanciero() {
        return codigoDestinoFinanciero;
    }

    public void setCodigoDestinoFinanciero(Long codigoDestinoFinanciero) {
        this.codigoDestinoFinanciero = codigoDestinoFinanciero;
    }
    
    public DestinoFinanciero getDestinoFinanciero() {
        return destinoFinanciero;
    }

    public void setDestinoFinanciero(DestinoFinanciero destinoFinanciero) {
        this.destinoFinanciero = destinoFinanciero;
    }
    
    public DestinoEspecifico getDestinoEspecifico() {
        return destinoEspecifico;
    }

    public void setDestinoEspecifico(DestinoEspecifico destinoEspecifico) {
        this.destinoEspecifico = destinoEspecifico;
    }
    
    public Long getCodigoDestinoEspecifico() {
        return codigoDestinoEspecifico;
    }

    public void setCodigoDestinoEspecifico(Long codigoDestinoEspecifico) {
        this.codigoDestinoEspecifico = codigoDestinoEspecifico;
    }

    public LineaCreditoSolEnvCom getLineaCreditoSolEnvCom() {
        return lineaCreditoSolEnvCom;
    }

    public void setLineaCreditoSolEnvCom(LineaCreditoSolEnvCom lineaCreditoSolEnvCom) {
        this.lineaCreditoSolEnvCom = lineaCreditoSolEnvCom;
    }

    public LineaCreditoSolAprNeg getLineaCreditoSolAprNeg() {
        return lineaCreditoSolAprNeg;
    }

    public void setLineaCreditoSolAprNeg(LineaCreditoSolAprNeg lineaCreditoSolAprNeg) {
        this.lineaCreditoSolAprNeg = lineaCreditoSolAprNeg;
    }

    public LineaCreditoSolicitudInfTec getLineaCreditoSolicitudInfTec() {
        return lineaCreditoSolicitudInfTec;
    }

    public void setLineaCreditoSolicitudInfTec(LineaCreditoSolicitudInfTec lineaCreditoSolicitudInfTec) {
        this.lineaCreditoSolicitudInfTec = lineaCreditoSolicitudInfTec;
    }
    
    public BigDecimal getPorcentajePagoMinimo() {
        return porcentajePagoMinimo;
    }

    public void setPorcentajePagoMinimo(BigDecimal porcentajePagoMinimo) {
        this.porcentajePagoMinimo = porcentajePagoMinimo;
    }

    public Long getCodigoCuentaAcreditada() {
        return codigoCuentaAcreditada;
    }

    public void setCodigoCuentaAcreditada(Long codigoCuentaAcreditada) {
        this.codigoCuentaAcreditada = codigoCuentaAcreditada;
    }

    public Long getCodigoCuentaDebito() {
        return codigoCuentaDebito;
    }

    public void setCodigoCuentaDebito(Long codigoCuentaDebito) {
        this.codigoCuentaDebito = codigoCuentaDebito;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }
    
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Cuenta getCuentaAcreditada() {
        return cuentaAcreditada;
    }
    
    public void setCuentaAcreditada(Cuenta cuentaAcreditada) {
        this.cuentaAcreditada = cuentaAcreditada;
    }

    public Cuenta getCuentaDebito() {
        return cuentaDebito;
    }

    public void setCuentaDebito(Cuenta cuentaDebito) {
        this.cuentaDebito = cuentaDebito;
    }

    public Long getCodigoLineaCreditoEstadoSol() {
        return codigoLineaCreditoEstadoSol;
    }

    public void setCodigoLineaCreditoEstadoSol(Long codigoLineaCreditoEstadoSol) {
        this.codigoLineaCreditoEstadoSol = codigoLineaCreditoEstadoSol;
    }

    public UbicacionGeografica getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    public void setUbicacionGeografica(UbicacionGeografica ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

    public Long getCodigoEnvioCanalServicioIfip() {
        return codigoEnvioCanalServicioIfip;
    }

    public void setCodigoEnvioCanalServicioIfip(Long codigoEnvioCanalServicioIfip) {
        this.codigoEnvioCanalServicioIfip = codigoEnvioCanalServicioIfip;
    }

    public Long getCodigoLineaCredito() {
        return codigoLineaCredito;
    }

    public void setCodigoLineaCredito(Long codigoLineaCredito) {
        this.codigoLineaCredito = codigoLineaCredito;
    }
    
    public Ifip getIfip() {
        return ifip;
    }

    public void setIfip(Ifip ifip) {
        this.ifip = ifip;
    }
    
    public List<LineaCreditoSolTipGar> getListaLineaCreditoSolicitudTipoGarantia() {
        return listaLineaCreditoSolicitudTipoGarantia;
    }

    public void setListaLineaCreditoSolicitudTipoGarantia(List<LineaCreditoSolTipGar> listaLineaCreditoSolicitudTipoGarantia) {
        this.listaLineaCreditoSolicitudTipoGarantia = listaLineaCreditoSolicitudTipoGarantia;
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
        if (!(object instanceof LineaCreditoSolicitud)) {
            return false;
        }
        LineaCreditoSolicitud other = (LineaCreditoSolicitud) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud[ codigo=" + codigo + " ]";
    }

    /**
     * @return the nombreSocio
     */
    public String getNombreSocio() {
        return nombreSocio;
    }

    /**
     * @param nombreSocio the nombreSocio to set
     */
    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * @return the usuarioAprueba
     */
    public String getUsuarioAprueba() {
        return usuarioAprueba;
    }

    /**
     * @param usuarioAprueba the usuarioAprueba to set
     */
    public void setUsuarioAprueba(String usuarioAprueba) {
        this.usuarioAprueba = usuarioAprueba;
    }

    /**
     * @return the observacionesEnvioComision
     */
    public String getObservacionesEnvioComision() {
        return observacionesEnvioComision;
    }

    /**
     * @param observacionesEnvioComision the observacionesEnvioComision to set
     */
    public void setObservacionesEnvioComision(String observacionesEnvioComision) {
        this.observacionesEnvioComision = observacionesEnvioComision;
    }

    /**
     * @return the nombreLineaCredito
     */
    public String getNombreLineaCredito() {
        return nombreLineaCredito;
    }

    /**
     * @param nombreLineaCredito the nombreLineaCredito to set
     */
    public void setNombreLineaCredito(String nombreLineaCredito) {
        this.nombreLineaCredito = nombreLineaCredito;
    }

}
