/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.MovimientoBoveda;
import ec.renafipse.mks.modelo.ifips.TipoMovimientoBoveda;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Viajes2
 */
@Stateless
public class MovimientoBovedaFacade extends AbstractFacade<MovimientoBoveda> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoBovedaFacade() {
        super(MovimientoBoveda.class);
    }
    
     // ---------------------------------------------------------------------------
    // --- METODOS PERSONALIZADOS
    /**
     * Obtiene los movimientos de la bodega, para el codigo de movimiento y comprobante.
     * @param codigoBoveda Codigo de la Boveda
     * @param codigoTipoMovimiento Codigo del Tipo de Movimiento
     * @param comprobante Numero de Comprobante
     * @return  Lista de TIpo de Movimiento.
     */
    public List<MovimientoBoveda> getItemsBodegaTipoMovimientoComprobante(Long codigoBoveda, Long codigoTipoMovimiento, String comprobante)
    {
        Query query = em.createNamedQuery(MovimientoBoveda.findByBodegaTipoMovimientoComprobante);
        query.setParameter("codigoBoveda", codigoBoveda);
        query.setParameter("codigoTipoMovimiento", codigoTipoMovimiento);
        query.setParameter("comprobante", comprobante);
        return query.getResultList();
    }
}
