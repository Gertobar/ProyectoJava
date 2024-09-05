/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.ifips;

import ec.renafipse.mks.modelo.ifips.EnvioCanalServicioIfip;
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
public class EnvioCanalServicioIfipFacade extends AbstractFacade<EnvioCanalServicioIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnvioCanalServicioIfipFacade() {
        super(EnvioCanalServicioIfip.class);
    }
    
    /**
     * 
     * @param codigoServicioIfip
     * @param codigoCanalServicioIfip
     * @return 
     */
    public EnvioCanalServicioIfip getEnvioCanalServicioIfipPorCodigoServicioIfipCodigoCanalServicioIfip(Long codigoServicioIfip, String codigoCanalServicioIfip) {
        List<EnvioCanalServicioIfip> lista = null;
        EnvioCanalServicioIfip envioCanalServicioIfip = null;
        Query query = em.createNamedQuery("EnvioCanalServicioIfip.findByCodigoServicioIfipCodigoCanalServicioIfip", EnvioCanalServicioIfip.class);
        query.setParameter("codigoServicioIfip", codigoServicioIfip);
        query.setParameter("codigoCanalServicioIfip", codigoCanalServicioIfip);
        if (!query.getResultList().isEmpty()) {
            List<EnvioCanalServicioIfip> resultado = query.getResultList();
            if (resultado.size() > 0) {
                lista = resultado;
            }
        }
        if (lista != null) {
            envioCanalServicioIfip = (EnvioCanalServicioIfip) lista.get(0);
        }
        return envioCanalServicioIfip;
    }
    
}
