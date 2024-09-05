/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TipoRubro;
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
public class TipoRubroFacade extends AbstractFacade<TipoRubro> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRubroFacade() {
        super(TipoRubro.class);
    }
    public List<TipoRubro> getItemsTipoRubro(char eliminado) {
        Query query = this.em.createNamedQuery(TipoRubro.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }
}
