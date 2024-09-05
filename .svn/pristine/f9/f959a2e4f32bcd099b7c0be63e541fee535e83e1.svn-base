/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.ComprobanteCompra;
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
public class ComprobanteCompraFacade extends AbstractFacade<ComprobanteCompra> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprobanteCompraFacade() {
        super(ComprobanteCompra.class);
    }

    public List<ComprobanteCompra> getItemsComprobantesComprafindByCodigoCompra(Long codigoCompra) {
        Query query = em.createNamedQuery(ComprobanteCompra.findByCodigoCompra);
        query.setParameter("codigoCompra", codigoCompra);
        return query.getResultList();
    }
}
