/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.tesoreria;

import ec.renafipse.mks.modelo.tesoreria.ParametroBanCenUsuMod;
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
public class ParametroBanCenUsuModFacade extends AbstractFacade<ParametroBanCenUsuMod> {
    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroBanCenUsuModFacade() {
        super(ParametroBanCenUsuMod.class);
    }
    
    public List<ParametroBanCenUsuMod> getListaParametroBanCenUsuModParametro(Long codigoParametro) {
        List<ParametroBanCenUsuMod> lista = null;
        Query query = em.createNamedQuery("ParametroBanCenUsuMod.findByCodigoParametro", ParametroBanCenUsuMod.class);
        query.setParameter("codigoParametro", codigoParametro);
        if (!query.getResultList().isEmpty()) {
            List<ParametroBanCenUsuMod> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        return lista;
    }
    
}
