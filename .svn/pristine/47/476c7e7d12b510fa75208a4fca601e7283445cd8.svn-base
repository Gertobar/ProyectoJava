/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.cajas;

import ec.renafipse.mks.modelo.cajas.AperturaDesgloceEfectivo;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vicastoc
 */
@Stateless
public class AperturaDesgloceEfectivoFacade extends AbstractFacade<AperturaDesgloceEfectivo> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AperturaDesgloceEfectivoFacade() {
        super(AperturaDesgloceEfectivo.class);
    }
    
}
