/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleNot;
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
public class PagoCreditoDetalleNotFacade extends AbstractFacade<PagoCreditoDetalleNot> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCreditoDetalleNotFacade() {
        super(PagoCreditoDetalleNot.class);
    }

    /**
     *
     * @param codigoPagoCredito
     * @param cuota
     * @return
     */
    public List<PagoCreditoDetalleNot> getItemsPagoCreditoDetalleNot(long codigoPagoCredito, long cuota) {
        Query query = em.createNamedQuery(PagoCreditoDetalleNot.findByCodigoPagoCreditoDetalleCuota);
        query.setParameter("codigoPagoCredito", codigoPagoCredito);
        query.setParameter("cuota", cuota);

        return query.getResultList();
    }

}
