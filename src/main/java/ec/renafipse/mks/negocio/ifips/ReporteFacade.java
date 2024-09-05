/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;


import ec.renafipse.mks.modelo.ifips.Reporte;

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
public class ReporteFacade extends AbstractFacade<Reporte> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

        protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteFacade() {
        super(Reporte.class);
    }
    
    public List<Reporte> getItemsreporte(char eliminado)
    {       Query query=this.em.createNamedQuery(Reporte.findByEliminado);
            query.setParameter("eliminado",eliminado);
            return query.getResultList();
    }
    
}
