/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.GaranteCredito;
import ec.renafipse.mks.modelo.creditos.MotivoPagoCreditoTotal;
import ec.renafipse.mks.modelo.creditos.TipoMotivoPagoCredito;
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
public class TipoMotivoPagoCreditoFacade extends AbstractFacade<TipoMotivoPagoCredito> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoMotivoPagoCreditoFacade() {
        super(TipoMotivoPagoCredito.class);
    }

    public List<TipoMotivoPagoCredito> getItemsTipoMotivoPagoCreditoTotal(char eliminado) {
        Query query = this.em.createNamedQuery(TipoMotivoPagoCredito.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }

}
