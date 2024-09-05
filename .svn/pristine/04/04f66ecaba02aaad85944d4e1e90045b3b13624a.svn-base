/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio.creditos;

import ec.renafipse.mks.modelo.creditos.TasaInteresProCreMonIfi;
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
public class TasaInteresProCreMonIfiFacade extends AbstractFacade<TasaInteresProCreMonIfi> {

    @PersistenceContext(unitName = "ec.renafipse.mksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TasaInteresProCreMonIfiFacade() {
        super(TasaInteresProCreMonIfi.class);
    }

    public TasaInteresProCreMonIfi getTasaInteresProCreMonIfi(Long codigoProducto, Long codigoMoneda, Long codigoIfip, Long codigoTasa, BigDecimal montoInicial) {
        Query query = this.em.createNamedQuery(TasaInteresProCreMonIfi.findByProductoCreditoMonedaIfip);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTasa", codigoTasa);
        query.setParameter("montoInicial", montoInicial);
        try {
            TasaInteresProCreMonIfi tasaInteresProCreMonIfi =(TasaInteresProCreMonIfi) query.getSingleResult();
            return tasaInteresProCreMonIfi == null ? null : tasaInteresProCreMonIfi;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TasaInteresProCreMonIfi> getItemsTasaInteresProCreMonIfi(Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(TasaInteresProCreMonIfi.findByEliminadoIfip);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();

    }
    public List<TasaInteresProCreMonIfi> getTasaInteresProCreMonIfi(Long codigoProducto,Long codigoMoneda, Long codigoIfip, Long codigoTasa, char eliminado) {
        Query query = this.em.createNamedQuery(TasaInteresProCreMonIfi.findByTasaProCreMonIfi);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTasa", codigoTasa);
        query.setParameter("eliminado", eliminado);
        List<TasaInteresProCreMonIfi> t=query.getResultList();
        //System.out.println("tamano: "+ t.size());
        return t;
    }
    public TasaInteresProCreMonIfi getTasaInteresProCreMonIfiMonto(Long codigoProducto,Long codigoMoneda, Long codigoIfip, Long codigoTasa, char eliminado, BigDecimal montoFinal) {
        Query query = this.em.createNamedQuery(TasaInteresProCreMonIfi.findByTasaProCreMonIfi);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("codigoTasa", codigoTasa);
        query.setParameter("eliminado", eliminado);
        query.setParameter("montoFinal", montoFinal);
        return (TasaInteresProCreMonIfi)query.getSingleResult();
    }
    public List<TasaInteresProCreMonIfi> getListaTasaInteresProCreMonIfi(Long codigoProducto,Long codigoMoneda, Long codigoIfip, char eliminado) {
        Query query = this.em.createNamedQuery(TasaInteresProCreMonIfi.findByProCreMonIfi);
        query.setParameter("codigoProducto", codigoProducto);
        query.setParameter("codigoMoneda", codigoMoneda);
        query.setParameter("codigoIfip", codigoIfip);
        query.setParameter("eliminado", eliminado);
        return query.getResultList();
    }
}
