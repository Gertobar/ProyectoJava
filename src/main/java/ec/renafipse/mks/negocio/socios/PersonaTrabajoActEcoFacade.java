/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.PersonaTrabajoActEco;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 */
@Stateless
public class PersonaTrabajoActEcoFacade extends AbstractFacade<PersonaTrabajoActEco> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaTrabajoActEcoFacade() {
        super(PersonaTrabajoActEco.class);
    }

    //------------------------------------------------------------------------
    //PERSONALIZADOS
    /**
     * Obtiene los lugares de trabajo de la persona filtrando si la actividad
     * economica esta eliminado
     *
     * @param codigoPersona Codigo de la persona
     * @param eliminadoActividadEconomica ELiminado S=SI N=NO
     * @return Lista de Trabajos de la Actividad Economica
     */
    public List<PersonaTrabajoActEco> getItemsCodigoPersonaActividadEconomicaEliminado(long codigoPersona, char eliminadoActividadEconomica) {
        Query query = this.em.createNamedQuery(PersonaTrabajoActEco.findByCodigoPersonaActividadEconomicaEliminado);
        query.setParameter("eliminado", eliminadoActividadEconomica);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }

    /**
     * Obtiene los trabajos de la persona
     *
     * @param codigoPersona
     * @return Lista de Traabajos de la Persona
     */
    public List<PersonaTrabajoActEco> getItemsCodigoPersona(long codigoPersona) {
        Query query = this.em.createNamedQuery(PersonaTrabajoActEco.findByfindByCodigoPersona);
        query.setParameter("codigoPersona", codigoPersona);
        return query.getResultList();
    }

    /**
     * Obtiene los trabajos de la persona con esa actividad en una empresa
     * especifica
     *
     * @param codigoPersona Codigo de Persona
     * @param codigoActividadEconomica Codigo de la Actividad Economica
     * @param codigoEmpresa Codigo de la Empresa
     * @return Lista de Traabajos de la Persona
     */
    public List<PersonaTrabajoActEco> getItemsCodigoPersonaCodigoActividadCodigoEmpresa(long codigoPersona, long codigoActividadEconomica, long codigoEmpresa) {
        Query query = this.em.createNamedQuery(PersonaTrabajoActEco.findByCodigoPersonaCodigoActividadCodigoEmpresa);
        query.setParameter("codigoPersona", codigoPersona);
        query.setParameter("codigoActividadEconomica", codigoActividadEconomica);
        query.setParameter("codigoEmpresa", codigoEmpresa);
        return query.getResultList();
    }

  

}
