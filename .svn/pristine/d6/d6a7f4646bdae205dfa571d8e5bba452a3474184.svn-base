/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.TipoProtestoCheque;
import ec.renafipse.mks.modelo.ifips.IfipAgenciaCuentaEntFin;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class TipoProtestoChequeFacade extends AbstractFacade<TipoProtestoCheque> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoProtestoChequeFacade() {
        super(TipoProtestoCheque.class);
    }

    /**
     *
     * @param eliminado
     * @return
     */
    public List<TipoProtestoCheque> getItemsTipoProtestoEliminado(char eliminado) {
        Query query = em.createNamedQuery(TipoProtestoCheque.findByTipoProtestoEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
