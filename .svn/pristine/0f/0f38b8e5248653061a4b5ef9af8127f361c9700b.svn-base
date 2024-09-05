/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.TipoContribuyente;
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
public class TipoContribuyenteFacade extends AbstractFacade<TipoContribuyente> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoContribuyenteFacade() {
        super(TipoContribuyente.class);
    }
    
 
    public List<TipoContribuyente> getItemsTipConEliminado()
    {
        Query query = em.createNamedQuery(TipoContribuyente.findByEliminado);        
         query.setParameter("eliminado", 'N'); 
        return query.getResultList();
    }
    
    public List<TipoContribuyente> getItemsTipConEliByCodigo(Long codigo)
    {
        Query query = em.createNamedQuery(TipoContribuyente.findByCodigoEliminado);        
         query.setParameter("codigo", codigo); 
         query.setParameter("eliminado", 'N'); 
        return query.getResultList();
    }
}
