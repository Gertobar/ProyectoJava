/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.ProductoCredito;
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
public class ProductoCreditoFacade extends AbstractFacade<ProductoCredito> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoCreditoFacade() {
        super(ProductoCredito.class);
    }
    public List<ProductoCredito> getItemsProductoCredito(Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(ProductoCredito.findByProductoCreditoMonedaIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }
    public List<ProductoCredito> getItemsTasaProductoCredito(Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(ProductoCredito.findByTasaIntProCreMonIfi);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }
    public List<ProductoCredito> getItemsProductoCredito(Long codigoIfip) {
        Query query = this.em.createNamedQuery(ProductoCredito.findByProductoCreditoMonedaI);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();

    }
}
