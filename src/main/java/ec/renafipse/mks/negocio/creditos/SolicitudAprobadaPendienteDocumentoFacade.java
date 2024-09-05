/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.SolicitudAprobadoPenDoc;
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
public class SolicitudAprobadaPendienteDocumentoFacade extends AbstractFacade<SolicitudAprobadoPenDoc> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
        protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudAprobadaPendienteDocumentoFacade() {
        super(SolicitudAprobadoPenDoc.class);
    }
    
    /**
     * Metodo para obtener las observaciones para la aprobacion de creditos con documentos pendientes
     * @param numeroCredito
     * @param codigoIfip
     * @return 
     */
    public String getObservacionesByNumeroCredito(Long numeroCredito, Long codigoIfip){
        Query query = this.em.createNamedQuery(SolicitudAprobadoPenDoc.findByNumeroCredito);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);
        SolicitudAprobadoPenDoc solicitudAprobadoPenDoc = (SolicitudAprobadoPenDoc)query.getResultList().get(0);
        return solicitudAprobadoPenDoc.getObservaciones();
    }
    
}
