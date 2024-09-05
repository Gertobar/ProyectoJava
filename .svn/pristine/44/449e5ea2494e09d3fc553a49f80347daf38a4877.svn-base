/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.dpfs;

import ec.renafipse.mks.modelo.ahorros.AhorroTasaInteresIfip;
import ec.renafipse.mks.modelo.dpfs.TasaInteresDpfIfip;
import ec.renafipse.mks.modelo.ifips.Ifip;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class TasaInteresDpfIfipFacade extends AbstractFacade<TasaInteresDpfIfip> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TasaInteresDpfIfipFacade() {
        super(TasaInteresDpfIfip.class);
    }
      public List<TasaInteresDpfIfip>getItemDpfTasaInteresIfip(Ifip codigoIfip)
    {
        Query query=em.createNamedQuery(TasaInteresDpfIfip.findByCodigoIfip);
        query.setParameter("codigoIfip",codigoIfip.getCodigo());
        
        return query.getResultList();
    }
    
}
