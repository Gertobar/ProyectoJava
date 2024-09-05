/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.Cuenta;
import ec.renafipse.mks.modelo.ahorros.Producto;
import ec.renafipse.mks.modelo.ahorros.ProductoIfip;
import ec.renafipse.mks.modelo.ahorros.TipoProducto;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class ProductoIfipFacade extends AbstractFacade<ProductoIfip> {
   @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoIfipFacade() {
        super(ProductoIfip.class);
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
    public List<ProductoIfip> getItemsProductoIfip(Long codigoIfip, Long codigoMoneda, char eliminado) {
        List<ProductoIfip> listProductosIfip;
        
        Query query = this.em.createNamedQuery(ProductoIfip.findByMonedaProductoIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("eliminado", eliminado);
        listProductosIfip = query.getResultList();

        return listProductosIfip;
    }
    public List<ProductoIfip> getItemsProductoIfip(String siglasLibreta){
     
        Query query = this.em.createNamedQuery(ProductoIfip.findBySiglasLibretaProductoIfip);
        query.setParameter("siglasLibreta", siglasLibreta);
        return query.getResultList();
    }
    public List<ProductoIfip> getItemsProductoIfip(Long codigoIfip, Long codigoMoneda, Long codigoTipoProducto){
     
        Query query = this.em.createNamedQuery(ProductoIfip.findByProductoIfipPK);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        return query.getResultList();
    }
    public List<TipoProducto> getItemsProducto(Long codigoIfip, Long codigoMoneda, char eliminado) {
        List<TipoProducto> listTiposProductos;
        Query query = this.em.createNamedQuery(ProductoIfip.findByMonedaProductoIfipProducto);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("eliminado", eliminado);
        listTiposProductos = query.getResultList();
        return listTiposProductos;
    }
    public List<ProductoIfip> getItemsProductoIfip(Long codigoIfip, Long codigoMoneda, Long codigoTipoProducto,String siglasLibreta){
     
        Query query = this.em.createNamedQuery(ProductoIfip.findByPKSiglasLibreta);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("siglasLibreta", siglasLibreta);
        return query.getResultList();
    }
    
     public ProductoIfip getProductoIfip(Long codigoTipoProducto, Long codigoIfip, Long codigoMoneda){
        Query query = this.em.createNamedQuery(ProductoIfip.findByProductoIfipPK);
         query.setParameter("codigoTipoProducto", codigoTipoProducto);
        query.setParameter("codigoIfip",codigoIfip);
        query.setParameter("codigoMoneda" , codigoMoneda);
       
        return (ProductoIfip)query.getSingleResult();
    }
}
