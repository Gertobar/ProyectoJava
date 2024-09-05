/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.PagoCompraDetalle;
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
public class PagoCompraDetalleFacade extends AbstractFacade<PagoCompraDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCompraDetalleFacade() {
        super(PagoCompraDetalle.class);
    }
    
    public List<PagoCompraDetalle> getItemsfindByCodigoPago(Long codigoPago) {
        Query query = em.createNamedQuery(PagoCompraDetalle.findByCodigoPago);          
        query.setParameter("codigoPago", codigoPago);
        return query.getResultList();
    }
    
    public List<Long> getMaximoPagoCompraDetalles() {
        Query query = em.createNamedQuery(PagoCompraDetalle.findMax);                   
        return query.getResultList();
    }
    
    public List<PagoCompraDetalle> getItemsfindByCodigo(Long codigo) {
        Query query = em.createNamedQuery(PagoCompraDetalle.findByCodigo);          
        query.setParameter("codigo", codigo);
        return query.getResultList();
    }
}
