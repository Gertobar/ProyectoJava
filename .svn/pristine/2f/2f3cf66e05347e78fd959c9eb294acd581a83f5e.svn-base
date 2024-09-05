/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.servicio.repositorio;

import ec.mss.web.servicio.entidad.CatalogoHeader;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andres
 */
@Stateless
public class CatalogoHeaderFacade extends AbstractFacade<CatalogoHeader> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoHeaderFacade() {
        super(CatalogoHeader.class);
    }
    
    public List<CatalogoHeader> getPorCodigoIfip(Long codigoIfip) {
        Query query = this.em.createNamedQuery("CatalogoHeader.findByCodigoIfip", CatalogoHeader.class);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
}
