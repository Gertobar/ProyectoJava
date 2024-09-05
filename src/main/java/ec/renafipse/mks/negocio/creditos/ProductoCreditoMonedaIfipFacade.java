/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.comunes.Moneda;
import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.modelo.creditos.TipoCartera;
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
public class ProductoCreditoMonedaIfipFacade extends AbstractFacade<ProductoCreditoMonedaIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoCreditoMonedaIfipFacade() {
        super(ProductoCreditoMonedaIfip.class);
    }

    public ProductoCreditoMonedaIfip getItemsProductoCreditoMonedaIfip(Long codigoProducto,Long codigoMoneda,Long codigoIfip) {
        Query query = this.em.createNamedQuery(ProductoCreditoMonedaIfip.findByProductoCreditoMonedaIfipPK);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        return (ProductoCreditoMonedaIfip)query.getSingleResult();

    }
    
    public List<Moneda> getItemsMonedaIfip(Long codigoIfip, char eliminado, Long codigoMoneda) {
        Query query = this.em.createNamedQuery(ProductoCreditoMonedaIfip.findMonedaByCodigoIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoMoneda", codigoMoneda);
        return query.getResultList();
    }
    
    public List<Moneda> getItemsIfipEliminado(Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(ProductoCreditoMonedaIfip.findMonedaByIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<ProductoCreditoMonedaIfip> getItemsIfipMoneda(Long codigoIfip, Long codigoMoneda, char eliminado) {
        Query query = this.em.createNamedQuery(ProductoCreditoMonedaIfip.findByIfipMonedaBy);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoMoneda", codigoMoneda);
        return query.getResultList();
    }
    
    public List<ProductoCredito> getItemsProductoCreditobyIfipMoneda(Long codigoMoneda,Long codigoIfip, Long codigoTipoCartera, char eliminado) {
        Query query = this.em.createNamedQuery(ProductoCreditoMonedaIfip.findMonedaByCodigoIfipMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTipoCartera", codigoTipoCartera);
        return query.getResultList();
    }
    
    public List<ProductoCredito> getItemsProductoCreditobyCodigoIfipMoneda(Long codigoMoneda, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery("ProductoCreditoMonedaIfip.findProductoCreditoByCodigoIfipMoneda", ProductoCredito.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoMoneda", codigoMoneda);
        return query.getResultList();
    }
    
    public List<ProductoCredito> getItemsProductoCreditobyCodigoIfipMonedaTipoCartera(Long codigoMoneda, Long codigoIfip, char eliminado, Long codigoTipoCartera) {
        Query query = this.em.createNamedQuery("ProductoCreditoMonedaIfip.findProductoCreditoByCodigoIfipMonedaTipoCartera", ProductoCredito.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTipoCartera", codigoTipoCartera);
        return query.getResultList();
    }
    
}
