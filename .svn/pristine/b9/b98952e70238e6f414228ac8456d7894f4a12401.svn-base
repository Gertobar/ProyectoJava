/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.etapa;

import ec.renafipse.mks.modelo.etapa.DescuentoSocio;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author vicastoc
 */
@Stateless
public class DescuentoSocioFacade extends AbstractFacade<DescuentoSocio> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DescuentoSocioFacade() {
        super(DescuentoSocio.class);
    }

    /**
     * 
     * @param fechaCorte
     * @return 
     */
    public List<DescuentoSocio> getItemsFechaCorte(Date fechaCorte) {
        Query query = this.em.createNamedQuery(DescuentoSocio.findByFechaCorte);
        query.setParameter("fechaCorte", fechaCorte, TemporalType.DATE);
        return query.getResultList();
    }

}
