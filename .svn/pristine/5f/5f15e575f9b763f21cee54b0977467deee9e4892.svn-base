/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleRubro;
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
public class PagoCreditoDetalleRubroFacade extends AbstractFacade<PagoCreditoDetalleRubro> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCreditoDetalleRubroFacade() {
        super(PagoCreditoDetalleRubro.class);
    }

    /**
     *
     * @param codigoPagoCredito
     * @param cuota
     * @return
     */
    public List<PagoCreditoDetalleRubro> getItemsPagoCreditoDetalleRubro(long codigoPagoCredito, long cuota) {
        Query query = em.createNamedQuery(PagoCreditoDetalleRubro.findByCodigoPagoCreditoDetalleCuota);
        query.setParameter("codigoPagoCredito", codigoPagoCredito);
        query.setParameter("cuota", cuota);
        return query.getResultList();
    }
}
