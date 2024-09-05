/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;


import ec.renafipse.mks.modelo.adquisiciones.CompraDetalle;
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
public class CompraDetalleFacade extends AbstractFacade<CompraDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraDetalleFacade() {
        super(CompraDetalle.class);
    }
    
    public List<CompraDetalle> getItemsAllCompraDetalleCodigoIfip(Long codigoIfip) {
        Query query = em.createNamedQuery(CompraDetalle.findByCodigoIfipAllComprasDetalle);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    //findByCodigoCompra
        public List<CompraDetalle> getItemsAllfindByCodigoCompra(Long codigoCompra) {
            //System.out.println("EL CODIGO ES "+codigoCompra);
        Query query = em.createNamedQuery(CompraDetalle.findByCodigoCompra);
        query.setParameter("codCompra", codigoCompra);
        return query.getResultList();
    }
     
}
