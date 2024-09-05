/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;


import ec.renafipse.mks.modelo.ifips.ReporteFragmentoIfip;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author scordero
 */
@Stateless
public class ReporteFragmentoIfipFacade extends AbstractFacade<ReporteFragmentoIfip>{

        @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;
        
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteFragmentoIfipFacade() {
        super(ReporteFragmentoIfip.class);
    }
    
 
      public ReporteFragmentoIfip getFragmento(Long codigoReporte,Long codigoFragmento,Long codigoIfip){
        Query query=em.createNamedQuery(ReporteFragmentoIfip.findByCodIfipRepFrag);
        query.setParameter("codigoReporte",codigoReporte);
        query.setParameter("codigoFragmento",codigoFragmento);
         query.setParameter("codigoIfip",codigoIfip);
        return (ReporteFragmentoIfip)query.getSingleResult();
}
       public ReporteFragmentoIfip getFragmentoReporte(Long codigoFragmento){
        Query query=em.createNamedQuery(ReporteFragmentoIfip.findByCodigoFragmento);
        query.setParameter("codigoFragmento",codigoFragmento);
        return (ReporteFragmentoIfip)query.getSingleResult();
}
}