/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.servicio.repositorio;

import ec.mss.web.servicio.entidad.CatalogoAutorizacion;
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
public class CatalogoAutorizacionFacade extends AbstractFacade<CatalogoAutorizacion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoAutorizacionFacade() {
        super(CatalogoAutorizacion.class);
    }
    
    public List<CatalogoAutorizacion> getPorCodigoIfip(Long codigoIfip) {
        Query query = this.em.createNamedQuery("CatalogoAutorizacion.findByCodigoIfip", CatalogoAutorizacion.class);
        query.setParameter("codigoIfip", codigoIfip);
        return query.getResultList();
    }
}
