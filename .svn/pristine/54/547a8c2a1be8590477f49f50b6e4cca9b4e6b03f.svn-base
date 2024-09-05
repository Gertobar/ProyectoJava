/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos.lineacredito;

import ec.renafipse.mks.modelo.creditos.lineacredito.LineaCreditoSolicitud;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andres
 */
@Stateless
public class LineaCreditoSolicitudFacade extends AbstractFacade<LineaCreditoSolicitud> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;    
    
    private String error = null;
    private String errorSql = null;
    private String errorCode = null;
    private Logger logger;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaCreditoSolicitudFacade() {
        super(LineaCreditoSolicitud.class);
        logger = LoggerFactory.getLogger(this.getClass());
    }
    
    /**
     * Devuelve el listado de todas las solicitudes de linea por identificación del socio
     * @param identificacion
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPorIdentificacionSocio(String identificacion){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByIdentificacionSocio", LineaCreditoSolicitud.class);
        query.setParameter("identificacion", identificacion);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /**
     * Devuelve el listado de todas las solicitudes de linea por código del socio
     * @param codigoSocio
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPorCodigoSocio(Long codigoSocio){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigoSocio", LineaCreditoSolicitud.class);
        query.setParameter("codigoSocio", codigoSocio);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /**
     * Devuelve en una lista una solicitud de linea de crédito por su código
     * @param codigo
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPorCodigo(Long codigo){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigo", LineaCreditoSolicitud.class);
        query.setParameter("codigo", codigo);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /**
     * Devulve el listado de solicitudes de linea de crédito por código de socio y código de estado de solicitud
     * @param codigoSocio
     * @param codigoEstado
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPorCodigoSocioYCodigoEstado(Long codigoSocio, Long codigoEstado){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigoSocioEstadoSolicitud", LineaCreditoSolicitud.class);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoEstado", codigoEstado);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /**
     * Devulve en una lista la solicitud de linea de crédito por código para la aplicacion de cambio de estado
     * @param codigo
     * @param codigoEstado
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudParaCambioEstado(Long codigo){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigoSocioParaCambioEstado", LineaCreditoSolicitud.class);
        query.setParameter("codigoSocio", codigo);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /**
     * Devuelve el listado de las solicitudes de socio en estado aprobadas y que no tienen avance vigente
     * solicitudes en la cuales se permite crear avances de linea
     * @param codigoSocio
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaAprobadasSinAvanceVigente(Long codigoSocio){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findAprobadasSinAvanceVigente", LineaCreditoSolicitud.class);
        query.setParameter("codigoSocio", codigoSocio);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /**
     * Devuelve en una lista una solicitud de linea de crédito por su código validando un listado de codigos de estados
     * @param codigo
     * @param listaParametrosEstado
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPorCodigoListaCodigoEstado(Long codigo, List<Long> listaParametrosEstado){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigoListaCodigoEstado", LineaCreditoSolicitud.class);
        query.setParameter("codigo", codigo);
        query.setParameter("codigosEstado", listaParametrosEstado);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /**
     * Devuelve en una lista una solicitud de linea de crédito por su código validando un listado de codigos de estados y el codigo de la agencia
     * @param codigo
     * @param listaParametrosEstado
     * @param codigoAgencia
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPorCodigoListaCodigoEstadoCodigoAgencia(Long codigo, List<Long> listaParametrosEstado, Long codigoAgencia){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigoListaCodigoEstadoCodigoAgencia", LineaCreditoSolicitud.class);
        query.setParameter("codigo", codigo);
        query.setParameter("codigosEstado", listaParametrosEstado);
        query.setParameter("codigoAgencia", codigoAgencia);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /**
     * Devulve el listado de solicitudes de linea de crédito por código de socio y validando un listado de código de estado
     * @param codigoSocio
     * @param listaParametrosEstado
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPorCodigoSocioListaCodigoEstado(Long codigoSocio, List<Long> listaParametrosEstado){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigoSocioListaCodigoEstado", LineaCreditoSolicitud.class);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigosEstado", listaParametrosEstado);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }

     /**
     * 
     * @param lineaCreditoSolicitud
     */
    @Transactional
    public void actualiza(LineaCreditoSolicitud lineaCreditoSolicitud) {
        Query query = em.createQuery("UPDATE LineaCreditoSolicitud l "
                                    + "SET codigoActEco = :codigoActEco, "
                                    + "codigoLineaCreditoEstadoSol = :codigoLineaCreditoEstadoSol, "
                                    + "codigoUbicacionGeografica = :codigoUbicacionGeografica, "
                                    + "montoLineaCredito = :montoLineaCredito, "
                                    + "diaPago= :diaPago, "
                                    + "diaEstadoCuenta= :diaEstadoCuenta, "
                                    + "fechaActualizacion= :fechaActualizacion, "
                                    + "codigoLineaCredito= :codigoLineaCredito, "
                                    + "codigoDestinoFinanciero= :codigoDestinoFinanciero, "
                                    + "codigoDestinoEspecifico= :codigoDestinoEspecifico, "
                                    + "porcentajePagoMinimo= :porcentajePagoMinimo, "
                                    + "codigoCuentaAcreditada= :codigoCuentaAcreditada, "
                                    + "codigoCuentaDebito= :codigoCuentaDebito, "
                                    + "observaciones= :observaciones "
                                    + "WHERE codigo = :codigo"
        );
        query.setParameter("codigoActEco", lineaCreditoSolicitud.getCodigoActEco());
        query.setParameter("codigoLineaCreditoEstadoSol", lineaCreditoSolicitud.getCodigoLineaCreditoEstadoSol());
        query.setParameter("codigoUbicacionGeografica", lineaCreditoSolicitud.getCodigoUbicacionGeografica());
        query.setParameter("montoLineaCredito", lineaCreditoSolicitud.getMontoLineaCredito());
        query.setParameter("diaPago", lineaCreditoSolicitud.getDiaPago());
        query.setParameter("diaEstadoCuenta", lineaCreditoSolicitud.getDiaEstadoCuenta());
        query.setParameter("fechaActualizacion", lineaCreditoSolicitud.getFechaActualizacion());
        query.setParameter("codigoLineaCredito", lineaCreditoSolicitud.getCodigoLineaCredito());
        query.setParameter("codigoDestinoFinanciero", lineaCreditoSolicitud.getCodigoDestinoFinanciero());
        query.setParameter("codigoDestinoEspecifico", lineaCreditoSolicitud.getCodigoDestinoEspecifico());
        query.setParameter("porcentajePagoMinimo", lineaCreditoSolicitud.getPorcentajePagoMinimo());
        query.setParameter("codigoCuentaAcreditada", lineaCreditoSolicitud.getCodigoCuentaAcreditada());
        query.setParameter("codigoCuentaDebito", lineaCreditoSolicitud.getCodigoCuentaDebito());
        query.setParameter("observaciones", lineaCreditoSolicitud.getObservaciones());
        query.setParameter("codigo", lineaCreditoSolicitud.getCodigo());
        query.executeUpdate();
    }

    /**
     * Devuelve listado de solicitudes de linea de crédito por código de agencia validando una lista de codigos de estados
     * @param codigoAgencia
     * @param listaParametrosEstado
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPorCodigoAgenciaListaCodigoEstado(Long codigoAgencia, List<Long> listaParametrosEstado){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigoAgenciaListaCodigoEstado", LineaCreditoSolicitud.class);
        query.setParameter("codigoAgencia", codigoAgencia);
        query.setParameter("codigosEstado", listaParametrosEstado);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
        
    /**
     * 
     * @param codigoSocio
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudPendientePagoPorCodigoSocio(Long codigoSocio){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findPendientePagoByCodigoSocioListaCodigoEstado", LineaCreditoSolicitud.class);
        query.setParameter("codigoSocio", codigoSocio);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
     
    
    /**
     * 
     * @param codigoSocio
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoAvancePendientePagoPorCodigoSocio(Long codigoSocio) {
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findPendientePagoAvanceByCodigoSocioListaCodigoEstado", LineaCreditoSolicitud.class);
        query.setParameter("codigoSocio", codigoSocio);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    /***
     * Metodo para  r los firmantes de una solicitud de linea de credito
     * @param codigoLineaCreditoSolicitud
     * @return 
     */
    public boolean guardaFirmante(Long codigoLineaCreditoSolicitud){
        // variables de error 
        error = null;
        errorSql = null;
        errorCode = null;
        
        StoredProcedureQuery storedProcedureQuery;
        
        storedProcedureQuery = em.createNamedStoredProcedureQuery( LineaCreditoSolicitud.guardaFirmante );
        storedProcedureQuery.setParameter( "pt_codigo_linea_credito_sol", codigoLineaCreditoSolicitud);
        storedProcedureQuery.execute();        
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) {
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
            return false;
        } 
        return true;
    }
    /***
     * Metodo para obtener las solicitudes de linea de credito en tramite para su aprobacion o negacion
     * @param codigoSocio
     * @return 
     */
    public List<LineaCreditoSolicitud> getSolicitudesEnProceso(Long codigoSocio) {
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery(LineaCreditoSolicitud.findByCodigoEstado,LineaCreditoSolicitud.class);
        query.setParameter("codigoSocio", codigoSocio);
        if (!query.getResultList().isEmpty()) {
            List<LineaCreditoSolicitud> resultado = query.getResultList();
            if (resultado.size() > 0){
                lista = resultado;
            }
        }
        return lista;
    }
    /***
     * Metodo para obtener el saldo ocupado de una linea de credito
     * @param codigoLineaCreditoSolicitud
     * @return 
     */
    public BigDecimal getSaldoOcupadoLineaCreditoSolicitud(Long codigoLineaCreditoSolicitud){        
        try{
            // variables de error 
            error = null;
            errorSql = null;
            errorCode = null;
            BigDecimal saldo = BigDecimal.ZERO;
            // llamar al procedimiento
            StoredProcedureQuery storedProcedureQuery;

            storedProcedureQuery = em.createNamedStoredProcedureQuery( "LineaCreditoSolicitud.obtieneSaldoOcupadoLinea" );
            storedProcedureQuery.setParameter( "pt_codigo_linea_credito_sol", codigoLineaCreditoSolicitud);
            storedProcedureQuery.execute();  
            saldo = (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_saldo_capital");
            errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
            errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
            error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
            if (error != null) {
                logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
                return BigDecimal.ZERO;
            } 
            return saldo;
        }catch(Exception ex){
            logger.error("CLASE {}. METODO {}. ERROR {}.", "LineaCreditoSolicitudFacade", "getSaldoOcupadoLineaCreditoSolicitud", ex.getMessage());
            return BigDecimal.ZERO;
        }
    }
    /***
     * Metodo para obtener las solicitud de linea de credito x agencia y estado
     * @param codigoEstado
     * @param codigoIfipAgencia
     * @return 
     */
    public List<LineaCreditoSolicitud> getListaLineaCreditoSolicitudByEstadoAndAgencia(long codigoEstado, String codigoIfipAgencia){
        List<LineaCreditoSolicitud> lista = null;
        Query query = em.createNamedQuery("LineaCreditoSolicitud.findByCodigoAgenciaListaCodigoEstado", LineaCreditoSolicitud.class);
        query.setParameter("codigoAgencia", codigoIfipAgencia);
        query.setParameter("codigosEstado", codigoEstado);
        if (!query.getResultList().isEmpty()) {
          List<LineaCreditoSolicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
    /***
     * Metodo para obtener el monto final de una solicitud de avance
     * @param codigoLineaCreditoSolicitud
     * @param montoSolicitado
     * @return 
     */
    public BigDecimal getMontoFinalAvance(Long codigoLineaCreditoSolicitud, BigDecimal montoSolicitado){
        // variables de error 
        String error = null;
        String errorSql = null;
        String errorCode = null;
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = em.createNamedStoredProcedureQuery( "LineaCreditoSolicitud.obtieneMontoFinalAvance" ); 
        storedProcedureQuery.setParameter( "pt_codigo_linea_credito_sol", codigoLineaCreditoSolicitud);
        storedProcedureQuery.setParameter( "pt_monto_solicitado", montoSolicitado);
        storedProcedureQuery.execute();
        BigDecimal montoFinal = (BigDecimal) storedProcedureQuery.getOutputParameterValue("pt_monto_final");
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) {
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
            return BigDecimal.valueOf(-1);
        } 
        return montoFinal;
    }
}
