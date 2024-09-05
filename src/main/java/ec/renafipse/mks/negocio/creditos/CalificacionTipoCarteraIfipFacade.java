/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.CalificacionTipoCartera;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andres
 */
@Stateless
public class CalificacionTipoCarteraIfipFacade extends AbstractFacade<CalificacionTipoCartera> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalificacionTipoCarteraIfipFacade() {
        super(CalificacionTipoCartera.class);
    }
    
    public CalificacionTipoCartera getCalificacionTipoCartera(int codigoIfip, Long codigoTipoCartera, String calificacion){
        CalificacionTipoCartera calificacionTipoCartera = null;
        Query query = this.em.createNamedQuery("CalificacionTipoCartera.findByIfipCarteraCalificacion", CalificacionTipoCartera.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTipoCartera", codigoTipoCartera);
        query.setParameter("calificacion", calificacion);
        if (!(query.getResultList().isEmpty())){
            List<CalificacionTipoCartera> lista = query.getResultList();
            if (lista.size() > 0)
                calificacionTipoCartera = lista.get(0);
        }
        return calificacionTipoCartera;
    }

    public List<CalificacionTipoCartera> getListaCalificacionTipoCarteraOrdenada(){
        List<CalificacionTipoCartera> lista = null;
        Query query = this.em.createNamedQuery("CalificacionTipoCartera.findAllOrderByTipoCartera", CalificacionTipoCartera.class);
        if (!(query.getResultList().isEmpty())){
            List<CalificacionTipoCartera> resultado = query.getResultList();
            if (resultado.size() > 0)
                lista = resultado;
        }
        return lista;
    }
}
