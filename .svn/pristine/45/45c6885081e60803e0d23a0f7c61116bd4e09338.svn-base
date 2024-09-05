/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.comunes;

import ec.renafipse.mks.modelo.comunes.Periodicidad;
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
public class PeriodicidadFacade extends AbstractFacade<Periodicidad> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodicidadFacade() {
        super(Periodicidad.class);
    }

    // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los items de la periodicidad acuerdo al estado eliminado
     *
     * @param eliminado S=SI N=NO
     * @return
     */
    public List<Periodicidad> getItemsPeriodicidadEliminado(char eliminado) {
        return this.em.createNamedQuery(Periodicidad.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }

    /**
     * Obtiene las Periodicidades para los dpfs y de acuerdo al estado eliminado
     *
     * @param esParaDpf S=SI N=NO
     * @param eliminado S=SI N=NO
     * @return Listado de Periodicidades.
     */
    public List<Periodicidad> getItemsEsParaDpfEliminado(char esParaDpf, char eliminado) {
        Query query = this.em.createNamedQuery(Periodicidad.findByEsParaDpfEliminado);
        query.setParameter("esParaDpf", esParaDpf);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    /**
     * Obtiene las Periodicidades para la Solicitud de Socios y de acuerdo al estado eliminado
     *
     * @param esParaSolicitudSocio S=SI N=NO
     * @param eliminado S=SI N=NO
     * @return Listado de Periodicidades.
     */
    public List<Periodicidad> getItemsEsParaSolicitudSocioEliminado(char esParaSolicitudSocio, char eliminado) {
        Query query = this.em.createNamedQuery(Periodicidad.findByEsParaSolicitudSocioEliminado);
        query.setParameter("esParaSolicitudSocio", esParaSolicitudSocio);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

    // -- FIN DE METODOS PERSONALIZADOS
    //----------------------------------------------------------------------------
}
