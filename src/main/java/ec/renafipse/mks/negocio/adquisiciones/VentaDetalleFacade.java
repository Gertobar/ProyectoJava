/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.VentaDetalle;
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
public class VentaDetalleFacade extends AbstractFacade<VentaDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaDetalleFacade() {
        super(VentaDetalle.class);
    }
    
    /**
     * Obtiene el detalle de la venta
     * @param codigoVenta Codigo de la Venta
     * @return Lista de Detalle de la Venta
     */
    public List<VentaDetalle> getItemsCodigoVenta(long codigoVenta)
    {
        Query query = this.em.createNamedQuery(VentaDetalle.findByCodigoVenta);
        query.setParameter("codigoVenta", codigoVenta);
        return query.getResultList();
    }
}
