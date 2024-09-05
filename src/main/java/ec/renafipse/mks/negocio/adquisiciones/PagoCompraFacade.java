/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.PagoCompra;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class PagoCompraFacade extends AbstractFacade<PagoCompra> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCompraFacade() {
        super(PagoCompra.class);
    }
        public List<PagoCompra> getItemsfindByCodigoCompra(Long codigoCompra) {
        Query query = em.createNamedQuery(PagoCompra.findByCodigoCompra);          
        query.setParameter("codigoCompra", codigoCompra);
        
        return query.getResultList();
    }
}
