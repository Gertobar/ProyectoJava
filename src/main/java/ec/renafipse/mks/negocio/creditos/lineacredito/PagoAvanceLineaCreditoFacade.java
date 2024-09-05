/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.PagoAvanceLineaCredito;
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
public class PagoAvanceLineaCreditoFacade extends AbstractFacade<PagoAvanceLineaCredito>{
 
    @PersistenceContext(unitName = "ec.renafipse.mksCreditosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PagoAvanceLineaCreditoFacade() {
        super(PagoAvanceLineaCredito.class);
    }  
    
    private String errorSql;
    private String errorCode;
    private String error;
    private StoredProcedureQuery storedProcedureQuery;

    /**
     * 
     * @param codigoAvanceLineaCredito
     * @param codigoUsuario
     * @param tipoOrigenPago
     * @param valor
     * @param codigoIfipAgencia
     * @param observaciones
     * @return 
     */
    public PagoAvanceLineaCredito pagoParcialAvanceLineaCredito( Long codigoAvanceLineaCredito,
                                                                 BigDecimal valor,                                                     
                                                                 String tipoOrigenPago,
                                                                 Long codigoUsuario,
                                                                 Long codigoIfipAgencia,
                                                                 String observaciones) {
        PagoAvanceLineaCredito pagoLineaCreditoAvance;
        errorSql = null;
        errorCode = null;
        error = null;
        storedProcedureQuery = em.createNamedStoredProcedureQuery("pagaLineaCreditoAvanceParcial");
        storedProcedureQuery.setParameter("pt_codigo_avance_linea_credito", codigoAvanceLineaCredito);
        storedProcedureQuery.setParameter("pt_valor", valor);
        storedProcedureQuery.setParameter("pv_tipo_origen", tipoOrigenPago);
        storedProcedureQuery.setParameter("pt_codigo_usuario", codigoUsuario);
        storedProcedureQuery.setParameter("pt_codigo_ifip_agencia", codigoIfipAgencia);
        storedProcedureQuery.setParameter("pt_observaciones", observaciones);
        try {
            storedProcedureQuery.execute();
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        } catch (Exception e) {
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        }
        if (error != null) {
            errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
            errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
            pagoLineaCreditoAvance = new PagoAvanceLineaCredito();
            pagoLineaCreditoAvance.setError(error);
            pagoLineaCreditoAvance.setErrorSql(errorSql);
            pagoLineaCreditoAvance.setErrorSql(errorCode);
            return pagoLineaCreditoAvance;
        }
        pagoLineaCreditoAvance = new PagoAvanceLineaCredito();
        pagoLineaCreditoAvance.setError(null);
        pagoLineaCreditoAvance.setErrorSql(null);
        pagoLineaCreditoAvance.setErrorSql(null);
        pagoLineaCreditoAvance.setCodigo((Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_avance_lin_cre"));
        return pagoLineaCreditoAvance;
    }
   
    /**
     * 
     * @param codigoAvanceLineaCredito
     * @param codigoUsuario
     * @param tipoOrigenPago
     * @param valor
     * @param codigoIfipAgencia
     * @param observaciones
     * @return 
     */
    public PagoAvanceLineaCredito pagoTotalAvanceLineaCredito(  Long codigoAvanceLineaCredito,
                                                                BigDecimal valor,                                                     
                                                                String tipoOrigenPago,
                                                                Long codigoUsuario,
                                                                Long codigoIfipAgencia,
                                                                String observaciones) {
        PagoAvanceLineaCredito pagoLineaCreditoAvance;
        errorSql = null;
        errorCode = null;
        error = null;
        storedProcedureQuery = em.createNamedStoredProcedureQuery("pagaLineaCreditoAvanceTotal");
        storedProcedureQuery.setParameter("pt_codigo_avance_linea_credito", codigoAvanceLineaCredito);
        storedProcedureQuery.setParameter("pt_valor", valor);
        storedProcedureQuery.setParameter("pv_tipo_origen", tipoOrigenPago);
        storedProcedureQuery.setParameter("pt_codigo_usuario", codigoUsuario);
        storedProcedureQuery.setParameter("pt_codigo_ifip_agencia", codigoIfipAgencia);
        storedProcedureQuery.setParameter("pt_observaciones", observaciones);
        try {
            storedProcedureQuery.execute();
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        } catch (Exception e) {
            error = (String) storedProcedureQuery.getOutputParameterValue("pv_error");
        }
        if (error != null) {
            errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
            errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
            pagoLineaCreditoAvance = new PagoAvanceLineaCredito();
            pagoLineaCreditoAvance.setError(error);
            pagoLineaCreditoAvance.setErrorSql(errorSql);
            pagoLineaCreditoAvance.setErrorSql(errorCode);
            return pagoLineaCreditoAvance;
        }
        pagoLineaCreditoAvance = new PagoAvanceLineaCredito();
        pagoLineaCreditoAvance.setError(null);
        pagoLineaCreditoAvance.setErrorSql(null);
        pagoLineaCreditoAvance.setErrorSql(null);
        pagoLineaCreditoAvance.setCodigo((Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_avance_lin_cre"));
        return pagoLineaCreditoAvance;
    }
}
