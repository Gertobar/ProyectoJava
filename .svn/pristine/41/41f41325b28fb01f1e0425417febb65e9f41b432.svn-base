/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.ServicioFinancieroTipoCanal;
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
public class ServicioFinancieroTipoCanalFacade extends AbstractFacade<ServicioFinancieroTipoCanal> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioFinancieroTipoCanalFacade() {
        super(ServicioFinancieroTipoCanal.class);
    }
    
    public List<ServicioFinancieroTipoCanal> getServicioFinancieroTipoCanalPorCodigoIfip(Long codigoIfip) {
        List<ServicioFinancieroTipoCanal> lista = null;
        Query query = em.createNamedQuery("ServicioFinancieroTipoCanal.findByCodigoIfip", ServicioFinancieroTipoCanal.class);
        query.setParameter("codigoIfip", codigoIfip);
        if (!query.getResultList().isEmpty()) {
          List<ServicioFinancieroTipoCanal> resultado = query.getResultList();
          if (resultado.size() > 0)
            lista = resultado;
        }
        return lista;
    }
    
}
