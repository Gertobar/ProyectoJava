/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.FragmentoReporte;
import ec.renafipse.mks.modelo.ifips.TipoIfip;
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
public class FragmentoReporteFacade extends AbstractFacade<FragmentoReporte> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FragmentoReporteFacade() {
        super(FragmentoReporte.class);
    }
    
    public List<FragmentoReporte> getItemsreporte(char eliminado)
    {       Query query=this.em.createNamedQuery(FragmentoReporte.findByEliminado);
            query.setParameter("eliminado",eliminado);
            return query.getResultList();
    }
    
}
