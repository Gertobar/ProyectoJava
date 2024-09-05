/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.Ats;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author renafipse1
 */
@Stateless
public class AtsFacade extends AbstractFacade<Ats> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtsFacade() {
        super(Ats.class);
    }
    
    public List<Ats> getItemAtsFecha(Date fechaCorte, char tipo) {
        Query query = this.em.createNamedQuery(Ats.findByFechaCorte);
        query.setParameter("fechaCorte", fechaCorte);
        query.setParameter("tipo", tipo);
        return query.getResultList();
    }
    
}
