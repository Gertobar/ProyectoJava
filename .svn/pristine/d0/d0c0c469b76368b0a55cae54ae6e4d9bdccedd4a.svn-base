/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.PersonaConyuge;
import ec.renafipse.mks.modelo.socios.PersonaNatural;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.slf4j.Logger;
import java.sql.ResultSet;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 */
@Stateless
public class PersonaConyugeFacade extends AbstractFacade<PersonaConyuge> {

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

    public PersonaConyugeFacade() {
        super(PersonaConyuge.class);
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Obtiene los conyuges de una persona
     *
     * @param codigoPersona
     * @return Lista de Conyuges
     */
    public List<PersonaConyuge> getItemsCodigoPersona(Long codigoPersona) {
        Query query = this.em.createNamedQuery(PersonaConyuge.findByCodigoPersona);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();

    }

    public List<PersonaConyuge> getItemsCodigoPersonaYcodigoConyuge(Long codigoPersona, Long codigoPersonaConyuge) {
        Query query = this.em.createNamedQuery(PersonaConyuge.findByCodigoPersonaYcodigoConyuge);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoPersonaConyuge", codigoPersonaConyuge);
        return query.getResultList();

    }

    /**
     * BUSCA CONYUGUE DE SOCIO
     *
     * @param nombreCompleto
     * @param codigoPersonaConyuge
     * @param codigoEstado
     * @param eliminado
     * @return
     */
    public List<PersonaConyuge> getItemsCasadoCodigoConyugue(String nombreCompleto, Long codigoPersonaConyuge, Long codigoEstado, char eliminado) {
        Query query = this.em.createNamedQuery(PersonaConyuge.findByCasadoCodigoPersonaConyuge);
        query.setParameter("codigoPersonaConyuge", codigoPersonaConyuge);
        query.setParameter("codigoEstado", codigoEstado);
        query.setParameter("nombreCompleto", nombreCompleto.toUpperCase());
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }

    /**
     * Obtiene el conyuge actual No eliminado
     *
     * @param codigoPersona
     * @param eliminado
     * @return Conyuge
     */
    public PersonaConyuge getPersonaCodigoFechaRegistro(Long codigoPersona, char eliminado) {
        Query query = this.em.createNamedQuery(PersonaConyuge.findByCodigoPersonaFechaRegistro);
        query.setMaxResults(1);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("eliminado", eliminado);
//        try {
//            PersonaConyuge personaConyuge = (PersonaConyuge) query.getSingleResult();
//            return personaConyuge == null ? null : personaConyuge;
//        } catch (NoResultException e) {
//            return null;
//        }
        List results = query.getResultList();
        if (!results.isEmpty()) {
            return (PersonaConyuge) results.get(0);
        } else {
            return null;
        }

    }

    public PersonaConyuge getPersonaYconyuge(Long codigoPersona, Long codigoPersonaConyuge) {
        Query query = this.em.createNamedQuery(PersonaConyuge.findByCodigoPersonaYconyuge);
        query.setMaxResults(1);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoPersonaConyuge", codigoPersonaConyuge);
//        try {
//            PersonaConyuge personaConyuge = (PersonaConyuge) query.getSingleResult();
//            return personaConyuge == null ? null : personaConyuge;
//        } catch (NoResultException e) {
//            return null;
//        }
        List results = query.getResultList();
        if (!results.isEmpty()) {
            return (PersonaConyuge) results.get(0);
        } else {
            return null;
        }

    }

    /**
     * Obtiene los conyuges de una persona y por el estado eliminado
     *
     * @param codigoPersona Codigo de la Persona
     * @param eliminado Eliminado S=Si N=No
     * @return Lista de Conyuges
     */
    public List<PersonaConyuge> getItemsCodigoPersonaElminado(Long codigoPersona, char eliminado) {
        Query query = this.em.createNamedQuery(PersonaConyuge.findByCodigoPersonaElminado);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }

    public List<PersonaConyuge> getItemsCodigoPersonaConyugeElminado(Long codigoPersonaConyuge, char eliminado) {
        Query query = this.em.createNamedQuery(PersonaConyuge.findByCodigoPersonaConyugeEliminado);
        query.setParameter("codigoPersonaConyuge", codigoPersonaConyuge);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    //-------------------------------------------------------------------------------------
    /**
     * Actualiza la relacion conyuge persona    
     * @param codigoPersonaConyuge Persona Conyuge
     * @param firma S o N Firma
     * @param eliminado S o N eliminado
     * @param codigoPersona Persona
     */
    @Transactional
    public void actualiza(PersonaNatural codigoPersonaConyuge, char firma, char eliminado,  PersonaNatural codigoPersona) {
        Query query = em.createQuery("UPDATE PersonaConyuge c "
                + "SET personaConyugePK.codigoPersonaConyuge  = :codigoPersonaConyuge, \n"
                + "firma = :firma, \n"
                + "eliminado = :eliminado \n"
                + "WHERE c.personaNatural.codigoPersona = :codigoPersona");
        query.setParameter("codigoPersonaConyuge", codigoPersonaConyuge.getCodigoPersona());
        query.setParameter("firma", firma);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoPersona", codigoPersona.getCodigoPersona());
        query.executeUpdate();
    }
    
    /***
     * Metodo para guardar el conyuge de una persona
     * @param codigoPersona
     * @param codigoPersonaConyuge
     * @return 
     */
    public boolean guardaConyuge(Long codigoPersona, Long codigoPersonaConyuge){
        // variables de error 
        error = null;
        errorSql = null;
        errorCode = null;
        // variable de salida
        boolean ejecutoCorrectamente = false;
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = em.createNamedStoredProcedureQuery( PersonaConyuge.insertaConyuge );
        storedProcedureQuery.setParameter( "pt_codigo_persona", codigoPersona );
        storedProcedureQuery.setParameter( "pt_codigo_persona_conyuge", codigoPersonaConyuge );
        storedProcedureQuery.execute();
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) 
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
        else
            ejecutoCorrectamente = true;
        return ejecutoCorrectamente;
    }

    /**
     * Metodo para validar los conyuges
     * @param codigoPersona
     * @param codigoPersonaConyuge
     * @return 
     */
    public String validaConyuge(Long codigoPersona, Long codigoPersonaConyuge){
        String mensaje = null;
        //Obtener los conyuges de la persona conyuge
        List<PersonaConyuge> listaConyugesPersonaConyuge = getItemsCodigoPersonaElminado(codigoPersonaConyuge,'N');
        //Obteenr los conyuges de la persona conyuge como conyuge de otra persona
        List<PersonaConyuge> listaConyugesPersonaConyuge2 = getItemsCodigoPersonaConyugeElminado(codigoPersonaConyuge,'N');
        // Validar
        if(!listaConyugesPersonaConyuge.isEmpty()){
            if(listaConyugesPersonaConyuge.size()>1){
                mensaje = "La persona "+listaConyugesPersonaConyuge.get(0).getPersonaNatural().getPersona().getNombreCompleto()+" tiene registrado mas de un conyuge.";
                return mensaje;
            }
            if(listaConyugesPersonaConyuge.get(0).getPersonaNaturalConyuge().getCodigoPersona() != codigoPersona){
                mensaje = "La persona "+listaConyugesPersonaConyuge.get(0).getPersonaNatural().getPersona().getNombreCompleto()+" tiene registrado que es conyuge de "+listaConyugesPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona().getNombreCompleto()+".";
                logger.error("La persona "+listaConyugesPersonaConyuge.get(0).getPersonaNatural().getPersona().getNombreCompleto()+" tiene registrado que es conyuge de "+listaConyugesPersonaConyuge.get(0).getPersonaNaturalConyuge().getPersona().getNombreCompleto()+". "+"("+listaConyugesPersonaConyuge.get(0).getPersonaNaturalConyuge().getCodigoPersona()+" != "+codigoPersona+")");
                return mensaje;
            }
        }
        if(!listaConyugesPersonaConyuge2.isEmpty()){
            if(listaConyugesPersonaConyuge2.size()>1){
                mensaje = "La persona "+listaConyugesPersonaConyuge.get(0).getPersonaNatural().getPersona().getNombreCompleto()+" esta registrado como conyuge mas de una vez.";
                return mensaje;
            }
            if(listaConyugesPersonaConyuge2.get(0).getPersonaNatural().getCodigoPersona() != codigoPersona){
                mensaje = "La persona "+listaConyugesPersonaConyuge2.get(0).getPersonaNaturalConyuge().getPersona().getNombreCompleto()+" tiene registrado que es conyuge de "+listaConyugesPersonaConyuge2.get(0).getPersonaNatural().getPersona().getNombreCompleto()+".";
                logger.error("La persona "+listaConyugesPersonaConyuge2.get(0).getPersonaNaturalConyuge().getPersona().getNombreCompleto()+" tiene registrado que es conyuge de "+listaConyugesPersonaConyuge2.get(0).getPersonaNatural().getPersona().getNombreCompleto()+". "+"("+listaConyugesPersonaConyuge2.get(0).getPersonaNatural().getCodigoPersona()+" != "+codigoPersona+")");
                return mensaje;
            }
        }
        return mensaje;
    }
    
    /**
     * Metodo para desvincular el conyuge de una persona
     * @param codigoPersona
     * @return 
     */
    public boolean desvinculaConyuge(Long codigoPersona, Long codigoUsuario){
        // variables de error 
        error = null;
        errorSql = null;
        errorCode = null;
        // variable de salida
        boolean ejecutoCorrectamente = false;
        StoredProcedureQuery storedProcedureQuery;
        storedProcedureQuery = em.createNamedStoredProcedureQuery( PersonaConyuge.desvinculaConyuge );
        storedProcedureQuery.setParameter( "pt_codigo_persona", codigoPersona );
        storedProcedureQuery.setParameter( "pt_codigo_usuario", codigoUsuario );
        storedProcedureQuery.execute();
        errorSql = (String) storedProcedureQuery.getOutputParameterValue("pv_error_sql");
        errorCode = (String) storedProcedureQuery.getOutputParameterValue("pv_error_code");
        error=(String) storedProcedureQuery.getOutputParameterValue("pv_error"); 
        if (error != null) 
            logger.error("ERROR EJECUCION {}. ERROR CODIGO {}. ERROR SQL {}.", error, errorCode, errorSql);
        else
            ejecutoCorrectamente = true;
        return ejecutoCorrectamente;
    }

    
}
