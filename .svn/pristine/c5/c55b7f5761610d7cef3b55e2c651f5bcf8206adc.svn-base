/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.modelo.socios.PersonaActividadEconomica;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.ArrayList;
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
public class PersonaActividadEconomicaFacade extends AbstractFacade<PersonaActividadEconomica> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaActividadEconomicaFacade() {
        super(PersonaActividadEconomica.class);
    }

    public List<PersonaActividadEconomica> getItemsPersonaActividadEconomoica(Long codigoPersona) {
        try {
            return this.em.createNamedQuery(PersonaActividadEconomica.findByCodigoPersona).setParameter("codigoPersona", codigoPersona).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public List<PersonaActividadEconomica> getItemsPerActEconMaxNivel(Long codigoPersona, char eliminado) {
        Query query = this.em.createNamedQuery(PersonaActividadEconomica.findByMaximoNivelEliminado);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

//    public List<ActividadEconomica> getItemsSectorSubsectorNivelEliminado(long codigoSubsector, long codigoNivel, char eliminado) {
//        Query query = this.em.createNamedQuery(PersonaActividadEconomica.findBySectorSubsectorNivelEliminado);
//        query.setParameter("codigoSubsector", codigoSubsector);
//        query.setParameter("codigoNivel", codigoNivel);
//        query.setParameter("eliminado", eliminado);
//        return query.getResultList();
//    }
    /**
     * Obtiene las Actividades de la Persona de acuerdo al estado eliminado
     *
     * @param codigoPersona Codigo de la Persona
     * @param eliminado Elimnado S=SI N=NO
     * @return
     */
    public List<PersonaActividadEconomica> getItemsCodigoPersonaEliminado(Long codigoPersona, char eliminado) {
        Query query = this.em.createNamedQuery(PersonaActividadEconomica.findByCodigoPersonaEliminado);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    //-------------------------------------------------------------------------------------
    /**
     * Actualiza la Actividad Economica
     *
     * @param codigoActividadEconomica Codigo de Actividad Economica
     * @param codigoPeriodicidad Codigo de Periodicidad
     * @param tiempo Tiempo de Actividad Economica
     * @param fechaActualizacion Fecha de Actualizacion
     * @param eliminado S o N Eliminado
     * @param esPrincipal S o N es Principal
     * @param codigoPersona Codigo de la Persona
     */
    @Transactional
    public void actualiza(long codigoActividadEconomica, long codigoPeriodicidad, int tiempo, Date fechaActualizacion, char eliminado, char esPrincipal, long codigoPersona) {
        Query query = em.createQuery("UPDATE PersonaActividadEconomica c "
                + "SET codigoPeriodicidad = :codigoPeriodicidad, \n"
                + "tiempo = :tiempo, \n"
                + "fechaActualizacion = :fechaActualizacion, \n"
                + "eliminado = :eliminado, \n"
                + "esPrincipal = :esPrincipal \n"
                + "WHERE c.personaActividadEconomicaPK.codigoPersona = :codigoPersona \n"
                + "AND  c.personaActividadEconomicaPK.codigoActividadEconomica = :codigoActividadEconomica");
        query.setParameter("codigoPeriodicidad", codigoPeriodicidad);
        query.setParameter("tiempo", tiempo);
        query.setParameter("fechaActualizacion", fechaActualizacion, TemporalType.TIMESTAMP);
        query.setParameter("eliminado", eliminado);
        query.setParameter("esPrincipal", esPrincipal);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoActividadEconomica", codigoActividadEconomica);
        query.executeUpdate();
    }
}
