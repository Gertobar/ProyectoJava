/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.reportes;

import ec.renafipse.mks.modelo.reportes.ReporteDetalle;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class ReporteDetalleFacade extends AbstractFacade<ReporteDetalle> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteDetalleFacade() {
        super(ReporteDetalle.class);
    }
    
}
