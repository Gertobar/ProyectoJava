/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.adquisiciones;

import ec.renafipse.mks.modelo.adquisiciones.ParametroCompraIfip;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class ParametroCompraIfipFacade extends AbstractFacade<ParametroCompraIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroCompraIfipFacade() {
        super(ParametroCompraIfip.class);
    }
    
    public List<ParametroCompraIfip> getItemsComprobanteDetalleFechaIfip(Long codigoIfip, Long codigoParametro){
        Query query = em.createNamedQuery(ParametroCompraIfip.findByIfipEntreFecha);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoParametro", codigoParametro);
        return query.getResultList();
    }
    
    public ParametroCompraIfip getParametroCompraIfip(Long codigoIfip, Long codigoParametro){
        List<ParametroCompraIfip> lista = null;
        ParametroCompraIfip parametroCompraIfip = null;
        Query query = em.createNamedQuery("ParametroCompraIfip.findByCodigIfipParametro", ParametroCompraIfip.class);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoParametro", codigoParametro);
        if (!query.getResultList().isEmpty()) {
        List<ParametroCompraIfip> resultado = query.getResultList();
        if (resultado.size() > 0)
          lista = resultado;
        }
        if (lista != null) {
          parametroCompraIfip = (ParametroCompraIfip)lista.get(0);
        }
        return parametroCompraIfip;
    }
}
