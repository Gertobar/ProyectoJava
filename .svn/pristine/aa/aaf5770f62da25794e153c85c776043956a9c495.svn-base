/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.ActividadEconomica;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class ActividadEconomicaFacade extends AbstractFacade<ActividadEconomica> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadEconomicaFacade() {
        super(ActividadEconomica.class);
    }

    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
//    /**
//     * Obtiene las actividades economicas de acuerdo al sector, subsector, nivel  y el estado eliminado
//     *
//     * @param codigoSector Codigo del Sector de la Actividad Economica
//     * @param codigoSubsector Codigo del Subsector de la Actividad Economica
//     * @param eliminado S=SI N=NO
//     * @param codigoNivel Nivel de la Actividad Economica
//     * @return Lista de actividades Economicas
//     */
//    public List<ActividadEconomica> getItemsSectorSubsectorNivelEliminado(long codigoSector, long codigoSubsector, long codigoNivel, char eliminado) {
//        Query query = this.em.createNamedQuery(ActividadEconomica.findBySectorSubsectorNivelEliminado);        
//        query.setParameter("codigoSubsector", codigoSubsector);
//        query.setParameter("codigoNivel", codigoNivel);
//        query.setParameter("eliminado", eliminado);
//        return query.getResultList();
//    }
    
    public List<ActividadEconomica> getItemsActividadEconomicaEliminado(long nivel,char catalogo, char eliminado)
    {
        Query query = em.createNamedQuery(ActividadEconomica.findByNivelEliminado);        
        query.setParameter("eliminado", eliminado);        
        query.setParameter("nivel", nivel);         
        query.setParameter("catalogo", catalogo);  
        return query.getResultList();
    }
    
    public List<ActividadEconomica> getItemsActEcoNivelPadreEliminado(long nivel, long codigoPadre,char catalogo,char eliminado)
    {
        Query query = em.createNamedQuery(ActividadEconomica.findByNivelPadreEliminado);        
        query.setParameter("eliminado", eliminado);        
        query.setParameter("nivel", nivel);      
        query.setParameter("catalogo", catalogo);          
        query.setParameter("codigoPadre", codigoPadre); 
        query.setParameter("eliminado", eliminado);        
        return query.getResultList();
    }

    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}
