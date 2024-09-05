/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.LicitudFondosAprobarExc;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author v.astudillo
 */
@Stateless
public class LicitudFondosAprobarExcFacade extends AbstractFacade<LicitudFondosAprobarExc> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicitudFondosAprobarExcFacade() {
        super(LicitudFondosAprobarExc.class);
    }
    
}
