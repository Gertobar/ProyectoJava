/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.adquisiciones.ItemVenta;
import ec.renafipse.mks.modelo.contables.CuentaContableItemVenta;
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
public class CuentaContableItemVentaFacade extends AbstractFacade<CuentaContableItemVenta> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaContableItemVentaFacade() {
        super(CuentaContableItemVenta.class);
    }

    /**
     * Obtiene las cuentas contables asgnadas a un Item de la Venta en la Ifip
     * @param codigoIfip Codigo de la IFIP
     * @return Lista de Cuentas Contables de los Items de Venta
     */
    public List<CuentaContableItemVenta> getItemsIfip(Long codigoIfip)
    {
        return this.em.createNamedQuery(CuentaContableItemVenta.findByIfip).setParameter("codigoIfip", codigoIfip).getResultList();
    }
    
     /**
     * Obtiene los items de venta de las que esten asignados cuentas y que no esten eliminados
     * @param codigoIfip Codigo de la IFIP
     * @param eliminado
     * @return Lista de items de Venta
     */
    public List<ItemVenta> getItemsItemVentaIfip(Long codigoIfip, char eliminado)
    {
        Query query = this.em.createNamedQuery(CuentaContableItemVenta.findByItemVentaIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);       
        return  query.getResultList();
    }
   
}
