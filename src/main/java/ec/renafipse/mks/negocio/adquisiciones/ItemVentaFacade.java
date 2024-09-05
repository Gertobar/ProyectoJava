/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.ItemVenta;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class ItemVentaFacade extends AbstractFacade<ItemVenta> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemVentaFacade() {
        super(ItemVenta.class);
    }
    
    /**
     * Obtiene los Items de de ventana segun su estado eliminado
     * @param eliminado S o N
     * @return Lista de Items de Venta
     */
    public List<ItemVenta> getItemsEliminado(char eliminado)
    {
        return this.em.createNamedQuery("ItemVenta.findByEliminado", ItemVenta.class).setParameter("eliminado", eliminado).getResultList();
    }   
}