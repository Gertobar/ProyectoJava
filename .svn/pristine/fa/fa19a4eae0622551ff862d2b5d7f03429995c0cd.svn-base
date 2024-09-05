/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.modelo.creditos;

import ec.renafipse.mks.modelo.ifips.IfipAgencia;
import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.modelo.socios.Socio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vastudillo
 */
@Entity
@Table(name = "MKS_CREDITOS.SOLICITUD")
@XmlRootElement
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
    name = "Solicitud.validaIndicadorEnSol", 
    procedureName = "MKS_CREDITOS.PKM_INDICADOR_CREDITO.P_VALIDA_INDICADOR_EN_SOL",
    parameters = { 
    @StoredProcedureParameter(name = "pt_codigo_persona", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_cuota_maxima", type = BigDecimal.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_periodicidad", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_monto_credito", type = BigDecimal.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_producto_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_mensaje", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) } ),
    @NamedStoredProcedureQuery(
    name = "Solicitud.validaBaseAhorro", 
    procedureName = "MKS_CREDITOS.PKM_SOLICITUD.P_VALIDA_BASE_AHORRO",
    parameters = { 
    @StoredProcedureParameter(name = "pt_codigo_socio", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_producto_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_monto_credito", type = BigDecimal.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) } ),
    @NamedStoredProcedureQuery(
    name = "Solicitud.validaValorCertificado", 
    procedureName = "MKS_CREDITOS.PKM_SOLICITUD.P_VALIDA_VALOR_CERTIFICADO",
    parameters = { 
    @StoredProcedureParameter(name = "pt_numero_credito", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pt_codigo_ifip", type = Long.class, mode = ParameterMode.IN),
    @StoredProcedureParameter(name = "pv_mensaje", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_sql", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error_code", type = String.class, mode = ParameterMode.OUT),
    @StoredProcedureParameter(name = "pv_error", type = String.class, mode = ParameterMode.OUT) } )
})
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findByNumero", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.numero = :numero"),
    @NamedQuery(name = "Solicitud.findByCodigoIfip", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Solicitud.findByCodigoIfipAgencia", query = "SELECT s FROM Solicitud s WHERE s.codigoIfipAgencia = :codigoIfipAgencia"),
    @NamedQuery(name = "Solicitud.findByCodigoSocio", query = "SELECT s FROM Solicitud s WHERE s.codigoSocio = :codigoSocio"),
    @NamedQuery(name = "Solicitud.findByCodigoActEco", query = "SELECT s FROM Solicitud s WHERE s.codigoActEco = :codigoActEco"),
    @NamedQuery(name = "Solicitud.findByFechaSolicitud", query = "SELECT s FROM Solicitud s WHERE s.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Solicitud.findByTasa", query = "SELECT s FROM Solicitud s WHERE s.tasa = :tasa"),
    @NamedQuery(name = "Solicitud.findByMontoCredito", query = "SELECT s FROM Solicitud s WHERE s.montoCredito = :montoCredito"),
    @NamedQuery(name = "Solicitud.findByDiaFijo", query = "SELECT s FROM Solicitud s WHERE s.diaFijo = :diaFijo"),
    @NamedQuery(name = "Solicitud.findByTipoTabla", query = "SELECT s FROM Solicitud s WHERE s.tipoTabla = :tipoTabla"),
    @NamedQuery(name = "Solicitud.findByNumeroCuotas", query = "SELECT s FROM Solicitud s WHERE s.numeroCuotas = :numeroCuotas"),
    @NamedQuery(name = "Solicitud.findByCodigoEstado", query = "SELECT s FROM Solicitud s WHERE s.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "Solicitud.findByEstadoColocadoPor", query = "SELECT s FROM Solicitud s WHERE s.estadoColocadoPor = :estadoColocadoPor"),
    @NamedQuery(name = "Solicitud.findByFechaEstado", query = "SELECT s FROM Solicitud s WHERE s.fechaEstado = :fechaEstado"),
//Personalizadas
    @NamedQuery(name = "Solicitud.findByNumeroCodigoIfip", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.numero = :numero AND s.solicitudPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Solicitud.findByCodigoSocioCodigoIfip", query = "SELECT s FROM Solicitud s WHERE s.codigoSocio = :codigoSocio AND s.solicitudPK.codigoIfip = :codigoIfip"),    
    @NamedQuery(name = "Solicitud.findByNombreSocioCodigoIfip", query = "SELECT s FROM Solicitud s WHERE s.socio.codigoPersona.nombreCompleto LIKE :nombreCompleto AND s.socio.socioPK.codigoIfip = :codigoIfip"),
    @NamedQuery(name = "Solicitud.findByNumeroCodigoIfipEstado", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.numero = :numero AND s.solicitudPK.codigoIfip = :codigoIfip AND s.estadoCredito.codigo = :estadoCredito"),
    @NamedQuery(name = "Solicitud.findByCodigoSocioCodigoIfipEstado", query = "SELECT s FROM Solicitud s WHERE s.codigoSocio = :codigoSocio AND s.solicitudPK.codigoIfip = :codigoIfip AND s.estadoCredito.codigo = :estadoCredito"),
    @NamedQuery(name = "Solicitud.findByCodigoIfipEstadoCredito", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.codigoIfip = :codigoIfip AND s.estadoCredito.codigo = :estadoCredito AND s.ifipAgencia.codigo = :codigoIfipAgencia"),
    @NamedQuery(name = "Solicitud.findByCodigoIfipEstadoCreditoAll", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.codigoIfip = :codigoIfip AND s.estadoCredito.codigo = :estadoCredito"),
    //Pago Credito
    @NamedQuery(name = "Solicitud.findBySolicitudCodigoSocioCodigoIfip", query = "SELECT s FROM Solicitud s WHERE s.codigoSocio = :codigoSocio AND s.solicitudPK.codigoIfip = :codigoIfip AND s.estadoCredito.codigo = :codigoEstado"),
    @NamedQuery(name = "Solicitud.findBySolicitudNombreSocioCodigoIfip", query = "SELECT s FROM Solicitud s WHERE s.socio.codigoPersona.nombreCompleto LIKE :nombreCompleto AND s.socio.socioPK.codigoIfip = :codigoIfip AND s.estadoCredito.codigo = :codigoEstado")

})
@NamedNativeQueries({
    @NamedNativeQuery(name="Solicitud.findByIdentificacionPersonaExternaCodigoIfip", query= "SELECT s.numero, s.codigo_ifip, s.codigo_ifip_agencia, s.codigo_socio, s.codigo_producto, s.codigo_moneda, s.codigo_periodicidad, s.codigo_act_eco, s.fecha_solicitud, s.codigo_tasa, s.tasa, s.monto_credito, s.dia_fijo, s.tipo_tabla, s.numero_cuotas, s.codigo_estado, s.estado_colocado_por, s.codigo_ubi_geo, s.fecha_estado, s.observaciones, s.fecha_actualizacion, s.codigo_origen_recursos, s.codigo_destino_financiero FROM mks_creditos.solicitud s, mks_servicios.solicitud_credito sc WHERE s.numero = sc.numero_credito AND s.codigo_ifip = :codigoIfip AND sc.identificacion = :numeroIdentificacion AND s.codigo_estado = 9" , resultClass=Solicitud.class),
    @NamedNativeQuery(name="Solicitud.findCreditoByPerfilFabricaCredito", query= "SELECT DISTINCT s.numero,\n" +
"       s.codigo_ifip,\n" +
"       s.codigo_ifip_agencia,\n" +
"       s.codigo_socio,\n" +
"       s.codigo_producto,\n" +
"       s.codigo_moneda,\n" +
"       s.codigo_periodicidad,\n" +
"       s.codigo_act_eco,\n" +
"       s.fecha_solicitud,\n" +
"       s.codigo_tasa,\n" +
"       s.tasa,\n" +
"       s.monto_credito,\n" +
"       s.dia_fijo,\n" +
"       s.tipo_tabla,\n" +
"       s.numero_cuotas,\n" +
"       s.codigo_estado,\n" +
"       s.estado_colocado_por,\n" +
"       s.codigo_ubi_geo,\n" +
"       s.fecha_estado,\n" +
"       s.observaciones,\n" +
"       s.fecha_actualizacion,\n" +
"       s.codigo_origen_recursos,\n" +
"       s.codigo_destino_financiero,\n" +
"       s.codigo_destino_especifico\n" +
"FROM   mks_creditos.solicitud s,\n" +
"       mks_creditos.producto_credito pc,\n" +
"       mks_creditos.fabrica_perfil_estado fpe,\n" +
"       mks_creditos.fabrica_perfil_monto fpm,\n" +
"       mks_creditos.fabrica_usuario_perfil fup\n" +
"WHERE  s.codigo_producto = pc.codigo\n" +
"AND    s.codigo_estado = fpe.codigo_estado_credito\n" +
"AND    pc.codigo_tipo_cartera = fpm.codigo_tipo_cartera\n" +
"AND    fpe.codigo_fabrica_perfil = fpm.codigo_fabrica_perfil\n" +
"AND    fpe.codigo_fabrica_perfil = fup.codigo_perfil\n" +
"AND    s.codigo_ifip_agencia = fup.codigo_ifip_agencia\n" +
"AND    s.monto_credito BETWEEN fpm.monto_inicial AND fpm.monto_final\n" +
"AND    fpe.vigente = 'S'\n" +
"AND    fpm.vigente = 'S'\n" +
"AND    fpe.tipo_estado_credito = 'C'" +
"AND    s.numero = :numero\n" +
"AND    s.codigo_ifip = :codigoIfip\n" +
"AND    s.codigo_estado = :codigoEstadoCredito\n" +
"AND    fup.codigo_usuario = :codigoUsuario\n" +
"ORDER BY s.fecha_estado, s.codigo_ifip_agencia", resultClass=Solicitud.class),
    @NamedNativeQuery(name="Solicitud.findByPerfilFabrica", query= "SELECT DISTINCT s.numero,\n" +
"       s.codigo_ifip,\n" +
"       s.codigo_ifip_agencia,\n" +
"       s.codigo_socio,\n" +
"       s.codigo_producto,\n" +
"       s.codigo_moneda,\n" +
"       s.codigo_periodicidad,\n" +
"       s.codigo_act_eco,\n" +
"       s.fecha_solicitud,\n" +
"       s.codigo_tasa,\n" +
"       s.tasa,\n" +
"       s.monto_credito,\n" +
"       s.dia_fijo,\n" +
"       s.tipo_tabla,\n" +
"       s.numero_cuotas,\n" +
"       s.codigo_estado,\n" +
"       s.estado_colocado_por,\n" +
"       s.codigo_ubi_geo,\n" +
"       s.fecha_estado,\n" +
"       s.observaciones,\n" +
"       s.fecha_actualizacion,\n" +
"       s.codigo_origen_recursos,\n" +
"       s.codigo_destino_financiero,\n" +
"       s.codigo_destino_especifico\n" +
"FROM   mks_creditos.solicitud s,\n" +
"       mks_creditos.producto_credito pc,\n" +
"       mks_creditos.fabrica_perfil_estado fpe,\n" +
"       mks_creditos.fabrica_perfil_monto fpm,\n" +
"       mks_creditos.fabrica_usuario_perfil fup\n" +
"WHERE  s.codigo_producto = pc.codigo\n" +
"AND    s.codigo_estado = fpe.codigo_estado_credito\n" +
"AND    pc.codigo_tipo_cartera = fpm.codigo_tipo_cartera\n" +
"AND    fpe.codigo_fabrica_perfil = fpm.codigo_fabrica_perfil\n" +
"AND    fpe.codigo_fabrica_perfil = fup.codigo_perfil\n" +
"AND    s.codigo_ifip_agencia = fup.codigo_ifip_agencia\n" +
"AND    s.monto_credito BETWEEN fpm.monto_inicial AND fpm.monto_final\n" +
"AND    fpe.vigente = 'S'\n" +
"AND    fpm.vigente = 'S'\n" +
"AND    fpe.tipo_estado_credito = 'C'" +
"AND    s.codigo_ifip = :codigoIfip\n" +
"AND    s.codigo_ifip_agencia = :codigoIfipAgencia\n" +
"AND    s.codigo_estado = :codigoEstadoCredito\n" +
"AND    fup.codigo_usuario = :codigoUsuario\n" +
"ORDER BY s.fecha_estado, s.codigo_ifip_agencia" , resultClass=Solicitud.class),
    @NamedNativeQuery(name="Solicitud.findByPerfilFabricaCreditoAll", query= "SELECT DISTINCT s.numero,\n" +
"       s.codigo_ifip,\n" +
"       s.codigo_ifip_agencia,\n" +
"       s.codigo_socio,\n" +
"       s.codigo_producto,\n" +
"       s.codigo_moneda,\n" +
"       s.codigo_periodicidad,\n" +
"       s.codigo_act_eco,\n" +
"       s.fecha_solicitud,\n" +
"       s.codigo_tasa,\n" +
"       s.tasa,\n" +
"       s.monto_credito,\n" +
"       s.dia_fijo,\n" +
"       s.tipo_tabla,\n" +
"       s.numero_cuotas,\n" +
"       s.codigo_estado,\n" +
"       s.estado_colocado_por,\n" +
"       s.codigo_ubi_geo,\n" +
"       s.fecha_estado,\n" +
"       s.observaciones,\n" +
"       s.fecha_actualizacion,\n" +
"       s.codigo_origen_recursos,\n" +
"       s.codigo_destino_financiero,\n" +
"       s.codigo_destino_especifico\n" +
"FROM   mks_creditos.solicitud s,\n" +
"       mks_creditos.producto_credito pc,\n" +
"       mks_creditos.fabrica_perfil_estado fpe,\n" +
"       mks_creditos.fabrica_perfil_monto fpm,\n" +
"       mks_creditos.fabrica_usuario_perfil fup\n" +
"WHERE  s.codigo_producto = pc.codigo\n" +
"AND    s.codigo_estado = fpe.codigo_estado_credito\n" +
"AND    pc.codigo_tipo_cartera = fpm.codigo_tipo_cartera\n" +
"AND    fpe.codigo_fabrica_perfil = fpm.codigo_fabrica_perfil\n" +
"AND    fpe.codigo_fabrica_perfil = fup.codigo_perfil\n" +
"AND    s.monto_credito BETWEEN fpm.monto_inicial AND fpm.monto_final\n" +
"AND    fpe.vigente = 'S'\n" +
"AND    fpm.vigente = 'S'\n" +
"AND    fpe.tipo_estado_credito = 'C'" +
"AND    s.codigo_ifip = :codigoIfip\n" +
"AND    s.codigo_estado = :codigoEstadoCredito\n" +
"AND    fup.codigo_usuario = :codigoUsuario\n" +
"ORDER BY s.fecha_estado, s.codigo_ifip_agencia" , resultClass=Solicitud.class)
,
@NamedNativeQuery(name="Solicitud.findByPerfilFabricaCreditoAllParaAprobar", query= "SELECT DISTINCT s.numero,\n" +
"       s.codigo_ifip,\n" +
"       s.codigo_ifip_agencia,\n" +
"       s.codigo_socio,\n" +
"       s.codigo_producto,\n" +
"       s.codigo_moneda,\n" +
"       s.codigo_periodicidad,\n" +
"       s.codigo_act_eco,\n" +
"       s.fecha_solicitud,\n" +
"       s.codigo_tasa,\n" +
"       s.tasa,\n" +
"       s.monto_credito,\n" +
"       s.dia_fijo,\n" +
"       s.tipo_tabla,\n" +
"       s.numero_cuotas,\n" +
"       s.codigo_estado,\n" +
"       s.estado_colocado_por,\n" +
"       s.codigo_ubi_geo,\n" +
"       s.fecha_estado,\n" +
"       s.observaciones,\n" +
"       s.fecha_actualizacion,\n" +
"       s.codigo_origen_recursos,\n" +
"       s.codigo_destino_financiero,\n" +
"       s.codigo_destino_especifico\n" +
"FROM   mks_creditos.solicitud s,\n" +
"       mks_creditos.producto_credito pc,\n" +
"       mks_creditos.fabrica_perfil_estado fpe,\n" +
"       mks_creditos.fabrica_perfil_monto fpm,\n" +
"       mks_creditos.fabrica_usuario_perfil fup,\n" +
"       mks_creditos.solicitud_pre_aprobada_negada span\n" +
"WHERE  s.codigo_producto = pc.codigo\n" +
"AND    s.codigo_estado = fpe.codigo_estado_credito\n" +
"AND    pc.codigo_tipo_cartera = fpm.codigo_tipo_cartera\n" +
"AND    fpe.codigo_fabrica_perfil = fpm.codigo_fabrica_perfil\n" +
"AND    fpe.codigo_fabrica_perfil = fup.codigo_perfil\n" +
"AND    s.numero = span.numero_credito\n" +
"AND    s.codigo_ifip = span.codigo_ifip\n" +
"AND    s.monto_credito BETWEEN fpm.monto_inicial AND fpm.monto_final\n" +
"AND    fpe.vigente = 'S'\n" +
"AND    fpm.vigente = 'S'\n" +
"AND    fpe.tipo_estado_credito = 'C'\n" +
"AND    s.codigo_ifip = :codigoIfip\n" +
"AND    s.codigo_estado = :codigoEstadoCredito\n" +
"AND    fup.codigo_usuario = :codigoUsuario\n" +
"AND    span.pre_aprobado = 'S'\n" +
"ORDER BY s.fecha_estado, s.codigo_ifip_agencia" , resultClass=Solicitud.class)
})

public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String findByNumeroCodigoIfip = "Solicitud.findByNumeroCodigoIfip";
    public static final String findByCodigoSocioCodigoIfip = "Solicitud.findByCodigoSocioCodigoIfip";
    public static final String findByNombreSocioCodigoIfip = "Solicitud.findByNombreSocioCodigoIfip";
    public static final String findByNumeroCodigoIfipEstado = "Solicitud.findByNumeroCodigoIfipEstado";
    public static final String findByCodigoSocioCodigoIfipEstado = "Solicitud.findByCodigoSocioCodigoIfipEstado";
    public static final String findByCodigoIfipEstadoCredito = "Solicitud.findByCodigoIfipEstadoCredito";
    public static final String findByCodigoIfipEstadoCreditoAll = "Solicitud.findByPerfilFabricaCreditoAll";
    public static final String findBySolicitudCodigoSocioCodigoIfip = "Solicitud.findBySolicitudCodigoSocioCodigoIfip";
    public static final String findBySolicitudNombreSocioCodigoIfip = "Solicitud.findBySolicitudNombreSocioCodigoIfip";
    public static final String validaIndicadorSolicitud = "Solicitud.validaIndicadorEnSol";
    public static final String validaValorCertificado = "Solicitud.validaValorCertificado";
    public static final String findCreditoByPerfilFabricaCredito = "Solicitud.findCreditoByPerfilFabricaCredito";
    public static final String findByPerfilFabricaCredito = "Solicitud.findByPerfilFabrica";
    public static final String findByPerfilFabricaCreditoAll = "Solicitud.findByPerfilFabricaCreditoAll";
    public static final String findByPerfilFabricaCreditoAllParaAprobar = "Solicitud.findByPerfilFabricaCreditoAllParaAprobar";
    @EmbeddedId
    protected SolicitudPK solicitudPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_IFIP_AGENCIA")
    private long codigoIfipAgencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_SOCIO")
    private long codigoSocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ACT_ECO")
    private long codigoActEco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA")
    private BigDecimal tasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_CREDITO")
    private BigDecimal montoCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA_FIJO")
    private char diaFijo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_TABLA")
    private char tipoTabla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_CUOTAS")
    private int numeroCuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTADO")
    private long codigoEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO_COLOCADO_POR")
    private long estadoColocadoPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_UBI_GEO")
    private long codigoUbiGeo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_MONEDA")
    private long codigoMoneda;
    @Basic(optional = true)    
    @Size(max = 100)
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @JoinColumn(name = "CODIGO_TASA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private TasaInteresProCreMonIfi codigoTasa;
    
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProductoCreditoMonedaIfip productoCreditoMonedaIfip;
    
    @JoinColumns({
        @JoinColumn(name = "CODIGO_PRODUCTO", referencedColumnName = "CODIGO_PRODUCTO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_MONEDA", referencedColumnName = "CODIGO_MONEDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_PERIODICIDAD", referencedColumnName = "CODIGO_PERIODICIDAD", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PeriodicidadProMonIfi periodicidadProMonIfi;

    @JoinColumns({
        @JoinColumn(name = "CODIGO_IFIP_AGENCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private IfipAgencia ifipAgencia;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_SOCIO", referencedColumnName = "CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_IFIP", referencedColumnName = "CODIGO_IFIP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Socio socio;
    /*@JoinColumns({
        @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO_ESTADO", insertable = false, updatable = false),
        @JoinColumn(name = "ESTADO_COLOCADO_POR", referencedColumnName = "CODIGO_USUARIO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private UsuarioEstadoCredito usuarioEstadoCredito;*/
    @JoinColumns({
        @JoinColumn(name = "CODIGO_ACT_ECO", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ActividadEconomica actividadEconomica;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EstadoCredito estadoCredito;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_ORIGEN_RECURSOS", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private OrigenRecursos origenRecursos;
    
    @JoinColumns({
        @JoinColumn(name = "CODIGO_DESTINO_FINANCIERO", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private DestinoFinanciero destinoFinanciero;
    
    @JoinColumns({
        @JoinColumn(name = "CODIGO_DESTINO_ESPECIFICO", referencedColumnName = "CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = true)
    private DestinoEspecifico destinoEspecifico;
    
    @Transient
    private String observacionPreAprobacion;
    
    @Transient
    private String observacionPendienteParaConceder;
    
    @Transient
    private SolicitudMotivoDevolucion solicitudMotivoDevolucion;
    
    @Transient
    private String observacionesDevolucion;
    
    @Transient
    private int plazoMeses;
    
    public Solicitud() {
    }

    public Solicitud(SolicitudPK solicitudPK) {
        this.solicitudPK = solicitudPK;
    }

    public Solicitud(SolicitudPK solicitudPK, long codigoIfipAgencia, long codigoSocio, long codigoActEco, Date fechaSolicitud, BigDecimal tasa, BigDecimal montoCredito, char diaFijo, char tipoTabla, int numeroCuotas, long codigoEstado, long estadoColocadoPor, Date fechaEstado) {
        this.solicitudPK = solicitudPK;
        this.codigoIfipAgencia = codigoIfipAgencia;
        this.codigoSocio = codigoSocio;
        this.codigoActEco = codigoActEco;
        this.fechaSolicitud = fechaSolicitud;
        this.tasa = tasa;
        this.montoCredito = montoCredito;
        this.diaFijo = diaFijo;
        this.tipoTabla = tipoTabla;
        this.numeroCuotas = numeroCuotas;
        this.codigoEstado = codigoEstado;
        this.estadoColocadoPor = estadoColocadoPor;
        this.fechaEstado = fechaEstado;
    }

    public Solicitud(long numero, long codigoIfip) {
        this.solicitudPK = new SolicitudPK(numero, codigoIfip);
    }

    public SolicitudPK getSolicitudPK() {
        return solicitudPK;
    }

    public void setSolicitudPK(SolicitudPK solicitudPK) {
        this.solicitudPK = solicitudPK;
    }

    public long getCodigoIfipAgencia() {
        return codigoIfipAgencia;
    }

    public void setCodigoIfipAgencia(long codigoIfipAgencia) {
        this.codigoIfipAgencia = codigoIfipAgencia;
    }

    public long getCodigoSocio() {
        return codigoSocio;
    }

    public void setCodigoSocio(long codigoSocio) {
        this.codigoSocio = codigoSocio;
    }

    public long getCodigoActEco() {
        return codigoActEco;
    }

    public void setCodigoActEco(long codigoActEco) {
        this.codigoActEco = codigoActEco;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public BigDecimal getTasa() {
        return tasa;
    }

    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }

    public BigDecimal getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(BigDecimal montoCredito) {
        this.montoCredito = montoCredito;
    }

    public char getDiaFijo() {
        return diaFijo;
    }

    public void setDiaFijo(char diaFijo) {
        this.diaFijo = diaFijo;
    }

    public char getTipoTabla() {
        return tipoTabla;
    }

    public void setTipoTabla(char tipoTabla) {
        this.tipoTabla = tipoTabla;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public long getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(long codigoEstado) {
        this.codigoEstado = codigoEstado;
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

    public TasaInteresProCreMonIfi getCodigoTasa() {
        return codigoTasa;
    }

    public void setCodigoTasa(TasaInteresProCreMonIfi codigoTasa) {
        this.codigoTasa = codigoTasa;
    }

    public ProductoCreditoMonedaIfip getProductoCreditoMonedaIfip() {
        return productoCreditoMonedaIfip;
    }

    public void setProductoCreditoMonedaIfip(ProductoCreditoMonedaIfip productoCreditoMonedaIfip) {
        this.productoCreditoMonedaIfip = productoCreditoMonedaIfip;
    }

    public PeriodicidadProMonIfi getPeriodicidadProMonIfi() {
        return periodicidadProMonIfi;
    }

    public void setPeriodicidadProMonIfi(PeriodicidadProMonIfi periodicidadProMonIfi) {
        this.periodicidadProMonIfi = periodicidadProMonIfi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudPK != null ? solicitudPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.solicitudPK == null && other.solicitudPK != null) || (this.solicitudPK != null && !this.solicitudPK.equals(other.solicitudPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.renafipse.mks.modelo.creditos.Solicitud[ solicitudPK=" + solicitudPK + " ]";
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
     * @return the socio
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * @param socio the socio to set
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * @return the usuarioEstadoCredito
     */
    /*public UsuarioEstadoCredito getUsuarioEstadoCredito() {
        return usuarioEstadoCredito;
    }

    /**
     * @param usuarioEstadoCredito the usuarioEstadoCredito to set
     */
    /*public void setUsuarioEstadoCredito(UsuarioEstadoCredito usuarioEstadoCredito) {
        this.usuarioEstadoCredito = usuarioEstadoCredito;
    }

    /**
     * @return the actividadEconomica
     */
    public ActividadEconomica getActividadEconomica() {
        return actividadEconomica;
    }

    /**
     * @param actividadEconomica the actividadEconomica to set
     */
    public void setActividadEconomica(ActividadEconomica actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    /**
     * @return the estadoCredito
     */
    public EstadoCredito getEstadoCredito() {
        return estadoCredito;
    }

    /**
     * @param estadoCredito the estadoCredito to set
     */
    public void setEstadoCredito(EstadoCredito estadoCredito) {
        this.estadoCredito = estadoCredito;
    }

    /**
     * @return the codigoUbiGeo
     */
    public long getCodigoUbiGeo() {
        return codigoUbiGeo;
    }

    /**
     * @param codigoUbiGeo the codigoUbiGeo to set
     */
    public void setCodigoUbiGeo(long codigoUbiGeo) {
        this.codigoUbiGeo = codigoUbiGeo;
    }

    /**
     * @return the codigoMoneda
     */
    public long getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * @param codigoMoneda the codigoMoneda to set
     */
    public void setCodigoMoneda(long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the origenRecursos
     */
    public OrigenRecursos getOrigenRecursos() {
        return origenRecursos;
    }

    /**
     * @param origenRecursos the origenRecursos to set
     */
    public void setOrigenRecursos(OrigenRecursos origenRecursos) {
        this.origenRecursos = origenRecursos;
    }

    /**
     * @return the destinoFinanciero
     */
    public DestinoFinanciero getDestinoFinanciero() {
        return destinoFinanciero;
    }

    /**
     * @param destinoFinanciero the destinoFinanciero to set
     */
    public void setDestinoFinanciero(DestinoFinanciero destinoFinanciero) {
        this.destinoFinanciero = destinoFinanciero;
    }

    /**
     * @return the observacionPreAprobacion
     */
    public String getObservacionPreAprobacion() {
        return observacionPreAprobacion;
    }

    /**
     * @param observacionPreAprobacion the observacionPreAprobacion to set
     */
    public void setObservacionPreAprobacion(String observacionPreAprobacion) {
        this.observacionPreAprobacion = observacionPreAprobacion;
    }

    /**
     * @return the observacionPendienteParaConceder
     */
    public String getObservacionPendienteParaConceder() {
        return observacionPendienteParaConceder;
    }

    /**
     * @param observacionPendienteParaConceder the observacionPendienteParaConceder to set
     */
    public void setObservacionPendienteParaConceder(String observacionPendienteParaConceder) {
        this.observacionPendienteParaConceder = observacionPendienteParaConceder;
    }

    /**
     * @return the observacionesDevolucion
     */
    public String getObservacionesDevolucion() {
        return observacionesDevolucion;
    }

    /**
     * @param observacionesDevolucion the observacionesDevolucion to set
     */
    public void setObservacionesDevolucion(String observacionesDevolucion) {
        this.observacionesDevolucion = observacionesDevolucion;
    }

    /**
     * @return the solicitudMotivoDevolucion
     */
    public SolicitudMotivoDevolucion getSolicitudMotivoDevolucion() {
        return solicitudMotivoDevolucion;
    }

    /**
     * @param solicitudMotivoDevolucion the solicitudMotivoDevolucion to set
     */
    public void setSolicitudMotivoDevolucion(SolicitudMotivoDevolucion solicitudMotivoDevolucion) {
        this.solicitudMotivoDevolucion = solicitudMotivoDevolucion;
    }

    /**
     * @return the plazoDias
     */
    public int getPlazoMeses() {
        return plazoMeses;
    }

    /**
     * @param plazoMeses the plazoMeses to set
     */
    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    /**
     * @return the destinoEspecifico
     */
    public DestinoEspecifico getDestinoEspecifico() {
        return destinoEspecifico;
    }

    /**
     * @param destinoEspecifico the destinoEspecifico to set
     */
    public void setDestinoEspecifico(DestinoEspecifico destinoEspecifico) {
        this.destinoEspecifico = destinoEspecifico;
    }

}
