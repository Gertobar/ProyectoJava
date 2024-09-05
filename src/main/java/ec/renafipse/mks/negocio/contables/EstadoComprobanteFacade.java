/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.EstadoComprobante;
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
public class EstadoComprobanteFacade extends AbstractFacade<EstadoComprobante> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoComprobanteFacade() {
        super(EstadoComprobante.class);
    }
    
    public List<EstadoComprobante> getItemsEstadoComprobante( char eliminado) {
        Query query = em.createNamedQuery(EstadoComprobante.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
}
