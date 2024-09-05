/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.TipoRetencion;
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
public class TipoRetencionFacade extends AbstractFacade<TipoRetencion> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRetencionFacade() {
        super(TipoRetencion.class);
    }
    
    public List<TipoRetencion> getItemsAllTipoRetencion() {
     
        Query query = em.createNamedQuery(TipoRetencion.findByAll);
        return query.getResultList();
    }
    
     public List<TipoRetencion> getItemsEliminado(char eliminado) {
     
        return em.createNamedQuery(TipoRetencion.findByEliminado).setParameter("eliminado", eliminado).getResultList();
    }
}
