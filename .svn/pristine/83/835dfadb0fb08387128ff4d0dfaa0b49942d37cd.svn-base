/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleCueAh;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author renafipse1
 */
@Stateless
public class PagoCompraDetalleCueAhFacade extends AbstractFacade<PagoCompraDetalleCueAh> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCompraDetalleCueAhFacade() {
        super(PagoCompraDetalleCueAh.class);
    }
    
    public List<PagoCompraDetalleCueAh> findByCodigoPagoCompraDetalle(Long codigoPagoCompraDetalle) {
        Query query = em.createNamedQuery(PagoCompraDetalleCueAh.findByCodigoPagoCompraDetalle);  
        query.setParameter("codigoPagoCompraDetalle", codigoPagoCompraDetalle);
        
        return query.getResultList();
    }
  
}
