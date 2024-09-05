/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TipoRubroCredito;
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
public class TipoRubroCreditoFacade extends AbstractFacade<TipoRubroCredito> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRubroCreditoFacade() {
        super(TipoRubroCredito.class);
    }
    /**
     * Obtiene los rubros que tiene un credito
     * @param numeroCredito
     * @param codigoIfip
     * @return Lista de Tipos de Rubros
     */
    public List<TipoRubroCredito> getItemsNumeroCreditoCodigoIfip(long numeroCredito, long codigoIfip) {
        Query query = this.em.createNamedQuery(TipoRubroCredito.findByNumeroCreditoCodigoIfip);
        query.setParameter("numeroCredito", numeroCredito);
        query.setParameter("codigoIfip", codigoIfip);        
        return query.getResultList();

    }
}
