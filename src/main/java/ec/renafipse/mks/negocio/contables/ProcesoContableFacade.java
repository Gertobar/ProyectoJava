/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.ProcesoContable;
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
public class ProcesoContableFacade extends AbstractFacade<ProcesoContable> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoContableFacade() {
        super(ProcesoContable.class);
    }
    
    public List<ProcesoContable> getItemsProcesosContablesEliminado(char eliminado ) {
        Query query = em.createNamedQuery(ProcesoContable.findByEliminado); 
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }   
    
}
