/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.Compra;
import ec.renafipse.mks.modelo.adquisiciones.TipoComprobanteCompra;
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
public class TipoComprobanteCompraFacade extends AbstractFacade<TipoComprobanteCompra> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoComprobanteCompraFacade() {
        super(TipoComprobanteCompra.class);
    }
    
    public List<TipoComprobanteCompra> getItemsTipoComprobanteEliminado(char eliminado) {
        Query query = em.createNamedQuery(TipoComprobanteCompra.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
    
    public List<TipoComprobanteCompra> getItemsTipoComprobantefindByCodigo(Long codigo,char eliminado) {
        Query query = em.createNamedQuery(TipoComprobanteCompra.findByCodigoYEliminado);
        query.setParameter("codigo", codigo);
        query.setParameter("eliminado", eliminado);        
        return query.getResultList();
    }
    
    public List<TipoComprobanteCompra> getItemsDocumentosProveedorEliminado(Long codigoProveedor,char eliminado) {
        //System.out.println("Proveedor "+codigoProveedor);
        Query query = em.createNamedQuery(TipoComprobanteCompra.findByDocumentosProveedorEliminado);
        query.setParameter("codigoProveedor", codigoProveedor);
        query.setParameter("eliminado", eliminado);        
        return query.getResultList();
    }
    
    public List<TipoComprobanteCompra> getItemsLiquidacionEliminado(char esLiquidacion,char eliminado) {
        Query query = em.createNamedQuery(TipoComprobanteCompra.findByEsLiquidacionEliminado);
        query.setParameter("esLiquidacion", esLiquidacion);
        query.setParameter("eliminado", eliminado);        
        return query.getResultList();
    }
}
