/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.Venta;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }
    
   /**
    * Obtiene las ventas del ciente con un numero de comprobante en especifico
    * @param numeroComprobante Numero de Comprobante
    * @param codigoCliente Codigo del Cliente
     * @param codigoTipoComprobante Codigo del TIpo de Comproabante
    * @return Lista de Comprobantes que tiene el cliente
    */
    public List<Venta> getItemsNumeroComprobanteClienteIfip(String numeroComprobante, long codigoCliente, long codigoTipoComprobante)
    {
        Query query = this.em.createNamedQuery(Venta.findByNumeroComprobanteClienteIfip);
        query.setParameter("numeroComprobante", numeroComprobante);
        query.setParameter("codigoCliente", codigoCliente);
        query.setParameter("codigoTipoComprobante", codigoTipoComprobante);
        return query.getResultList();
    }
    
    /**
    * Obtiene las ventas del ciente 
    * @param codigoCliente Codigo del Cliente
     * @param codigoIfip Codigo de la ifio
    * @return Lista de Comprobantes que tiene el cliente
    */
    public List<Venta> getItemsClienteIfip(long codigoCliente, long codigoIfip)
    {
        Query query = this.em.createNamedQuery(Venta.findByClienteIfip);
        query.setParameter("codigoCliente", codigoCliente);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
    
}
