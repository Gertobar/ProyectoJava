/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.Solicitud;
import ec.renafipse.mks.modelo.creditos.SolicitudDevuelta;
import ec.renafipse.mks.modelo.creditos.SolicitudMotivoDevolucion;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import org.slf4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author vastudillo
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;
    private String error;
    private String errorSql;
    private String errorCode;
    private Logger logger;
    @EJB
    private SolicitudPreAprobadaNegadaFacade ejbSolicitudPreAprobadaNegadaFacade;
    @EJB
    private SolicitudAprobadaPendienteDocumentoFacade ejbSolicitudPendienteDocumentoFacade;
    @EJB
    private SolicitudDevueltaFacade ejbSolicitudDevueltaFacade;
    @EJB
    private SolicitudMotivoDevolucionFacade ejbSolicitudMotivoDevolucionFacade;
    @EJB
    private TablaAmortizacionFacade ejbFacadeTablaAmortizacion;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }

    /**
     * BUSCA LOS CREDITOS DE ACUERDO EL NUMERO Y CODIGO DE IFIP
     *
     * @param numero Numero de Credito
     * @param codigoIfip Codigo Ifip
     * @return Lista de Solicitudes
     */
    public List<Solicitud> getItemsNumeroCodigoIfip(Long numero, Long codigoIfip) {
        Query query = this.em.createNamedQuery(Solicitud.findByNumeroCodigoIfip);
        query.setParameter("numero", numero);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    
    /**
     * 
     * @param numeroIdentificacion
     * @param codigoIfip
     * @return 
     */
    public List<Solicitud> getItemsIdentificacionPersonaExterna(String numeroIdentificacion, Long codigoIfip) {
        Query query = this.em.createNamedQuery("Solicitud.findByIdentificacionPersonaExternaCodigoIfip", Solicitud.class);
        query.setParameter("numeroIdentificacion", numeroIdentificacion);
        query.setParameter("codigoIfip", codigoIfip);
        List<Solicitud> listado = query.getResultList();
        return listado;
    }

    /**
     *
     * @param codigoSocio
     * @param codigoIfip
     * @return
     */
    public List<Solicitud> getItemsCodigoSocioCodigoIfip(Long codigoSocio, Long codigoIfip) {
        Query query = this.em.createNamedQuery(Solicitud.findByCodigoSocioCodigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }

    /**
     *
     * @param nombreCompleto
     * @param codigoIfip
     * @return
     */
    public List<Solicitud> getItemsSolicitudNombreSocioCodigoIfip(String nombreCompleto, Long codigoIfip) {
        Query query = this.em.createNamedQuery(Solicitud.findByNombreSocioCodigoIfip);
        query.setParameter("nombreCompleto", nombreCompleto);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }

    /**
     * Obtiene las solicitudes de credito que se encuentren en estado 1
     * Solicitado
     *
     * @param numero
     * @param codigoIfip
     * @param estadoCredito
     * @return
     */
    public List<Solicitud> getItemsNumeroCodigoIfipEstado(Long numero, Long codigoIfip, Long estadoCredito) {
        Query query = this.em.createNamedQuery(Solicitud.findByNumeroCodigoIfipEstado);
        query.setParameter("numero", numero);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estadoCredito", estadoCredito);
        return query.getResultList();
    }
    /****
     * Metodo para obtener los creditos dependiente de los parametros de la fabrica
     * @param numero
     * @param codigoIfip
     * @param estadoCredito
     * @param codigoUsuario
     * @return 
     */
    public List<Solicitud> getItemsNumeroCodigoIfipEstadoFabrica(Long numero, Long codigoIfip, Long estadoCredito, Long codigoUsuario) {
        List<Solicitud> lista = null;
        Query query = em.createNamedQuery(Solicitud.findCreditoByPerfilFabricaCredito,Solicitud.class);
        query.setParameter("numero", numero);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoEstadoCredito", estadoCredito);
        query.setParameter("codigoUsuario", codigoUsuario);
        if (!query.getResultList().isEmpty()) {
          List<Solicitud> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }

    /**
     *
     * @param codigoSocio
     * @param codigoIfip
     * @param estadoCredito
     * @return
     */
    public List<Solicitud> getItemsCodigoSocioCodigoIfipEstado(Long codigoSocio, Long codigoIfip, Long estadoCredito) {
        Query query = this.em.createNamedQuery(Solicitud.findByCodigoSocioCodigoIfipEstado);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estadoCredito", estadoCredito);
        return query.getResultList();
    }

    public List<Solicitud> getItemsSolicitudCodigoIfip(Long codigoIfip, Long estadoCredito, Long codigoIfipAgencia) {
        Query query = this.em.createNamedQuery(Solicitud.findByCodigoIfipEstadoCredito);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estadoCredito", estadoCredito);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }
    /***
     * Metodo para obtener todas las solicitudes segun el perfil de fabrica de credito
     * @param codigoIfip
     * @param estadoCredito
     * @param codigoUsuario
     * @return 
     */
    public List<Solicitud> getItemsSolicitudCodigoIfipFabrica(Long codigoIfip, Long codigoIfipAgencia, Long estadoCredito, Long codigoUsuario) {
        List<Solicitud> lista = null;
        Query query = em.createNamedQuery(Solicitud.findByPerfilFabricaCredito,Solicitud.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("codigoEstadoCredito", estadoCredito);
        query.setParameter("codigoUsuario", codigoUsuario);
        if (!query.getResultList().isEmpty()) {
            List<Solicitud> resultado = query.getResultList();
            if (resultado.size() > 0)
                lista = resultado;
            //Obtener los datos de devolucion
            for(int a=0; a<lista.size();a++){
                //Obtener la devolucion de solicitud
                SolicitudDevuelta solicitudDevuelta = ejbSolicitudDevueltaFacade.getPorNumeroCredito(lista.get(a).getSolicitudPK().getNumero(),lista.get(a).getSolicitudPK().getCodigoIfip());
                if(solicitudDevuelta != null){
                    //Obtener el motivo de devolucion
                    SolicitudMotivoDevolucion solicitudMotivoDevolucion = ejbSolicitudMotivoDevolucionFacade.getMotivoPorCodigo(solicitudDevuelta.getCodigoSolicitudMotivoDev());
                    //Pasa a la solicitud los valores
                    lista.get(a).setObservacionesDevolucion(solicitudDevuelta.getObservaciones());
                    lista.get(a).setSolicitudMotivoDevolucion(solicitudMotivoDevolucion);
                }
            }
        }
        return lista;
    }
    
    public List<Solicitud> getItemsSolicitudCodigoIfipAll(Long codigoIfip, Long estadoCredito) {
        Query query = this.em.createNamedQuery(Solicitud.findByCodigoIfipEstadoCreditoAll);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("estadoCredito", estadoCredito);
        return query.getResultList();
    }
    /***
     * Metodo para obtener todas las solicitudes de acuerdo al perfil de la Fabrica de Credito
     * @param codigoIfip
     * @param estadoCredito
     * @param codigoUsuario
     * @return 
     */
    public List<Solicitud> getItemsSolicitudCodigoIfipAllFabrica(Long codigoIfip, Long estadoCredito, Long codigoUsuario) {
        List<Solicitud> lista = null;
        Query query = em.createNamedQuery(Solicitud.findByPerfilFabricaCreditoAll,Solicitud.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoEstadoCredito", estadoCredito);
        query.setParameter("codigoUsuario", codigoUsuario);
        if (!query.getResultList().isEmpty()) {
          List<Solicitud> resultado = query.getResultList();
          if (resultado.size() > 0){
            //Llenar las fechas de entrega a aprobacion 
            lista = resultado;
          }
          //Colocar las observacione para los creditos pendientes documentos para conceder
            for(int a=0; a<lista.size(); a++){
                Solicitud solicitud = (Solicitud)lista.get(a);
                solicitud.setObservacionPendienteParaConceder(ejbSolicitudPendienteDocumentoFacade.getObservacionesByNumeroCredito(solicitud.getSolicitudPK().getNumero(),solicitud.getSolicitudPK().getCodigoIfip()));
            }
        }
        return lista;
    }
    
    /***
     * Metodo para obtener todas las solicitudes de acuerdo al perfil de la Fabrica de Credito
     * @param codigoIfip
     * @param estadoCredito
     * @param codigoUsuario
     * @return 
     */
    public List<Solicitud> getItemsSolicitudCodigoIfipAllFabricaParaAprobar(Long codigoIfip, Long estadoCredito, Long codigoUsuario) {
        List<Solicitud> lista = null;
        Query query = em.createNamedQuery(Solicitud.findByPerfilFabricaCreditoAllParaAprobar,Solicitud.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoEstadoCredito", estadoCredito);
        query.setParameter("codigoUsuario", codigoUsuario);
        if (!query.getResultList().isEmpty()) {
            List<Solicitud> resultado = query.getResultList();
            if (resultado.size() > 0){
                lista = resultado;
            }
            for(int a=0;a<lista.size();a++){
                Solicitud solicitud = (Solicitud)lista.get(a);
                solicitud.setObservacionPreAprobacion(ejbSolicitudPreAprobadaNegadaFacade.getObservacionesByNumeroCredito(solicitud.getSolicitudPK().getNumero(),solicitud.getSolicitudPK().getCodigoIfip()));
                solicitud.setPlazoMeses((ejbFacadeTablaAmortizacion.getPlazoCreditoIfip(solicitud.getSolicitudPK().getNumero(),solicitud.getSolicitudPK().getCodigoIfip()))/30);
           }
        }
        return lista;
    }

    //Pago Credito
    /**
     *
     * @param codigoSocio
     * @param codigoIfip
     * @param codigoEstado
     * @return
     */
    public List<Solicitud> getItemsSolicitudCodigoSocioCodigoIfip(Long codigoSocio, Long codigoIfip, Long codigoEstado) {
        Query query = this.em.createNamedQuery(Solicitud.findBySolicitudCodigoSocioCodigoIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoEstado", codigoEstado);
        return query.getResultList();
    }

    /**
     *
     * @param nombreCompleto
     * @param codigoIfip
     * @param codigoEstado
     * @return
     */
    public List<Solicitud> getItemsSolicitudNombreSocioCodigoIfip(String nombreCompleto, Long codigoIfip, Long codigoEstado) {
        Query query = this.em.createNamedQuery(Solicitud.findBySolicitudNombreSocioCodigoIfip);
        query.setParameter("nombreCompleto", nombreCompleto);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoEstado", codigoEstado);
        return query.getResultList();
    }
    
    /***
     * Metodo para validar los indicadores en solicitud de credito
     * @param codigoPersona
     * @param cuotaMaxima
     * @param codigoPeriodicidad
     * @param montoCredito
     * @param codigoProductoCredito
     * @return 
     */
    public String validaIndicadorSolicitud(Long codigoPersona, BigDecimal cuotaMaxima, Long codigoPeriodicidad, BigDecimal montoCredito, Long codigoProductoCredito){
        String mensaje;
        error = null;
        errorSql = null;
        errorCode = null;
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = em.createNamedStoredProcedureQuery( Solicitud.validaIndicadorSolicitud );
        storedProcedureQuery.setParameter( "pt_codigo_persona", codigoPersona);
        storedProcedureQuery.setParameter( "pt_cuota_maxima", cuotaMaxima);
        storedProcedureQuery.setParameter( "pt_codigo_periodicidad", codigoPeriodicidad);
        storedProcedureQuery.setParameter( "pt_monto_credito", montoCredito);
        storedProcedureQuery.setParameter( "pt_codigo_producto_credito", codigoProductoCredito);
        storedProcedureQuery.execute();
        mensaje = (String) storedProcedureQuery.getOutputParameterValue("pv_mensaje");
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) {
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
            mensaje = null;
        } 
        return mensaje;
    }
    public String validaValorCertificado(Long numeroCredito, Long codigoIfip){
        String mensaje;
        error = null;
        errorSql = null;
        errorCode = null;
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = em.createNamedStoredProcedureQuery( Solicitud.validaValorCertificado );
        storedProcedureQuery.setParameter( "pt_numero_credito", numeroCredito);
        storedProcedureQuery.setParameter( "pt_codigo_ifip", codigoIfip);
        storedProcedureQuery.execute();
        
        mensaje = (String) storedProcedureQuery.getOutputParameterValue("pv_mensaje");
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) {
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
            mensaje = null;
        } 
        return mensaje;
    }
    /***
     * Metodo para validar la base de ahorro
     * @param codigoSocio
     * @param codigoIfip
     * @param codigoProductoCredito
     * @param montoCredito
     * @return 
     */
    public String validaBaseAhorro(long codigoSocio, long codigoIfip, long codigoProductoCredito, BigDecimal montoCredito){
        String mensaje;
        error = null;
        errorSql = null;
        errorCode = null;
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = em.createNamedStoredProcedureQuery( "Solicitud.validaBaseAhorro" );
        storedProcedureQuery.setParameter( "pt_codigo_socio", codigoSocio);
        storedProcedureQuery.setParameter( "pt_codigo_ifip", codigoIfip);
        storedProcedureQuery.setParameter( "pt_codigo_producto_credito", codigoProductoCredito);
        storedProcedureQuery.setParameter( "pt_monto_credito", montoCredito);
        storedProcedureQuery.execute();
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        return error;
    }
}
