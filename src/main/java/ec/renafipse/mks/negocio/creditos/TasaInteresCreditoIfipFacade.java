/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TasaInteresCreditoIfip;
import ec.renafipse.mks.negocio.AbstractFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vastudillo
 */
@Stateless
public class TasaInteresCreditoIfipFacade extends AbstractFacade<TasaInteresCreditoIfip> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TasaInteresCreditoIfipFacade() {
        super(TasaInteresCreditoIfip.class);
    }

    public TasaInteresCreditoIfip getTasaInteresCreditoIfip(BigDecimal valor) {
        Query query = this.em.createNamedQuery(TasaInteresCreditoIfip.findByValor);
        query.setParameter("valor", valor);
        try {
            TasaInteresCreditoIfip tasaInteresCreditoIfip = (TasaInteresCreditoIfip) query.getSingleResult();
            return tasaInteresCreditoIfip == null ? null : tasaInteresCreditoIfip;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TasaInteresCreditoIfip> getItemsTasaInteresCreditoIfip(Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(TasaInteresCreditoIfip.findByCodigoIfipEliminado);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }
    public List<TasaInteresCreditoIfip> getItemsTasaInteres(Long codigoProducto,Long codigoMoneda, Long codigoIfip, char eliminado) {
        List<TasaInteresCreditoIfip> listMonedas;
        Query query = this.em.createNamedQuery(TasaInteresCreditoIfip.findByTasaIntProCreMonIfi);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        listMonedas = query.getResultList();
        return listMonedas;
    }
}
