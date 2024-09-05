/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.SolicitudDevuelta;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Santiago Araujo
 */
@Stateless
public class SolicitudDevueltaFacade extends AbstractFacade<SolicitudDevuelta> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
        protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudDevueltaFacade() {
        super(SolicitudDevuelta.class);
    }  
    
    /***
     * Metodo para obtener la devolucion vigente de una solicitud
     * @param numeroCredito
     * @param codigoIfip
     * @return 
     */
    public SolicitudDevuelta getPorNumeroCredito(Long numeroCredito, Long codigoIfip){
        Query query = this.em.createNamedQuery(SolicitudDevuelta.findByNumeroCredito);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        SolicitudDevuelta solicitudDevuelta = null;
        if (!query.getResultList().isEmpty())
            solicitudDevuelta = (SolicitudDevuelta)query.getResultList().get(0);
        return solicitudDevuelta;
    }
}
