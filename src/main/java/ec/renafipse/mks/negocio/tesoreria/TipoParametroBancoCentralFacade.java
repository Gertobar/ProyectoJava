/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.tesoreria;

import ec.renafipse.mks.modelo.tesoreria.TipoParametroBancoCentral;
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
public class TipoParametroBancoCentralFacade extends AbstractFacade<TipoParametroBancoCentral> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoParametroBancoCentralFacade() {
        super(TipoParametroBancoCentral.class);
    }

    public List<TipoParametroBancoCentral> getTipoParametroBancoCentralEliminado(char eliminado) {
        List<TipoParametroBancoCentral> lista = null;
        Query query = em.createNamedQuery("TipoParametroBancoCentral.findByEliminado", TipoParametroBancoCentral.class);
        query.setParameter("eliminado", eliminado);
        if (!query.getResultList().isEmpty()) {
            List<TipoParametroBancoCentral> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        return lista;
    }
    
}
