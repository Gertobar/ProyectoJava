/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;


import ec.renafipse.mks.modelo.ifips.ParametroServidorIfip;
import ec.renafipse.mks.negocio.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author FliaAstudillo
 */
@Stateless
public class ParametroServidorIfipFacade extends AbstractFacade<ParametroServidorIfip> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroServidorIfipFacade() {
        super(ParametroServidorIfip.class);
    }
    
}
