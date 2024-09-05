/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.SolicitudPreAprobadaNegada;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class SolicitudPreAprobadaNegadaFacade extends AbstractFacade<SolicitudPreAprobadaNegada> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
        protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudPreAprobadaNegadaFacade() {
        super(SolicitudPreAprobadaNegada.class);
    }
    /**
     * Metodo para obtener las observaciones de pre aprobacion de un credito
     * @param numeroCredito
     * @param codigoIfip
     * @return 
     */
    public String getObservacionesByNumeroCredito(Long numeroCredito, Long codigoIfip){
        Query query = this.em.createNamedQuery(SolicitudPreAprobadaNegada.findByNumeroCredito);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        SolicitudPreAprobadaNegada solicitudPreAprobadaNegada = (SolicitudPreAprobadaNegada)query.getResultList().get(0);
        return solicitudPreAprobadaNegada.getObservaciones();
    }
}
