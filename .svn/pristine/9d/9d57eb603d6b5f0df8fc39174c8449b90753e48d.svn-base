/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.Producto;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    // -- METODOS PERSONALIZADOS
    /**
     *  OBTIENE LAS PRODUCTOS DE LA IFIP VIGENTES DE ACUERDO A LA MONEDA E IFIP.
     *  
     * @param codigoIfip Codigo de la  IFIP
     * @param codigoMoneda Codigo de la  Moneda
     * @param eliminado Eliminado S=SI N= NO
     * @return
     */
    public List<Producto> getItemsProducto(Long codigoIfip, Long codigoMoneda, char eliminado) {
        List<Producto> listProductos;
        
        Query query = this.em.createNamedQuery(Producto.findByMonedaProducto);
        
        query.setParameter("codigoIfip" , codigoIfip);
        query.setParameter("codigoMoneda" , codigoMoneda);
        query.setParameter("eliminado" , eliminado);
        listProductos =  query.getResultList();

        return listProductos;
    }
  
     public List<Producto> getItemsProducto(Long codigoTipoProducto){
        Query query = this.em.createNamedQuery(Producto.findByCodigoTipoProducto);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        return query.getResultList();
    }
     public Producto getProducto(Long codigoTipoProducto, Long codigoMoneda){
        Query query = this.em.createNamedQuery(Producto.findByProductoPK);
        query.setParameter("codigoMoneda" , codigoMoneda);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        return (Producto)query.getSingleResult();
    }
    public Producto getProductoUnico(Long codigoTipoProducto, Long codigoMoneda){
        Query query = this.em.createNamedQuery(Producto.findByUnique);
        query.setParameter("codigoMoneda" , codigoMoneda);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        try {
            Producto producto = (Producto)query.getSingleResult();
            return producto == null ? null : producto;
        } catch (NoResultException e) {
            return null;
        }
    }
}
