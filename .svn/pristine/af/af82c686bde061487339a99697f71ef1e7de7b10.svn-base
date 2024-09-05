/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.PagoCreditoMovimientoCue;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vastudillo
 */
@Stateless
public class PagoCreditoMovimientoCueFacade extends AbstractFacade<PagoCreditoMovimientoCue> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoCreditoMovimientoCueFacade() {
        super(PagoCreditoMovimientoCue.class);
    }
    
}
