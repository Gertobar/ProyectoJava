/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.ProcesoContableDetalle;
import ec.renafipse.mks.modelo.contables.ProcesoContableDetallePK;
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
public class ProcesoContableDetalleFacade extends AbstractFacade<ProcesoContableDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoContableDetalleFacade() {
        super(ProcesoContableDetalle.class);
    }
    
    public List<ProcesoContableDetalle> getItemsProcesoDetalle(Long codigo){
        Query query = em.createNamedQuery(ProcesoContableDetalle.findProcesosDetalle);
        query.setParameter("codigoProceso", codigo);    
        return query.getResultList();
    }
    
    
}
