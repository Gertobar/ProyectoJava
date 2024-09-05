/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.PagoCreditoDetalleCuota;
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
public class PagoCreditoDetalleCuotaFacade extends AbstractFacade<PagoCreditoDetalleCuota> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCreditoDetalleCuotaFacade() {
        super(PagoCreditoDetalleCuota.class);
    }

    /**
     *
     * @param codigoPagoCredito
     * @return
     */
    public List<PagoCreditoDetalleCuota> getItemsPagoCreditoDetalleCuota(Long codigoPagoCredito) {
        Query query = this.em.createNamedQuery(PagoCreditoDetalleCuota.findByDetalleCuotaPagoCredito);
        query.setParameter("codigoPagoCredito", codigoPagoCredito);
        return query.getResultList();
    }
}
