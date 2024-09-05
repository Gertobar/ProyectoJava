/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.RetencionDetalle;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class RetencionDetalleFacade extends AbstractFacade<RetencionDetalle> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RetencionDetalleFacade() {
        super(RetencionDetalle.class);
    }

    public List<RetencionDetalle> getItemsRetencionesfindByCodigoRetencion(Long codigoRetencion) {
        Query query = em.createNamedQuery(RetencionDetalle.findByCodigoRetencion);
        query.setParameter("codigoRetencion", codigoRetencion);
        return query.getResultList();

    }
}
