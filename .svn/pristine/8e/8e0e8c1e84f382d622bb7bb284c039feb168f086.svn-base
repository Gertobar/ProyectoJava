/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.Computador;
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
public class IfipFacade extends AbstractFacade<Ifip> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IfipFacade() {
        super(Ifip.class);
    }
    
    public List<Ifip> getItemsIfipEliminado(char eliminado) {
        Query query = em.createNamedQuery(Ifip.findByEliminado);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
   
    public List<Ifip> getItemsIfipCodigo(Long codigo,char eliminado) {
        Query query = em.createNamedQuery(Ifip.findByIfipEliminado);
        query.setParameter("codigoIfip", codigo);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
  
}
