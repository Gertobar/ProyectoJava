
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.ProductoCredito;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoMonedaIfip;
import ec.renafipse.mks.modelo.creditos.ProductoCreditoTipoPersona;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Santiago Araujo
 */
@Stateless
public class ProductoCreditoTipoPersonaFacade extends AbstractFacade<ProductoCreditoTipoPersona> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoCreditoTipoPersonaFacade() {
        super(ProductoCreditoTipoPersona.class);
    }

    public List<ProductoCredito> getItemsProductoCreditoMonedaIfip(Long codigoTipoCartera, Long codigoTipoPersona) {
        Query query = this.em.createNamedQuery(ProductoCreditoTipoPersona.findProdcutoCreditoMonIfiipByTipoPersona);
        query.setParameter("codigoTipoCartera", codigoTipoCartera);
        query.setParameter("codigoTipoPersona", codigoTipoPersona);
        List<ProductoCredito> listadoAct = new ArrayList<ProductoCredito>();
        List<ProductoCreditoTipoPersona> listado = query.getResultList();
        for( ProductoCreditoTipoPersona item : listado ){
            listadoAct.add(item.getProductoCreditoMonedaIfip().getProductoCreditoMoneda().getProductoCredito());
        }
        return listadoAct;
    }
}
