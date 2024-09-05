/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.ConocimientoIfip;
import ec.renafipse.mks.modelo.socios.EstadoSocio;
import ec.renafipse.mks.modelo.socios.MotivoSocio;
import ec.renafipse.mks.modelo.socios.Persona;
import ec.renafipse.mks.modelo.socios.Socio;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 */
@Stateless
public class SocioFacade extends AbstractFacade<Socio> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioFacade() {
        super(Socio.class);
    }

    // --------------------------------------------------------------------------
    // -- METODOS PERSONALIZADOS
    /**
     *
     * @param criterio
     * @param buscarPor
     * @param codigoIfip
     * @return
     */
    public List<Socio> getItemsSocio(String criterio, String buscarPor, Long codigoIfip) {
        List<Socio> listSocios = null;

        if (buscarPor.equals("N")) {
            Query query = this.em.createNamedQuery(Socio.findByNombres);
            query.setParameter("nombreCompleto", criterio.toUpperCase());
            query.setParameter("codigoIfip", codigoIfip);
            listSocios = query.getResultList();
        }

        if (buscarPor.equals("I")) {
            Query query = this.em.createNamedQuery(Socio.findByIdentificacion);
            query.setParameter("identificacion", criterio);
            query.setParameter("codigoIfip", codigoIfip);
            listSocios = query.getResultList();
        }

        return listSocios;
    }

    /**
     * BUSCA LAS PERSONAS MEDIANTE EL NOMBRE Y CODIGO DE LA IFIP DE ACUERDO A SU
     * ESTADO INDICA ACTIVO
     *
     * @param nombreCompleto
     * @param codigoIfip
     * @param indicaActivo
     * @return
     */
    public List<Socio> getItemsNombresIndicaActivo(String nombreCompleto, Long codigoIfip, char indicaActivo) {
        Query query = this.em.createNamedQuery(Socio.findByNombresIndicaActivo);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("indicaActivo", indicaActivo);
        return query.getResultList();
    }

    public Long getSecuenciaCodigoSocio(Long codigoIfip) {
        Query query = em.createNamedQuery(Socio.getCodigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        List<Object> listCodigoSocio = query.getResultList();

        if (listCodigoSocio == null) {
            return Long.parseLong("1");
        }

        int codigoSocio = Integer.parseInt((listCodigoSocio.get(0) == null) ? "0" : listCodigoSocio.get(0).toString());
        codigoSocio++;
        return Long.parseLong(String.valueOf(codigoSocio));

    }

    /**
     *
     * @param codigoIfip
     * @return
     */
    public Long getSecuenciaCodigoArchivo(Long codigoIfip) {
        Query query = em.createNamedQuery(Socio.getCodigoArchivo);
        query.setParameter("codigoIfip", codigoIfip);
        List<Object> listCodigoArchivo = query.getResultList();
        // ////System.out.println("Tamaño listCodigoArchivo "+listCodigoArchivo.size());

        if (listCodigoArchivo == null) {
            return Long.parseLong("1");
        }

        int codigoArchivo = Integer.parseInt((listCodigoArchivo.get(0) == null) ? "0" : listCodigoArchivo.get(0).toString());
        codigoArchivo++;
        return Long.parseLong(String.valueOf(codigoArchivo));
    }

    /**
     *
     * @param codigoSocio
     * @param codigoIfip
     * @return
     */
    public List<Socio> getItemsSocioCodigo(Long codigoSocio, Long codigoIfip) {
        Query query = this.em.createNamedQuery(Socio.findByCodigoSocioIfip);
        query.setParameter("codigoSocio", codigoSocio);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }

//
    public List<Socio> getItemsSociofindByIdentificacion(String identificacion) {
        Query query = this.em.createNamedQuery(Socio.findByIdPersona);
        query.setParameter("identificacion", identificacion);
        return query.getResultList();
    }

    public List<Socio> getItemsSociofindByIdIfip(String identificacion, Long codigoIfip) {
        Query query = this.em.createNamedQuery(Socio.findByIdIfip);
        query.setParameter("identificacion", identificacion);
        query.setParameter("codigoIfip", codigoIfip);

        return query.getResultList();
    }

    public List<Socio> getItemsSociofindByIdIfipAgencia(String identificacion, Long codigoIfip, Long codigoIfipAgencia) {
        Query query = this.em.createNamedQuery(Socio.findByIdIfipAgencia);
        query.setParameter("identificacion", identificacion);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }

    public List<Socio> getItemsSociofindByIdIfipAgenciaIndicaActivo(String identificacion, Long codigoIfip, Long codigoIfipAgencia, char indicaActivo) {
        Query query = this.em.createNamedQuery(Socio.findByIdIfipAgenciaIndicaActivo);
        query.setParameter("identificacion", identificacion);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        query.setParameter("indicaActivo", indicaActivo);
        return query.getResultList();
    }

    //-------------------------------------------------------------------------------------
    /**
     * Actualiza el Socio
     * @param codigoPersona Codigo de la Personas
     * @param codigoMotivoSocio Motivo por lo que se hizo socio
     * @param codigoConocimientoIfip Como conocio la Ifip
     * @param codigoEstadoSocio Estado del Socio
     * @param entregoRequesitios Si entrego o no los requisitos de servicios basicos
     * @param observaciones Observaciones del Socio
     * @param fechaActualizacion Fecha de Actualizacion
     * @param codigo Codigo del Socio
     * @param codigoIfip  Codigo de la Ifip
     */
    @Transactional
    public void actualiza(Persona codigoPersona, MotivoSocio codigoMotivoSocio, ConocimientoIfip codigoConocimientoIfip, EstadoSocio codigoEstadoSocio, char entregoRequesitios, String observaciones, Date fechaActualizacion, long codigo, long codigoIfip) {
        Query query = em.createQuery("UPDATE Socio c "
                + "SET codigoPersona = :codigoPersona, \n"
                + "codigoMotivoSocio = :codigoMotivoSocio, \n"
                + "codigoConocimientoIfip = :codigoConocimientoIfip, \n"
                + "codigoEstadoSocio = :codigoEstadoSocio, \n"
                + "entregoRequesitios = :entregoRequesitios, \n"
                + "observaciones = :observaciones, \n"
                + "fechaActualizacion = :fechaActualizacion \n"
                + "WHERE c.socioPK.codigo = :codigo \n"
                + "AND c.socioPK.codigoIfip = :codigoIfip");
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoMotivoSocio", codigoMotivoSocio);
        query.setParameter("codigoConocimientoIfip", codigoConocimientoIfip);
        query.setParameter("codigoEstadoSocio", codigoEstadoSocio);        
        query.setParameter("entregoRequesitios", entregoRequesitios);
        query.setParameter("observaciones", observaciones);
        query.setParameter("fechaActualizacion", fechaActualizacion, TemporalType.TIMESTAMP);
        query.setParameter("codigo", codigo);
        query.setParameter("codigoIfip", codigoIfip);
        query.executeUpdate();

    }
    // -- FIN DE METODOS PERSONALIZADOS
    // --------------------------------------------------------------------------
    
    /**
     * 
     * @param identificacion
     * @return 
     */
    public Socio getSocioPorIdentificacionPersona(String identificacion){
        List<Socio> lista = null;
        Socio Socio = null;
        Query query = em.createNamedQuery("Socio.findByIdPersona", Socio.class);
        query.setParameter("identificacion", identificacion);
        if (!query.getResultList().isEmpty()) {
          List<Socio> resultado = query.getResultList();
          if (resultado.size() > 0) {
              if (resultado.size() == 1){
                lista = resultado;
              }
          }
        }
        if (lista != null) {
          Socio = (Socio)lista.get(0);
        }
        return Socio;
    }
    
     /**
     * 
     * @param codigo
     * @return 
     */
    public Socio getSocioPorCodigo(Long codigo){
        List<Socio> lista = null;
        Socio Socio = null;
        Query query = em.createNamedQuery("Socio.findByCodigo", Socio.class);
        query.setParameter("codigo", codigo);
        if (!query.getResultList().isEmpty()) {
          List<Socio> resultado = query.getResultList();
          if (resultado.size() > 0) {
              if (resultado.size() == 1){
                lista = resultado;
              }
          }
        }
        if (lista != null) {
          Socio = (Socio)lista.get(0);
        }
        return Socio;
    }
    
    /**
     * Retorna solo socios activos de la misma agencia que se encuentra logueado el usuario
     * @param nombreCompleto
     * @param codigoIfip
     * @param indicaActivo
     * @param codigoIfipAgencia
     * @return 
     */
    public List<Socio> getItemsNombresIndicaActivoAgencia(String nombreCompleto, Long codigoIfip, char indicaActivo, Long codigoIfipAgencia) {
        Query query = this.em.createNamedQuery("Socio.findByNombresIndicaActivoAgencia", Socio.class);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("indicaActivo", indicaActivo);
        query.setParameter("codigoIfipAgencia", codigoIfipAgencia);
        return query.getResultList();
    }
    
    /**
     * 
     * @param identificacion
     * @param codigoIfip
     * @param indicaActivo
     * @return 
     */
    public List<Socio> getItemsIdentificacionIndicaActivo(String identificacion, Long codigoIfip, char indicaActivo) {
        Query query = this.em.createNamedQuery("Socio.findByIdIndicaActivo", Socio.class);
        query.setParameter("identificacion", identificacion);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("indicaActivo", indicaActivo);
        return query.getResultList();
    }
}
