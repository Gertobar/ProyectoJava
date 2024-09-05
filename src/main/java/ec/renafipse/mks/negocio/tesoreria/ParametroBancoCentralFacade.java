/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.tesoreria;

import ec.renafipse.mks.modelo.tesoreria.ParametroBancoCentral;
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
public class ParametroBancoCentralFacade extends AbstractFacade<ParametroBancoCentral> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroBancoCentralFacade() {
        super(ParametroBancoCentral.class);
    }

    public List<ParametroBancoCentral> getTipoParametroBancoCentral(Long codigoIfip) {
        List<ParametroBancoCentral> lista = null;
        Query query = em.createNamedQuery("ParametroBancoCentral.findByCodigoIfip", ParametroBancoCentral.class);
        query.setParameter("codigoIfip", codigoIfip);
        if (!query.getResultList().isEmpty()) {
            List<ParametroBancoCentral> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        return lista;
    }

}
