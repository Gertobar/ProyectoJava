/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.clase.creditos.PagoCreditoParcial;
import ec.renafipse.mks.clase.creditos.PagoCreditoReestructuraCaptalCuotaVencida;
import ec.renafipse.mks.clase.creditos.PagoCreditoTotal;
import ec.renafipse.mks.clase.creditos.PagoCreditoValorAPagar;
import ec.renafipse.mks.clase.creditos.TablaAmortizacionReestructuraCapital;
import ec.renafipse.mks.modelo.creditos.CalculoCuotaPagar;
import ec.renafipse.mks.modelo.creditos.PagoCredito;
import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.TablaAmortizacion;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author vastudillo
 */
@Stateless
public class PagoCreditoFacade extends AbstractFacade<PagoCredito> {
    @PersistenceContext
    private EntityManager manager;
    
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;
    private StoredProcedureQuery storedProcedureQuery;
    private Logger logger;
    private ResultSet resultSet;
    private String error;
    private String errorSql;
    private String errorCode;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCreditoFacade() {
        super(PagoCredito.class);
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Obtiene todos los creditos del socio por codigo del socio y lor codigo de
     * la ifip
     *
     * @param codigoSocio
     * @param codigoIfip
     * @return
     */
    public List<Solicitud> getItemsPagoCreditoSocio(long codigoSocio, long codigoIfip) {
        Query query = this.em.createNamedQuery(PagoCredito.findBySolicitudCreditoSocio);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }

    /**
     * 
     * @param numeroSolicitud
     * @param codigoSocio
     * @param codigoIfip
     * @return 
     */
    public List<PagoCredito> getItemsPagoCreditoSocioRealizados(long numeroSolicitud, long codigoSocio, long codigoIfip) {
        Query query = this.em.createNamedQuery(PagoCredito.findByPagosCreditoSolicitudSocioIfip);
        query.setParameter("numeroSolicitud", numeroSolicitud);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    /***
     * Metodo para realizar el pago de credito
     * @param codigoAvanceLineaCredito
     * @param numeroCredito
     * @param codigoIfip
     * @param codigoUsuario
     * @param codigoCanalServicio
     * @param tipoOrigenPago
     * @param valor
     * @param codigoIfipAgencia
     * @param observaciones
     * @param restructuraXAbonoCapital
     * @return PagoCreditoParcial
     */
    public PagoCreditoParcial pagaCredito(Long codigoAvanceLineaCredito, Long numeroCredito, Long codigoIfip, Long codigoUsuario, Long codigoCanalServicio, String tipoOrigenPago, BigDecimal valor, Long codigoIfipAgencia, String observaciones, String restructuraXAbonoCapital ){
        // variables de error 
        error = null;
        errorSql = null;
        errorCode = null;
        StoredProcedureQuery storedProcedureQuery;     
        storedProcedureQuery = manager.createNamedStoredProcedureQuery( PagoCredito.ejecutaPagoCredito ); 
        storedProcedureQuery.setParameter( "pt_codigo_avance_linea_credito", codigoAvanceLineaCredito);
        storedProcedureQuery.setParameter( "pt_numero_credito", numeroCredito);
        storedProcedureQuery.setParameter( "pt_codigo_ifip", codigoIfip);
        storedProcedureQuery.setParameter( "pt_codigo_usuario", codigoUsuario);
        storedProcedureQuery.setParameter( "pt_codigo_canal_servicio", codigoCanalServicio);
        storedProcedureQuery.setParameter( "pv_tipo_origen_pago", tipoOrigenPago);
        storedProcedureQuery.setParameter( "pt_valor", valor);
        storedProcedureQuery.setParameter( "pt_codigo_ifip_agencia", codigoIfipAgencia);
        storedProcedureQuery.setParameter( "pt_observaciones", observaciones);
        storedProcedureQuery.setParameter( "pv_restructura_x_abono_capital", restructuraXAbonoCapital);
        storedProcedureQuery.execute();
        PagoCreditoParcial pagoCreditoParcial = new PagoCreditoParcial((String) storedProcedureQuery.getOutputParameterValue("pv_mensaje_formulario"),
                                                                        (Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_formulario"),
                                                                        (Long) storedProcedureQuery.getOutputParameterValue("pn_genera_formulario"),
                                                                        (Long)storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_credito"),
                                                                        (Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_avance_lin_cre"));
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) {
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
            pagoCreditoParcial = null;
        } 
        return pagoCreditoParcial;
    }
    /***
     * 
     * @param numeroCredito
     * @param codigoIfip
     * @param numeroCuotas
     * @param valor
     * @return PagoCreditoValorAPagar
     */
    public PagoCreditoValorAPagar obtieneValorAPagarCredito(Long numeroCredito, Long codigoIfip, Long numeroCuotas, BigDecimal valor){
        // variables de error 
        error = null;
        errorSql = null;
        errorCode = null;
        // variable de salida
        PagoCreditoValorAPagar pagoCreditoValorAPagar;
        
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = manager.createNamedStoredProcedureQuery( PagoCredito.ejecutaObtieneValorAPagar ); 
        storedProcedureQuery.setParameter( "pt_numero_credito", numeroCredito );
        storedProcedureQuery.setParameter( "pt_codigo_ifip", codigoIfip );
        storedProcedureQuery.setParameter( "pt_numero_cuotas", numeroCuotas );
        storedProcedureQuery.setParameter( "pt_valor", valor );
        
        storedProcedureQuery.execute();
        pagoCreditoValorAPagar = new PagoCreditoValorAPagar((Long) storedProcedureQuery.getOutputParameterValue("pt_numero_cuota_a_pagar"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_capital"),
                                                            (Long) storedProcedureQuery.getOutputParameterValue("pt_dias_interes"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_interes"),
                                                            (Long) storedProcedureQuery.getOutputParameterValue("pt_dias_mora"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_mora"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_rubros"),
                                                            (Long) storedProcedureQuery.getOutputParameterValue("pt_numero_notificaciones"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_notificaciones"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_costo_judicial"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_total"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_saldo_capital"),
                                                            (String) storedProcedureQuery.getOutputParameterValue("pv_cuotas_a_pagar"));
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) {
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
            pagoCreditoValorAPagar = null;
        }
        return pagoCreditoValorAPagar;
    }
    /***
     * 
     * @param numeroCredito
     * @param codigoIfip
     * @param numeroCuotas
     * @return PagoCreditoReestructuraCaptalCuotaVencida
     */
    public PagoCreditoReestructuraCaptalCuotaVencida verificaReestructuraCapitalCuotasVencidas(Long numeroCredito, Long codigoIfip, Long numeroCuotas){        
        // variables de error 
        error = null;
        errorSql = null;
        errorCode = null;
        // variable de salida
        PagoCreditoReestructuraCaptalCuotaVencida pagoCreditoReestructuraCaptalCuotaVencida = new PagoCreditoReestructuraCaptalCuotaVencida(0,0,0);
        // llamar al procedimiento
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = manager.createNamedStoredProcedureQuery( PagoCredito.ejecutaVerificaReestructuraCapital ); 
        storedProcedureQuery.setParameter( "pt_numero_credito", numeroCredito );
        storedProcedureQuery.setParameter( "pt_codigo_ifip", codigoIfip );
        storedProcedureQuery.setParameter( "pt_cuotas_a_pagar", numeroCuotas );
        storedProcedureQuery.execute();
        pagoCreditoReestructuraCaptalCuotaVencida.setReestructura((Integer) storedProcedureQuery.getOutputParameterValue("pn_reestructura"));
        pagoCreditoReestructuraCaptalCuotaVencida.setCuotasVencidas((Integer) storedProcedureQuery.getOutputParameterValue("pt_cuotas_vencidas"));
        pagoCreditoReestructuraCaptalCuotaVencida.setCuotasCastigadas((Integer) storedProcedureQuery.getOutputParameterValue("pt_cuotas_castigadas"));
        return pagoCreditoReestructuraCaptalCuotaVencida;
    }
    /***
     * 
     * @param numeroCredito
     * @param codigoIfip
     * @return PagoCreditoValorAPagar
     */
    public PagoCreditoValorAPagar obtieneValorTotalAPagarCredito(Long numeroCredito, Long codigoIfip){
        // variables de error 
        error = null;
        errorSql = null;
        errorCode = null;
        // variable de salida
        PagoCreditoValorAPagar pagoCreditoValorAPagar;
        
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = manager.createNamedStoredProcedureQuery( PagoCredito.ejecutaObtieneValorTotalAPagar ); 
        storedProcedureQuery.setParameter( "pt_numero_credito", numeroCredito );
        storedProcedureQuery.setParameter( "pt_codigo_ifip", codigoIfip );
        
        storedProcedureQuery.execute();
        pagoCreditoValorAPagar = new PagoCreditoValorAPagar((Long) storedProcedureQuery.getOutputParameterValue("pt_numero_cuota_a_pagar"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_capital"),
                                                            (Long) storedProcedureQuery.getOutputParameterValue("pt_dias_interes"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_interes"),
                                                            (Long) storedProcedureQuery.getOutputParameterValue("pt_dias_mora"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_mora"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_rubros"),
                                                            (Long) storedProcedureQuery.getOutputParameterValue("pt_numero_notificaciones"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_notificaciones"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_costo_judicial"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_total"),
                                                            (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_saldo_capital"),
                                                            (String) storedProcedureQuery.getOutputParameterValue("pv_cuotas_a_pagar"));
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) {
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
            pagoCreditoValorAPagar = null;
        }
        return pagoCreditoValorAPagar;
    }
    
    public PagoCreditoTotal pagaTotalCredito(Long numeroCredito, Long codigoIfip, Long codigoUsuario, Long codigoCanalServicio, String tipoOrigenPago, Long codigoIfipAgencia, String observaciones, Long codigoTipoMotivo, String observacionesMotivo){
        // variables de error 
        error = null;
        errorSql = null;
        errorCode = null;
        
        StoredProcedureQuery storedProcedureQuery;
        
        storedProcedureQuery = manager.createNamedStoredProcedureQuery( PagoCredito.ejecutaPagoTotalCredito );                 
        storedProcedureQuery.setParameter( "pt_numero_credito", numeroCredito);
        storedProcedureQuery.setParameter( "pt_codigo_ifip", codigoIfip);
        storedProcedureQuery.setParameter( "pt_codigo_usuario", codigoUsuario);
        storedProcedureQuery.setParameter( "pt_codigo_canal_servicio", codigoCanalServicio);
        storedProcedureQuery.setParameter( "pv_tipo_origen_pago", tipoOrigenPago);
        storedProcedureQuery.setParameter( "pt_codigo_ifip_agencia", codigoIfipAgencia);
        storedProcedureQuery.setParameter( "pt_observaciones", observaciones);
        storedProcedureQuery.setParameter( "pt_codigo_tipo_motivo", codigoTipoMotivo);
        storedProcedureQuery.setParameter( "pt_observaciones_mot", observacionesMotivo);
        storedProcedureQuery.execute();
        PagoCreditoTotal pagoCreditoTotal = new PagoCreditoTotal((String) storedProcedureQuery.getOutputParameterValue("pv_mensaje_formulario"),
                                                                 (Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_formulario"),
                                                                 (Long) storedProcedureQuery.getOutputParameterValue("pn_genera_formulario"),
                                                                 (Long)storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_credito"),
                                                                 (Long) storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_avance_lin_cre"),
                                                                 (Long)storedProcedureQuery.getOutputParameterValue("pt_codigo_pago_linea_cre_sol") );
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) {
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
            pagoCreditoTotal = null;
        } 
        return pagoCreditoTotal;
    }
    /***
     * Metodo para obtener la tabla final de un credito en la reestructuracion de capital
     * @param numeroCredito
     * @param codigoIfip
     * @param tasa
     * @param baseCalculo
     * @return 
     */
    public List<TablaAmortizacionReestructuraCapital> getTablaReestructuraCapital(Long numeroCredito, Long codigoIfip, BigDecimal tasa, BigDecimal baseCalculo){
        try{
            Query query = this.em.createNamedQuery("CalculoCuotaPagar.findByNumeroCreditoAndCodigoIfip");
            query.setParameter("numeroCredito", numeroCredito);
            query.setParameter("codigoIfip", codigoIfip);
            List<CalculoCuotaPagar> calculoCuotaPagarLista = query.getResultList();
            List<TablaAmortizacionReestructuraCapital> tablaAmortizacionReestructuraCapital = null;
            if(calculoCuotaPagarLista.isEmpty()){
                
            }else{
                tablaAmortizacionReestructuraCapital = new ArrayList();
                for(int a=0; a<calculoCuotaPagarLista.size();a++){
                    query = this.em.createNamedQuery("TablaAmortizacion.findByNumeroCreditoAndCodigoIfipAndCuota");
                    query.setParameter("numeroCredito", calculoCuotaPagarLista.get(a).getCalculoCuotaPagarPK().getNumeroCredito());
                    query.setParameter("codigoIfip", calculoCuotaPagarLista.get(a).getCalculoCuotaPagarPK().getCodigoIfip());
                    query.setParameter("cuota", calculoCuotaPagarLista.get(a).getCalculoCuotaPagarPK().getCuota());
                    TablaAmortizacion cuotaTablaAmortizacion = (TablaAmortizacion) query.getResultList().get(0);
                    TablaAmortizacionReestructuraCapital cuotaTablaAmortizacionReestructuraCapital = new TablaAmortizacionReestructuraCapital();
                    cuotaTablaAmortizacionReestructuraCapital.setCapital(calculoCuotaPagarLista.get(a).getCapital());
                    cuotaTablaAmortizacionReestructuraCapital.setCuota(calculoCuotaPagarLista.get(a).getCalculoCuotaPagarPK().getCuota());
                    cuotaTablaAmortizacionReestructuraCapital.setFechaInicio(cuotaTablaAmortizacion.getFechaInicio());
                    cuotaTablaAmortizacionReestructuraCapital.setFechaPago(cuotaTablaAmortizacion.getFechaPago());
                    cuotaTablaAmortizacionReestructuraCapital.setSaldoCapital(calculoCuotaPagarLista.get(a).getSaldoCapital());
                    // Calculo de interes
                    long diferenciaEnMeses = cuotaTablaAmortizacionReestructuraCapital.getFechaPago().getTime() - cuotaTablaAmortizacionReestructuraCapital.getFechaInicio().getTime();
                    long diferenciaEnDias = diferenciaEnMeses / (1000 * 60 * 60 * 24);
                    BigDecimal interes = ( ( cuotaTablaAmortizacionReestructuraCapital.getSaldoCapital().multiply(( tasa.divide(new BigDecimal("100"),2, RoundingMode.HALF_UP) ) ) ).divide(baseCalculo,2, RoundingMode.HALF_UP) ).multiply(( new BigDecimal(diferenciaEnDias) ));
                    cuotaTablaAmortizacionReestructuraCapital.setInteres(interes);
                    cuotaTablaAmortizacionReestructuraCapital.setRubros(calculoCuotaPagarLista.get(a).getRubroActual());
                    BigDecimal total = cuotaTablaAmortizacionReestructuraCapital.getCapital().add(cuotaTablaAmortizacionReestructuraCapital.getInteres());
                    total = total.add(cuotaTablaAmortizacionReestructuraCapital.getRubros());
                    cuotaTablaAmortizacionReestructuraCapital.setTotalCuota(total);
                    tablaAmortizacionReestructuraCapital.add(cuotaTablaAmortizacionReestructuraCapital);
                }
            }
            return tablaAmortizacionReestructuraCapital;   
        }catch(Exception ex){
            logger.error("CLASE {}. METODO {}. ERROR {}.", "PagoCreditoFacade", "getTablaReestructuraCapital", ex.getMessage());
            return null;
        }
    }
}
