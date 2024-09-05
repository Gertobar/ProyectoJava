/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.socios;

import ec.renafipse.mks.modelo.socios.TipoIdentificacion;
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
public class TipoIdentificacionFacade extends AbstractFacade<TipoIdentificacion> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoIdentificacionFacade() {
        super(TipoIdentificacion.class);
    }
    
        public List<TipoIdentificacion> getItemsTipoIdentificaciobyCodigo(Long codigo)
    {
        Query query = em.createNamedQuery(TipoIdentificacion.findByCodigo);        
        query.setParameter("codigo", codigo);        
        return query.getResultList();
    }

}
