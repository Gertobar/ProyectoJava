/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalleTransf;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author renafipse1
 */
@Stateless
public class PagoCompraDetalleTransfFacade extends AbstractFacade<PagoCompraDetalleTransf> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCompraDetalleTransfFacade() {
        super(PagoCompraDetalleTransf.class);
    }
    
    public List<PagoCompraDetalleTransf> findByCodigoPagoCompraDetalle(Long codigoPagoCompraDetalle) {
        Query query = em.createNamedQuery(PagoCompraDetalleTransf.findByCodigoPagoCompraDetalle);
        query.setParameter("codigoPagoCompraDetalle", codigoPagoCompraDetalle);

        return query.getResultList();
    }
}
