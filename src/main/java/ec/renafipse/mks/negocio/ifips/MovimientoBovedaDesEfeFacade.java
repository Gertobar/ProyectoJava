/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.MovimientoBovedaDesEfe;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Viajes2
 */
@Stateless
public class MovimientoBovedaDesEfeFacade extends AbstractFacade<MovimientoBovedaDesEfe> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoBovedaDesEfeFacade() {
        super(MovimientoBovedaDesEfe.class);
    }
    
}
