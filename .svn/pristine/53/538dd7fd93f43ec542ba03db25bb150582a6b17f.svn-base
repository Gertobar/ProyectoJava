/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ifips;


import ec.renafipse.mks.modelo.ifips.EntidadFinancieraIfiAge;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author vicastoc
 */
@Stateless
public class EntidadFinancieraIfiAgeFacade extends AbstractFacade<EntidadFinancieraIfiAge> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntidadFinancieraIfiAgeFacade() {
        super(EntidadFinancieraIfiAge.class);
    }
    
      public List<EntidadFinancieraIfiAge> getItemsEntidadFinancieraIfip(Long codigoIfip)
    {
        return this.em.createNamedQuery(EntidadFinancieraIfiAge.findByEntidadFinanIfip).setParameter("codigoIfip", codigoIfip).getResultList();
    }



}
