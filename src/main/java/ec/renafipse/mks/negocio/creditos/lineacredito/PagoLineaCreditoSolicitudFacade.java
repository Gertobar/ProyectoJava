/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.PagoLineaCreditoSolicitud;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author andres
 */
@Stateless
public class PagoLineaCreditoSolicitudFacade extends AbstractFacade<PagoLineaCreditoSolicitud>{
 
    @PersistenceContext(unitName = "ec.renafipse.mksCreditosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PagoLineaCreditoSolicitudFacade() {
        super(PagoLineaCreditoSolicitud.class);
    }  
    
    private String errorSql;
    private String errorCode;
    private String error;
    private StoredProcedureQuery storedProcedureQuery;
    
    /**
     * 
     * @param codigoLineaCreditoSolicitud
     * @param numeroCuotas
     * @param valor
     * @return 
     */
    public PagoLineaCreditoSolicitud calculaPagoParcialLineaCreditoSolicitud( Long codigoLineaCreditoSolicitud,
                                                                              Short numeroCuotas,
                                                                              BigDecimal valor) {
        PagoLineaCreditoSolicitud pagoLineaCreditoSolicitud;
        errorSql = null;
        errorCode = null;
        error = null;
        storedProcedureQuery = em.createNamedStoredProcedureQuery("calculaLineaCreditoPagoParcial");
        storedProcedureQuery.setParameter("pt_codigo_linea_credito_sol", codigoLineaCreditoSolicitud);
        storedProcedureQuery.setParameter("pt_cuotas", numeroCuotas);
        storedProcedureQuery.setParameter("pt_valor", valor);
        try {
            storedProcedureQuery.execute();
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        } catch (Exception e) {
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        }        
        if (error != null) {
            errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
            errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
            pagoLineaCreditoSolicitud = new PagoLineaCreditoSolicitud();
            pagoLineaCreditoSolicitud.setError(error);
            pagoLineaCreditoSolicitud.setErrorSql(errorSql);
            pagoLineaCreditoSolicitud.setErrorSql(errorCode);
            return pagoLineaCreditoSolicitud;
        }
        pagoLineaCreditoSolicitud = new PagoLineaCreditoSolicitud();
        pagoLineaCreditoSolicitud.setError(null);
        pagoLineaCreditoSolicitud.setErrorSql(null);
        pagoLineaCreditoSolicitud.setErrorSql(null);
        pagoLineaCreditoSolicitud.setCapital((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_capital"));
        pagoLineaCreditoSolicitud.setInteres((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_interes"));
        pagoLineaCreditoSolicitud.setMora((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_mora"));
        pagoLineaCreditoSolicitud.setRubros((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_rubro"));
        pagoLineaCreditoSolicitud.setNotificaciones((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_notificaciones"));
        pagoLineaCreditoSolicitud.setCostoJudicial((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_costo_judicial"));
        pagoLineaCreditoSolicitud.setTotal((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_total"));
        pagoLineaCreditoSolicitud.setNumeroCuotasApagar((String) storedProcedureQuery.getOutputParameterValue("pv_cuotas_a_pagar"));
        return pagoLineaCreditoSolicitud;
    }
    
    /**
     * 
     * @param codigoLineaCreditoSolicitud
     * @param codigoAvanceLineaCredito
     * @param codigoIfip
     * @param codigoUsuario
     * @param codigoCanalServicio
     * @param tipoOrigenPago
     * @param valor
     * @param codigoIfipAgencia
     * @param observaciones
     * @param restructuraPorAbonoCapital
     * @return 
     */
    public PagoLineaCreditoSolicitud pagoParcialLineaCreditoSolicitud( Long codigoLineaCreditoSolicitud,
                                                                       Long codigoAvanceLineaCredito,
                                                                       Long codigoIfip,
                                                                       Long codigoUsuario,
                                                                       Long codigoCanalServicio,
                                                                       String tipoOrigenPago,
                                                                       BigDecimal valor,
                                                                       Long codigoIfipAgencia,
                                                                       String observaciones,
                                                                       String restructuraPorAbonoCapital) {
        PagoLineaCreditoSolicitud pagoLineaCreditoSolicitud;
        errorSql = null;
        errorCode = null;
        error = null;
        storedProcedureQuery = em.createNamedStoredProcedureQuery("pagaLineaCreditoParcial");
        storedProcedureQuery.setParameter("pt_codigo_linea_credito_sol", codigoLineaCreditoSolicitud);
        storedProcedureQuery.setParameter("pt_codigo_avance_linea_credito", codigoAvanceLineaCredito);
        storedProcedureQuery.setParameter("pt_codigo_ifip", codigoIfip);
        storedProcedureQuery.setParameter("pt_codigo_usuario", codigoUsuario);
        storedProcedureQuery.setParameter("pt_codigo_canal_servicio", codigoCanalServicio);
        storedProcedureQuery.setParameter("pv_tipo_origen_pago", tipoOrigenPago);
        storedProcedureQuery.setParameter("pt_valor", valor);
        storedProcedureQuery.setParameter("pt_codigo_ifip_agencia", codigoIfipAgencia);
        storedProcedureQuery.setParameter("pt_observaciones", observaciones);
        storedProcedureQuery.setParameter("pv_restructura_x_abono_capital", restructuraPorAbonoCapital);
        try {
            storedProcedureQuery.execute();
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        } catch (Exception e) {
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        }
        if (error != null) {
            errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
            errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
            pagoLineaCreditoSolicitud = new PagoLineaCreditoSolicitud();
            pagoLineaCreditoSolicitud.setError(error);
            pagoLineaCreditoSolicitud.setErrorSql(errorSql);
            pagoLineaCreditoSolicitud.setErrorSql(errorCode);
            return pagoLineaCreditoSolicitud;
        }
        pagoLineaCreditoSolicitud = new PagoLineaCreditoSolicitud();
        pagoLineaCreditoSolicitud.setError(null);
        pagoLineaCreditoSolicitud.setErrorSql(null);
        pagoLineaCreditoSolicitud.setErrorSql(null);
        pagoLineaCreditoSolicitud.setMensajeFormulario((String) storedProcedureQuery.getOutputParameterValue("pv_mensaje_formulario"));
        pagoLineaCreditoSolicitud.setCodigoFormulario((Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_formulario"));
        pagoLineaCreditoSolicitud.setCodigoFormulario((Long) storedProcedureQuery.getOutputParameterValue("pn_genera_formulario"));
        pagoLineaCreditoSolicitud.setCodigo((Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_linea_cre_sol"));
        return pagoLineaCreditoSolicitud;
    }
    
    /**
     * 
     * @param codigoLineaCreditoSolicitud
     * @return 
     */
    public PagoLineaCreditoSolicitud calculaPagoTotalLineaCreditoSolicitud( Long codigoLineaCreditoSolicitud) {
        PagoLineaCreditoSolicitud pagoLineaCreditoSolicitud;
        errorSql = null;
        errorCode = null;
        error = null;
        storedProcedureQuery = em.createNamedStoredProcedureQuery("calculaLineaCreditoPagoTotal");
        storedProcedureQuery.setParameter("pt_codigo_linea_credito_sol", codigoLineaCreditoSolicitud);
        try {
            storedProcedureQuery.execute();
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        } catch (Exception e) {
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        }        
        if (error != null) {
            errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
            errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
            pagoLineaCreditoSolicitud = new PagoLineaCreditoSolicitud();
            pagoLineaCreditoSolicitud.setError(error);
            pagoLineaCreditoSolicitud.setErrorSql(errorSql);
            pagoLineaCreditoSolicitud.setErrorSql(errorCode);
            return pagoLineaCreditoSolicitud;
        }
        pagoLineaCreditoSolicitud = new PagoLineaCreditoSolicitud();
        pagoLineaCreditoSolicitud.setError(null);
        pagoLineaCreditoSolicitud.setErrorSql(null);
        pagoLineaCreditoSolicitud.setErrorSql(null);
        pagoLineaCreditoSolicitud.setCapital((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_capital"));
        pagoLineaCreditoSolicitud.setInteres((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_interes"));
        pagoLineaCreditoSolicitud.setMora((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_mora"));
        pagoLineaCreditoSolicitud.setRubros((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_rubro"));
        pagoLineaCreditoSolicitud.setNotificaciones((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_notificaciones"));
        pagoLineaCreditoSolicitud.setCostoJudicial((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_costo_judicial"));
        pagoLineaCreditoSolicitud.setTotal((BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_total"));
        return pagoLineaCreditoSolicitud;
    }
    
    /**
     * 
     * @param codigoLineaCreditoSolicitud
     * @param codigoAvanceLineaCredito
     * @param codigoIfip
     * @param codigoUsuario
     * @param codigoCanalServicio
     * @param tipoOrigenPago
     * @param valor
     * @param codigoIfipAgencia
     * @param observaciones
     * @param restructuraPorAbonoCapital
     * @return 
     */
    public PagoLineaCreditoSolicitud pagoTotalLineaCreditoSolicitud( Long codigoLineaCreditoSolicitud,
                                                                     Long codigoAvanceLineaCredito,
                                                                     Long codigoIfip,
                                                                     Long codigoUsuario,
                                                                     Long codigoCanalServicio,
                                                                     String tipoOrigenPago,
                                                                     BigDecimal valor,
                                                                     Long codigoIfipAgencia,
                                                                     String observaciones,
                                                                     String restructuraPorAbonoCapital) {
        PagoLineaCreditoSolicitud pagoLineaCreditoSolicitud;
        errorSql = null;
        errorCode = null;
        error = null;
        storedProcedureQuery = em.createNamedStoredProcedureQuery("pagaLineaCreditoTotal");
        storedProcedureQuery.setParameter("pt_codigo_linea_credito_sol", codigoLineaCreditoSolicitud);
        storedProcedureQuery.setParameter("pt_codigo_avance_linea_credito", codigoAvanceLineaCredito);
        storedProcedureQuery.setParameter("pt_codigo_ifip", codigoIfip);
        storedProcedureQuery.setParameter("pt_codigo_usuario", codigoUsuario);
        storedProcedureQuery.setParameter("pt_codigo_canal_servicio", codigoCanalServicio);
        storedProcedureQuery.setParameter("pv_tipo_origen_pago", tipoOrigenPago);
        storedProcedureQuery.setParameter("pt_valor", valor);
        storedProcedureQuery.setParameter("pt_codigo_ifip_agencia", codigoIfipAgencia);
        storedProcedureQuery.setParameter("pt_observaciones", observaciones);
        storedProcedureQuery.setParameter("pv_restructura_x_abono_capital", restructuraPorAbonoCapital);
        try {
            storedProcedureQuery.execute();
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        } catch (Exception e) {
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        }
        if (error != null) {
            errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
            errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
            pagoLineaCreditoSolicitud = new PagoLineaCreditoSolicitud();
            pagoLineaCreditoSolicitud.setError(error);
            pagoLineaCreditoSolicitud.setErrorSql(errorSql);
            pagoLineaCreditoSolicitud.setErrorSql(errorCode);
            return pagoLineaCreditoSolicitud;
        }
        pagoLineaCreditoSolicitud = new PagoLineaCreditoSolicitud();
        pagoLineaCreditoSolicitud.setError(null);
        pagoLineaCreditoSolicitud.setErrorSql(null);
        pagoLineaCreditoSolicitud.setErrorSql(null);
        pagoLineaCreditoSolicitud.setMensajeFormulario((String) storedProcedureQuery.getOutputParameterValue("pv_mensaje_formulario"));
        pagoLineaCreditoSolicitud.setCodigoFormulario((Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_formulario"));
        pagoLineaCreditoSolicitud.setCodigoFormulario((Long) storedProcedureQuery.getOutputParameterValue("pn_genera_formulario"));
        pagoLineaCreditoSolicitud.setCodigo((Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_linea_cre_sol"));
        return pagoLineaCreditoSolicitud;
    }
}
