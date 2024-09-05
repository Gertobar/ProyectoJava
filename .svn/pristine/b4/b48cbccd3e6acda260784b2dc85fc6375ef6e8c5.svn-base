/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ahorros;

import ec.renafipse.mks.modelo.ahorros.AhorroTasaInteresIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vicastoc
 */
@Stateless
public class AhorroTasaInteresIfipFacade extends AbstractFacade<AhorroTasaInteresIfip> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AhorroTasaInteresIfipFacade() {
        super(AhorroTasaInteresIfip.class);
    }
    public List<AhorroTasaInteresIfip>getItemAhorroTasaInteresIfip(Ifip codigoIfip)
    {
        Query query=em.createNamedQuery(AhorroTasaInteresIfip.findByCodigoIfip);
        query.setParameter("codigoIfip",codigoIfip);
        
        return query.getResultList();
    }
    
}
