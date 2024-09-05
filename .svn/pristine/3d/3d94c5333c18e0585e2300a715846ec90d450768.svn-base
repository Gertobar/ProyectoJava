/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.seguridades;

import ec.renafipse.mks.modelo.seguridades.TipoOperacion;
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
public class TipoOperacionFacade extends AbstractFacade<TipoOperacion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoOperacionFacade() {
        super(TipoOperacion.class);
    }

 /**
  * 
  * @return 
  */
    public List<TipoOperacion> getItemsTipoOperacion() {
        Query query = em.createNamedQuery(TipoOperacion.findByEliminado);
        query.setParameter("eliminado", 'N');
        return query.getResultList();
    }

    /**
     * 
     * @param codigoMenu
     * @return 
     */
    public List<TipoOperacion> getItemsTipoOperacionExistentesOpOp(Long codigoMenu) {
        Query query = em.createNamedQuery(TipoOperacion.findByTipoOperacionExistentesOpOp);
        query.setParameter("codigoMenu", codigoMenu);
        return query.getResultList();
    }

}
