/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.contables;

import ec.renafipse.mks.modelo.contables.CierreAnual;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class CierreAnualFacade extends AbstractFacade<CierreAnual> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CierreAnualFacade() {
        super(CierreAnual.class);
    }
    
}
