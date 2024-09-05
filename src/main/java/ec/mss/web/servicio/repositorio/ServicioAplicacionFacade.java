/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mss.web.servicio.repositorio;

import ec.mss.web.servicio.entidad.ServicioAplicacion;
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
public class ServicioAplicacionFacade extends AbstractFacade<ServicioAplicacion> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioAplicacionFacade() {
        super(ServicioAplicacion.class);
    }
    
    public List<ServicioAplicacion> getPorEstado(short estado) {
        Query query = this.em.createNamedQuery("ServicioAplicacion.findByEstado", ServicioAplicacion.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
}
